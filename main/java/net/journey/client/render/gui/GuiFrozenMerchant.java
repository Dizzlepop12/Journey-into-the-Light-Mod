package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiFrozenMerchant extends GuiModVillager {

	public GuiFrozenMerchant(ContainerModVillager container, IMerchant mer) {
		super(container, mer, "Frozen Merchant", "frozenMerchant");
	}
}