package io.github.crysscoder.skillprogress.service.facade;

import io.github.crysscoder.skillprogress.cache.SkillCache;
import io.github.crysscoder.skillprogress.dto.User;
import io.github.crysscoder.skillprogress.service.DatabaseService;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class CacheFacade {
    private final SkillCache cache;
    private final DatabaseService service;

    public CompletableFuture<User> get(String name) {
        return service.getPlayer(name).thenApply(user -> {
            User value = user == null ? new User(name, null, 0, 0) : user;
            cache.addPlayer(name, value);
            return value;
        });
    }

    public void add(User user) {
        if (user == null) {
            return;
        }
        if (user.className() == null) {
            cache.removePlayer(user.name());
            return;
        }
        service.add(user);
        cache.removePlayer(user.name());
    }

    public void remove(User user) {
        if (user == null) {
            return;
        }
        service.remove(user);
        cache.removePlayer(user.name());
    }

    public void update(String name, User newUser) {
        cache.addPlayer(name, newUser);
    }
}
