package net.journey.main.items;

import java.util.List;

import net.journey.main.JourneyItems;
import net.journey.main.JourneyTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.PlayerHelper;
import net.slayerapi.base.SlayerAPI;

public class ItemHealth extends ItemFood {

	public int max;
	public double hearts;
	public boolean isSentry;
	public boolean isntSentry;
    public ItemHealth(String name, String actual, int hearts, int heal, float f, boolean sat, boolean b, int max, boolean isSentry, boolean isntSentry) {
        super(heal, sat);
        this.hearts = hearts;
        this.isSentry = isSentry;
        this.isntSentry = isntSentry;
        this.max = max;
        setMaxStackSize(8);
        setAlwaysEdible();
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.crops);
        GameRegistry.registerItem(this, name);
        LangRegistry.addItem(name, actual);
        JourneyItems.itemNames.add(name);
    }

    @Override
    protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
    	if(isntSentry && p.getMaxHealth() < max) {
    		p.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(p.getMaxHealth() + hearts);
    		PlayerHelper.getPersistedpTag(p).setDouble("health", p.getMaxHealth());
    	}
    	else if(isSentry && p.getMaxHealth() >= max) {
    		p.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(p.getMaxHealth() + hearts);
    		PlayerHelper.getPersistedpTag(p).setDouble("health", p.getMaxHealth());
    	}
    }
	
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(SlayerAPI.Colour.RED + "Adds " + hearts / 2F + " Heart(s)");
		if(isntSentry && player.getMaxHealth() >= 60 && !isSentry) {
			list.add(SlayerAPI.Colour.DARK_RED + "You have reached the maximum amount of health.");
			list.add(SlayerAPI.Colour.DARK_RED + "No more can be achieved without a Sentry's Heart");
		}
		if(isSentry) {
			list.add(SlayerAPI.Colour.GOLD + "Grants 10 more health points");
			list.add(SlayerAPI.Colour.GOLD + "Recommended only if player has reached max health");
		}
	}
}