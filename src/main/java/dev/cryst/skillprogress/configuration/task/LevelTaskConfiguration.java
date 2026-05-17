package dev.cryst.skillprogress.configuration.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LevelTaskConfiguration {
    Map<String, TaskConfiguration> tasks = new HashMap<>(Map.of(
            "1", new TaskConfiguration()
    ));
}
