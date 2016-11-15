package net.journey.enums;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public enum EnumSounds {

	CAVE_MOB("journey:caveMob"),
	ROCK("journey:rock"),
	BASE_MOB_HURT("journey:baseMobHurt"),
	BOSS_DEATH("journey:bossDeath"),
	SLUG("journey:terraSlug"),
	SLUG_HURT("journey:terraSlugHurt"),
	SLUG_DEATH("journey:terraSlugDeath"),
	SUMMON("journey:summonBoss"),
	SUMMON_TABLE("journey:summon"),
	SORCERER_DEATH("journey:sorcererDeath"),
	SORCERER_HURT("journey:sorcererHurt"),
	SORCERER("journey:sorcerer"),
	CHEST_OPEN_0("journey:chestOpen_0"),
	CHEST_OPEN("journey:chest"),
	CHEST_CLOSED("journey:chestClose"),
	ROBOT("journey:robot"),
	ROBOT_HURT("journey:robotHurt"),
	ROBOT_DEATH("journey:robotDeath"),
	PSYOLLOM("journey:psyollom"),
	PSYOLLOM_HURT("journey:psyollomHurt"),
	INSECTO("journey:insecto"),
	INSECTO_HURT("journey:turtleHurt"),
	SPIKED_BEAST("journey:spikedBeast"),
	SPIKED_BEAST_HURT("journey:spikedBeastHurt"),
	MAGMA_GIANT("journey:magmaGiant"),
	MAGMA_GIANT_HURT("journey:magmaGiantHurt"),
	SPYCLOPS("journey:spyclops"),
	SPYCLOPS_HURT("journey:spyclopsHurt"),
	TURTLE("journey:turtle"),
	TURTLE_HURT("journey:turtleHurt"),
	BUNNY("journey:bunny"),
	BUNNY_HURT("journey:bunnyHurt"),
	HONGO("journey:hongo"),
	SMALL_HONGO("journey:smallHongo"),
	SMALL_HONGO_HURT("journey:smallHongoHurt"),
	HONGO_HURT("journey:hongoHurt"),
	REAPER("journey:reaper"),
	REAPER_HURT("journey:reaperHurt"),
	SAND_CRAWLER("journey:sandCrawler"),
	DEPTHS_HUNTER("journey:depthsHunter"),
	DEPTHS_HUNTER_HURT("journey:depthsHunterHurt"),
	NETHER_BEAST("journey:netherBeast"),
	NETHER_BEAST_HURT("journey:netherBeastHurt"),
	CALCIA("journey:calcia"),
	CALCIA_HURT("journey:calciaHurt"),
	IRON_GOLEM_HURT("mob.irongolem.hit"),
	IRON_GOLEM_DEATH("mob.irongolem.death"),
	BLAZE("mob.blaze.breathe"),
	BLAZE_HURT("mob.blaze.hit"),
	BLAZE_DEATH("mob.blaze.death"),
	WITHER("mob.wither.idle"),
	WITHER_HURT("mob.wither.hurt"),
	WITHER_DEATH("mob.wither.death"),
	CREEPER("mob.creeper.say"),
	CREEPER_DEATH("mob.creeper.death"),
	STAFF("journey:staff"),
	WRAPPER("journey:wrapper"),
	PAGE_FLIP("journey:pageFlip"),
	SPARKLE("journey:magicSparkle"),
	CANNON("journey:cannon"),
	PLASMA("journey:plasma"),
	DYNASTER("journey:dynaster"),
	DYNASTER_HURT("journey:dynasterHurt"),
	DYNASTER_DEATH("journey:dynasterDeath"),
	SHIMMERER("journey:shimmerer"),
	SHIMMERER_HURT("journey:shimmererHurt"),
	SHIMMERER_DEATH("journey:shimmererDeath"),
	BUSH("journey:bush"),
	BUSH_DEATH("journey:bushDeath"),
	BUSH_HURT("journey:bushHurt"),
	WRAITH("journey:wraith"),
	WRAITH_DEATH("journey:wraithDeath"),
	WRAITH_HURT("journey:wraithHurt"),
	OVERSEER("journey:overseer"),
	OVERSEER_DEATH("journey:overseerDeath"),
	OVERSEER_HURT("journey:overeerHurt"),
	HAMMER("journey:hammer"),
	BIRD("journey:bird"),
	BIRD_HURT("journey:birdHurt"),
	BIRD_DEATH("journey:birdDeath"),
	CREAK("journey:gateCreak"),
	UNLOCK("journey:unlock"),
	EMPTY("");

	private String sound;
	private static int size;
	public static ArrayList<String> sounds = new ArrayList<String>();
	
	
	private EnumSounds(String sound) {
		this.sound = sound;
		addSounds(sound);
	}
	
	public void addSounds(String s) {
		//sounds.add(s);
	}

	public String getPrefixedName() {
		return sound;
	}

	public String getNonPrefixedName() {
		return sound;
	}

	public static void init() {
		size = SoundEvent.REGISTRY.getKeys().size();
		for(String s : sounds) {
			register(s);
		}
	}

	public static SoundEvent register(String name) {
		ResourceLocation loc = new ResourceLocation(name);
		SoundEvent e = new SoundEvent(loc);

		SoundEvent.REGISTRY.register(size, loc, e);
		size++;

		return e;
	}
}