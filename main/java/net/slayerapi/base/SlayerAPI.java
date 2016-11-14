package net.slayerapi.base;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.journey.Journey;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class SlayerAPI {

	public static int mobID = Config.baseMobID, projectileID = Config.baseProjectileID, entityListID = Config.baseEntityListID;
	public static Logger logger = Logger.getLogger(SlayerAPI.MOD_ID);

	public static final String 
			MOD_NAME = "Journey into the Light", 
			MOD_ID = "journey", 
			PREFIX = MOD_ID + ":", 
			MOD_VERSION = "1.0"; 
	public static final boolean DEVMODE = true, BETA = false;

	public static void addRecipe(ItemStack i, Object... o) {
		GameRegistry.addRecipe(i, o);
	}

	public static void addBucket(Fluid fluid, ItemStack modBucket) {
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(fluid.getName(), FluidContainerRegistry.BUCKET_VOLUME), modBucket, new ItemStack(Items.BUCKET)));
	}

	public static void registerEvent(Object o) {
		MinecraftForge.EVENT_BUS.register(o);
		FMLCommonHandler.instance().bus().register(o);
	}

	public static void registerMob(Class entityClass, String name, String finalN, int base, int fore) {
		LangRegistry.addMob(name, finalN);
		EntityRegistry.registerModEntity(entityClass, name, mobID++, Journey.instance, 128, 5, true, base, fore);
	}
	
	public static void registerEndMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x440089, 0xBC00BC);
	}
	
	public static void registerOverworldMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x7c4c2c, 0x26b530);
	}
	
	public static void registerNetherMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0xff0000, 0xffd800);
	}
	
	public static void registerPets(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x64ffe4, 0x009cff);
	}
	
	public static void registerBPMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0xff7800, 0xffa800);
	}
	
	public static void registerFLMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x00d8ff, 0xd8f9ff);
	}
	
	public static void registerEucaMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0xffba00, 0xe0e0e0);
	}
	
	public static void registerDepthsMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x003CA5, 0x0098A3);
	}
	
	public static void registerCorbaMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x1e8c00, 0x36ff00);
	}
	
	public static void registerCloudiaMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0xa87abd, 0x9000ff);
	}
	
	public static void registerTerraniaMob(Class entityClass, String entityName, String finalN) {
		registerMob(entityClass, entityName, finalN, 0x7813ff, 0xff58f5);
		
	}
	
	public static void registerNPC(Class entityClass, String entityName, String finalN) {
		LangRegistry.addMob(entityName, finalN);
		EntityRegistry.registerModEntity(entityClass, entityName, mobID++, Journey.instance, 128, 5, true, 0x00FF8C, 0x00F6FF);
	}
	
	public static void registerEntity(Class entityClass, String entityName, int ID) {
		EntityRegistry.registerModEntity(entityClass, entityName, ID, Journey.instance, 120, 5, true);
	}

	public static void registerBossMob(Class entityClass, String entityName, String finalN) {
		LangRegistry.addMob(entityName, finalN);
		EntityRegistry.registerModEntity(entityClass, entityName, mobID++, Journey.instance, 128, 5, true, 0x000000, 0x9B0000);
	}

	public static void registerProjectile(Class entityClass, String entityName) {
		EntityRegistry.registerModEntity(entityClass, entityName + " Projectile", projectileID, Journey.instance, 250, 5, true);
		projectileID++;
	}

    public static ArmorMaterial addArmorMaterial(String name, int durability, int[] oldArmor, int enchantability) {
        int duraNew = (int) Math.round(durability / 13.75);
        return EnumHelper.addArmorMaterial(name, name, duraNew, oldArmor, enchantability, null, duraNew);
    }

    @SideOnly(Side.CLIENT)
	public static void addChatMessageWithColour(EntityPlayer p, TextFormatting colour, String str) {
    	TextComponentString chat = new TextComponentString(
				SlayerAPI.Colour.YELLOW + "[" + 
				SlayerAPI.Colour.GOLD + MOD_NAME + 
				SlayerAPI.Colour.YELLOW + "] " + str);
		chat.getStyle().setColor(colour);
		p.addChatMessage(chat);
	}

	@SideOnly(Side.CLIENT)
	public static void addChatMessage(EntityPlayer p, String str) {
		TextComponentString ret = new TextComponentString(str);
		p.addChatMessage(ret);
	}
	
	@SideOnly(Side.CLIENT)
	public static void addFormattedChatMessage(EntityPlayer p, String str) {
		TextComponentString ret = new TextComponentString(I18n.format(str, new Object[0]));
		p.addChatMessage(ret);
	}

	private static final String	SECTION_SIGN	= "\u00a7";

	public static final class Colour {
		public static final String	BLACK		= SECTION_SIGN + "0";
		public static final String	DARK_BLUE	= SECTION_SIGN + "1";
		public static final String	DARK_GREEN	= SECTION_SIGN + "2";
		public static final String	DARK_AQUA	= SECTION_SIGN + "3";
		public static final String	DARK_RED	= SECTION_SIGN + "4";
		public static final String	PURPLE		= SECTION_SIGN + "5";
		public static final String	GOLD		= SECTION_SIGN + "6";
		public static final String	GRAY		= SECTION_SIGN + "7";
		public static final String	DARK_GRAY	= SECTION_SIGN + "8";
		public static final String	BLUE		= SECTION_SIGN + "9";
		public static final String	GREEN		= SECTION_SIGN + "A";
		public static final String	AQUA		= SECTION_SIGN + "B";
		public static final String	RED			= SECTION_SIGN + "C";
		public static final String	LIGHT_PURPLE= SECTION_SIGN + "D";
		public static final String	YELLOW		= SECTION_SIGN + "E";
		public static final String	WHITE		= SECTION_SIGN + "F";
	}

	public static final class Format {
		public static final String	OBFUSCATED	= SECTION_SIGN + "k";
		public static final String	BOLD		= SECTION_SIGN + "l";
		public static final String	STRIKE		= SECTION_SIGN + "m";
		public static final String	UNDERLINE	= SECTION_SIGN + "n";
		public static final String	ITALIC		= SECTION_SIGN + "o";
		public static final String	RESET		= SECTION_SIGN + "r";
	}

	
	/**
	 * Not used in 1.8
	 */
	/**
	@Deprecated
	public static void registerItemRenderer(Item i, ItemRenderer ir) {
		MinecraftForgeClient.registerItemRenderer(i, ir);
	}

	/**
	 * Not used in 1.8
	 */
	/**
	@Deprecated
	public static void registerItemRenderer(Block b, ItemRenderer ir) {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(b), ir);
	}
	*/

	@SideOnly(Side.CLIENT)
	public static void sendMessageToAll(String message, boolean showMod) {
		if(showMod) FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.DARK_AQUA + "[" + SlayerAPI.Colour.DARK_GREEN + MOD_NAME + SlayerAPI.Colour.DARK_AQUA + "] " + SlayerAPI.Colour.GREEN + message));
		else FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.GREEN + message));
	}

	@SideOnly(Side.CLIENT)
	public static void sendContinuedMessageToAll(String message) {
		FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(SlayerAPI.Colour.GREEN + message));
	}

	public static void removeCraftingRecipe(Item removed) {
		ItemStack recipeResult = null;
		ArrayList recipes = (ArrayList)CraftingManager.getInstance().getRecipeList();

		for (int i = 0; i < recipes.size(); i++) {
			IRecipe tmpRecipe = (IRecipe) recipes.get(i);

			if (tmpRecipe instanceof ShapedRecipes) {
				ShapedRecipes recipe = (ShapedRecipes) tmpRecipe;
				recipeResult = recipe.getRecipeOutput();
			}

			if (ItemStack.areItemStacksEqual(new ItemStack(removed), recipeResult)) {
				System.out.println("[" + MOD_NAME + "] Removed Recipe: " + recipes.get(i) + " -> " + recipeResult);
				recipes.remove(i);
			}
		}
	}

	public static void removeCraftingRecipe(Block removed) {
		ItemStack recipeResult = null;
		ArrayList recipes = (ArrayList)CraftingManager.getInstance().getRecipeList();

		for (int i = 0; i < recipes.size(); i++) {
			IRecipe tmpRecipe = (IRecipe) recipes.get(i);

			if (tmpRecipe instanceof ShapedRecipes) {
				ShapedRecipes recipe = (ShapedRecipes) tmpRecipe;
				recipeResult = recipe.getRecipeOutput();
			}

			if (ItemStack.areItemStacksEqual(new ItemStack(removed), recipeResult)) {
				System.out.println("[" + MOD_NAME + "] Removed Recipe: " + recipes.get(i) + " -> " + recipeResult);
				recipes.remove(i);
			}
		}
	}
	
	public static void removeCraftingRecipe(ItemStack removed) {
		ItemStack recipeResult = null;
		ArrayList recipes = (ArrayList)CraftingManager.getInstance().getRecipeList();

		for (int i = 0; i < recipes.size(); i++) {
			IRecipe tmpRecipe = (IRecipe) recipes.get(i);

			if (tmpRecipe instanceof ShapedRecipes) {
				ShapedRecipes recipe = (ShapedRecipes) tmpRecipe;
				recipeResult = recipe.getRecipeOutput();
			}

			if (ItemStack.areItemStacksEqual(removed, recipeResult)) {
				System.out.println("[" + MOD_NAME + "] Removed Recipe: " + recipes.get(i) + " -> " + recipeResult);
				recipes.remove(i);
			}
		}
	}
	
	public static void removeSmeltingRecipe(ItemStack removed) {
		FurnaceRecipes.instance().getSmeltingList().remove(removed);
	}

	public static Item toItem(Block block){
		return Item.getItemFromBlock(block);
	}
	
	public static void addBow(Item bow, String name) {
		SlayerAPI.registerModelBakery(bow, new ResourceLocation[] {new ResourceLocation(SlayerAPI.PREFIX + name), new ResourceLocation(SlayerAPI.PREFIX + name + "_0"), new ResourceLocation(SlayerAPI.PREFIX + name + "_1"), new ResourceLocation(SlayerAPI.PREFIX  + name + "_2")});
	}
	
	public static void addBowRender(Item bow, String name) {
		registerItemRender(bow, 0, name);
		registerItemRender(bow, 1, name + "_0");
		registerItemRender(bow, 2, name + "_1");
		registerItemRender(bow, 3, name + "_2");
	}

	public static void registerModelBakery(Item i, ResourceLocation[] names) {
		ModelBakery.registerItemVariants(i, names);
	}

	public static void registerModelBakery(Block b, ResourceLocation[] names) {
		ModelBakery.registerItemVariants(SlayerAPI.toItem(b), names);
	}

	public static void registerItemRender(Item item, int metadata, String itemName) {
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(item, metadata, new ModelResourceLocation(SlayerAPI.PREFIX + itemName, "inventory"));
	}

	public static void registerBlockRender(Block block, int metadata, String blockName) {
		registerItemRender(Item.getItemFromBlock(block), metadata, blockName);
	}

	public static void registerItemRender(Item item, String itemName) {
		registerItemRender(item, 0, itemName);
		
	}
}
