package dev.cryst.skillprogress.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import dev.cryst.skillprogress.cache.SkillCache;
import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.service.facade.CacheFacade;

@AllArgsConstructor
public class CacheListener implements Listener {
    private CacheFacade cache;
    private SkillCache cacheSkill;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        cache.get(player.getName());
        System.out.println("3i");

    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final User user = cacheSkill.getPlayer(player.getName());
        cache.add(user);
    }
}
