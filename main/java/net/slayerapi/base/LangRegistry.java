package net.slayerapi.base;

import java.awt.Window;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.journey.util.EnumArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.slayerapi.item.ItemModArmor;
import scala.Function;
import scala.tools.nsc.transform.patmat.ScalaLogic.TreesAndTypesDomain.Var;

public class LangRegistry {

	public static LangRegistry instance = new LangRegistry();
	
	public String location = System.getProperty("user.home") + "/Desktop/";
	private BufferedWriter writer;

	public static ArrayList<String> 
		blockUnloc = new ArrayList<String>(), 
		blockFinal = new ArrayList<String>(), 
		itemUnloc = new ArrayList<String>(), 
		itemFinal = new ArrayList<String>(), 
		fileText = new ArrayList<String>();
	
	public static ArrayList<String> 
		mobUnloc = new ArrayList<String>(), 
		mobFinal = new ArrayList<String>(),  
		armourUnloc = new ArrayList<String>(), 
		armorType = new ArrayList<String>(), 
		armorPiece = new ArrayList<String>();

	public LangRegistry() {
		File en_US = new File(location + "en_US.lang");
		try {
			en_US.mkdirs();
			if(en_US.exists()) en_US.delete();
			en_US.createNewFile();
			writer = new BufferedWriter(new FileWriter(en_US));
			//Desktop.getDesktop().open(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void creativeTabs() {

	}
	
	public void achievements() {

	}
	
	public void achievementDescription() {

	}
	
	public void misc() {

	}

	public void register() {
		block();
		item();
		mob();
		armour();
		creativeTabs();
		misc();
		achievements();
		achievementDescription();
		closeFile();
	}

	public static void addBlock(String unloc, String finalName) {
		blockUnloc.add(unloc);
		blockFinal.add(finalName);
	}

	public static void addItem(String unloc, String finalName) {
		itemUnloc.add(unloc);
		itemFinal.add(finalName);
	}

	public static void addMob(String unloc, String finalName) {
		mobUnloc.add("journey." + unloc);
		mobFinal.add(finalName);
	}

	public static void addArmour(ItemModArmor a, EnumArmor ar, EntityEquipmentSlot t) {
		armourUnloc.add(a.getUnlocalizedName());
		EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;
		armorType.add(ar.getFinalName());
		String part = t == HEAD ? "Helmet" : t == BODY ? "Chestplate" : t == LEGS ? "Leggings" : t == BOOTS ? "Boots" : "UNKNOWN";
		armorPiece.add(part);
	}

	public void block() {
		for(int i = 0; i < blockUnloc.size(); i++)
			writeToFile("tile." + blockUnloc.get(i) + ".name=" + blockFinal.get(i));
	}

	public void mob() {
		for(int i = 0; i < mobUnloc.size(); i++)
			writeToFile("entity." + mobUnloc.get(i) + ".name=" + mobFinal.get(i));
	}

	public void item() {
		for(int i = 0; i < itemUnloc.size(); i++)
			writeToFile("item." + itemUnloc.get(i) + ".name=" + itemFinal.get(i));
	}
	
	public void armour() {
		for(int i = 0; i < armourUnloc.size(); i++)
			writeToFile(armourUnloc.get(i) + ".name=" + armorType.get(i) + " " + armorPiece.get(i));
	}

	private void writeToFile(String s) {
		try {
			instance.writer.write(s + "\n");
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	private void closeFile() {
		try {
			instance.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}