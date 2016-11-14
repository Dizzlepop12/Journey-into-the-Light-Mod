package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiRockite extends GuiModVillager {

	public GuiRockite(ContainerModVillager container, IMerchant mer) { 
		super(container, mer, "Rockite Golem", "rockiteGolem", true);
	}
}