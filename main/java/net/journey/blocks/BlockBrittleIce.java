package net.journey.blocks;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.block.BlockMod;
import net.slayerapi.block.BlockModGrass;

public class BlockBrittleIce extends BlockModGrass {

	public BlockBrittleIce(BlockMod dirt, String name, String finalName, float hardness) {
		super(dirt, name, finalName, hardness);
		this.isNormalCube = false;
		this.isOpaque = false;
		setCreativeTab(JourneyTabs.blocks);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState iba, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		Block block = iba.getBlock();
        return block == this ? false : super.shouldSideBeRendered(iba, blockAccess, pos, side);
	}
}