package net.journey.main.client.render.gui;

import net.minecraft.entity.IMerchant;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;
import net.slayerapi.gui.GuiModVillager;

public class GuiTerranian extends GuiModVillager {

	public GuiTerranian(ContainerModVillager container, IMerchant mer) { 
		super(container, mer, "Terranian Trader", "terranianTrader", true);
	}
}