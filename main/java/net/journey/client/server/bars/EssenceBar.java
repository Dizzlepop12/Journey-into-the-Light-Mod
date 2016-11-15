package net.journey.client.server.bars;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class EssenceBar implements IEssenceBar {

	private int essence = 0, regenDelay = 0;
	
	@Override
	public boolean useBar(int amount) {
		if(essence < amount) {
			regenDelay = 10;
			return false;
		}
		essence -= amount;
		regenDelay = 10;
		return true;
	}

	@Override
	public int getBarValue() {
		return essence;
	}

	@Override
	public void mainUpdate() {
		if(getBarValue() >= 10) essence = 10;
		System.out.println(essence);
	}

	@Override
	public void setBarValue(int mana) {
		essence = mana;
	}

	@Override
	public void removeBarPoints(int mana) {
		regenDelay = 10;
		essence -= mana;
	}

	@Override
	public void updateAllBars() {
		essence += 1;
	}
}