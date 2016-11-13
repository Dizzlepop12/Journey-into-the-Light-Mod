package net.journey.main.util;

import net.journey.main.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.slayerapi.base.SlayerAPI;

public enum EssenceToolMaterial {

	BLOODCRUST_SWORD(JourneyItems.bloodcrust, JourneyItems.bloodcrustIngot),
	FLAIRIUM_SWORD(JourneyItems.flairiumSwordMat, JourneyItems.flairiumIngot),
	LUNIUM_SWORD(JourneyItems.luniumSwordMat, JourneyItems.luniumIngot),
	CELESTIUM_SWORD(JourneyItems.celestiumSwordMat, JourneyItems.celestiumIngot),
	SHADIUM_SWORD(JourneyItems.shadiumSwordMat, JourneyItems.shadiumIngot),
	SAPPHIRE_SWORD(JourneyItems.sapphiretoolSwordMat, JourneyItems.sapphire),
	ORBADITE_SWORD(JourneyItems.orbaditeSwordMat, JourneyItems.orbaditeIngot),
	GORBITE_SWORD(JourneyItems.gorbiteSwordMat, JourneyItems.gorbiteGem),
	DES_SWORD(JourneyItems.desSwordMat, JourneyItems.desIngot),
	
	BLOODCRUST(JourneyItems.bloodcrust, JourneyItems.bloodcrustIngot, 12),
	FLAIRIUM(JourneyItems.flairium, JourneyItems.flairiumIngot, 16),
	LUNIUM(JourneyItems.lunium, JourneyItems.luniumIngot, 10),
	CELESTIUM(JourneyItems.celestium, JourneyItems.celestiumIngot, 14),
	SHADIUM(JourneyItems.shadium, JourneyItems.shadiumIngot, 10),
	SAPPHIRE(JourneyItems.sapphiretool, JourneyItems.sapphire, 8),
	ORBADITE(JourneyItems.orbadite, JourneyItems.orbaditeIngot, 148),
	GORBITE(JourneyItems.gorbite, JourneyItems.gorbiteGem, 148),
	DES(JourneyItems.des, JourneyItems.desIngot, 26),
	KORITE(JourneyItems.korite, JourneyItems.koriteIngot, 14),
	
	NETHER_BEAST_SWORD(JourneyItems.addToolMaterial(3000, 16, 25, true)),
	WITHERING_BEAST_SWORD(JourneyItems.addToolMaterial(3000, 16, 25, true)),
	CALCIA_SWORD(JourneyItems.addToolMaterial(3000, 13, 25, true)),
	CHAMPIONS_SWORD(JourneyItems.addToolMaterial(3000, 14, 25, true)),
	THE_WRAITH(JourneyItems.addToolMaterial(3000, 17, 25, true)),
	BUBBLE_SWORD(JourneyItems.addToolMaterial(3000, 23, 25, true)),

