package best.nquantum.veinpvp;

import best.nquantum.veinpvp.handlers.FallDamageRemover;
import best.nquantum.veinpvp.handlers.HungerRemover;
import best.nquantum.veinpvp.handlers.MobSpawningRemover;
import best.nquantum.veinpvp.kit.Kit;
import best.nquantum.veinpvp.kit.KitManager;
import best.nquantum.veinpvp.kit.impl.BasicKit;
import best.nquantum.veinpvp.kit.impl.FisherKit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Vein extends JavaPlugin implements Listener {
    private Inventory kitSelector;
    private KitManager kitManager;

    @Override
    public void onEnable() {
        kitManager = new KitManager();

        getServer().getPluginManager().registerEvents(this, this);

        /*
        Handlers
         */

        getServer().getPluginManager().registerEvents(new FallDamageRemover(), this);
        getServer().getPluginManager().registerEvents(new HungerRemover(), this);
        getServer().getPluginManager().registerEvents(new MobSpawningRemover(), this);

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

    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("kits")) {
            player.openInventory(kitSelector);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(kitSelector)) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            switch (event.getSlot()) {
                case 0: {
                    kitManager.getKitByName("Basic").equip(player);
                    break;
                }
                case 1: {
                    kitManager.getKitByName("Fisherman").equip(player);
                    break;
                }

            }

            player.closeInventory();
        }
    }
}
