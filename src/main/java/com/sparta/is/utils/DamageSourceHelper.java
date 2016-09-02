package com.sparta.is.utils;

import net.minecraft.util.DamageSource;

public class DamageSourceHelper
{
    private DamageSourceHelper()
    {

    }

    public static final DamageSourceSword sword = new DamageSourceSword();

    public static class DamageSourceSword extends DamageSource
    {
        boolean barrierVoid = false;

        protected DamageSourceSword()
        {
            super("sword");

            if(barrierVoid)
            {
                this.setDamageBypassesArmor();
            }
        }

        public void setBarrierVoid(boolean b)
        {
            this.barrierVoid = b;
        }

        public boolean isBarrierVoid()
        {
            return barrierVoid;
        }
    }
}
