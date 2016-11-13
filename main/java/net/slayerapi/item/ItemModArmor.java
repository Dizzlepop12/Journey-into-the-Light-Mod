package net.slayerapi.item;

import java.util.List;

import net.journey.main.JourneyItems;
import net.journey.main.JourneyTabs;
import net.journey.main.util.ArmorDescription;
import net.journey.main.util.EnumArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

public class ItemModArmor extends ItemArmor implements ISpecialArmor {

	protected final EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;
	protected double damageReduction;
	protected boolean unbreakable;
	protected String textureName = SlayerAPI.PREFIX + "textures/models/armor/", name;
	protected int fullReduction;
	protected EnumArmor armorMaterial;

	public ItemModArmor(EnumArmor armorMaterial, EntityEquipmentSlot type) {
		this(armorMaterial, type, armorMaterial.getType());
	}

	public ItemModArmor(EnumArmor armorMaterial, EntityEquipmentSlot type, String name) {
		super(armorMaterial.getArmorMaterial(), 0, type);
		this.armorMaterial = armorMaterial;
		this.fullReduction = armorMaterial.getDamageReduction();
		if (armorType == type.HEAD) damageReduction = ((((double)fullReduction) / 24) * 5) / 100;
		else if (armorType == type.CHEST) damageReduction = ((((double)fullReduction) / 24) * 8) / 100;
		else if (armorType == type.LEGS) damageReduction = ((((double)fullReduction) / 24) * 7) / 100;
		else if (armorType == type.LEGS) damageReduction = ((((double)fullReduction) / 24) * 4) / 100;
		this.unbreakable = armorMaterial.isUndamageable();
		setCreativeTab(JourneyTabs.armor);
		setArmorType(name, armorType);
		setUnlocalizedName(this.name);
		JourneyItems.itemNames.add(this.name);
		GameRegistry.registerItem(this, this.name);
		LangRegistry.addArmour(this, armorMaterial, type);
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		return armorMaterial.getRepairItem() != null && armorMaterial.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
	}

	protected void setArmorType(String material, EntityEquipmentSlot armorType) {
		this.name = armorType == HEAD ? material + "Helmet" : armorType == BODY ? material + "Body" : armorType == LEGS ? material + "Legs" : armorType == BOOTS ? material + "Boots" : material + "Unknown";
		this.textureName = (armorType == EntityEquipmentSlot.HEAD || armorType == EntityEquipmentSlot.CHEST || armorType == EntityEquipmentSlot.FEET) ? textureName + armorMaterial.getType() + "_1.png" : textureName + armorMaterial.getType() + "_2.png";
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return textureName;
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		double roundPH = Math.round(damageReduction * 1000);
		double roundedDamage = roundPH / 10;
		list.add(damageReduction == 0.0 ? (SlayerAPI.Colour.DARK_AQUA + "No Protection") : SlayerAPI.Colour.AQUA + "Damage Reduction: " + roundedDamage);
		list.add(!unbreakable ? (item.getMaxDamage() - item.getItemDamage() + " Uses Remaining") : "Unlimited Uses");
		ArmorDescription.add(item, player, list);
	}

	@Override
	public boolean isDamageable() {
		return !unbreakable;
	}

	@Override
	public void damageArmor(EntityLivingBase l, ItemStack s, DamageSource d, int amount, int slot) {
		if(!unbreakable) s.damageItem(1, l);
	}

	@Override
	public int getArmorDisplay(EntityPlayer p, ItemStack s, int b) {
		return (int)Math.round((damageReduction * 100) / 4);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase l, ItemStack s, DamageSource d, double amount, int slot) {
		return new ISpecialArmor.ArmorProperties(0, damageReduction, 50000);
	}
}