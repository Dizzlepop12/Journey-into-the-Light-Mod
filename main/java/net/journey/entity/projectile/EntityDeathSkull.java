package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDeathSkull extends EntityWitherSkull {

	public EntityDeathSkull(World par1World) {
		super(par1World);
	}

	public EntityDeathSkull(World par1World, EntityLivingBase par2EntityLivingBase, double vX, double vY, double vZ) {
		super(par1World, par2EntityLivingBase, vX, vY, vZ);
	}

	public EntityDeathSkull(World par1World, EntityLivingBase par2EntityLivingBase, double posX, double posY, double posZ, double vX, double vY, double vZ) {
		super(par1World, par2EntityLivingBase, vX, vY, vZ);
		this.setPosition(posX, posY, posZ);
		Double d3 = (double)MathHelper.sqrt_double(vX * vX + vY * vY + vZ * vZ);
		this.accelerationX = vX / d3 * 0.1D;
		this.accelerationY = vY / d3 * 0.1D;
		this.accelerationZ = vZ / d3 * 0.1D;
	}

	public EntityDeathSkull(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
	}

	protected void onImpact(RayTraceResult par1MovingObjectPosition) {
		if(!this.worldObj.isRemote) {
			if(par1MovingObjectPosition.entityHit != null) {
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.magic, 20.0F);
				if(par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
					byte witherSeconds = 10;
					if(witherSeconds > 0) ((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * 1, 1));
				}
			}
			this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getBoolean("mobGriefing"));
			this.setDead();
		}
	}
}