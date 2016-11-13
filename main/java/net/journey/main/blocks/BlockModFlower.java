package net.journey.main.blocks;

import java.util.Random;

import net.journey.main.JourneyBlocks;
import net.journey.main.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.EnumMaterialTypes;
import net.slayerapi.block.BlockMod;

public class BlockModFlower extends BlockMod implements IPlantable {

    protected static final AxisAlignedBB FLOWER = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
	private boolean damageWhenContact = false;
	private boolean isFrozenPlant = false;
	private boolean isNether = false;

	public BlockModFlower(String name, String finalName) {
		super(EnumMaterialTypes.PLANT, name, finalName, 0.0F);
		this.setTickRandomly(true);
		float f = 0.3F;
		this.setCreativeTab(JourneyTabs.decoration);
	}
	
	public BlockModFlower(String name, String finalName, Boolean isNether) {
		super(EnumMaterialTypes.PLANT, name, finalName, 0.0F);
		this.setTickRandomly(true);
		float f = 0.3F;
		this.setCreativeTab(JourneyTabs.decoration);
		this.isNether = isNether;
	}


	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FLOWER;
    }
	
	public BlockModFlower setContactDamage() {
		damageWhenContact = true;
		return this;
	}

	public BlockModFlower setFrozenPlant() {
		isFrozenPlant = true;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState s, World w, BlockPos pos, Random random) {
		if(isFrozenPlant) {
			if(random.nextInt(15) == 0) {
				for(int i = 0; i < 6; ++i) {
					double d0 = (double)pos.getX() + rand.nextDouble();
					double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.7D;
					double d2 = (double)pos.getZ() + rand.nextDouble();
					w.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				}
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(damageWhenContact) entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(getDefaultState(), worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		super.neighborChanged(state, worldIn, pos, blockIn);
		checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public void updateTick(World w, BlockPos pos, IBlockState s, Random r) {
		this.checkAndDropBlock(w, pos, s);
	}

	protected void checkAndDropBlock(World w, BlockPos pos, IBlockState s) {
		if(!this.canBlockStay(w, pos, true)) {
			//if(this != EssenceBlocks.eucaTallGrass)
				this.dropBlockAsItem(w, pos, s, 0);
			w.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	public boolean canBlockStay(World w, BlockPos pos, boolean b) {
		if(b) return w.getBlockState(pos.down()).getBlock().getMaterial(getDefaultState()) == Material.GRASS || w.getBlockState(pos.down()).getBlock().getMaterial(getDefaultState()) == Material.GROUND;
		if(this.isNether && b) return w.getBlockState(pos.down()).getBlock() == Blocks.NETHERRACK || w.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil;
		else return w.getBlockState(pos.down()).getBlock().getMaterial(getDefaultState()) == Material.GRASS;
	}
	@Override
	public BlockModFlower setLightLevel(float value) {
		this.lightValue = (int)(15.0F * value);
		return this;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isOpaqueCube(IBlockState world) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState world) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		//if(this == EssenceBlocks.permaFlower || this == EssenceBlocks.shiverFlower || this == EssenceBlocks.iceBush)
		//	return EnumWorldBlockLayer.TRANSLUCENT;
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}
	
	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
}