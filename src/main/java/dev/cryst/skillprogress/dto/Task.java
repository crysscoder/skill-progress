package dev.cryst.skillprogress.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    String task_id;
    String skill_id;
    String name;
    List<String> lore;
    PlayerStats stats;
    int requiredAmount;
}
