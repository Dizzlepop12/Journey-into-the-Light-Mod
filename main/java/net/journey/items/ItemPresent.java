package net.journey.items;

import java.util.ArrayList;
import java.util.Random;

import net.journey.JourneyItems;
import net.journey.enums.EnumSounds;
import net.journey.util.LangHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemPresent extends ItemMod {

	public ItemPresent(String name, String finalName) {
		super(name, finalName);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		Random r = new Random();
		items.add(new ItemStack(JourneyItems.frostFlake, 6));
		items.add(new ItemStack(JourneyItems.blueGem, 8));
		items.add(new ItemStack(JourneyItems.frostGem, 2));
		items.add(new ItemStack(JourneyItems.crystalFlake, 6));
		items.add(new ItemStack(Items.SNOWBALL, 12));
		items.add(new ItemStack(Blocks.ICE, 4));
		items.add(new ItemStack(Items.DIAMOND));
		if(!world.isRemote) {
			//EnumSounds.playSound(EnumSounds.WRAPPER, world, player);
			int index = r.nextInt(items.size());
			String name = LangHelper.getFormattedText(items.get(index).getItem().getUnlocalizedName() + ".name");
			SlayerAPI.addChatMessage(player, "You recieved " + name);
			EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, items.get(index));
			world.spawnEntityInWorld(item);
		}
		stack.stackSize--;
		return super.onItemRightClick(stack, world, player, hand);
	}
	
}