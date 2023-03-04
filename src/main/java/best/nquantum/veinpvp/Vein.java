package best.nquantum.veinpvp;

import best.nquantum.veinpvp.command.KitsCommand;
import best.nquantum.veinpvp.data.PlayerStats;
import best.nquantum.veinpvp.data.PlayerStatsManager;
import best.nquantum.veinpvp.data.Scoreboard;
import best.nquantum.veinpvp.handlers.*;
import best.nquantum.veinpvp.kit.Kit;
import best.nquantum.veinpvp.kit.KitManager;
import best.nquantum.veinpvp.kit.impl.BasicKit;
import best.nquantum.veinpvp.kit.impl.FisherKit;
import best.nquantum.veinpvp.placeholder.PlaceholderRegistry;
import best.nquantum.veinpvp.util.PlayerHandler;
import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Vein extends JavaPlugin implements Listener {
    public static Inventory kitSelector;
    public static KitManager kitManager;


    @Override
    public void onEnable() {
        kitManager = new KitManager();
        Scoreboard scoreboard = new Scoreboard(this);
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            PlaceholderAPI.registerPlaceholderHook(this, new PlaceholderRegistry());
        }

        getServer().getPluginManager().registerEvents(this, this);

        /*
        Handlers
         */

        getServer().getPluginManager().registerEvents(new FallDamageRemover(), this);
        getServer().getPluginManager().registerEvents(new HungerRemover(), this);
        getServer().getPluginManager().registerEvents(new MobSpawningRemover(), this);
        getServer().getPluginManager().registerEvents(new WeatherRemover(), this);
        getServer().getPluginManager().registerEvents(new AntiItemDrop(), this);
        getServer().getPluginManager().registerEvents(new PlayerStatsManager(), this);

        /*
        Kits
         */

        getServer().getPluginManager().registerEvents(new BasicKit(), this);
        getServer().getPluginManager().registerEvents(new FisherKit(), this);


        kitSelector = getServer().createInventory(null, 9, "Select your kit");

        int index = 0;
        for (Kit kit : kitManager.getKits()) {
            kitSelector.setItem(index, kit.getGuiItem());
            ++index;
        }

        DecimalFormat format = new DecimalFormat("#.##");

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : getServer().getOnlinePlayers()) {
                PlayerStats stats = PlayerHandler.getPlayerStats(p);
                stats.setKdRatio(stats.getDeaths() == 0D || stats.getKills() == 0D ? stats.getKills() : Double.parseDouble(format.format(stats.getKills() / stats.getDeaths())));
                PlayerHandler.setPlayerStats(p, stats);
            }
        }, 100L, 100L);

    }
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("kits")) {
            player.openInventory(Vein.kitSelector);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(Vein.kitSelector)) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            switch (event.getSlot()) {
                case 0: {
                    Vein.kitManager.getKitByName("Basic").equip(player);
                    break;
                }
                case 1: {
                   // Vein.kitManager.getKitByName("Fisherman").equip(player);
                    player.sendMessage(ChatColor.RED + "You can't equip that kit.");
                    break;
                }

            }

            player.closeInventory();
        }
    }

}
