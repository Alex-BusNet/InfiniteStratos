package net.minecraft.client.resources.data;

import net.minecraft.client.resources.Language;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class LanguageMetadataSection implements IMetadataSection
{
    private final Collection<Language> languages;

    public LanguageMetadataSection(Collection<Language> p_i1311_1_)
    {
        this.languages = p_i1311_1_;
    }

    public Collection<Language> getLanguages()
    {
        return this.languages;
    }
}