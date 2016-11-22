package net.journey.client.server.bars;

import net.journey.Journey;
import net.journey.event.message.MessageEssenceBar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

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
		Journey.wrapper.sendToAll(new MessageEssenceBar(essence, regenDelay == 0));
		return true;
	}

	@Override
	public int getBarValue() {
		Journey.wrapper.sendToAll(new MessageEssenceBar(essence, regenDelay == 0));
		return essence;
	}

	@Override
	public void mainUpdate() {
		if(getBarValue() >= 10) essence = 10;
		Journey.wrapper.sendToAll(new MessageEssenceBar(essence, regenDelay == 0));
		System.out.println(essence);
	}

	@Override
	public void setBarValue(int mana) {
		essence = mana;
		Journey.wrapper.sendToAll(new MessageEssenceBar(essence, regenDelay == 0));
	}

	@Override
	public void removeBarPoints(int mana) {
		regenDelay = 10;
		essence -= mana;
		Journey.wrapper.sendToAll(new MessageEssenceBar(essence, regenDelay == 0));
	}

	@Override
	public void updateAllBars() {
		essence += 1;
		Journey.wrapper.sendToAll(new MessageEssenceBar(essence, regenDelay == 0));
	}
}