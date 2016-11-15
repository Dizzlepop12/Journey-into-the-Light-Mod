package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemChaosCannon extends ItemMod {

	public int damage;
	public String ability;
	public ItemChaosCannon(String name, String f, int damage, int i, String ability) {
		super(name, f, JourneyTabs.staves);
		this.ability = ability;
		this.damage = damage;
		setMaxStackSize(1);
		setMaxDamage(500);
		setFull3D();
	}

	@SuppressWarnings("")
	/*@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote && EssenceBar.getProperties(player).useBar(2)) {
			EnumSounds.playSound(EnumSounds.PLASMA, world, player);
			try {
				world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				stack.damageItem(1, player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stack;
	}*/

	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l) {
		l.add("Infinite ammo");
		l.add("Uses 2 Essence");
		l.add(SlayerAPI.Colour.GOLD + "Ability: " + ability);
		l.add(SlayerAPI.Colour.AQUA + "Damage: " + damage + " ranged damage");
		l.add(i.getMaxDamage() - i.getItemDamage() + SlayerAPI.Colour.DARK_GREEN + " Uses remaining");
	}
}