	POISON_SWORD(JourneyItems.addToolMaterial(1500, 10, 25, true)),
	CLOUD_SLICER(JourneyItems.addToolMaterial(1500, 12, 25, true)),
	DRAGONS_TOOTH(JourneyItems.addToolMaterial(1500, 13, 25, true)),
	BOILING_BLADE(JourneyItems.addToolMaterial(3000, 14, 25, true)),
	MOLTEN_KNIFE(JourneyItems.addToolMaterial(3000, 10, 25, true)),
	LOGGERS_SWORD(JourneyItems.addToolMaterial(3000, 30, 25, true)),
	NATURES_BLADE(JourneyItems.addToolMaterial(3000, 34, 25, true)),
	DEPTHS_DARKSWORD(JourneyItems.addToolMaterial(3000, 28, 25, true)),
	DEPTHS_SLAYER(JourneyItems.addToolMaterial(3000, 22, 25, true)),
	SNOW_SHOVELER(JourneyItems.addToolMaterial(3000, 15, 25, true)),
	FROSTBITTEN_SWORD(JourneyItems.addToolMaterial(3000, 15, 25, true)),
	FROSTY_SWORD(JourneyItems.addToolMaterial(3000, 13, 25, true)),
	TREE_HUGGER(JourneyItems.addToolMaterial(3000, 32, 25, true)),
	HEALERS_BLADE(JourneyItems.addToolMaterial(3000, 15, 25, true)),
	CORE_MENDER(JourneyItems.addToolMaterial(3000, 26, 25, true)),
	ROYAL_BLADE(JourneyItems.addToolMaterial(3000, 18, 25, true)),
	ROYAL_STABBER(JourneyItems.addToolMaterial(3000, 21, 25, true)),
	ROC_SWORD(JourneyItems.addToolMaterial(3000, 29, 25, true)),
	SWORD_THUNDERBIRD(JourneyItems.addToolMaterial(3000, 32, 25, true)),
	BLOODWIELD_SWORD(JourneyItems.addToolMaterial(3000, 14, 25, true)),
	CHARRED_BLADE(JourneyItems.addToolMaterial(3000, 18, 25, true)),
	SIZZLER_SWORD(JourneyItems.addToolMaterial(3000, 22, 25, true)),
	FLUFFY_BLADE(JourneyItems.addToolMaterial(3000, 40, 25, true)),
	GOLEM_SWORD(JourneyItems.addToolMaterial(3000, 38, 25, true)),
	THUNDERBLADE(JourneyItems.addToolMaterial(3000, 35, 25, true)),
	SENTRY_SWORD(JourneyItems.addToolMaterial(3000, 35, 25, true)),
	CRYSTAL_BLADE(JourneyItems.addToolMaterial(1690, 9, 25, true)),
	STARLIGHT_BLADE(JourneyItems.addToolMaterial(3000, 38, 25, true)),
	KORITE_SWORD(JourneyItems.addToolMaterial(3000, 12, 25, true)),
	PEDAL_SWORD(JourneyItems.addToolMaterial(100, 5.5F, 25, true)),
	RE_CRYSTAL_SWORD(JourneyItems.addToolMaterial(512, 9.5F, 25, true)),
	RE_STONE_SWORD(JourneyItems.addToolMaterial(256, 6.5F, 25, true)),
	WITHIC_BLADE(JourneyItems.addToolMaterial(70, 12, 25, true)),
	TERRALIGHT_BLADE(JourneyItems.addToolMaterial(3500, 35, 25, true)),
	TERRANA_SWORD(JourneyItems.addToolMaterial(3400, 34, 25, true)),
	TERROLICA_SWORD(JourneyItems.addToolMaterial(3500, 36, 25, true)),
	VOLITE_SWORD(JourneyItems.addToolMaterial(3500, 35, 25, true)),
	TERRONIC_BLADE(JourneyItems.addToolMaterial(1000, 34, 25, true)),
	KINGS_SWORD(JourneyItems.addToolMaterial(1500, 20, 25, true)),
	VINESTRAND_BLADE(JourneyItems.addToolMaterial(2000, 30, 25, true)),
	DARK_PINE_SWORD(JourneyItems.addToolMaterial(2000, 31, 25, true)),
	DEMONIC_SWORD(JourneyItems.addToolMaterial(850, 5.5F, 25, true)),
	
	CREATIVE(JourneyItems.addToolMaterial(1000, 39, 25, true)),
	EARTHEN_HAMMER(JourneyItems.addToolMaterial(1, 7, 25, true)),
	FLAMING_HAMMER(JourneyItems.addToolMaterial(1000, 10, 25, true)),
	NETHIC_HAMMER(JourneyItems.addToolMaterial(1000, 8, 25, true)),
	WITHIC_HAMMER(JourneyItems.addToolMaterial(1000, 9, 25, true)),
	ROYAL_HAMMER(JourneyItems.addToolMaterial(1000, 11, 25, true)),
	OVERGROWN_HAMMER(JourneyItems.addToolMaterial(1000, 16, 25, true)),
	ROCKY_HAMMER(JourneyItems.addToolMaterial(2230, 8, 25, true)),
	CRYSTAL_HAMMER(JourneyItems.addToolMaterial(3320, 9, 25, true)),
	
