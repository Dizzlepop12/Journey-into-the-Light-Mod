package net.slayerapi.item;

import java.util.List;
import java.util.Set;

import net.journey.main.JourneyItems;
import net.journey.main.JourneyTabs;
import net.journey.main.util.EssenceToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayerapi.base.LangRegistry;

import com.google.common.collect.Sets;

public class ItemMultiTool extends ItemTool {

	private static final Set<Block> blocksEffectiveAgainst = Sets.newHashSet(Block.REGISTRY);
	protected EssenceToolMaterial mat;

    public ItemMultiTool(String name, String f, EssenceToolMaterial tool, int damage) {
        super(tool.getToolMaterial(), blocksEffectiveAgainst);
        LangRegistry.addItem(name, f);
		mat = tool;
		this.setMaxDamage(damage);
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.tools);
        setHarvestLevel("pickaxe", tool.getHarvestLevel());
        JourneyItems.itemNames.add(name);
        GameRegistry.registerItem(this, name);
	}

	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}
	
	@Override
	public boolean isItemTool(ItemStack i) {
		return true;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState b, ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return isEfficient(blockIn.getBlock());
	}
	
    public float getCorrectEfficiency(ItemStack is, Block b) {
        return this.toolMaterial.getEfficiencyOnProperMaterial();
    }

	protected boolean isEfficient(Block block) {
		return block == Blocks.OBSIDIAN ? this.toolMaterial.getHarvestLevel() == 3 :
			(block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE ?
			(block != Blocks.EMERALD_BLOCK && block != Blocks.EMERALD_ORE ?
			(block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE ?
			(block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE ?
			(block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE ?
			(block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE ?
			(block.getMaterial(block.getDefaultState()) == Material.ROCK ? true :
			(block.getMaterial(block.getDefaultState()) == Material.IRON ? true :
			block.getMaterial(block.getDefaultState()) == Material.ANVIL)) :
			this.toolMaterial.getHarvestLevel() >= 2) :
			this.toolMaterial.getHarvestLevel() >= 1) :
			this.toolMaterial.getHarvestLevel() >= 1) :
			this.toolMaterial.getHarvestLevel() >= 2) :
			this.toolMaterial.getHarvestLevel() >= 2) :
			this.toolMaterial.getHarvestLevel() >= 2);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if(facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
                if(block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
                    this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if(block == Blocks.DIRT) {
                    switch((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(stack, playerIn, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(stack, playerIn, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }
	
	protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state) {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if(!worldIn.isRemote) {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " Uses Remaining");
	}
}
