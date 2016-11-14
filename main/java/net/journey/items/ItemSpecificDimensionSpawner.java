package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayerapi.base.SlayerAPI;
import net.slayerapi.item.ItemMod;

public class ItemSpecificDimensionSpawner extends ItemMod {

	private int ticks;
	public String dimName;
	public int dimID;
	
	public ItemSpecificDimensionSpawner(int dimID, String name, String f, String dimName) {
		super(name, f, JourneyTabs.spawners);
		setMaxStackSize(1);
		this.dimID = dimID;
		this.dimName = dimName;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack i, EntityPlayer p, World w, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/*Item item = i.getItem();
		if(!w.isRemote) {
			if(w.provider.getDimension() == dimID) {
				EntityLightningBall light = new EntityLightningBall(w);
				EntityBlazier blaze = new EntityBlazier(w);
				EntitySoulWatcher soul = new EntitySoulWatcher(w);
				EntitySentryKing sentry = new EntitySentryKing(w);
				EntitySkyStalker sky = new EntitySkyStalker(w);
				EntityTameRoc roc = new EntityTameRoc(w, p);
				EntityScale scale = new EntityScale(w);
				EntityCorallator corallator = new EntityCorallator(w);
				EntityLogger logger = new EntityLogger(w);
				EntityThunderbird thunder = new EntityThunderbird(w);
				EntityTerranianProtector terrastar = new EntityTerranianProtector(w);
				if(item == JourneyItems.blazierOrb) {			
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					SlayerAPI.sendMessageToAll("You're playing with hot fire. It's not too late to turn back...", true);
				    blaze.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
				    w.spawnEntityInWorld(blaze);
				}
				if(item == JourneyItems.rocSpawnEgg) {
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					roc.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(roc);
					p.triggerAchievement(JourneyAchievements.achievementRoc);
				}
				if(item == JourneyItems.sentryKingOrb) {
					SlayerAPI.sendMessageToAll("It sucked all the energy out of this world, don't let it suck the energy out of you...", true);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					sentry.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(sentry);
				}
				if(item == JourneyItems.mysteriousDisk) {
					SlayerAPI.sendMessageToAll("The purple flying fish monster is not happy...", true);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					sky.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(sky);
				}
				if(item == JourneyItems.corallatorOrb) {
					SlayerAPI.sendMessageToAll("You will regret this mistake for the rest of your life - if you'll still have one, that is...", true);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					corallator.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(corallator);
				}
				if(item == JourneyItems.loggerOrb) {
					SlayerAPI.sendMessageToAll("You'll get chopped to pieces with this one...", true);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					logger.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(logger);
				}
				if(item == JourneyItems.scaleOrb) {
					SlayerAPI.sendMessageToAll("(W.I.P) The blue blubby fish monster has been summoned!", true);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					scale.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(scale);
				}
				EntityEudor eudor = new EntityEudor(w);
				if(item == JourneyItems.eudorOrb){
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					SlayerAPI.sendMessageToAll("The King has been summoned", true);
					eudor.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(eudor);
				}
				if(item == JourneyItems.thunderbirdOrb) {
					SlayerAPI.sendMessageToAll("The thunderbird is not pleased with its awakening...", true);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					thunder.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(thunder);
				}
				if(item == JourneyItems.enchantedTerrastar) {
					SlayerAPI.sendMessageToAll("It's sole purpose was to protect this land. Why would you try to destroy it?", true);
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					terrastar.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(terrastar);
				}
				if(!p.capabilities.isCreativeMode) i.stackSize--;
			} else {
				SlayerAPI.addChatMessage(p, SlayerAPI.Colour.GREEN + "Cannot be called upon unless in the " + dimName + " dimension.");
			}
		}*/
		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		Item item = stack.getItem();
		/*if(item == JourneyItems.blazierOrb) list.add(LangHelper.setBossSpawner("Blazier"));
		if(item == JourneyItems.sentryKingOrb) list.add(LangHelper.setBossSpawner("Sentry King"));
		if(item == JourneyItems.mysteriousDisk) list.add(LangHelper.setBossSpawner("Sky Stalker"));
		if(item == JourneyItems.corallatorOrb) list.add(LangHelper.setBossSpawner("Corallator"));
		if(item == JourneyItems.loggerOrb) list.add(LangHelper.setBossSpawner("Logger"));
		if(item == JourneyItems.scaleOrb) list.add(LangHelper.setBossSpawner("Sclae"));
		if(item == JourneyItems.thunderbirdOrb) list.add(LangHelper.setBossSpawner("Thunderbird"));
		if(item == JourneyItems.enchantedTerrastar) list.add(LangHelper.setBossSpawner("Terranian Protector"));
		if(item == JourneyItems.rocSpawnEgg) list.add(LangHelper.setPetSpawner("Roc"));
		if(item == JourneyItems.rocSpawnEgg) list.add(SlayerAPI.Colour.DARK_GREEN + "More powerful than a dog, less cuddly.");*/
		
	}
}