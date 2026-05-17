package dev.cryst.skillprogress.gui.action.impl;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import dev.cryst.skillprogress.cache.SkillCache;
import dev.cryst.skillprogress.dto.Skill;
import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.gui.action.MenuAction;
import dev.cryst.skillprogress.service.MenuService;

@AllArgsConstructor
public class ChooseClassAction implements MenuAction {
    private final SkillCache cache;
    private MenuService menuService;

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) return;
        final String className = args[0];
        final User user = cache.getPlayer(player.getName());
        user.className(Skill.valueOf(className));
        menuService.main().open(player);

    }
}
