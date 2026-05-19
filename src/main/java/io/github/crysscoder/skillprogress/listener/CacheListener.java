package io.github.crysscoder.skillprogress.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import io.github.crysscoder.skillprogress.cache.SkillCache;
import io.github.crysscoder.skillprogress.dto.User;
import io.github.crysscoder.skillprogress.service.facade.CacheFacade;

@AllArgsConstructor
public class CacheListener implements Listener {
    private CacheFacade cache;
    private SkillCache cacheSkill;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        cache.get(player.getName());
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final User user = cacheSkill.getPlayer(player.getName());
        if (user == null) {
            return;
        }
        cache.add(user);
    }
}
