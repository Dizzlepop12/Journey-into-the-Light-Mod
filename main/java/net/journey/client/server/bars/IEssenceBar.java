package net.journey.client.server.bars;

import net.minecraft.nbt.NBTTagCompound;

public interface IEssenceBar {

	boolean useBar(int mana);
	int getBarValue();
	void mainUpdate();
	void setBarValue(int mana);
	void removeBarPoints(int mana);
	void updateAllBars();
	//NBTTagCompound write(NBTTagCompound nbt);
	//void read(NBTTagCompound nbt);
}