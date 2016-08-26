package com.sparta.is.item;

import com.sparta.is.creativetab.CreativeTabIS;
import com.sparta.is.entity.EntityIS;
import com.sparta.is.reference.Names;
import com.sparta.is.reference.Reference;
import com.sparta.is.reference.Textures;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemTabaneSpawnEgg extends ItemEgg
{
//    @SideOnly(Side.CLIENT)
    protected int colorBase = 0x000000;
    protected int colorSpots = 0xFFFFFF;
    protected String entityToSpawnName = "";
    protected String entityToSpawnNameFull = "";
    protected EntityIS entityToSpawn = null;

    public ItemTabaneSpawnEgg()
    {
        super();
    }

    public ItemTabaneSpawnEgg(int parPrimaryColor, int parSecondaryColor)
    {
        this.setHasSubtypes(false);
        this.setUnlocalizedName(Names.Items.TABANE_SPAWN_EGG);
//        this.setTextureName(Names.Items.TABANE_SPAWN_EGG);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabIS.IS_TAB);
        this.setEntityToSpawnName(Names.Villagers.TABANE);
        this.colorBase = parPrimaryColor;
        this.colorSpots = parSecondaryColor;
        // DEBUG
        System.out.println("Spawn egg constructor for " + entityToSpawnName);
    }

//    @Override
//    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos blockPos, int par7, float par8, float par9, float par10)
//    {
//        if (par3World.isRemote)
//        {
//            return true;
//        }
//        else
//        {
//            Block block = par3World.getBlockState(blockPos).getBlock();
//            par4 += block.;
//            par5 += Facing.offsetsYForSide[par7];
//            par6 += Facing.offsetsZForSide[par7];
//            double d0 = 0.0D;
//
//            if (par7 == 1 && block.getRenderType() == 11)
//            {
//                d0 = 0.5D;
//            }
//
//            Entity entity = spawnEntity(par3World, par4 + 0.5D, par5 + d0, par6 + 0.5D);
//
//            if (entity != null)
//            {
//                if (entity instanceof EntityIS && par1ItemStack.hasDisplayName())
//                {
//                    ((EntityIS)entity).setCustomNameTag(par1ItemStack.getDisplayName());
//                }
//
//                if (!par2EntityPlayer.capabilities.isCreativeMode)
//                {
//                    --par1ItemStack.stackSize;
//                }
//            }
//
//            return true;
//        }
//    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed.
     *Args: itemStack, world, entityPlayer
     */
//    @Override
//    public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand)
//    {
//        if (par2World.isRemote)
//        {
//            return new ActionResult(EnumActionResult.FAIL, par1ItemStack);
//        }
//        else
//        {
//            MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
//
//            if (movingobjectposition == null)
//            {
//                return new ActionResult(EnumActionResult.FAIL, par1ItemStack);
//            }
//            else
//            {
//                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
//                {
////                    int i = movingobjectposition.getBlockPos().getX();
////                    int j = movingobjectposition.getBlockPos().getY();
////                    int k = movingobjectposition.getBlockPos().getZ();
//
//                    if (!par2World.canMineBlockBody(par3EntityPlayer, movingobjectposition.getBlockPos()))
//                    {
//                        return new ActionResult(EnumActionResult.FAIL, par1ItemStack);
//                    }
//
//                    if (!par3EntityPlayer.canPlayerEdit(movingobjectposition.getBlockPos(), movingobjectposition.sideHit, par1ItemStack))
//                    {
//                        return new ActionResult(EnumActionResult.FAIL, par1ItemStack);
//                    }
//
//                    if (par2World.getBlockState(movingobjectposition.getBlockPos()) instanceof BlockLiquid)
//                    {
//                        Entity entity = spawnEntity(par2World, movingobjectposition.getBlockPos().getX(), movingobjectposition.getBlockPos().getY(), movingobjectposition.getBlockPos().getZ());
//
//                        if (entity != null)
//                        {
//                            if (entity instanceof EntityIS && par1ItemStack.hasDisplayName())
//                            {
//                                ((EntityIS)entity).setCustomNameTag(par1ItemStack.getDisplayName());
//                            }
//
//                            if (!par3EntityPlayer.capabilities.isCreativeMode)
//                            {
//                                --par1ItemStack.stackSize;
//                            }
//                        }
//                    }
//                }
//
//                return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
//            }
//        }
//    }

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
            entityToSpawnNameFull = Names.Villagers.TABANE;

            if (EntityList.getEntityString(entityToSpawn).contains(entityToSpawnNameFull))
            {
                entityToSpawn = (EntityIS) EntityList.createEntityByName(entityToSpawnNameFull, parWorld);
                entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
                parWorld.spawnEntityInWorld(entityToSpawn);
                entityToSpawn.onInitialSpawn(parWorld.getDifficultyForLocation(pos), (IEntityLivingData) null);
                entityToSpawn.playLivingSound();
            }
            else
            {
                //DEBUG
                System.out.println("Entity not found " + entityToSpawnName);
            }
        }

        return entityToSpawn;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, List parList)
    {
        parList.add(new ItemStack(parItem, 1, 0));
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
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s",Textures.RESOURCE_PREFIX, getUnwrappedLocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedLocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
    {
        list.add("Creator of the Infinite Stratos System.");
        list.add("Spawn at your own risk");
    }
}
