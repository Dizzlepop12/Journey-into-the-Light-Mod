package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.essence.EssenceBar;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		final IEssenceBar bar1 = player.getCapability(BarTickHandler.ESSENCE_CAP, null);
		if(!world.isRemote && bar1.useBar(2, player)) {
			//EnumSounds.playSound(EnumSounds.PLASMA, world, player);
			try {
				world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
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