package me.rhapso.eggdrop;

import com.sun.istack.internal.Nullable;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;


public abstract class EggMap {

    @Nullable
    public static Material getEgg(EntityType t) {
        return Material.getMaterial(t.name() + "_SPAWN_EGG");
    }
}
