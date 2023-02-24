package best.nquantum.veinpvp.kit.impl;

import best.nquantum.veinpvp.kit.Kit;
import best.nquantum.veinpvp.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FisherKit extends Kit implements Listener {
    public FisherKit() {
        super("Fisherman", ChatColor.RESET + "" + ChatColor.GRAY + "A kit with iron sword, iron armor, fishing rod and a golden apple", null);
        List<String> lore = new ArrayList<>();
        lore.add(getDescription());
        ItemStack guiItem = new ItemStack(Material.FISHING_ROD);
        ItemMeta itemMeta = guiItem.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(getName());
        guiItem.setItemMeta(itemMeta);
        setGuiItem(guiItem);
    }

    @Override
    public void equip(Player player) {
        player.getInventory().clear();
        getItems().clear();
        getArmor().clear();
        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 1);

        this.getItems().addAll(Arrays.asList(sword, rod, gapple));
        this.getArmor().addAll(Arrays.asList(helmet, chest, legs, boots));

        getItems().forEach(item -> System.out.println(item));

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chest);
        player.getInventory().setLeggings(legs);
        player.getInventory().setBoots(boots);

        getItems().forEach(i -> player.getInventory().addItem(i));

        player.sendMessage(ChatUtil.PREFIX + "Successfully equipped " + ChatColor.AQUA + getName() + ChatColor.WHITE + " kit");
    }
}
