package net.journey.items;

import java.util.List;
import java.util.Random;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.darkEnergy.DarkEnergyBar;
import net.journey.client.server.bars.darkEnergy.IDarkEnergyBar;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.journey.entity.projectile.EntityAttractor;
import net.journey.entity.projectile.EntityDetractor;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemDetractor extends ItemMod {

	private int magic;
	public boolean attracts;
	public boolean detracts;

	public ItemDetractor(String name, String finalName, int magic, boolean attracts, boolean detracts) {
		super(name, finalName, JourneyTabs.staves);
		setMaxStackSize(1);
		this.magic = magic;
		this.attracts = attracts;
		this.detracts = detracts;
		this.setFull3D();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		final IDarkEnergyBar bar1 = player.getCapability(BarTickHandler.DARK_CAP, null);
		Random r = new Random();
		if (detracts) {
			if(!world.isRemote && bar1.useBar(magic, player)) {
				//EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				EntityThrowable entity = new EntityDetractor(world, player);
				world.spawnEntityInWorld(entity);
			}
		}
		if (attracts) {
			if(!world.isRemote && bar1.useBar(magic, player)) {
				//EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				EntityThrowable entity = new EntityAttractor(world, player);
				world.spawnEntityInWorld(entity);
			}
		}
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l) {
		if (detracts) {
			l.add(SlayerAPI.Colour.AQUA + "Uses " + magic + " Dark Energy");
			l.add(SlayerAPI.Colour.AQUA + "Fires a mob away from you");
		}
		if (attracts) {
			l.add(SlayerAPI.Colour.AQUA + "Uses " + magic + " Dark Energy");
			l.add(SlayerAPI.Colour.AQUA + "Pulls a mob towards you");
		}
	}
}