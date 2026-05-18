package io.github.crysscoder.skillprogress;

import org.bukkit.plugin.java.JavaPlugin;
import io.github.crysscoder.skillprogress.cache.SkillCache;
import io.github.crysscoder.skillprogress.command.SkillCommand;
import io.github.crysscoder.skillprogress.configuration.Configuration;
import io.github.crysscoder.skillprogress.gui.MenuListener;
import io.github.crysscoder.skillprogress.listener.CacheListener;
import io.github.crysscoder.skillprogress.repository.Database;
import io.github.crysscoder.skillprogress.service.facade.CacheFacade;


public final class SkillProgressPlugin extends JavaPlugin {
    private Database database;

    @Override
    public void onEnable() {
        Configuration config = new Configuration(this);
        database = new Database(config.getConfiguration().database());
        PluginContext pluginContext = new PluginContext(database, config);
        getServer().getPluginManager().registerEvents(new CacheListener(pluginContext.cacheFacade(), pluginContext.skillCache()), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getCommand("skill").setExecutor(new SkillCommand(pluginContext.menuService(),  pluginContext.skillCache()));
    }

    @Override
    public void onDisable() {
        if (database != null) {
            database.shutdown();
        }
    }
}
