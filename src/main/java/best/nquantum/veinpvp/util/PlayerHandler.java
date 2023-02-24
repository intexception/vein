package best.nquantum.veinpvp.util;

import best.nquantum.veinpvp.Vein;
import best.nquantum.veinpvp.data.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.util.HashMap;
import java.util.Map;

public class PlayerHandler {
    private static final Map<String, PlayerStats> playerStats = new HashMap<>();

    public static PlayerStats getPlayerStats(final Player p) {
        if(playerStats.containsKey(p.getName())) {
            PlayerStats stats = new PlayerStats();
            playerStats.put(p.getName(), stats);
            return stats;
        }
        return playerStats.get(p.getName());
    }

    public static void setPlayerStats(final Player p, final PlayerStats stats){
        playerStats.put(p.getName(), stats);
    }

    public static String getDirectory(final Player p) {
        return JavaPlugin.getPlugin(Vein.class).getDataFolder().getAbsolutePath() + "/stats/" + p.getName();
    }


}
