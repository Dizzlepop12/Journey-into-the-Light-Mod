package net.journey.client.server.bars.darkEnergy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class DarkEnergyStorage implements Capability.IStorage<IDarkEnergyBar>{
	
	@Override
	public NBTBase writeNBT(Capability<IDarkEnergyBar> capability, IDarkEnergyBar instance, EnumFacing side) {
		return new NBTTagByte((byte)0);
	}

	@Override
	public void readNBT(Capability<IDarkEnergyBar> capability, IDarkEnergyBar instance, EnumFacing side, NBTBase nbt) {
		
	}
}