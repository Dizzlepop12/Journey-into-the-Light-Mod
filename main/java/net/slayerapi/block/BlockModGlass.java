package net.slayerapi.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.EnumMaterialTypes;

public class BlockModGlass extends BlockMod {
	
    public BlockModGlass(String name, String finalName) {
        super(EnumMaterialTypes.GLASS, name, finalName, 0.3F);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    	return false;
    }
}