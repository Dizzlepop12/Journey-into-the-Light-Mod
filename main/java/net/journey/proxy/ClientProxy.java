package net.journey.proxy;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayerapi.base.SlayerAPI;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerClient() { }

	@Override
	public void clientPreInit() { }

	@Override
	public void clientInit(FMLInitializationEvent event) { }

	@Override
	public void registerModModels() {
		for(String s : JourneyItems.itemNames) {
			Item i = GameRegistry.findItem(SlayerAPI.MOD_ID, s);
			SlayerAPI.registerItemRender(i, s);
		}
		
		for(String s : JourneyBlocks.blockName) {
			Item i = GameRegistry.findItem(SlayerAPI.MOD_ID, s);
			SlayerAPI.registerItemRender(i, s);
		}
	}
}