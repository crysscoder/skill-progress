package io.github.crysscoder.skillprogress.gui.ex;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import io.github.crysscoder.skillprogress.PluginContext;
import io.github.crysscoder.skillprogress.configuration.menu.IconConfiguration;
import io.github.crysscoder.skillprogress.gui.Menu;

import java.util.List;

public class ChooseMenu extends Menu {
    private final PluginContext context;

    public ChooseMenu(PluginContext context) {
        super(context, "choose_menu");
        this.context = context;
    }

    @Override
    public void setup(Player player) {
        List<IconConfiguration> icons = context.configuration().getConfiguration().menus().get("choose_menu").icons();
        for (IconConfiguration icon : icons) {
            ItemStack item = icon.build(player, context);
            getInventory().setItem(icon.slot(), item);
        }

    }
}
