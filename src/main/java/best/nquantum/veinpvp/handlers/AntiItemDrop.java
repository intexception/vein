package best.nquantum.veinpvp.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class AntiItemDrop implements Listener {
    @EventHandler
    public final void onDrop(final ItemSpawnEvent event) {
        event.setCancelled(true);
    }
}
