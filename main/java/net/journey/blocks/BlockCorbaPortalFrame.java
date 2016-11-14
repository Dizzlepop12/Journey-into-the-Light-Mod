package net.journey.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.block.BlockMod;

public class BlockCorbaPortalFrame extends BlockMod {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool EYE = PropertyBool.create("eye");
    protected static final AxisAlignedBB AABB_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
    protected static final AxisAlignedBB AABB_EYE = new AxisAlignedBB(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
    private static BlockPattern portalShape;
    private Block portal;

    public BlockCorbaPortalFrame(String name, String f) {
        super(name, f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EYE, Boolean.valueOf(false)));
    }

    @Override
    public boolean isOpaqueCube(IBlockState s) {
        return false;
    }
    
    @Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		if (((Boolean)state.getValue(EYE))) {
			drops.add(new ItemStack(JourneyItems.demonicEye, 1));
		}
		drops.add(new ItemStack(SlayerAPI.toItem(JourneyBlocks.corbaPortalFrame)));
		return drops;   
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BLOCK);

        if (((Boolean)worldIn.getBlockState(pos).getValue(EYE)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EYE);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(this);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(EYE, Boolean.valueOf(false));
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
        return ((Boolean)worldIn.getBlockState(pos).getValue(EYE)).booleanValue() ? 15 : 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(EYE, Boolean.valueOf((meta & 4) != 0)).withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(EYE)).booleanValue()) {
            i |= 4;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, EYE});
    }
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		//portal = JourneyBlocks.corbaPortal;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int var1 = ((MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3) + 2) % 4;
		world.setBlockState(new BlockPos(x, y, z), state, var1);
		if(placer instanceof EntityPlayerMP && placer.dimension != Config.wither) {
			int x1 = x;
			int z1 = z;
			boolean frameArea = true;
			if (world.getBlockState(new BlockPos(x1 - 1, y, z1)) == this || world.getBlockState(new BlockPos(x1 + 1, y, z1)) == this) {
				while (world.getBlockState(new BlockPos(x1 - 1, y, z1)) == this) {
					x1--;
				}		
				x1--;
				if (world.getBlockState(new BlockPos(x1, y, z1 - 1)) == this) {
					z1 = z1 - 4;
				}
			} else if (world.getBlockState(new BlockPos(x1, y, z1 - 1)) == this || world.getBlockState(new BlockPos(x1, y, z1 + 1)) == this) {
				while (world.getBlockState(new BlockPos(x1, y, z1 - 1)) == this) {
					z1--;
				}
				z1--;
				if (world.getBlockState(new BlockPos(x1 - 1, y, z1)) == this) {
					x1 = x1 - 4;
				}
			}
			
			canCreate: for (int z2 = z1; z2 < z1 + 5; z2++) {
				if ((z2 == z1 || z2 == z1 + 4)) {
					for (int x2 = x1 + 1; x2 < x1 + 4; x2++) {
						if (world.getBlockState(new BlockPos(x2, y, z2)) != this) {
							frameArea = false;
							break canCreate;
						}
					}
				} else {
					for (int x2 = x1; x2 < x1 + 5; x2++) {
						if (x2 == x1 || x2 == x1 + 4) {
							if (world.getBlockState(new BlockPos(x2, y, z2)) !=this) {
								frameArea = false;
								break canCreate;
							}
						} else if (world.getBlockState(new BlockPos(x2, y, z2)) != Blocks.AIR) {
							frameArea = false;
							break canCreate;
						}
					}
				}
				
				if(frameArea) {
	                world.setBlockState(new BlockPos(x1 + 1, y, z1 + 1), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 2, y, z1 + 1), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 3, y, z1 + 1), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 1, y, z1 + 2), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 2, y, z1 + 2), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 3, y, z1 + 2), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 1, y, z1 + 3), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 2, y, z1 + 3), portal.getDefaultState());
	                world.setBlockState(new BlockPos(x1 + 3, y, z1 + 3), portal.getDefaultState());
				}
			}
		}
	}
}
