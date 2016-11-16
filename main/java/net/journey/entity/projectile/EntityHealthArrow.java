package net.journey.entity.projectile;

import net.journey.JourneyItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntityHealthArrow extends EntityEssenceArrow {
    private int duration = 200;
	private float health = 1;
	
    public EntityHealthArrow(World w){
        super(w);
    }

    public EntityHealthArrow(World w, EntityLivingBase shooter){
        super(w, shooter);
    }

    public EntityHealthArrow(World w, double x, double y, double z){
        super(w, x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.worldObj.isRemote && !this.inGround) {
            this.worldObj.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(JourneyItems.essenceArrow);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
		float hearts = living.getHealth();
		if(hearts >= 1F) {
			living.setHealth(hearts + this.health);
		}
    }

    public static void dataFixer(DataFixer fixer) {
        EntityHealthArrow.func_189657_a(fixer, "HealthArrow");
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag){
        super.readEntityFromNBT(tag);

        if (tag.hasKey("Duration")){
            this.duration = tag.getInteger("Duration");
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag){
        super.writeEntityToNBT(tag);
        tag.setInteger("Duration", this.duration);
    }
}