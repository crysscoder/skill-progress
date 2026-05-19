package io.github.crysscoder.skillprogress.cache;

import io.github.crysscoder.skillprogress.dto.User;

import java.util.HashMap;
import java.util.Map;

public class SkillCache {
    private final Map<String, User> players = new HashMap<>();

    public void addPlayer(String name, User user) {
        players.put(name, user);
    }

    public void removePlayer(String name) {
        players.remove(name);
    }

    public void clear() {
        players.clear();
    }

    public User getPlayer(String name) {
        return players.get(name);
    }


}
