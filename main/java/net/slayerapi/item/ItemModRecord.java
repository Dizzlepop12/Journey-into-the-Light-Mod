package net.slayerapi.item;

import java.util.List;
import java.util.Map;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangHelper;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.LangRegistry;
import net.slayerapi.base.SlayerAPI;

import com.google.common.collect.Maps;

public class ItemModRecord extends ItemRecord {

    private static final Map RECORDS = Maps.newHashMap();
    private String recordName;
    
	public ItemModRecord(String name, String finalName, SoundEvent sound) {
		super(name, sound);
		setUnlocalizedName(name + "Record");
		LangRegistry.addItem(name + "Record", finalName);
		setCreativeTab(JourneyTabs.util);
		JourneyItems.itemNames.add(name + "Record");
		this.recordName = name;
        this.maxStackSize = 1;
		RECORDS.put(name, this);
		GameRegistry.registerItem(this, name + "Record");
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if(iblockstate.getBlock() == Blocks.JUKEBOX && !((Boolean)iblockstate.getValue(BlockJukebox.HAS_RECORD)).booleanValue()) {
            if (!worldIn.isRemote) {
                ((BlockJukebox)Blocks.JUKEBOX).insertRecord(worldIn, pos, iblockstate, stack);
                worldIn.playEvent((EntityPlayer)null, 1010, pos, Item.getIdFromItem(this));
                --stack.stackSize;
                playerIn.addStat(StatList.RECORD_PLAYED);
            }
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.PASS;
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List list, boolean advanced) {
        list.add(this.getRecordNameLocal());
    }

    @SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return LangHelper.getFormattedText("item.record." + this.recordName + ".desc");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @SideOnly(Side.CLIENT)
    public static ItemModRecord getRecord(String name) {
        return (ItemModRecord)RECORDS.get(name);
    }
	
    @Override
	public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(SlayerAPI.PREFIX + "music." + name);
    }
}