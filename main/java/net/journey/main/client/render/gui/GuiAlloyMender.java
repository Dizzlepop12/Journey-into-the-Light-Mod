package net.journey.main.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiAlloyMender extends GuiModVillager {

	public GuiAlloyMender(ContainerModVillager container, IMerchant mer) {
		super(container, mer, "Alloy Mender", "alloyMender");
	}
}