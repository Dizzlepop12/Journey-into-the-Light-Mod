package net.journey.items;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.item.ItemModFood;

public class ItemGoldenFood extends ItemModFood {
	
	private boolean op;
	
    public ItemGoldenFood(String name, String actual, int heal, float sat, boolean wolf, boolean isOP) {
        super(name, actual, heal, sat, wolf);
        op = isOP;
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.crops);
        this.setAlwaysEdible();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack i) {
        return op;
    }

    @Override
    public EnumRarity getRarity(ItemStack i) {
        return !op ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    @Override
    protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
        if(!w.isRemote) {
            p.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
        if(op) {
                p.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 4));
                p.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
                p.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
            }
        } else {
            super.onFoodEaten(i, w, p);
        }
    }
}