package dev.cryst.skillprogress.service.facade;

import lombok.AllArgsConstructor;
import dev.cryst.skillprogress.cache.SkillCache;
import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.service.DatabaseService;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class CacheFacade {
    private final SkillCache cache;
    private final DatabaseService service;

    public CompletableFuture<User> get(String name) {
        System.out.println("Тут начинается метод");
        return service.getPlayer(name).thenApply(p -> {
            User user = p;
            if (user == null) {
                System.out.println("1");
                user = new User(name, null, 0, 0);
                cache.addPlayer(name, user);
                return user;
            }
            System.out.println("2");
            cache.addPlayer(name, p);
            return user;
        });
    }

    public void add(User user) {
        service.add(user);
        cache.removePlayer(user);
    }

    public void remove(User user) {
        service.remove(user);
        cache.removePlayer(user);
    }

    public void update(String name, User newUser) {
        cache.addPlayer(name, newUser);
    }

}
