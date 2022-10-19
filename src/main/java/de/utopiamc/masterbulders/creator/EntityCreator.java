package de.utopiamc.masterbulders.creator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;

import java.util.HashMap;
import java.util.UUID;

public class EntityCreator {

    private static final HashMap<String, UUID> entities = new HashMap<>();
    private static final HashMap<UUID, String> identifiers = new HashMap<>();

    private final Entity entity;
    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final EntityType entityType;

    public EntityCreator(String world, double x, double y, double z, float yaw, float pitch, EntityType type, String name, String identifier) {
        Entity entity = Bukkit.getWorld(world)
                .spawnEntity(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch), type);
        entity.setCustomNameVisible(true);
        entity.setCustomName(name);

        entities.put(identifier, entity.getUniqueId());
        identifiers.put(entity.getUniqueId(), identifier);

        this.entity = entity;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.entityType = type;
    }

    public EntityCreator setAI(boolean ai) {
        ((LivingEntity) entity).setAI(ai);
        return this;
    }

    public EntityCreator setGlowing(boolean glowing) {
        entity.setGlowing(glowing);
        return this;
    }

    public EntityCreator setInvulnerable(boolean invulnerable) {
        entity.setInvulnerable(invulnerable);
        return this;
    }

    public EntityCreator setSilent(boolean silent) {
        entity.setSilent(silent);
        return this;
    }

    public EntityCreator setGravity(boolean gravity) {
        entity.setGravity(gravity);
        return this;
    }

    public EntityCreator setCollidable(boolean collidable) {
        ((LivingEntity) entity).setCollidable(collidable);
        return this;
    }

    public EntityCreator setVisible(boolean visible){
        ((ArmorStand) entity).setVisible(visible);
        return this;
    }

    public EntityCreator setBaby(boolean baby) {
        ((Ageable) entity).setBaby();
        return this;
    }

    public EntityCreator setClickArmorStand(){
        ArmorStand armorStand = (ArmorStand) Bukkit.getWorld(world)
                .spawnEntity(new Location(Bukkit.getWorld(world), x, y + (entityType == EntityType.IRON_GOLEM? 0.45: -0.25), z, yaw, pitch), EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName("§7§l[§e§l§oKlicke mich§7§l]");
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);

        return this;
    }

    public Entity getEntity() {
        return entity;
    }

    public static UUID getEntity(String identifier) {
        return entities.get(identifier);
    }

    public static String getIdentifier(Entity entity) {
        if (identifiers.containsKey(entity.getUniqueId())) return identifiers.get(entity.getUniqueId());
        return null;
    }
}
