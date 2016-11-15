package net.journey.client.server.bars;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EssenceStorage implements Capability.IStorage<IEssenceBar>{

	@Override
	public NBTBase writeNBT(Capability<IEssenceBar> capability, IEssenceBar instance, EnumFacing side) {
		BarTickHandler.essenceAmount = instance.getBarValue();
		return new NBTTagByte((byte)1);
	}

	@Override
	public void readNBT(Capability<IEssenceBar> capability, IEssenceBar instance, EnumFacing side, NBTBase nbt) {
		
	}
}