package best.nquantum.veinpvp.data;

import best.nquantum.veinpvp.util.ChatUtil;
import best.nquantum.veinpvp.util.MathUtil;
import best.nquantum.veinpvp.util.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class PlayerStatsManager implements Listener {
    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        PlayerStats stats = new PlayerStats();
        File file = new File(PlayerHandler.getDirectory(event.getPlayer()) + "/data.yml");

        if (file.exists()) {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            stats.setLevel(fileConfiguration.getInt("stats.level"));
            stats.setXp(fileConfiguration.getInt("stats.xp"));
            stats.setKills(fileConfiguration.getInt("stats.kills"));
            stats.setDeaths(fileConfiguration.getInt("stats.deaths"));
            stats.setKillStreak(fileConfiguration.getInt("stats.killstreak"));
            stats.setKdRatio(fileConfiguration.getDouble("stats.kdr"));
        } else {
            stats.setKdRatio(0.0);
            stats.setLevel(0);
            stats.setDeaths(0);
            stats.setKills(0);
            stats.setKillStreak(0);
        }

        PlayerHandler.setPlayerStats(event.getPlayer(), stats);
    }

    @EventHandler
    private void onKill(PlayerDeathEvent event) {
        final Player killer = event.getEntity().getKiller();
        final Player victim = event.getEntity();
        
        final PlayerStats killerStats = PlayerHandler.getPlayerStats(killer);
        final PlayerStats victimStats = PlayerHandler.getPlayerStats(victim);
        
        killerStats.setKills(killerStats.getKills() + 1);
        victimStats.setDeaths(victimStats.getDeaths() + 1);

        killerStats.setKillStreak(killerStats.getKillStreak() + 1);
        victimStats.setKillStreak(0);

        /*
            XP breakdown
            1 kill = 10xp,
            1 death = -5xp,
            5 killstreak = 50xp,
            10 killstreak = 150xp,
            15 killstreak = 200xp,
            20 killstreak = 300xp,
            30 killstreak = 500xp
         */

        int killStreakAmount = 0;
        int killAmount = 10;
        int deathAmount = -5;

        killerStats.giveXp(killAmount);
        victimStats.giveXp(deathAmount);

        switch (killerStats.getKillStreak()) {
            case 5:
                killStreakAmount = 50;
                break;
            case 10:
                killStreakAmount = 150;
                break;
            case 15:
                killStreakAmount = 200;
                break;
            case 20:
                killStreakAmount = 300;
                break;
            case 30:
                killStreakAmount = 500;
                break;
            default:
                killStreakAmount = 0;
                break;
        }

        killerStats.giveXp(killStreakAmount);
        killer.sendMessage(ChatColor.GREEN + "+" + killAmount + "XP for killing " + ChatColor.AQUA + victim.getName());
        victim.sendMessage(ChatColor.RED + "" + deathAmount + "XP for dying from " + ChatColor.AQUA + killer.getName());

        if (killerStats.getKillStreak() % 5 == 0) {
            Bukkit.broadcastMessage(ChatColor.WHITE + "" + ChatColor.BOLD + killer.getName() + ChatColor.AQUA + "" + ChatColor.BOLD + " is on " + ChatColor.GREEN + "" + ChatColor.BOLD + killerStats.getKillStreak() + ChatColor.AQUA + "" + ChatColor.BOLD + " kill streak!");
        }

        if(killStreakAmount > 0) {
            killer.sendMessage(ChatColor.YELLOW + "+" + killStreakAmount + "XP for " + ChatColor.YELLOW + "" + killerStats.getKillStreak() + " killstreak!");
        }

        int prevLevelKiller = killerStats.getLevel();
        int prevLevelVictim = victimStats.getLevel();

        killerStats.setLevel(MathUtil.calculateLevel(killerStats.getXp()));
        victimStats.setLevel(MathUtil.calculateLevel(victimStats.getXp()));

        if (prevLevelKiller < killerStats.getLevel()) {
            killer.sendMessage(ChatColor.GREEN + "You have advanced to level " + ChatColor.WHITE + killerStats.getLevel() + ChatColor.GREEN + "!");
        }
        if (prevLevelVictim < victimStats.getLevel()) {
            victim.sendMessage(ChatColor.RED + "You have fallen to level " + ChatColor.WHITE + victimStats.getLevel() + ChatColor.RED + ".");
        }

        Bukkit.broadcastMessage(ChatColor.AQUA + "" + killer.getName() + " §7[§c" +  (Math.round(killer.getHealth() * 100.0) / 100.0) + "§7] killed " + "§b" + victim.getName()) ;

        killer.setHealth(20);
        killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

    }

    @EventHandler
    private void onLeave(PlayerQuitEvent event) {
        PlayerStats stats = PlayerHandler.getPlayerStats(event.getPlayer());
        File file = new File(PlayerHandler.getDirectory(event.getPlayer()) + "/data.yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("stats.level", stats.getLevel());
        fileConfiguration.set("stats.xp", stats.getXp());
        fileConfiguration.set("stats.kills", stats.getKills());
        fileConfiguration.set("stats.deaths", stats.getDeaths());
        fileConfiguration.set("stats.killstreak", stats.getKillStreak());
        fileConfiguration.set("stats.kdr", stats.getKdRatio());
        System.out.println("Saving...");
        try {
            fileConfiguration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

       // PlayerHandler.setPlayerStats(event.getPlayer(), null);
    }


}
