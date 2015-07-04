package com.sparta.is.api.definitions;

import com.google.common.base.Optional;
import net.minecraft.item.Item;

public interface IItemDefinition extends IComparableDefinition
{
    Optional<Item> maybeItem();

    Optional<Item> maybeStack(int stackSize);
}
