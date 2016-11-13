package net.slayerapi.item;

import java.util.List;

import net.journey.main.JourneyItems;
import net.journey.main.JourneyTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.LangRegistry;

public class ItemMod extends Item {

	protected int healAmount = 0;

	public ItemMod(String name, String finalName){
		this(name, finalName, JourneyTabs.items);
	}

	public ItemMod(String name, String finalName, CreativeTabs tab){
		LangRegistry.addItem(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(tab);
		JourneyItems.itemNames.add(name);
		GameRegistry.registerItem(this, name);
	}

	public ItemMod setHealAmount(int healAmount){
		this.healAmount = healAmount;
		return this;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		if(healAmount != 0){
			if(player.getHealth() < player.getMaxHealth()){
				player.heal(healAmount);
				player.getHeldItem(hand).stackSize--;

			}
		}
		return super.onItemRightClick(stack, worldIn, player, hand);
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, String sound, boolean damage, ItemStack item, int dam) {
		if(!w.isRemote){
			if(magic) w.spawnEntityInWorld(entity);
		}
		if(magic) {
			if(damage) item.damageItem(dam, p);
		}
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, String sound, boolean damage, ItemStack item, int dam) {
		if(!w.isRemote){
			w.spawnEntityInWorld(entity);
			if(damage) item.damageItem(dam, p);
		}
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, String sound) {
		spawnEntityIntoWorld(w, p, entity, magic, sound, false, new ItemStack(Items.APPLE), 0);
	}
	

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list){ }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		//ItemDescription.addInformation(item, player, list);
		addInformation(item, player, list);
	}
}