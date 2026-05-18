package io.github.crysscoder.skillprogress.gui.action.impl;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import io.github.crysscoder.skillprogress.cache.SkillCache;
import io.github.crysscoder.skillprogress.dto.Skill;
import io.github.crysscoder.skillprogress.dto.User;
import io.github.crysscoder.skillprogress.gui.action.MenuAction;
import io.github.crysscoder.skillprogress.service.MenuService;

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
