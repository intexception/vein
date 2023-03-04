package best.nquantum.veinpvp.placeholder;

import best.nquantum.veinpvp.util.PlayerHandler;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

public class PlaceholderRegistry extends PlaceholderHook {

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        // %<your plugin name>_level%
        if (identifier.equals("level")) {
            return String.valueOf(PlayerHandler.getPlayerStats(p).getLevel());
        }
        return null;
    }

}