package dev.cryst.skillprogress.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import dev.cryst.skillprogress.cache.SkillCache;
import dev.cryst.skillprogress.command.impl.HelpCommand;
import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.service.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillCommand implements CommandExecutor, TabExecutor {
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    private final MenuService service;
    private final SkillCache cache;

    public SkillCommand(MenuService service, SkillCache cache) {
        this.service = service;
        this.cache = cache;
        subCommands.put("help", new HelpCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        final User playerdto = cache.getPlayer(player.getName());
        if (playerdto == null) {
            System.out.println("Player is null");
            return true;
        }

        if (playerdto.className() == null) {
            service.choose().open(player);
            return true;
        }
        service.main().open(player);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
