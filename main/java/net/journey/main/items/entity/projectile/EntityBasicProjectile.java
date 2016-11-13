package net.journey.main.items.entity.projectile;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBasicProjectile extends EntityThrowable {

	public float damage;

	public EntityBasicProjectile(World var1) {
		super(var1);
	}

	public EntityBasicProjectile(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3);
		damage = dam;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	@Override
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		//for(int i = 0; i < 6; ++i) {
			//EntityFX effect = new EntityHellstoneFX(this.worldObj, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
			//FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		//}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.001F;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entityHit != null) result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		if(!worldObj.isRemote) this.setDead();
	}
}