package io.github.crysscoder.skillprogress.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import io.github.crysscoder.skillprogress.PluginContext;
import io.github.crysscoder.skillprogress.gui.action.MenuActionRegistry;
import io.github.crysscoder.skillprogress.gui.ex.ChooseMenu;
import io.github.crysscoder.skillprogress.gui.ex.MainMenu;

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
