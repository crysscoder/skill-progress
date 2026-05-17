package dev.cryst.skillprogress.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import dev.cryst.skillprogress.configuration.menu.MenuConfiguration;
import dev.cryst.skillprogress.configuration.message.MessageConfiguration;
import dev.cryst.skillprogress.configuration.task.LevelTaskConfiguration;
import dev.cryst.skillprogress.dto.Skill;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PluginConfiguration {
    MessageConfiguration message = new MessageConfiguration();
    DatabaseConfiguration database = new DatabaseConfiguration();
    Map<String, MenuConfiguration> menus = new HashMap<>(Map.of("main_menu", new MenuConfiguration()));
    Map<Skill, LevelTaskConfiguration> taskWarrior = new HashMap<>(Map.of(
            Skill.WARRIOR, new LevelTaskConfiguration()
    ));

    Map<Skill, LevelTaskConfiguration> taskFarmer = new HashMap<>(Map.of(
            Skill.FARMER, new LevelTaskConfiguration()
    ));

}
