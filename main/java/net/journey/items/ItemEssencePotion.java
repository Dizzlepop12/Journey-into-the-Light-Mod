package net.journey.items;

import net.journey.JourneyTabs;
import net.slayerapi.item.ItemMod;

public class ItemEssencePotion extends ItemMod {

	public ItemEssencePotion(String name, String finalName, boolean strong, boolean essence) {
		super(name, finalName, JourneyTabs.misc);
	}
}