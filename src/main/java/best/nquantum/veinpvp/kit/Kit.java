package best.nquantum.veinpvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Kit implements Listener {
    private String name;
    private String description;
    private List<ItemStack> items = new ArrayList<>();
    private List<ItemStack> armor = new ArrayList<>();
    private ItemStack guiItem;

    public Kit(String name, String description, ItemStack guiItem) {
        this.name = name;
        this.description = description;
        this.guiItem = guiItem;
    }

    public String getName() {
        return name;
    }

    public Kit setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Kit setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public Kit setItems(List<ItemStack> items) {
        this.items = items;
        return this;
    }

    public List<ItemStack> getArmor() {
        return armor;
    }

    public Kit setArmor(List<ItemStack> armor) {
        this.armor = armor;
        return this;
    }

    public ItemStack getGuiItem() {
        return guiItem;
    }

    public Kit setGuiItem(ItemStack guiItem) {
        this.guiItem = guiItem;
        return this;
    }

    public abstract void equip(Player player);
}
