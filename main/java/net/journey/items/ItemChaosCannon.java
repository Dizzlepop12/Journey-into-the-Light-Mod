package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.journey.entity.projectile.EntityBouncingProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemChaosCannon extends ItemMod {

	public int damage;
	public String ability;
	public int bounces;

	public ItemChaosCannon(String name, String f, int damage, int bounces, String ability) {
		super(name, f, JourneyTabs.staves);
		this.ability = ability;
		this.damage = damage;
		this.bounces = bounces;
		setMaxStackSize(1);
		setMaxDamage(500);
		setFull3D();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		final IEssenceBar bar1 = player.getCapability(BarTickHandler.ESSENCE_CAP, null);
		if(!world.isRemote && bar1.useBar(2, player)) {
			//EnumSounds.playSound(EnumSounds.PLASMA, world, player);
			try {
				world.spawnEntityInWorld(new EntityBouncingProjectile(world, player, damage, bounces));
				stack.damageItem(1, player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l) {
		l.add("Infinite ammo");
		l.add("Uses 2 Essence");
		l.add(SlayerAPI.Colour.GOLD + "Ability: " + ability);
		l.add(SlayerAPI.Colour.AQUA + "Damage: " + damage + " ranged damage");
		l.add(i.getMaxDamage() - i.getItemDamage() + SlayerAPI.Colour.DARK_GREEN + " Uses remaining");
	}
}