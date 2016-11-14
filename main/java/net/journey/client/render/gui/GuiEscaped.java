package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiEscaped extends GuiModVillager {

	public GuiEscaped(ContainerModVillager container, IMerchant mer) {
		super(container, mer, "Escaped Convict", "escapedConvict");
	}
}