package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiTordo extends GuiModVillager {

	public GuiTordo(ContainerModVillager container, IMerchant mer) {
		super(container, mer, "Tordo", "tordo", true);
	}
}