package com.sparta.is.item;

import com.sparta.is.core.creativetab.CreativeTab;
import com.sparta.is.core.entity.EntityIS;
import com.sparta.is.entity.EntityTabane;
import com.sparta.is.core.reference.Names;
import com.sparta.is.core.reference.Reference;
import com.sparta.is.core.utils.helpers.ResourceLocationHelper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemTabaneSpawnEgg extends ItemEgg
{
    protected int colorBase = 0x000000;
    protected int colorSpots = 0xFFFFFF;
    protected String entityToSpawnName = "";
    protected String entityToSpawnNameFull = "";
    protected EntityTabane entityToSpawn = null;
    private static ResourceLocation resourceLocation;

    public ItemTabaneSpawnEgg()
    {
        super();
    }

    public ItemTabaneSpawnEgg(int parPrimaryColor, int parSecondaryColor)
    {
        super();
        super.setFull3D();
        this.setHasSubtypes(false);
        this.setRegistryName(Names.Items.TABANE_SPAWN_EGG);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTab.IS_TAB);
        this.setEntityToSpawnName(Names.Villagers.TABANE);
        this.colorBase = parPrimaryColor;
        this.colorSpots = parSecondaryColor;

        resourceLocation = ResourceLocationHelper.getResourceLocation(entityToSpawnName);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer par2EntityPlayer, World par3World, BlockPos blockPos, EnumHand hand, EnumFacing facing, float par8, float par9, float par10)
    {
        ItemStack itemStack = par2EntityPlayer.getHeldItem(hand);

        if (par3World.isRemote)
        {
            return EnumActionResult.PASS;
        }
        else
        {
            Entity entity = spawnEntity(par3World, blockPos.getX() + 0.5D, blockPos.getY() + 1.5D, blockPos.getZ() + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityIS && itemStack.hasDisplayName())
                {
                    ((EntityIS)entity).setCustomNameTag(itemStack.getDisplayName());
                }

                if (!par2EntityPlayer.capabilities.isCreativeMode)
                {
                    itemStack.setCount(itemStack.getCount() - 1);
                }
            }

            return EnumActionResult.PASS;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed.
     *Args: itemStack, world, entityPlayer
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand hand)
    {
        ItemStack itemStack = entityPlayer.getHeldItem(hand);

        if (world.isRemote)
        {
            return new ActionResult(EnumActionResult.FAIL, itemStack);
        }
        else
        {
            RayTraceResult result = rayTrace(world, entityPlayer, true);

            if (result == null)
            {
                return new ActionResult(EnumActionResult.FAIL, itemStack);
            }
            else
            {
                if (result.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    if (!world.canMineBlockBody(entityPlayer, result.getBlockPos()))
                    {
                        return new ActionResult(EnumActionResult.FAIL, itemStack);
                    }

                    if (!entityPlayer.canPlayerEdit(result.getBlockPos(), result.sideHit, itemStack))
                    {
                        return new ActionResult(EnumActionResult.FAIL, itemStack);
                    }

                    if (world.getBlockState(result.getBlockPos()) instanceof BlockLiquid )
                    {
                        Entity entity = spawnEntity(world, result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ());

                        if (entity != null)
                        {
                            if (entity instanceof EntityIS && itemStack.hasDisplayName())
                            {
                                ((EntityIS)entity).setCustomNameTag(itemStack.getDisplayName());
                            }

                            if (!entityPlayer.capabilities.isCreativeMode)
                            {
                                itemStack.setCount(itemStack.getCount() - 1);
                            }
                        }
                    }
                }

                return new ActionResult(EnumActionResult.SUCCESS, itemStack);
            }
        }
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by
     * the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public Entity spawnEntity(World parWorld, double parX, double parY, double parZ)
    {
        BlockPos pos = new BlockPos(parX, parY, parZ);
        if (!parWorld.isRemote) // never spawn entity on client side
        {
            entityToSpawn = (EntityTabane) EntityList.createEntityByIDFromName(resourceLocation, parWorld);

            entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
            parWorld.spawnEntity(entityToSpawn);

            if(entityToSpawn instanceof EntityLiving)
            {
                entityToSpawn.onInitialSpawn(parWorld.getDifficultyForLocation(pos), null);
            }
        }

        return entityToSpawn;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int parColorType)
    {
        return (parColorType == 0) ? colorBase : colorSpots;
    }

    public void setColors(int parColorBase, int parColorSpots)
    {
        colorBase = parColorBase;
        colorSpots = parColorSpots;
    }

    public int getColorBase()
    {
        return colorBase;
    }

    public int getColorSpots()
    {
        return colorSpots;
    }

    public void setEntityToSpawnName(String parEntityToSpawnName)
    {
        entityToSpawnName = parEntityToSpawnName;
        entityToSpawnNameFull = Reference.MOD_ID + "." + entityToSpawnName;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s:%s", Reference.MOD_ID, Names.Items.TABANE_SPAWN_EGG);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s:%s", Reference.MOD_ID, Names.Items.TABANE_SPAWN_EGG);
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void addInformation(ItemStack itemStack, World worldIn, List<String> toolTip, ITooltipFlag ttFlag)
    {
        toolTip.add("Creator of the Infinite Stratos System.");
        toolTip.add("Spawn at your own risk.");
    }
}
