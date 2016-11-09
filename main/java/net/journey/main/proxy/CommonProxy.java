package net.journey.main.proxy;

import net.journey.main.enums.EnumSounds;
import net.journey.main.util.Config;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
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
		EnumSounds.init();
		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
	}
	
	public void init(FMLInitializationEvent event) { }
	public void postInit(FMLPostInitializationEvent event) { }
	public void serverStarting(FMLServerStartingEvent event) { }
	private void addOreDictionary() { }
}