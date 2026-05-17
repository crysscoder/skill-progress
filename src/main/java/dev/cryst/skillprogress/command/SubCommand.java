package dev.cryst.skillprogress.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface SubCommand {
    String name();

    String description();

    String permission();

    void accept(@NotNull CommandSender sender, @NotNull String[] args);
}
