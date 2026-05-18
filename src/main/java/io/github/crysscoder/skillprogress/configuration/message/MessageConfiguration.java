package io.github.crysscoder.skillprogress.configuration.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageConfiguration {
    ErrorMessageConfiguration errorMessageConfiguration = new ErrorMessageConfiguration();
}
