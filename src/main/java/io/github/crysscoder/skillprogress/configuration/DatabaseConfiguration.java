package io.github.crysscoder.skillprogress.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseConfiguration {
    String host = "localhost";
    int port = 3306;
    String database = "skill";
    String user = "root";
    String password = "root";
}
