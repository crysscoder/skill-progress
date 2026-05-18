package io.github.crysscoder.skillprogress;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import io.github.crysscoder.skillprogress.cache.SkillCache;
import io.github.crysscoder.skillprogress.configuration.Configuration;
import io.github.crysscoder.skillprogress.repository.Database;
import io.github.crysscoder.skillprogress.service.DatabaseService;
import io.github.crysscoder.skillprogress.service.MenuService;
import io.github.crysscoder.skillprogress.service.facade.CacheFacade;

@Getter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PluginContext {
    Database database;
    DatabaseService databaseService;
    MenuService menuService;
    Configuration configuration;
    SkillCache skillCache;
    CacheFacade cacheFacade;

    public PluginContext(Database database, Configuration configuration) {
        this.database = database;
        this.configuration = configuration;
        databaseService = new DatabaseService(database);
        this.skillCache = new SkillCache();
        this.cacheFacade = new CacheFacade(skillCache, databaseService);
        menuService = new MenuService(this);

    }
}
