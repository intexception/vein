package best.nquantum.veinpvp.data;

import best.nquantum.veinpvp.Vein;
import best.nquantum.veinpvp.util.PlayerHandler;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Scoreboard implements Listener {

    private final Map<String, FastBoard> boards = new HashMap<>();

    public Scoreboard(Vein plugin) {

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board);
            }
        }, 10L, 10L);
    }

    @EventHandler
    public final void onJoin(final PlayerJoinEvent event) {
        Bukkit.getLogger().log(Level.INFO, "JOINED " + event.getPlayer().getName());
        Player player = event.getPlayer();
        FastBoard board = new FastBoard(player);

        String title = "§bVeinPVP";
        board.updateTitle(title);

        for (PlayerStats stats : PlayerHandler.playerStats.values()){
            System.out.println(stats);
        }

        if (PlayerHandler.getPlayerStats(player) != null) {
            String[] stats = new String[]{
                    "§bKills: §f" + PlayerHandler.getPlayerStats(player).getKills(),
                    "§bDeaths: §f" + PlayerHandler.getPlayerStats(player).getDeaths(),
                    "§bKDR: §f" + PlayerHandler.getPlayerStats(player).getKdRatio(),
                    "",
                    "§bLevel: §f" + PlayerHandler.getPlayerStats(player).getLevel(),
                    "§bXP: §f" + PlayerHandler.getPlayerStats(player).getXp(),
                    "§bKill Streak: §f" + PlayerHandler.getPlayerStats(player).getKillStreak(),
            };
            board.updateLines(stats);
        } else {
            String[] stats = new String[]{
                    "no stats currently :("
            };
            board.updateLines(stats);
        }

        boards.put(player.getName(), board);
    }

    @EventHandler
    public final void onQuit(final PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FastBoard board = boards.remove(player.getName());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(final FastBoard board, final String... lines) {
        final Player player = board.getPlayer();
        String[] stats = new String[]{
                "§bKills: §f" + PlayerHandler.getPlayerStats(player).getKills(),
                "§bDeaths: §f" + PlayerHandler.getPlayerStats(player).getDeaths(),
                "§bKDR: §f" + PlayerHandler.getPlayerStats(player).getKdRatio(),
                "",
                "§bLevel: §f" + PlayerHandler.getPlayerStats(player).getLevel(),
                "§bXP: §f" + PlayerHandler.getPlayerStats(player).getXp(),
                "§bKill Streak: §f" + PlayerHandler.getPlayerStats(player).getKillStreak(),
        };
        board.updateLines(stats);
    }
}
