package net.journey.proxy;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.darkEnergy.DarkEnergyBar;
import net.journey.client.server.bars.darkEnergy.DarkEnergyStorage;
import net.journey.client.server.bars.darkEnergy.IDarkEnergyBar;
import net.journey.client.server.bars.essence.EssenceBar;
import net.journey.client.server.bars.essence.EssenceStorage;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.journey.enums.EnumSounds;
import net.journey.util.Config;
import net.journey.util.EntityRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

public class CommonProxy {

	public void registerClient() { }
	public void clientInit(FMLInitializationEvent event) { }
	public void clientPreInit() { }
	public void registerSounds() { }
	public void registerModModels() { }
	public void updateDarkEnergy(int amount) { }
	public void updateEssence(int amount) { }
	
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event);
		JourneyTabs.init();
		EntityRegistry.init();
		EnumSounds.init();
		CapabilityManager.INSTANCE.register(IEssenceBar.class, new EssenceStorage(), EssenceBar.class);
		CapabilityManager.INSTANCE.register(IDarkEnergyBar.class, new DarkEnergyStorage(), DarkEnergyBar.class);
        MinecraftForge.EVENT_BUS.register(new BarTickHandler());
		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
	}
	
	public void init(FMLInitializationEvent event) { }
	public void postInit(FMLPostInitializationEvent event) { }
	public void serverStarting(FMLServerStartingEvent event) { }
	private void addOreDictionary() { }
}