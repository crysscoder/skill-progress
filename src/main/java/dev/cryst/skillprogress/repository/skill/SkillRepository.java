package dev.cryst.skillprogress.repository.skill;

import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.repository.Repository;

import java.util.concurrent.CompletableFuture;

public interface SkillRepository extends Repository<User> {
    CompletableFuture<User> getPlayer(String name);

}
