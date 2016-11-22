package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.darkEnergy.DarkEnergyBar;
import net.journey.client.server.bars.darkEnergy.IDarkEnergyBar;
import net.journey.client.server.bars.essence.EssenceBar;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayerapi.item.ItemMod;

public class ItemEssencePotion extends ItemMod {

	private boolean isStrong, essence;

	public ItemEssencePotion(String name, String f, boolean strong, boolean essence) {
		super(name, f, JourneyTabs.util);
		isStrong = strong;
		this.essence = essence;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 20;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.DRINK;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
		//worldIn.playSoundAtEntity(playerIn, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
		this.drink(stack, worldIn, entityplayer);
		return stack;
	}

	public ItemStack drink(ItemStack stack, World world, EntityPlayer player) {
		final IEssenceBar bar1 = player.getCapability(BarTickHandler.ESSENCE_CAP, null);
		final IDarkEnergyBar bar2 = player.getCapability(BarTickHandler.DARK_CAP, null);
		int amount = isStrong ? 10 : 5;
		if(!world.isRemote){
			if(essence) bar1.addPoints(amount, player);
			else bar2.addPoints(amount, player);
			if(!player.capabilities.isCreativeMode) stack.stackSize--;
		}
		return stack;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return isStrong ? EnumRarity.EPIC : EnumRarity.RARE;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return isStrong;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		String type = "";
		if(essence) type = " Essence";
		else type = " Dark Energy";
		int amount = isStrong ? 10 : 5;
		list.add("Replenishes " + amount + type);
	}
}