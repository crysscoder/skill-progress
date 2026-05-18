package io.github.crysscoder.skillprogress.repository.skill;

import io.github.crysscoder.skillprogress.dto.User;
import io.github.crysscoder.skillprogress.repository.Repository;

import java.util.concurrent.CompletableFuture;

public interface SkillRepository extends Repository<User> {
    CompletableFuture<User> getPlayer(String name);

}
