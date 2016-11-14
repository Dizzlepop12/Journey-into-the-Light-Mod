package net.journey.blocks.portals;

import java.util.Random;

import javax.annotation.Nullable;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.dimension.ModTeleporter;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.EnumMaterialTypes;
import net.slayerapi.base.LangRegistry;

import com.google.common.cache.LoadingCache;

public class BlockGoldenPortal extends BlockBreakable {

	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class, new EnumFacing.Axis[] {EnumFacing.Axis.X, EnumFacing.Axis.Z});
	protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
	protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
	protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

	public static Block portal = JourneyBlocks.goldenPortal;
	public static Block portalFrame = JourneyBlocks.goldenPortalFrame;
	public static Block fire = JourneyBlocks.essenceFire;
	public int dimensionID = Config.golden;
	
	public BlockGoldenPortal(String name, String finalName) {
		super(Material.PORTAL, false);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
		this.setTickRandomly(true);
		LangRegistry.addBlock(name, finalName);
		setHardness(-1);
		setSoundType(EnumMaterialTypes.PORTAL.getSound());
		setCreativeTab(JourneyTabs.portalBlocks);
		setUnlocalizedName(name);
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumFacing.Axis)state.getValue(AXIS)) {
		case X: return X_AABB;
		case Y:
		default: return Y_AABB;
		case Z: return Z_AABB;
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	public static int getMetaForAxis(EnumFacing.Axis axis) {
		return axis == EnumFacing.Axis.X ? 1 : (axis == EnumFacing.Axis.Z ? 2 : 0);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		BlockGoldenPortal.Size blockportal$size = new BlockGoldenPortal.Size(worldIn, pos, EnumFacing.Axis.X);
		if(blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			BlockGoldenPortal.Size blockportal$size1 = new BlockGoldenPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
			if(blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
				blockportal$size1.placePortalBlocks();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);
		if(enumfacing$axis == EnumFacing.Axis.X) {
			BlockGoldenPortal.Size blockportal$size = new BlockGoldenPortal.Size(worldIn, pos, EnumFacing.Axis.X);
			if(!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
		else if(enumfacing$axis == EnumFacing.Axis.Z) {
			BlockGoldenPortal.Size blockportal$size1 = new BlockGoldenPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

			if(!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		pos = pos.offset(side);
		EnumFacing.Axis enumfacing$axis = null;

		if(blockState.getBlock() == this) {
			enumfacing$axis = (EnumFacing.Axis)blockState.getValue(AXIS);
			if(enumfacing$axis == null) return false;    
			if(enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST) return false;
			if(enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) return false;
		}
		boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
		boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
		boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
		boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
		boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
		boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;
		return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if ((entity.getRidingEntity() == null) && (entity.getRidingEntity() == null) && ((entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
			//thePlayer.triggerAchievement(JourneyAchievements.achievementEuca);
			if(thePlayer.timeUntilPortal > 0) 
				thePlayer.timeUntilPortal = 10;
			else if(thePlayer.dimension != dimensionID) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.getServer().getPlayerList().transferPlayerToDimension(thePlayer, dimensionID, new ModTeleporter(thePlayer.mcServer.worldServerForDimension(dimensionID), dimensionID, this, portalFrame));
			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.getServer().getPlayerList().transferPlayerToDimension(thePlayer, 0, new ModTeleporter(thePlayer.mcServer.worldServerForDimension(0), 0, this, portalFrame));
			}
		}
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(rand.nextInt(100) == 0) {
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for(int i = 0; i < 4; i++) {
			double d0 = (double)((float)pos.getX() + rand.nextFloat());
			double d1 = (double)((float)pos.getY() + rand.nextFloat());
			double d2 = (double)((float)pos.getZ() + rand.nextFloat());
			double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;

			if(worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
			} else {
				d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
			}
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5, new int[0]);
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return getMetaForAxis((EnumFacing.Axis)state.getValue(AXIS));
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch((EnumFacing.Axis)state.getValue(AXIS)) {
			case X:
				return state.withProperty(AXIS, EnumFacing.Axis.Z);
			case Z:
				return state.withProperty(AXIS, EnumFacing.Axis.X);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {AXIS});
	}

	public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos p_181089_2_) {
		EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
		BlockGoldenPortal.Size blockportal$size = new BlockGoldenPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.X);
		LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);

		if(!blockportal$size.isValid()) {
			enumfacing$axis = EnumFacing.Axis.X;
			blockportal$size = new BlockGoldenPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.Z);
		}

		if(!blockportal$size.isValid()) {
			return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[EnumFacing.AxisDirection.values().length];
			EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
			BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

			for(EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

				for(int i = 0; i < blockportal$size.getWidth(); i++) {
					for(int j = 0; j < blockportal$size.getHeight(); ++j) {
						BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);
						if(blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR) {
							aint[enumfacing$axisdirection.ordinal()]++;
						}
					}
				}
			}

			EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;
			for(EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
				if(aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
					enumfacing$axisdirection1 = enumfacing$axisdirection2;
				}
			}
			return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
		}
	}

	public static class Size {
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(World worldIn, BlockPos pos, EnumFacing.Axis a) {
			this.world = worldIn;
			this.axis = a;
			if(a == EnumFacing.Axis.X) {
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			} else {
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}

			for(BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.isEmptyBlock(worldIn.getBlockState(pos.down()).getBlock()); pos = pos.down()) {
				;
			}

			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;

			if(i >= 0) {
				this.bottomLeft = pos.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);

				if(this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if(this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos pos, EnumFacing facing) {
			int i;

			for(i = 0; i < 22; i++) {
				BlockPos blockpos = pos.offset(facing, i);

				if(!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()).getBlock() != portalFrame) {
					break;
				}
			}

			Block block = this.world.getBlockState(pos.offset(facing, i)).getBlock();
			return block == portalFrame ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label24:
				for(this.height = 0; this.height < 21; ++this.height) {
					for(int i = 0; i < this.width; i++) {
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						Block block = this.world.getBlockState(blockpos).getBlock();

						if(!this.isEmptyBlock(block)) {
							break label24;
						}

						if(block == portal) {
							++this.portalBlockCount;
						}

						if(i == 0) {
							block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();

							if(block != portalFrame) {
								break label24;
							}
						}
						else if(i == this.width - 1) {
							block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();

							if(block != portalFrame) {
								break label24;
							}
						}
					}
				}

		for(int j = 0; j < this.width; ++j) {
			if(this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != portalFrame) {
				this.height = 0;
				break;
			}
		}

		if(this.height <= 21 && this.height >= 3) {
			return this.height;
		} else {
			this.bottomLeft = null;
			this.width = 0;
			this.height = 0;
			return 0;
		}
		}

		protected boolean isEmptyBlock(Block blockIn) {
			return blockIn.getMaterial(blockIn.getDefaultState()) == Material.AIR || blockIn == fire || blockIn == portal;
		}

		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void placePortalBlocks() {
			for(int i = 0; i < this.width; i++) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
				for(int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j), portal.getDefaultState().withProperty(BlockGoldenPortal.AXIS, this.axis), 2);
				}
			}
		}
	}
}