	DEVELOPER_SWORD(JourneyItems.addToolMaterial(3000, 9000, 25, true)),
	
	BLOODCRUST_MULTI_TOOL(JourneyItems.bloodcrustMulti, JourneyItems.bloodcrustIngot, 3),
	FLAIRIUM_MULTI_TOOL(JourneyItems.flairiumMulti, JourneyItems.flairiumIngot, 3),
	LUNIUM_MULTI_TOOL(JourneyItems.luniumMulti, JourneyItems.luniumIngot, 3),
	CELESTIUM_MULTI_TOOL(JourneyItems.celestiumMulti, JourneyItems.celestiumIngot, 3),
	SHADIUM_MULTI_TOOL(JourneyItems.shadiumMulti, JourneyItems.shadiumIngot, 3),
	SAPPHIRE_MULTI_TOOL(JourneyItems.sapphireMulti, JourneyItems.sapphire, 2),
	ORBADITE_MULTI_TOOL(JourneyItems.orbaditeMulti, JourneyItems.orbaditeIngot, 3),
	GORBITE_MULTI_TOOL(JourneyItems.gorbiteMulti, JourneyItems.gorbiteGem, 3),
	DES_MULTI_TOOL(JourneyItems.desMulti, JourneyItems.desIngot, 3),
	KORITE_MULTI_TOOL(JourneyItems.koriteMulti, JourneyItems.koriteIngot, 3),
	SMELTING_TOOL(JourneyItems.smeltingMulti, null, 3),
	WOOD_MULTI_TOOL(JourneyItems.woodMulti, SlayerAPI.toItem(Blocks.PLANKS), 1),
	STONE_MULTI_TOOL(JourneyItems.stoneMulti, SlayerAPI.toItem(Blocks.COBBLESTONE), 2),
	IRON_MULTI_TOOL(JourneyItems.ironMulti, Items.IRON_INGOT, 3),
	GOLD_MULTI_TOOL(JourneyItems.goldMulti, Items.GOLD_INGOT, 3),
	DIAMOND_MULTI_TOOL(JourneyItems.diamondMulti, Items.DIAMOND, 3),

	CRYSTAL_BATTLEAXE(JourneyItems.addToolMaterial(3600, 13, 17, true)),
	ROCKY_BATTLEAXE(JourneyItems.addToolMaterial(2600, 13, 16, true)),
	BACK_BITER(JourneyItems.addToolMaterial(1300, 13, 15, true)),
	DAWN_BREAKER(JourneyItems.addToolMaterial(1300, 13, 10, true)),
	TEMPEST_BATTLEAXE(JourneyItems.addToolMaterial(1300, 13, 13, true)),
	BRONZED_BATTLEAXE(JourneyItems.addToolMaterial(1300, 13, 16, true)),
	STORUM_BATTLEAXE(JourneyItems.addToolMaterial(1300, 13, 17, true)),
	CELESTITE_BATTLEAXE(JourneyItems.addToolMaterial(1300, 13, 18, true)),
	CELEKIUM_BATTLEAXE(JourneyItems.addToolMaterial(1300, 13, 19, true)),
	THUNDERBIRD_BATTLEAXE(JourneyItems.addToolMaterial( 1300, 13, 27, true));

	private ToolMaterial toolMaterial;
	private Item repairItem;
	private int harvestLevel = 0;

	private EssenceToolMaterial(ToolMaterial toolMaterial, Item repair) {
		this.toolMaterial = toolMaterial;
		this.repairItem = repair;
		harvestLevel = 0;
	}
	
	private EssenceToolMaterial(ToolMaterial toolMaterial, Item repair, int level) {
		this.toolMaterial = toolMaterial;
		this.repairItem = repair;
		this.harvestLevel = level;
	}

	private EssenceToolMaterial(ToolMaterial toolMaterial) {
		this(toolMaterial, null);
	}

	public int getHarvestLevel(){
		return harvestLevel;
	}
	
	public Item getRepairItem(){
		return repairItem;
	}

	public ToolMaterial getToolMaterial() {
		return toolMaterial;
	}
}