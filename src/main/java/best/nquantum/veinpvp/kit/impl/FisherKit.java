package best.nquantum.veinpvp.kit.impl;

import best.nquantum.veinpvp.kit.Kit;
import best.nquantum.veinpvp.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
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
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.spigot().setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);

        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        chestMeta.spigot().setUnbreakable(true);
        chest.setItemMeta(chestMeta);

        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta legsMeta = legs.getItemMeta();
        legsMeta.spigot().setUnbreakable(true);
        legs.setItemMeta(legsMeta);

        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.spigot().setUnbreakable(true);
        boots.setItemMeta(bootsMeta);

        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.spigot().setUnbreakable(true);
        sword.setItemMeta(swordMeta);

        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta rodMeta = rod.getItemMeta();
        rodMeta.spigot().setUnbreakable(true);
        rod.setItemMeta(rodMeta);

        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 1);

        this.getItems().addAll(Arrays.asList(sword, rod, gapple));
        this.getArmor().addAll(Arrays.asList(helmet, chest, legs, boots));

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chest);
        player.getInventory().setLeggings(legs);
        player.getInventory().setBoots(boots);

        getItems().forEach(i -> player.getInventory().addItem(i));

        player.sendMessage(ChatUtil.PREFIX + "Successfully equipped " + ChatColor.AQUA + getName() + ChatColor.WHITE + " kit");
    }
}
