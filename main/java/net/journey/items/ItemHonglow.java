package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

public class ItemHonglow extends ItemFood {

	private boolean op;

	public ItemHonglow(String name, String actual, int heal, float f, boolean sat, boolean b) {
		super(heal, sat);
		LangRegistry.addItem(name, actual);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		JourneyItems.itemNames.add(name);
		setCreativeTab(JourneyTabs.crops);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack i) {
		return op;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, EntityPlayer p, List list, boolean par4) {
		list.add(SlayerAPI.Colour.YELLOW + "Note: All Variants Grant Nightvision Upon Eaten");
		list.add(SlayerAPI.Colour.DARK_GREEN + "Green - Jump Boost");
		list.add(SlayerAPI.Colour.BLUE + "Blue - Speed Boost");
		list.add(SlayerAPI.Colour.RED + "Red - Regeneration");
		list.add(SlayerAPI.Colour.GREEN + "Light Green - Nightvision (only)");

	}
	@Override
	protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
		if(!w.isRemote) {
			p.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 2400, 0));
		}
		super.onFoodEaten(i, w, p);
	}
}