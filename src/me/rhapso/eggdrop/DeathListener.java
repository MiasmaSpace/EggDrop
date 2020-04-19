package me.rhapso.eggdrop;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class DeathListener implements Listener {
    final String EGG_DROP_KEY = "likelihood_egg_drop";

    private final Plugin plugin;
    private final double odds;
    private final Random random;

    public DeathListener(Plugin plugin) {
        this.plugin = plugin;
        Object  likelihood_egg_drop = plugin.getConfig().get(EGG_DROP_KEY);
        if(likelihood_egg_drop==null){
            plugin.getConfig().set(EGG_DROP_KEY, 0.1);
            odds = 0.1;
        } else {
            odds = (Double) likelihood_egg_drop;
        }
        random = new Random();

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller();
            if (killer.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                if(random.nextDouble()>odds){
                    return;
                }
                Material egg = EggMap.getEgg(e.getEntity().getType());
                if (egg != null) {
                    e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(egg));
                    e.setDroppedExp(0);
                    for (ItemStack i : e.getDrops()) {
                        i.setAmount(0);
                    }
                }
            }
        }
    }
}
