package dev.cryst.skillprogress;

import org.bukkit.plugin.java.JavaPlugin;
import dev.cryst.skillprogress.cache.SkillCache;
import dev.cryst.skillprogress.command.SkillCommand;
import dev.cryst.skillprogress.configuration.Configuration;
import dev.cryst.skillprogress.gui.MenuListener;
import dev.cryst.skillprogress.listener.CacheListener;
import dev.cryst.skillprogress.repository.Database;
import dev.cryst.skillprogress.service.facade.CacheFacade;


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
