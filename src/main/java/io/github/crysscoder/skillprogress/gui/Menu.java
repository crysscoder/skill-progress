package io.github.crysscoder.skillprogress.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import io.github.crysscoder.skillprogress.PluginContext;
import io.github.crysscoder.skillprogress.configuration.menu.IconConfiguration;
import io.github.crysscoder.skillprogress.gui.button.Icon;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Menu implements InventoryHolder, Listener {
    protected final String id;
    protected final Map<Integer, Icon> buttons = new HashMap<>();
    private final PluginContext context;
    protected Map<Integer, IconConfiguration> slots = new HashMap<>();
    private Inventory inventory;

    public Menu(PluginContext context, String id) {
        this.id = id;
        this.context = context;
        this.inventory = Bukkit.createInventory(
                this,
                context.configuration().getConfiguration().menus().get(id).size(),
                context.configuration().getConfiguration().menus().get(id).title());
    }

    public abstract void setup(Player player);

    public void open(Player player) {
        var menuCfg = context.configuration().getConfiguration().menus().get(id);
        this.inventory = Bukkit.createInventory(this, menuCfg.size(), menuCfg.title());
        buttons.clear();
        slots.clear();
        menuCfg.icons().forEach(icon -> {
            slots.put(icon.slot(), icon);
        });

        setup(player);
        player.openInventory(inventory);
    }

    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);

        int slot = event.getRawSlot();
        if (slot < 0 || slot >= inventory.getSize()) return;

        Player player = (Player) event.getWhoClicked();

        Icon button = buttons.get(slot);
        if (button != null) {
            button.onClick(event);
            return;
        }

        IconConfiguration icon = slots.get(slot);
        if (icon != null && icon.action() != null && !icon.action().isEmpty()) {
            context.menuService().registry().execute(icon.action(), player);
        }
    }

}
