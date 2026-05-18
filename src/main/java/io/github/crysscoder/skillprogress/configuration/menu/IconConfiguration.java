package io.github.crysscoder.skillprogress.configuration.menu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import io.github.crysscoder.skillprogress.PluginContext;
import io.github.crysscoder.skillprogress.util.ItemBuilder;
import io.github.crysscoder.skillprogress.dto.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IconConfiguration {
    String type = "type";
    int amount = 0;
    int slot = 0;
    String action = "action";
    String name = "name";
    List<String> lore = new ArrayList<>();


    public ItemStack build(Player player, PluginContext context) {

        if (type == null) {
            throw new IllegalStateException("Material type is null");
        }

        String normalized = type.toUpperCase();
        if (normalized.contains(":")) {
            normalized = normalized.split(":")[1];
        }

        final Material material = Material.matchMaterial(normalized);
        if (material == null) {
            throw new IllegalArgumentException("Invalid material: " + type);
        }

        User user = context.skillCache().getPlayer(player.getName());
        return new ItemBuilder(material, amount)
                .name(name)
                .lore(lore)
                .replace("{name}", user.name())
                .replace("{class}", user.className().name())
                .replace("{level}", String.valueOf((user.level())))
                .replace("{progress}", String.valueOf(user.progress()))
                .build();
    }
}
