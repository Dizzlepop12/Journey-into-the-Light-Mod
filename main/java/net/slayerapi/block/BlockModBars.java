package net.slayerapi.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.journey.main.JourneyBlocks;
import net.journey.main.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.EnumMaterialTypes;
import net.slayerapi.base.EnumToolType;
import net.slayerapi.base.LangRegistry;

public class BlockModBars extends Block
{

	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[] {new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

	private boolean canDrop = false;

	protected BlockModBars(Material materialIn, boolean canDrop)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		this.canDrop = canDrop;
	}

	protected EnumMaterialTypes blockType;
	protected Item drop = null;
	protected Random rand;
	public int boostBrightnessLow;
	public int boostBrightnessHigh;
	public boolean enhanceBrightness;
	public String name;
	protected boolean isOpaque = true, isNormalCube = true;

	public BlockModBars(String name, String finalName, float hardness) {
		this(EnumMaterialTypes.STONE, name, finalName, hardness, JourneyTabs.blocks);
	}

	public BlockModBars(String name, String finalName) {
		this(EnumMaterialTypes.STONE, name, finalName, 2.0F, JourneyTabs.blocks);
	}

	public BlockModBars(EnumMaterialTypes type, String name, String finalName, float hardness) {
		this(type, name, finalName, hardness, JourneyTabs.blocks);
	}

	public BlockModBars(String name, String finalName, boolean breakable, CreativeTabs tab) {
		this(EnumMaterialTypes.STONE, name, finalName, tab);
	}

	public BlockModBars(String name, String finalName, boolean breakable) {
		this(name, finalName, breakable, JourneyTabs.blocks);
	}

	public BlockModBars(EnumMaterialTypes blockType, String name, String finalName, CreativeTabs tab) {
		super(blockType.getMaterial());
		LangRegistry.addBlock(name, finalName);
		this.blockType = blockType;
		setHardness(2.0F);
		rand = new Random();
		setSoundType(blockType.getSound());
		setCreativeTab(tab);
		setUnlocalizedName(name);
		this.name = name; 
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	public BlockModBars(EnumMaterialTypes blockType, String name, String finalName, float hardness, CreativeTabs tab) {
		super(blockType.getMaterial());
		LangRegistry.addBlock(name, finalName);
		this.blockType = blockType;
		rand = new Random();
		setSoundType(blockType.getSound());
		setCreativeTab(tab);
		setUnlocalizedName(name);
		setHardness(hardness);
		this.name = name;
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	public Block addName(String name) {
		JourneyBlocks.blockName.add(name);
		return this;
	}

	public String getName() {
		return name;
	}

	public BlockModBars setHarvestLevel(EnumToolType type) {
		setHarvestLevel(type.getType(), type.getLevel());
		return this;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public boolean isNormalCube(IBlockState s) {
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, canPaneConnectTo(worldIn, pos, EnumFacing.NORTH))
				.withProperty(SOUTH, canPaneConnectTo(worldIn, pos, EnumFacing.SOUTH))
				.withProperty(WEST, canPaneConnectTo(worldIn, pos, EnumFacing.WEST))
				.withProperty(EAST, canPaneConnectTo(worldIn, pos, EnumFacing.EAST));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return !this.canDrop ? null : super.getItemDropped(state, rand, fortune);
	}

	@Override
	public boolean isOpaqueCube(IBlockState s) {
		return false;
	}
 
	@Override
	public boolean isFullCube(IBlockState s) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState s, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return worldIn.getBlockState(pos).getBlock() == this ? false : super.shouldSideBeRendered(s, worldIn, pos, side);
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
		state = this.getActualState(state, worldIn, pos);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[0]);
		if(((Boolean)state.getValue(NORTH)).booleanValue())
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.NORTH)]);
		if(((Boolean)state.getValue(SOUTH)).booleanValue())
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.SOUTH)]);
		if(((Boolean)state.getValue(EAST)).booleanValue())
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.EAST)]);
		if(((Boolean)state.getValue(WEST)).booleanValue())
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.WEST)]);
	}

	private static int getBoundingBoxIndex(EnumFacing f) {
		return 1 << f.getHorizontalIndex();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = this.getActualState(state, source, pos);
		return AABB_BY_INDEX[getBoundingBoxIndex(state)];
	}

	private static int getBoundingBoxIndex(IBlockState state) {
		int i = 0;
		if(((Boolean)state.getValue(NORTH)).booleanValue()) 
			i |= getBoundingBoxIndex(EnumFacing.NORTH);
		if(((Boolean)state.getValue(EAST)).booleanValue()) 
			i |= getBoundingBoxIndex(EnumFacing.EAST);
		if(((Boolean)state.getValue(SOUTH)).booleanValue()) 
			i |= getBoundingBoxIndex(EnumFacing.SOUTH);
		if(((Boolean)state.getValue(WEST)).booleanValue()) 
			i |= getBoundingBoxIndex(EnumFacing.WEST);
		return i;
	}

	public final boolean canPaneConnectToBlock(Block blockIn) {
		return blockIn.getDefaultState().isFullBlock() || 
				blockIn == this || 
				blockIn == Blocks.GLASS || 
				blockIn == Blocks.STAINED_GLASS || 
				blockIn == Blocks.IRON_BARS || 
				blockIn == Blocks.STAINED_GLASS_PANE || 
				blockIn instanceof BlockModBars;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
	}

	public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir) {
		BlockPos off = pos.offset(dir);
		Block block = world.getBlockState(off).getBlock();
		return canPaneConnectToBlock(block) || world.getBlockState(off).isSideSolid(world, off, dir.getOpposite());
	}
}