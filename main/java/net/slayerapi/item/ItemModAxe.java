package net.slayerapi.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.EssenceToolMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

public class ItemModAxe extends ItemAxe {

	private ToolMaterial toolMaterial;
	private Item rep;
	
	public ItemModAxe(String name, String finalName, EssenceToolMaterial t){
		this(name, finalName, JourneyTabs.tools, t);
	}

	public ItemModAxe(String name, String finalName, CreativeTabs tab, EssenceToolMaterial t){
		super(t.getToolMaterial(), t.getToolMaterial().getDamageVsEntity(), 0.01F);
		rep = t.getRepairItem();
		toolMaterial = t.getToolMaterial();
		JourneyItems.itemNames.add(name);
		LangRegistry.addItem(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(tab);
		GameRegistry.registerItem(this, name);
	}

	@Override
	public boolean isItemTool(ItemStack i) {
		return true;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = rep != null;
		if(canRepair) return rep == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		infoList.add(SlayerAPI.Colour.BLUE + "Efficiency: " + toolMaterial.getEfficiencyOnProperMaterial());
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " Uses Remaining");
		else infoList.add(SlayerAPI.Colour.GREEN + "Infinate uses");
	}
}
