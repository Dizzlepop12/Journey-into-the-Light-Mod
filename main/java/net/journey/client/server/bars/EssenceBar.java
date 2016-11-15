package net.journey.client.server.bars;

import net.minecraft.nbt.NBTBase;

public class EssenceBar implements IEssenceBar {

	private int essence, regenDelay;
	
	@Override
	public NBTBase write() {
		return null;
	}

	@Override
	public NBTBase read() {
		return null;
	}
	
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
	public void regen(int regen) {
		if(regenDelay == 0) essence += regen;
		else regenDelay--;
	}

	@Override
	public void mainUpdate() {
		if(essence >= 10) essence = 10;
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