package io.github.crysscoder.skillprogress.configuration.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskConfiguration {
    int id = 1;
    String name = "task1";
    List<String> lore = new ArrayList<>(List.of("Описание задания"));

}
