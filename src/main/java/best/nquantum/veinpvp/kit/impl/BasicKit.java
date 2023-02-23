package best.nquantum.veinpvp.kit.impl;

import best.nquantum.veinpvp.kit.Kit;
import best.nquantum.veinpvp.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BasicKit extends Kit {
    public BasicKit() {
        super("Basic", "A basic kit, containing iron armor, diamond sword and a golden apple", null);
        List<String> lore = new ArrayList<>();
        lore.add(getDescription());
        ItemStack guiItem = new ItemStack(Material.DIAMOND_SWORD);
        guiItem.getItemMeta().setLore(lore);
        setGuiItem(guiItem);
    }

    @Override
    public void equip(Player player) {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 16);

        this.getItems().addAll(Arrays.asList(sword, gapple));
        this.getArmor().addAll(Arrays.asList(helmet, chest, legs, boots));

        getItems().forEach(item -> System.out.println(item));

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chest);
        player.getInventory().setLeggings(legs);
        player.getInventory().setBoots(boots);

        player.getInventory().addItem(sword);
        player.getInventory().addItem(gapple);

        player.sendMessage(ChatUtil.PREFIX + "successfully equipped " + getName() + " kit");
    }
}
