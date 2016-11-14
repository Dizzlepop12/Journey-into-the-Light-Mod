package net.slayerapi.item;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayerapi.base.LangRegistry;

public class ItemModSeeds extends ItemSeeds {
	
	public ItemModSeeds(String name, String f, Block block) {
		super(block, Blocks.FARMLAND);
		LangRegistry.addItem(name, f);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.crops);
		JourneyItems.itemNames.add(name);
		GameRegistry.registerItem(this, name);
	}
}