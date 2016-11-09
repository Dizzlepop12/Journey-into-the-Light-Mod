package net.slayerapi.block;

import net.journey.main.JourneyBlocks;
import net.journey.main.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayerapi.base.LangRegistry;

public class BlockModFence extends BlockFence {

	public BlockModFence(Block block, String name, String finalName, boolean light) {
		super(block.getMaterial(null), MapColor.IRON);
		LangRegistry.addBlock(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.blocks);
		if(light) setLightLevel(0.5F);
		setHardness(block.getBlockHardness(null, null, null));
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}
	
	public BlockModFence(Block b, String n, String finalName) {
		this(b, n, finalName, false);
	}
	
	@Override
	public boolean canConnectTo(IBlockAccess blockAccess, BlockPos pos) {
		Block block = blockAccess.getBlockState(pos).getBlock();
		 return 
				 block == Blocks.BARRIER ? false : ((!(
				 block instanceof BlockFence) || 
				 block.getMaterial(getDefaultState()) != this.blockMaterial) && !(
				 block instanceof BlockFenceGate) ? (
				 block.getMaterial(getDefaultState()).isOpaque() && 
				 block.isFullCube(getDefaultState()) ? 
				 block.getMaterial(getDefaultState()) != Material.GOURD : false) : true);
	}
}