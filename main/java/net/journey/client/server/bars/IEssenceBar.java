package net.journey.client.server.bars;

import net.minecraft.nbt.NBTBase;

public interface IEssenceBar {

	boolean useBar(int mana);
	int getBarValue();
	void regen(int regen);
	void mainUpdate();
	void setBarValue(int mana);
	void removeBarPoints(int mana);
	void updateAllBars();
	NBTBase write();
	NBTBase read();
}