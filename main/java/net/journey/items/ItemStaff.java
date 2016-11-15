package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.EssenceBar;
import net.journey.client.server.bars.IEssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.util.LangHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemStaff extends ItemMod {

	protected int usage;
	protected int damage;
	protected boolean essence, unBreakable;
	protected Class<? extends EntityBasicProjectile> projectile; 

	public ItemStaff(String name, String f, boolean essence, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityBasicProjectile> projectile) {
		super(name, f);
		this.projectile = projectile;
		damage = dam;
		usage = magic;
		this.essence = essence;
		this.unBreakable = unbreakable;
		setMaxDamage(uses);
		setMaxStackSize(1);
		setFull3D();
		setCreativeTab(JourneyTabs.staves);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		final IEssenceBar bar1 = player.getCapability(BarTickHandler.ESSENCE_CAP, null);
		if(essence) {

			if(!world.isRemote && bar1.useBar(usage)) {
				//EnumSounds.playSound(EnumSounds.SPARKLE, world, player);
				if(!unBreakable) stack.damageItem(1, player);
				try {
					//world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} /*else {
			if(!world.isRemote && DarkEnergyBar.getProperties(player).useBar(usage)) {
				EnumSounds.playSound(EnumSounds.SPARKLE, world, player);
				if(!unBreakable) stack.damageItem(1, player);
				try {
					world.spawnEntityInWorld(projectile.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}*/
		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		list.add(SlayerAPI.Colour.DARK_GREEN + damage + " Ranged Damage");
		if(essence) LangHelper.useDarkEnergy(usage);
		else LangHelper.useDarkEnergy(usage);
		if(unBreakable) list.add("Infinate Uses");
		else list.add(stack.getMaxDamage() - stack.getItemDamage() + " Uses Remaining");
	}
}