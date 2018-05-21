package com.sparta.is.entity.driveables;

import com.sparta.is.armor.models.ModelUnit;
import com.sparta.is.entity.driveables.types.BulletType;
import com.sparta.is.entity.driveables.types.InfoType;
import com.sparta.is.entity.driveables.types.PartType;
import com.sparta.is.entity.driveables.types.TypeFile;
import com.sparta.is.reference.EnumFireMode;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DriveableType extends InfoType
{
    @SideOnly(Side.CLIENT)
    public ModelUnit model;

    public HashMap<EnumDriveablePart, CollisionBox> health = new HashMap<EnumDriveablePart, CollisionBox>();
    public HashMap<EnumDriveablePart, ItemStack[]> partwiseRecipe = new HashMap<EnumDriveablePart, ItemStack[]>();
    public ArrayList<ItemStack> driveableRecipe = new ArrayList<ItemStack>();

    public boolean acceptAllAmmo = true;
    public List<BulletType> ammo = new ArrayList<BulletType>();

    public EnumWeaponType primary = EnumWeaponType.NONE, secondary = EnumWeaponType.NONE;
    public boolean alternatePrimary = false, alternateSecondary = false;
    public int shootDelayPrimary = 1, shootDelaySecondary = 1;
    public EnumFireMode modePrimary = EnumFireMode.FULL_AUTO, modeSecondary = EnumFireMode.FULL_AUTO;
    public int damageModifierPrimary = 1, damageModifierSecondary = 1;
    //
    //Sounds go here
    //
    public ArrayList<DriveablePosition> shootPointsPrimary = new ArrayList<DriveablePosition>(), shootPointsSecondary = new ArrayList<DriveablePosition>();
    public int numberOfPassengers = 0;
    public Seat[] seats;
    public int numberOfPassengerGunners = 0;
    public  ArrayList<PilotGun> pilotGuns = new ArrayList<PilotGun>();


    public int numCargoSlots, numBombSlots, numMissileSlots;
    public int shieldCapacity;

    public float yOffset = 10f / 16f;
    public float cameraDistance = 5f;
    public ArrayList<ParticleEmitter> emitters = new ArrayList<ParticleEmitter>();
    public float maxThrottle = 1f, maxNegativeThrottle = 1f;
    public Vector3f turretOrigin = new Vector3f();

    public ArrayList<DriveablePosition> collisionPoints = new ArrayList<DriveablePosition>();
    public boolean placeableOnLand = true, placeableOnWater = false;
    public float bulletDetectionRadius = 5F;

    public int animFrames = 0;


    public static ArrayList<DriveableType> types = new ArrayList<DriveableType>();

    public DriveableType(TypeFile file)
    {
        super(file);
    }

//    @Override
//    public void preRead(TypeFile file)
//    {
//        super.preRead(file);
//        //Make sure the passenger arrays are set up first
//        for(String line : file.lines)
//        {
//            if(line == null)
//                break;
//            if(line.startsWith("//"))
//                continue;
//            String[] split = line.split(" ");
//            if(split.length < 2)
//                continue;
//
//            if(split[0].equals("Passengers"))
//            {
//                numberOfPassengers = Integer.parseInt(split[1]);
//                seats = new Seat[numberOfPassengers + 1];
//                break;
//            }
//        }
//        types.add(this);
//    }

//    public abstract EntityDriveable createDriveable(World world, double x, double y, double z, DriveableData data);

    public int numEngines()
    {
        return 1;
    }

    public int ammoSlots()
    {
        return numberOfPassengerGunners + pilotGuns.size();
    }

    public boolean isValidAmmo(BulletType bulletType, EnumWeaponType weaponType)
    {
        return (acceptAllAmmo || ammo.contains(bulletType)) && bulletType.weaponType == weaponType;
    }

    /** Find the items needed to rebuild a part. The returned array is disconnected from the template items it has looked up */
    public ArrayList<ItemStack> getItemsRequired(DriveablePart part, PartType engine)
    {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        //Start with the items required to build this part
        if(partwiseRecipe.get(part.type) != null)
        {
            for(ItemStack stack : partwiseRecipe.get(part.type))
            {
                stacks.add(stack.copy());
            }
        }
        //Add the items required for the guns connected to this part
        for(PilotGun gun : pilotGuns)
        {
            if(gun.part == part.type)
            {
                stacks.add(new ItemStack(gun.type.item));
                //if(data.ammo[numPassengerGunners + pilotGuns.indexOf(gun)] != null)
                //	stacks.add(data.ammo[numPassengerGunners + pilotGuns.indexOf(gun)]);
            }
        }
        for(Seat seat : seats)
        {
            if(seat != null && seat.part == part.type && seat.gunType != null)
            {
                stacks.add(new ItemStack(seat.gunType.item));
                //if(data.ammo[seat.id] != null)
                //	stacks.add(data.ammo[seat.id]);
            }
        }
        return stacks;
    }


    public static DriveableType getDriveable(String find)
    {
        for(DriveableType type : types)
        {
            if(type.shortName.equals(find))
                return type;
        }
        return null;
    }

    public class ParticleEmitter
    {
        /** The name of the effect */
        public EnumParticleTypes effectType;
        /** The rate of emission */
        public int emitRate;
        /** The centre of the effect emitter */
        public Vector3f origin;
        /** The size of the box in which it emits */
        public Vector3f extents;
        /** The velocity of the particle */
        public Vector3f velocity;
        /** Lower throttle bound */
        public float minThrottle;
        /** Upper throttle bound */
        public float maxThrottle;
        /** Model part the emitter is bound to */
        public String part;
        /** Minimum health for the emitter to work */
        public float minHealth;
        /** Maximum health for the emitter to work */
        public float maxHealth;
    }

    @SideOnly(Side.CLIENT)
    public ModelBase GetModel()
    {
        return model;
    }
}
