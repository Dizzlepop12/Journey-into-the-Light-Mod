package net.journey.client.server.bars.essence;

import net.journey.Journey;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class EssenceBar implements IEssenceBar {

	private int essence = 0, regenDelay = 0;
	
	@Override
	public boolean useBar(int amount, EntityPlayer player) {
		if(essence < amount) {
			regenDelay = 10;
			return false;
		}
		essence -= amount;
		regenDelay = 10;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
		return true;
	}

	@Override
	public int getBarValue() {
		return essence;
	}

	@Override
	public void mainUpdate(EntityPlayer player) {
		if(getBarValue() >= 10) essence = 10;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void setBarValue(int mana, EntityPlayer player) {
		essence = mana;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void removeBarPoints(int mana, EntityPlayer player) {
		regenDelay = 10;
		essence -= mana;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void updateAllBars(EntityPlayer player) {
		essence += 1;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void addPoints(int amount, EntityPlayer player) {
		essence += amount;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageEssenceBar(essence, regenDelay == 0), (EntityPlayerMP)player);
	}
}