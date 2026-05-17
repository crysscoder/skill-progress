package dev.cryst.skillprogress.gui.ex;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import dev.cryst.skillprogress.PluginContext;
import dev.cryst.skillprogress.configuration.menu.IconConfiguration;
import dev.cryst.skillprogress.gui.Menu;

import java.util.List;

public class MainMenu extends Menu {
    private final PluginContext context;

    public MainMenu(PluginContext context) {
        super(context, "main_menu");

        this.context = context;
    }

    @Override
    public void setup(Player player) {

        List<IconConfiguration> icons = context.configuration().getConfiguration().menus().get("main_menu").icons();
        for (IconConfiguration icon : icons) {
            ItemStack item = icon.build(player, context);
            getInventory().setItem(icon.slot(), item);
        }

    }
}
