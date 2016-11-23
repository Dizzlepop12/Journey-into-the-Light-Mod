package net.journey.client.server.bars.darkEnergy;

import net.journey.Journey;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DarkEnergyBar implements IDarkEnergyBar {

	private int darkEnergy = 0, regenDelay = 0;
	
	@Override
	public boolean useBar(int amount, EntityPlayer player) {
		if(darkEnergy < amount) {
			regenDelay = 10;
			return false;
		}
		darkEnergy -= amount;
		regenDelay = 10;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
		return true;
	}

	@Override
	public int getBarValue() {
		return darkEnergy;
	}

	@Override
	public void mainUpdate(EntityPlayer player) {
		if(getBarValue() >= 10) darkEnergy = 10;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void setBarValue(int mana, EntityPlayer player) {
		darkEnergy = mana;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void removeBarPoints(int mana, EntityPlayer player) {
		regenDelay = 10;
		darkEnergy -= mana;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void updateAllBars(EntityPlayer player) {
		darkEnergy += 1;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}

	@Override
	public void addPoints(int amount, EntityPlayer player) {
		darkEnergy += amount;
		if(player instanceof EntityPlayerMP) Journey.wrapper.sendTo(new MessageDarkEnergyBar(darkEnergy, regenDelay == 0), (EntityPlayerMP)player);
	}
}