package net.journey.main.items;

import java.util.List;

import net.journey.main.JourneyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.slayerapi.item.ItemMod;

public class ItemKnife extends ItemMod {

	private Class<? extends EntityThrowable> entity;
	private float damage;
	private int maxBounces = 0;
	
	public ItemKnife(String name, String f, float damage, int bounces, Class<? extends EntityThrowable> entity) {
		super(name, f);
		this.maxBounces = bounces;
		this.damage = damage;
		this.entity = entity;
		setCreativeTab(JourneyTabs.piercers);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World w, EntityPlayer player, EnumHand hand) {
		try {
			if(!w.isRemote) {
				w.playSound(player, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				w.spawnEntityInWorld(entity.getConstructor(World.class, EntityLivingBase.class, float.class, int.class).newInstance(w, player, damage, maxBounces));
				if(!player.capabilities.isCreativeMode) stack.stackSize--;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return super.onItemRightClick(stack, w, player, hand);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		list.add(damage + "Ranged Damage");
	}
}