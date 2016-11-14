package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiOvergrownMerchant extends GuiModVillager {

	public GuiOvergrownMerchant(ContainerModVillager container, IMerchant mer) { 
		super(container, mer, "Overgrown Merchant", "overgrownMerchant", true);
	}
}