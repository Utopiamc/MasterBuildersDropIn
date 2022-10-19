package de.utopiamc.masterbulders.creator;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SkullBuilder {

    private ItemStack itemStack;
    private SkullMeta skullMeta;

    public SkullBuilder(String owner) {
        itemStack = new ItemStack(Material.SKULL_ITEM);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
    }

    public SkullBuilder(OfflinePlayer player) {
        itemStack = new ItemStack(Material.SKULL_ITEM);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwningPlayer(player);
    }

    public SkullBuilder(PlayerProfile player) {
        itemStack = new ItemStack(Material.SKULL_ITEM);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setPlayerProfile(player);
    }

    public SkullBuilder addLineLore(String loreLine) {
        List<String> lore;
        if (skullMeta.hasLore()){
            lore = skullMeta.getLore();
        }else {
            lore = new ArrayList<>();
        }
        lore.add(loreLine);
        skullMeta.setLore(lore);

        return this;
    }

    public SkullBuilder(String value, String name) {
        itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);

        skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", value));

        try
        {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);

        }
        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
        {
            error.printStackTrace();
        }
        itemStack.setItemMeta(skullMeta);
    }

    public SkullBuilder setDisplayName(String displayname) {
        skullMeta.setDisplayName(displayname);
        return this;
    }

    public SkullBuilder setLore(List<String> lore) {
        skullMeta.setLore(lore);
        return this;
    }

    public SkullBuilder setUnbreakable(Boolean unbreakable) {
        skullMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public static ItemStack createSkull(String url)
    {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if (url.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try
        {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);

        }
        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
        {
            error.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public static ItemStack createPlayerSkull(String name){
        ItemStack myAwesomeSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
        myAwesomeSkullMeta.setOwner(name);
        myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);

        return myAwesomeSkull;
    }

}
