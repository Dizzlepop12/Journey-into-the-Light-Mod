package net.journey.proxy;

import java.beans.EventHandler;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.EssenceBar;
import net.journey.client.server.bars.EssenceStorage;
import net.journey.client.server.bars.IEssenceBar;
import net.journey.enums.EnumSounds;
import net.journey.util.Config;
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
	
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event);
		JourneyTabs.init();
		EnumSounds.init();
		CapabilityManager.INSTANCE.register(IEssenceBar.class, new EssenceStorage(), EssenceBar.class);
        MinecraftForge.EVENT_BUS.register(new BarTickHandler());
		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
	}
	
	public void init(FMLInitializationEvent event) { }
	public void postInit(FMLPostInitializationEvent event) { }
	public void serverStarting(FMLServerStartingEvent event) { }
	private void addOreDictionary() { }
}