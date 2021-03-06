package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.server.bars.BarTickHandler;
import net.journey.client.server.bars.essence.IEssenceBar;
import net.journey.util.LangHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemTeleport extends ItemMod {

	public ItemTeleport(String name, String f) {
		super(name, f, JourneyTabs.util);
		setMaxStackSize(1);
		setMaxDamage(100);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack par1, World par2, EntityPlayer par3, EnumHand hand) {
		final IEssenceBar bar1 = par3.getCapability(BarTickHandler.ESSENCE_CAP, null);
		float var4 = par3.rotationPitch;
		float var5 = par3.rotationYaw;
		double var6 = par3.posX;
		double var8 = par3.posY + 1.62D;
		double var10 = par3.posZ;
		Vec3d var12 = new Vec3d(var6, var8, var10);
		float var13 = MathHelper.cos(-var5 * 0.01745329F - (float)Math.PI);
		float var14 = MathHelper.sin(-var5 * 0.01745329F - (float)Math.PI);
		float var15 = -MathHelper.cos(-var4 * 0.01745329F);
		float var16 = MathHelper.sin(-var4 * 0.01745329F);
		float var17 = var14 * var15;
		float var18 = var13 * var15;
		double var19 = 30.0D;
		Vec3d var21 = var12.addVector((double)var17 * var19, (double)var16 * var19, (double)var18 * var19);
		RayTraceResult var22 = par2.rayTraceBlocks(var12, var21);
		if(var22 == null) {
			return new ActionResult(EnumActionResult.PASS, par1);
		} else {
			if (var22.typeOfHit == Type.BLOCK) {
				int var23 = var22.getBlockPos().getX();
				int var24 = var22.getBlockPos().getY();
				int var25 = var22.getBlockPos().getZ();
				int var26 = var22.subHit;

				if (var26 == 0) --var24;                
				if (var26 == 1) ++var24;               
				if (var26 == 2) --var25;                
				if (var26 == 3) ++var25;                
				if (var26 == 4) --var23;                
				if (var26 == 5) ++var23;                

				if(bar1.useBar(5, par3)) {
					par3.getLook(1);
					this.teleportTo(par3, par2, (double)var23, (double)var24, (double)var25);
				}
			}
		}
		return new ActionResult(EnumActionResult.PASS, par1);
	}

	protected void teleportTo(EntityPlayer par1, World par2, double par3, double par4, double par5) {
		par1.setPosition(par3, par4, par5);
		//par2.playSoundAtEntity(par1, "mob.endermen.portal", 1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		list.add(SlayerAPI.Colour.DARK_GREEN + LangHelper.useEssence(5));
		list.add(stack.getMaxDamage() - stack.getItemDamage() + " " + LangHelper.getUsesRemaining());
	}
}