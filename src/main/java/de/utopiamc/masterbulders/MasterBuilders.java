package de.utopiamc.masterbulders;

import de.utopiamc.framework.api.dropin.annotations.OnEnable;
import de.utopiamc.framework.api.stereotype.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;

@Plugin
public class MasterBuilders {

    @OnEnable
    public void onEnable() {
        MasterBuilders.onLoadMap("world1");
    }

    public static void onLoadMap(String worldname){
        if (!Bukkit.getWorlds().contains(Bukkit.getWorld(worldname))) {
            WorldCreator wc = new WorldCreator(worldname);
            wc.environment(World.Environment.NORMAL);
            wc.createWorld();
            Bukkit.getWorld(worldname).loadChunk(-4, 3);
            Bukkit.getWorld(worldname).setGameRuleValue("doFireTick", "false");
            Bukkit.getWorld(worldname).setGameRuleValue("announceAdvancements", "false");
            Bukkit.getWorld(worldname).setDifficulty(Difficulty.PEACEFUL);
        }
    }

}
