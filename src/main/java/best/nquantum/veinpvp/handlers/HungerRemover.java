package best.nquantum.veinpvp.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerRemover implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        ((Player)e.getEntity()).getPlayer().setFoodLevel(20);
    }
}
