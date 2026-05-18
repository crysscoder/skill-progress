package io.github.crysscoder.skillprogress.gui.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public record Icon(ItemStack item, Consumer<InventoryClickEvent> action) {
    public void onClick(InventoryClickEvent event) {
        if (action != null) action.accept(event);
    }
}
