package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.darkEnergy.IDarkEnergyBar;
import net.journey.client.server.bars.essence.EssenceBar;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.enums.EnumSounds;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

public class ItemHammer extends ItemSword{

	protected int usage;
	protected int damage;
	protected boolean essence, unbreakable;
	protected Class<? extends EntityBasicProjectile> projectile;
	protected EssenceToolMaterial mat;
	private boolean dark;

	public ItemHammer(String name, String f, EssenceToolMaterial toolMaterial, boolean durability, Class<? extends EntityBasicProjectile> projectile, boolean essence, boolean dark, int dam, int magic, int uses) {
		super(toolMaterial.getToolMaterial());
		this.projectile = projectile;
		damage = dam;
		usage = magic;
		this.essence = essence;
		this.dark = dark;
		setMaxDamage(uses);
		setMaxStackSize(1);
		LangRegistry.addItem(name, f);
		setUnlocalizedName(name);
		mat = toolMaterial;
		setCreativeTab(JourneyTabs.hammers);
		JourneyItems.itemNames.add(name);
		GameRegistry.registerItem(this, name);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		final IEssenceBar bar1 = player.getCapability(BarTickHandler.ESSENCE_CAP, null);
		final IDarkEnergyBar bar2 = player.getCapability(BarTickHandler.DARK_CAP, null);
		if(dark) {
			if(!world.isRemote && bar2.useBar(usage, player)) {
				//EnumSounds.playSound(EnumSounds.HAMMER, world, player);
				if(!unbreakable) stack.damageItem(1, player);
				try {
					world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(essence) {
				if(!world.isRemote && bar1.useBar(usage, player)) {
					//EnumSounds.playSound(EnumSounds.HAMMER, world, player);
					if(!unbreakable) stack.damageItem(1, player);
					try {
						world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	public boolean isItemTool(ItemStack i) {
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List l, boolean par4) {
		if(item.getMaxDamage() != -1) l.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else l.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
		if(essence) LangHelper.useDarkEnergy(usage);
		else LangHelper.useDarkEnergy(usage);
		l.add(SlayerAPI.Colour.DARK_GREEN + "+" + LangHelper.rangedDamage(damage));
	}
}