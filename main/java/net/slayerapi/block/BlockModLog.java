package net.slayerapi.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayerapi.base.EnumMaterialTypes;

public class BlockModLog extends BlockMod {

    public static final PropertyEnum<BlockModLog.EnumAxis> LOG_AXIS = PropertyEnum.<BlockModLog.EnumAxis>create("axis", BlockModLog.EnumAxis.class);

    public BlockModLog(String name, String finalName) {
		super(EnumMaterialTypes.WOOD, name, finalName, 2.0F);
	}
    
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(LOG_AXIS, BlockModLog.EnumAxis.fromFacingAxis(facing.getAxis()));
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((BlockModLog.EnumAxis)state.getValue(LOG_AXIS)) {
                    case X:
                        return state.withProperty(LOG_AXIS, BlockModLog.EnumAxis.Z);
                    case Z:
                        return state.withProperty(LOG_AXIS, BlockModLog.EnumAxis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override 
    public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos) { 
    	return true; 
    }
    
    @Override 
    public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos) { 
    	return true; 
    }
    
    public static enum EnumAxis implements IStringSerializable {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");

        private final String name;

        private EnumAxis(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public static BlockModLog.EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
            switch (axis)  {
                case X:
                    return X;
                case Y:
                    return Y;
                case Z:
                    return Z;
                default:
                    return NONE;
            }
        }

        public String getName() {
            return this.name;
        }
    }
}
