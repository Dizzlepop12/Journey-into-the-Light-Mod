package net.slayerapi.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.SlayerAPI;

public class BlockModGrass extends BlockMod {
	
	private Block dirt;
	
    public BlockModGrass(Block dirt, String name, String finalName, float hardness) {
        super(name, finalName, hardness);
        this.dirt = dirt;
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(!worldIn.isRemote) {
            if(worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
            } else {
                if(worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                    for(int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        if(blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
                            return;
                        }
                        IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
                        if(iblockstate1.getBlock() == dirt && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2) {
                            worldIn.setBlockState(blockpos, this.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(dirt);
    }
}