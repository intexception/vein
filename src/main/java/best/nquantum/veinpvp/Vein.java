package best.nquantum.veinpvp;

import best.nquantum.veinpvp.handlers.FallDamageRemover;
import best.nquantum.veinpvp.kit.Kit;
import best.nquantum.veinpvp.kit.KitManager;
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
        getServer().getPluginManager().registerEvents(new FallDamageRemover(), this);

        kitSelector = getServer().createInventory(null, 9, "Select your kit");

        int index = 0;
        for (Kit kit : kitManager.getKits()) {
            kitSelector.setItem(0, kit.getGuiItem());
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

            for (Kit kit : kitManager.getKits()) {
                System.out.println(clickedItem);
                System.out.println(kit.getGuiItem());
                if (clickedItem == kit.getGuiItem()) {
                    System.out.println("clicked");
                    kit.equip(player);
                }
            }

            player.closeInventory();
        }
    }
}
