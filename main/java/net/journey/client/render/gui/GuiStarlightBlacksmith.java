package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiStarlightBlacksmith extends GuiModVillager {

	public GuiStarlightBlacksmith(ContainerModVillager container, IMerchant mer) { 
		super(container, mer, "Starlight Blacksmith", "starlightBlacksmith", true);
	}
}