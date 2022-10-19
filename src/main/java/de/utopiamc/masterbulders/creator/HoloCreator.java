package de.utopiamc.masterbulders.creator;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

@Builder
public class HoloCreator {

    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private UUID uuid;
    private Set<UUID> armorStands = new HashSet<>();

    @Getter
    private final Consumer<Player> onClick;
    private final List<String> lines;

    public void spawn() {
        for (int i = 0; i < lines.size(); i++) {
            ArmorStand as = (ArmorStand) Bukkit.getWorld(world).spawnEntity(new Location(Bukkit.getWorld(world), x, (y - (i * 0.3)) + (lines.size() * 0.3), z), EntityType.ARMOR_STAND);
            as.setCustomName(lines.get(i));
            as.setCustomNameVisible(true);
            as.setInvulnerable(true);
            as.setVisible(false);
            as.setGravity(false);
            armorStands.add(as.getUniqueId());
        }
    }

    public void bind(Map<UUID, HoloCreator> map) {
        armorStands.forEach(uuid -> map.put(uuid, this));
    }
}
