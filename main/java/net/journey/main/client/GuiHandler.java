package net.journey.main.client;

import net.journey.main.blocks.container.ContainerKnowledgeTable;
import net.journey.main.blocks.container.ContainerSummoningTable;
import net.journey.main.blocks.container.ContainerTrophy;
import net.journey.main.blocks.container.tileentity.TileEntityKnowledgeTable;
import net.journey.main.blocks.container.tileentity.TileEntitySummoningTable;
import net.journey.main.blocks.container.tileentity.TileEntityTrophyTable;
import net.journey.main.client.render.gui.GuiAlloyMender;
import net.journey.main.client.render.gui.GuiBlacksmith;
import net.journey.main.client.render.gui.GuiBoilTrader;
import net.journey.main.client.render.gui.GuiEscaped;
import net.journey.main.client.render.gui.GuiFrozenMerchant;
import net.journey.main.client.render.gui.GuiKnowledgeTable;
import net.journey.main.client.render.gui.GuiMage;
import net.journey.main.client.render.gui.GuiOvergrownMerchant;
import net.journey.main.client.render.gui.GuiRockite;
import net.journey.main.client.render.gui.GuiStaringGuardian;
import net.journey.main.client.render.gui.GuiStarlightBlacksmith;
import net.journey.main.client.render.gui.GuiStarlightVillager;
import net.journey.main.client.render.gui.GuiSummoningTable;
import net.journey.main.client.render.gui.GuiTerranian;
import net.journey.main.client.render.gui.GuiTordo;
import net.journey.main.client.render.gui.GuiTrophyTable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.slayerapi.base.entity.tileentity.container.ContainerModVillager;

public class GuiHandler implements IGuiHandler {

	public static int id = 0;
	public static int 

	mage = id++,
	blacksmith = id++,
	frozenMerchant = id++,
	rockite = id++,
	staringGuardian = id++,
	tordo = id++,
	boilTrader = id++,
	alloyMender = id++,
	starlightVillager = id++,
	starlightBlacksmith = id++,
	terranian = id++,
	terranianEnchanter = id++,
	overgrownMerchant = id++,
	escaped = id++,

	knowledge = id++,
	summoning = id++,
	trophy = id++;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		if(ID == mage) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == blacksmith) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == frozenMerchant) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == knowledge) 
			return new ContainerKnowledgeTable(player.inventory, (TileEntityKnowledgeTable)entity, world);
		if(ID == summoning) 
			return new ContainerSummoningTable(player.inventory, (TileEntitySummoningTable)entity, world);
		if(ID == trophy) 
			return new ContainerTrophy(player.inventory, (TileEntityTrophyTable)entity, world);
		if(ID == staringGuardian) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == tordo) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == boilTrader) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == alloyMender) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == starlightVillager) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == starlightBlacksmith) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == terranian) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == terranianEnchanter) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == overgrownMerchant) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == escaped) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == rockite) 
			return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		if(ID == mage) return new GuiMage(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == blacksmith) return new GuiBlacksmith(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == frozenMerchant) return new GuiFrozenMerchant(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == knowledge) return new GuiKnowledgeTable(player.inventory, (TileEntityKnowledgeTable)entity, world);
		if(ID == summoning) return new GuiSummoningTable(player.inventory, (TileEntitySummoningTable)entity, world);
		if(ID == trophy) return new GuiTrophyTable(player.inventory, (TileEntityTrophyTable)entity, world);
		if(ID == staringGuardian) return new GuiStaringGuardian(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == tordo) return new GuiTordo(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == boilTrader) return new GuiBoilTrader(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == alloyMender) return new GuiAlloyMender(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == starlightVillager) return new GuiStarlightVillager(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == starlightBlacksmith) return new GuiStarlightBlacksmith(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == terranian) return new GuiTerranian(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == terranianEnchanter) return new GuiTerranian(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == overgrownMerchant) return new GuiOvergrownMerchant(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == escaped) return new GuiEscaped(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == rockite) return new GuiRockite(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		return null;
	}

	private Entity getEntityByID(int entityID, World world) {
		for(int i = 0; i < world.loadedEntityList.size(); i++) {
			if(((Entity)world.loadedEntityList.get(i)).getEntityId() == entityID) {
				return ((Entity)world.loadedEntityList.get(i));
			}
		}
		return null;
	}
}