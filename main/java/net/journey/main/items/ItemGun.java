package net.journey.main.items;

import java.util.List;

import net.journey.main.JourneyTabs;
import net.journey.main.enums.EnumSounds;
import net.journey.main.items.entity.projectile.EntityBasicProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemGun extends ItemMod {

	public int damage;
	protected Class<? extends EntityBasicProjectile> projectile; 
	public String ability;
	public ItemGun(String name, String f, int damage, String ability, Class<? extends EntityBasicProjectile> projectile) {
		super(name, f, JourneyTabs.staves);
		this.ability = ability;
		this.projectile = projectile;
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