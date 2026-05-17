package dev.cryst.skillprogress;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import dev.cryst.skillprogress.cache.SkillCache;
import dev.cryst.skillprogress.configuration.Configuration;
import dev.cryst.skillprogress.repository.Database;
import dev.cryst.skillprogress.service.DatabaseService;
import dev.cryst.skillprogress.service.MenuService;
import dev.cryst.skillprogress.service.facade.CacheFacade;

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
