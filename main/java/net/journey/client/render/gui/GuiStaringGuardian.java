package net.journey.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiStaringGuardian extends GuiModVillager {

	public GuiStaringGuardian(ContainerModVillager container, IMerchant mer) { 
		super(container, mer, "Staring Guardian", "staringGuardian", true);
	}
}