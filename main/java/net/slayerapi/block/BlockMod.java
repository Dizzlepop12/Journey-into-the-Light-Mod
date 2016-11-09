package net.slayerapi.block;

import java.util.Random;

import net.journey.main.JourneyBlocks;
import net.journey.main.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.EnumMaterialTypes;
import net.slayerapi.base.EnumToolType;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

public class BlockMod extends Block{

	protected EnumMaterialTypes blockType;
	protected Item drop = null;
	protected Random rand;
	public String name;
	protected boolean isOpaque = true, isNormalCube = true;
	
	public BlockMod(String name, String finalName, float hardness) {
		this(EnumMaterialTypes.STONE, name, finalName, hardness, JourneyTabs.blocks);
	}

	public BlockMod(String name, String finalName) {
		this(EnumMaterialTypes.STONE, name, finalName, 2.0F, JourneyTabs.blocks);
	}

	public BlockMod(EnumMaterialTypes type, String name, String finalName, float hardness) {
		this(type, name, finalName, hardness, JourneyTabs.blocks);
	}

	public BlockMod(String name, String finalName, boolean breakable, CreativeTabs tab) {
		this(EnumMaterialTypes.STONE, name, finalName, tab);
	}

	public BlockMod(String name, String finalName, boolean breakable) {
		this(name, finalName, breakable, JourneyTabs.blocks);
	}

	public BlockMod(EnumMaterialTypes blockType, String name, String finalName, CreativeTabs tab) {
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

	public BlockMod(EnumMaterialTypes blockType, String name, String finalName, float hardness, CreativeTabs tab) {
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

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(drop == null) return SlayerAPI.toItem(this);
		return drop;
	}

	public BlockMod setHarvestLevel(EnumToolType type) {
		setHarvestLevel(type.getType(), type.getLevel());
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState s) {
		return isOpaque;
	}

	@Override
	public boolean isNormalCube(IBlockState s) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState s, World w, BlockPos pos, Random random) {}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		return true;
	}
}