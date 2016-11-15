package net.journey.client.server.bars;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class BarTickHandler {

	private EntityPlayer player;
	private int ticks = 10;

	public static int darkAmount, powerAmount;
	@CapabilityInject(IEssenceBar.class)
	public static Capability<IEssenceBar> ESSENCE_CAP = null;

	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent evt) {
		evt.addCapability(new ResourceLocation(SlayerAPI.MOD_ID, "IEssenceBar"), new ICapabilitySerializable<NBTPrimitive>() {
			IEssenceBar inst = ESSENCE_CAP.getDefaultInstance();
			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == ESSENCE_CAP;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == ESSENCE_CAP ? ESSENCE_CAP.<T>cast(inst) : null;
			}

			@Override
			public NBTPrimitive serializeNBT() {
				return (NBTPrimitive)ESSENCE_CAP.getStorage().writeNBT(ESSENCE_CAP, inst, null);
			}

			@Override
			public void deserializeNBT(NBTPrimitive nbt) {
				ESSENCE_CAP.getStorage().readNBT(ESSENCE_CAP, inst, null, nbt);
			}
		});
	}

	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		if(event.phase == Phase.END) tickEnd(event.player);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderEvent(RenderTickEvent event) {
		onTickRender(Minecraft.getMinecraft().thePlayer);
	}

	@SideOnly(Side.CLIENT)
	private void onTickRender(EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.currentScreen == null) {
			if(!player.capabilities.isCreativeMode) {
				GL11.glPushMatrix();
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GuiIngame gig = mc.ingameGUI;
				ScaledResolution scaledresolution = new ScaledResolution(mc);
				mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.MOD_ID, "textures/gui/misc.png"));
				int y = scaledresolution.getScaledHeight() - 30, x = 10, x1 = 10, x2 = 10;
				gig.drawTexturedModalRect(x - 10, y + 10, 0, 177, 117, 19);
				gig.drawTexturedModalRect(x - 10, y - 5, 0, 177, 117, 19);
				gig.drawTexturedModalRect(x - 10, y - 20, 0, 177, 117, 19);

				gig.drawTexturedModalRect(x - 6, y - 13, 0, 23, 109, 5);

				for(int i = 0; i < player.getCapability(ESSENCE_CAP, null).getBarValue(); i++) {
					x += 11;
					gig.drawTexturedModalRect(x - 17, y - 13, 0, 0, 10, 5);
				}
				y += 15;
				gig.drawTexturedModalRect(x1 - 6, y - 13, 0, 36, 109, 5);
				for(int i = 0; i < darkAmount; i++) {
					x1 += 11;
					gig.drawTexturedModalRect(x1 - 17, y - 13, 0, 5, 10, 5);
				}
				gig.drawTexturedModalRect(x2 - 6, y + 2, 0, 49, 109, 5);
				for(int i = 0; i < powerAmount; i++) {
					x2 += 11;
					gig.drawTexturedModalRect(x2 - 17, y + 2, 0, 10, 10, 5);
				}
				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				GL11.glPopMatrix();
			}
		}
	}

	private void tickEnd(EntityPlayer player) {
		final IEssenceBar essence = player.getCapability(ESSENCE_CAP, null);
		if(ticks-- <= 0) ticks = 20;
		if(ticks >= 20) {
			//DarkEnergyBar.getProperties(player).updateAllBars();
			essence.updateAllBars();
			//PowerBar.getProperties(player).updateAllBars();
		}
		//DarkEnergyBar.getProperties(player).mainUpdate();
		essence.mainUpdate();
		//PowerBar.getProperties(player).mainUpdate();
	}
}