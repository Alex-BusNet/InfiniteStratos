package com.sparta.is.entity.driveables.types;

import com.sparta.is.core.item.ItemIS;
import com.sparta.is.core.utils.helpers.LogHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Random;

public class InfoType
{
    public static HashMap<Integer, InfoType> infoTypes = new HashMap<Integer, InfoType>();

    public String contentPack;
    public Item item;
    public Object[] recipe;
    public String[] recipeLine;
    public int recipeOutput = 1;
    public boolean shapeless;
    public String name;
    public String shortName;
    public String texture;
    public String modelString;
    public String description;
    public float modelScale = 1f;
    // Can the item be dropped
    public boolean canDrop = true;

    private static Random rand = new Random();

    public InfoType(TypeFile file)
    {
        contentPack = file.contentPack;
    }

    protected void read(String[] split, TypeFile file) {}

    public void read(TypeFile file) {}

    protected void preRead(TypeFile file) {}

    protected void postRead(TypeFile file) {}

    @SideOnly(Side.CLIENT)
    public ModelBase getModel()
    {
        return null;
    }

    public void addRecipe()
    {
        this.addRecipe(getItem());
    }

    public void addRecipe(Item item)
    {

    }

    public static ItemStack getRecipeElement(String s, int damage)
    {
        return getRecipeElement(s, 1, damage);
    }

    public static ItemStack getRecipeElement(String s, int amount, int damage)
    {
        return getRecipeElement(s, amount, damage, "nothing");
    }

    public static ItemStack getRecipeElement(String s, int amount, int damage, String requester)
    {
        if (s.equals("doorIron"))
        {
            return new ItemStack(Items.IRON_DOOR, amount);
        }
        if (s.equals("clayItem"))
        {
            return new ItemStack(Items.CLAY_BALL, amount);
        }
        for(Object object : Item.REGISTRY)
        {
            Item item = (Item)object;
            if (item != null && item.getUnlocalizedName() != null && (item.getUnlocalizedName().equals("item." + s) || item.getUnlocalizedName().equals("tile." + s)))
            {
                return new ItemStack(item, amount, damage);
            }
        }
        for(InfoType type : infoTypes.values())
        {
            if(type.shortName.equals(s))
                return new ItemStack(type.item, amount, damage);
        }
        if (s.equals("gunpowder"))
        {
            return new ItemStack(Items.GUNPOWDER, amount);
        }
        if (s.equals("iron"))
        {
            return new ItemStack(Items.IRON_INGOT, amount);
        }
        LogHelper.info(LogHelper.MOD_MARKER, "Could not find " + s + " when adding recipe for " + requester);
        return null;
    }

    public Item getItem()
    {
        return item;
    }

    /* Override in subtype models */
    public void reloadModel() {}

    @Override
    public int hashCode()
    {
        return shortName.hashCode();
    }

    public static InfoType getType(String s)
    {
        return infoTypes.get(s.hashCode());
    }

    public static InfoType getType(int hash)
    {
        return infoTypes.get(hash);
    }

    public void onWorldLoad(World world)
    {

    }

    public static InfoType getType(ItemStack itemStack)
    {
        if(itemStack == null)
            return null;
        Item item = itemStack.getItem();
        if(item instanceof ItemIS)
            return ((ItemIS)item).getType();
        return null;
    }

}
