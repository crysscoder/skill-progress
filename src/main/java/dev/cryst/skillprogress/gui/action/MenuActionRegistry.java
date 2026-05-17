package dev.cryst.skillprogress.gui.action;

import org.bukkit.entity.Player;
import dev.cryst.skillprogress.PluginContext;
import dev.cryst.skillprogress.gui.action.impl.ChooseClassAction;

import java.util.HashMap;
import java.util.Map;

public class MenuActionRegistry {
    private final Map<String, MenuAction> actions = new HashMap<>();

    public MenuActionRegistry(PluginContext context) {
        register("choose_class", new ChooseClassAction(context.skillCache(), context.menuService()));

    }

    public void register(String key, MenuAction action) {
        actions.put(key.toLowerCase(), action);
    }

    public void execute(String fullAction, Player player) {
        if (fullAction == null || fullAction.isEmpty()) return;

        final String[] parts = fullAction.split(":");
        final String key = parts[0].toLowerCase();
        final String[] args = parts.length > 1 ? parts[1].split(",") : new String[0];

        MenuAction action = actions.get(key);
        if (action != null) {
            action.execute(player, args);
        }
    }
}
