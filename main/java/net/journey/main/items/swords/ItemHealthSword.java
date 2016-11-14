package net.journey.main.items.swords;

import java.util.List;
import java.util.Random;

import net.journey.main.client.render.particles.EntityHellstoneFX;
import net.journey.main.util.EssenceToolMaterial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemModSword;

public class ItemHealthSword extends ItemModSword {

	private float health;
	public ItemHealthSword(String name, String f , EssenceToolMaterial toolMaterial, float health) {
		super(name, f, toolMaterial);
		this.health = health;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase hit, EntityLivingBase player) {
		float hearts = player.getHealth();
		if(hearts >= 1F) {
			player.setHealth(hearts + this.health);
		}
		
		addParticles(hit);
		return super.hitEntity(par1ItemStack, hit, player);
	}
	
	@SideOnly(Side.CLIENT)
	public void addParticles(EntityLivingBase hit) {
		Random r = new Random();
		for(int i = 0; i < 20; i++){
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(new EntityHellstoneFX(hit.worldObj, hit.posX + r.nextFloat() - 0.5F, hit.posY + 0.5D + r.nextFloat(), hit.posZ + r.nextFloat() - 0.5F, 0.0D, 0.0D, 0.0D));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		infoList.add(SlayerAPI.Colour.RED + "On hit: Heals player " + health / 2 + " heart(s)");
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " Uses Remaining");
	}
}