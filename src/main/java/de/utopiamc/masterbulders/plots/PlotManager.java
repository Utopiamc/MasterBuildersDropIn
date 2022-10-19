package de.utopiamc.masterbulders.plots;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlotManager {

    public HashMap<UUID, PlotManager> plotManagers = new HashMap<>();

    private final Integer x;
    private final Integer z;
    private final Location homeLocation;
    private final List<UUID> players;
    private ArrayList<Entity> entitysOnPlot;
    private ArrayList<ParticleManager> particelsOnPlot;

    public PlotManager(Integer x, Integer z, Location homeLocation, List<UUID> players) {
        this.x = x;
        this.z = z;
        this.homeLocation = homeLocation;
        this.players = players;
        this.entitysOnPlot = new ArrayList<>();
        this.particelsOnPlot = new ArrayList<>();

        players.forEach(player -> {
            plotManagers.put(player, this);
        });
    }

    public void addPlotParticle(ParticleManager particleManager){
        particelsOnPlot.add(particleManager);
    }

    public void removePlotParticle(ParticleManager particleManager){
        particelsOnPlot.remove(particleManager);
        ParticleManager.particleManagers.remove(particleManager);
    }

    public Location getHomeLocation(UUID player){
        if(plotManagers.containsKey(player)){
            return plotManagers.get(player).getHomeLocation();
        }
        return null;
    }
}
