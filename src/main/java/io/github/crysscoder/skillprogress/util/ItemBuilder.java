package io.github.crysscoder.skillprogress.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public ItemBuilder name(String name) {
        if(name == null) return this;
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        if(lore == null) return this;
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        if(lore == null) return this;
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    public ItemBuilder replace(String target, String replacement) {
        if (target == null || replacement == null) return this;

        if (meta.hasDisplayName()) {
            String newName = meta.getDisplayName().replace(target, replacement);
            meta.setDisplayName(newName);
        }

        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            if (lore != null) {
                lore.replaceAll(line -> line.replace(target, replacement));
                meta.setLore(lore);
            }
        }

        return this;
    }
}
