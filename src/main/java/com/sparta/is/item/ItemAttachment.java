package com.sparta.is.item;

import com.sparta.is.entity.driveables.types.AttachmentType;
import com.sparta.is.item.base.ItemIS;

public class ItemAttachment extends ItemIS
{
    public AttachmentType type;

    public ItemAttachment()
    {
        super("Generic Attachment");
    }
}
