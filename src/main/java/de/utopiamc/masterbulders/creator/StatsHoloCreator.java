package de.utopiamc.masterbulders.creator;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class StatsHoloCreator {

    private final Location bottomLocation;
    private final Integer updateInterval;
    private final String databaseName;
    private final String title;

    public StatsHoloCreator(String world, Double x, Double y, Double z, String databaseName, Integer updateInterval, String title){
        this.bottomLocation = new Location(Bukkit.getWorld(world), x, y, z);
        this.databaseName = databaseName;
        this.updateInterval = updateInterval;
        this.title = title;
    }
}
