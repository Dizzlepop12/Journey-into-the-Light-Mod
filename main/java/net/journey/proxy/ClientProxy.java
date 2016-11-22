package net.journey.proxy;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.client.EntityRendering;
import net.journey.client.GuiHandler;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.darkEnergy.IDarkEnergyBar;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayerapi.base.SlayerAPI;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerClient() { 
		/*NetworkRegistry.INSTANCE.registerGuiHandler(Journey.instance, new GuiHandler());
		if(!SlayerAPI.DEVMODE) SlayerAPI.registerEvent(new UpdateCheckerEvent()); */
	}

	@Override
	public void clientPreInit() {
		EntityRendering.init();
	}

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
	
	@Override
	public void updateDarkEnergy(int amount) {
		final IDarkEnergyBar dark = Minecraft.getMinecraft().thePlayer.getCapability(BarTickHandler.DARK_CAP, null);
		dark.setBarValue(amount, Minecraft.getMinecraft().thePlayer);
	}
	
	@Override
	public void updateEssence(int amount) {
		final IEssenceBar essence = Minecraft.getMinecraft().thePlayer.getCapability(BarTickHandler.ESSENCE_CAP, null);
		essence.setBarValue(amount, Minecraft.getMinecraft().thePlayer);
	}
}