package net.slayerapi.item;

import net.journey.main.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayerapi.block.BlockModDoor;

public class ItemModDoor extends ItemMod {

	private Block door;

	public ItemModDoor(BlockModDoor block, String name, String f) {
		super(name, f);
		this.door = block;
		setCreativeTab(JourneyTabs.blocks);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(side != EnumFacing.UP) return EnumActionResult.FAIL;
		else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
			if(!block.isReplaceable(worldIn, pos)) pos = pos.offset(side);
			if(!playerIn.canPlayerEdit(pos, side, stack)) return EnumActionResult.FAIL;
			else if(!this.door.canPlaceBlockAt(worldIn, pos)) return EnumActionResult.FAIL;
			else {
				ItemDoor.placeDoor(worldIn, pos, EnumFacing.fromAngle((double)playerIn.rotationYaw), this.door, bFull3D);
				stack.stackSize--;
				return EnumActionResult.SUCCESS;
			}
		}
	}
}