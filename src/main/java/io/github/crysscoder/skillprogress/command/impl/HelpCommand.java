package io.github.crysscoder.skillprogress.command.impl;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import io.github.crysscoder.skillprogress.command.SubCommand;

public class HelpCommand implements SubCommand {
    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Send help message to all commands.";
    }

    @Override
    public String permission() {
        return "skill.help";
    }

    @Override
    public void accept(@NotNull CommandSender sender, @NotNull String[] args) {

    }
}
