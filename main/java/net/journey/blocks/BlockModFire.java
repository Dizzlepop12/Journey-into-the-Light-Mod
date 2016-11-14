package net.journey.blocks;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.EnumMaterialTypes;
import net.slayerapi.block.BlockMod;

public class BlockModFire extends BlockMod {

    public BlockModFire(String name, String finalName) {
        super(EnumMaterialTypes.FIRE, name, finalName, null);
        this.setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return NULL_AABB;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean requiresUpdates() {
        return false;
    }
    
    @Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.setFire(6);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setFire(6);
	}

	@Override
    public boolean isCollidable() {
        return false;
    }

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState s) {
		if(!JourneyBlocks.eucaPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.depthsPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.boilPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.frozenPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.cloudiaPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.terraniaPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.goldenPortal.trySpawnPortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}
    
    @Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World w, BlockPos pos, Random random) {
		for(int i = 0; i < 3; ++i) {
			double d0 = (double)pos.getX() + rand.nextDouble();
			double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
			double d2 = (double)pos.getZ() + rand.nextDouble();
			w.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}