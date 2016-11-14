package net.journey.blocks;

import javax.annotation.Nullable;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.slayerapi.base.EnumMaterialTypes;
import net.slayerapi.block.BlockMod;

public class BlockStoneCraftingTable extends BlockMod {

	public BlockStoneCraftingTable(String name, String finalName) {
		super(EnumMaterialTypes.WOOD, name, finalName, JourneyTabs.machineBlocks);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) {
			return true;
		} else {
			playerIn.displayGui(new BlockStoneCraftingTable.InterfaceCraftingTable(worldIn, pos));
			playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
			return true;
		}
	}

	public static class InterfaceCraftingTable implements IInteractionObject {
		private final World world;
		private final BlockPos position;

		public InterfaceCraftingTable(World worldIn, BlockPos pos) {
			this.world = worldIn;
			this.position = pos;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public boolean hasCustomName() {
			return false;
		}

		@Override
		public ITextComponent getDisplayName() {
			return new TextComponentTranslation(JourneyBlocks.stoneCraftingTable.getUnlocalizedName() + ".name", new Object[0]);
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerWorkbench(playerInventory, this.world, this.position);
		}

		@Override
		public String getGuiID() {
			return "minecraft:crafting_table";
		}
	}
}