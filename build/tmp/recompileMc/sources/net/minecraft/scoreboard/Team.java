package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.Map;

public abstract class Team
{
    /**
     * Same as ==
     */
    public boolean isSameTeam(Team other)
    {
        return other == null ? false : this == other;
    }

    /**
     * Retrieve the name by which this team is registered in the scoreboard
     */
    public abstract String getRegisteredName();

    public abstract String formatString(String input);

    @SideOnly(Side.CLIENT)
    public abstract boolean getSeeFriendlyInvisiblesEnabled();

    public abstract boolean getAllowFriendlyFire();

    @SideOnly(Side.CLIENT)
    public abstract Team.EnumVisible getNameTagVisibility();

    public abstract Collection<String> getMembershipCollection();

    public abstract Team.EnumVisible getDeathMessageVisibility();

    public static enum EnumVisible
    {
        ALWAYS("always", 0),
        NEVER("never", 1),
        HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
        HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

        private static Map<String, Team.EnumVisible> field_178828_g = Maps.<String, Team.EnumVisible>newHashMap();
        public final String internalName;
        public final int id;

        public static String[] func_178825_a()
        {
            return (String[])field_178828_g.keySet().toArray(new String[field_178828_g.size()]);
        }

        public static Team.EnumVisible func_178824_a(String p_178824_0_)
        {
            return (Team.EnumVisible)field_178828_g.get(p_178824_0_);
        }

        private EnumVisible(String p_i45550_3_, int p_i45550_4_)
        {
            this.internalName = p_i45550_3_;
            this.id = p_i45550_4_;
        }

        static
        {
            for (Team.EnumVisible team$enumvisible : values())
            {
                field_178828_g.put(team$enumvisible.internalName, team$enumvisible);
            }
        }
    }
}