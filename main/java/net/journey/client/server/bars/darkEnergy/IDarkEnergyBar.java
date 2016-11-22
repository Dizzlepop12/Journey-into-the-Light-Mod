package net.journey.client.server.bars.darkEnergy;

import net.minecraft.entity.player.EntityPlayer;

public interface IDarkEnergyBar {

	boolean useBar(int mana, EntityPlayer player);
	int getBarValue();
	void mainUpdate(EntityPlayer player);
	void setBarValue(int mana, EntityPlayer player);
	void removeBarPoints(int mana, EntityPlayer player);
	void updateAllBars(EntityPlayer player);
	void addPoints(int amount, EntityPlayer player);

}