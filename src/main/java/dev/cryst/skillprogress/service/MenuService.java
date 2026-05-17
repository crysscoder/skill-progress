package dev.cryst.skillprogress.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import dev.cryst.skillprogress.PluginContext;
import dev.cryst.skillprogress.gui.action.MenuActionRegistry;
import dev.cryst.skillprogress.gui.ex.ChooseMenu;
import dev.cryst.skillprogress.gui.ex.MainMenu;

@Getter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuService {
    MainMenu main;
    ChooseMenu choose;
    MenuActionRegistry registry;

    public MenuService(PluginContext context) {
        this.choose = new ChooseMenu(context);
        this.main = new MainMenu(context);
        this.registry = new MenuActionRegistry(context);
    }
}
