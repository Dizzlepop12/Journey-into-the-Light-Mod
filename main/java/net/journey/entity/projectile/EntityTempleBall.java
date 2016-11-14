package net.journey.entity.projectile;

import java.util.Random;

import net.journey.client.render.particles.EntityGreenpaceFX;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityTempleBall extends EntityBasicProjectile {

	public EntityTempleBall(World var1) {
		super(var1);
	}
	
	public EntityTempleBall(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 20; ++i) {
			Particle effect = new EntityGreenpaceFX(this.worldObj, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
	}
	
	@Override
	protected void onImpact(RayTraceResult var1) {
		if(var1.entityHit != null) { 
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			((EntityLivingBase)var1.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2));
			((EntityLivingBase)var1.entityHit).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 5));
			((EntityLivingBase)var1.entityHit).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, 2));
		}
		if(!worldObj.isRemote) this.setDead();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}