package de.utopiamc.masterbulders.plots;

import org.bukkit.Location;
import org.bukkit.Particle;

import java.util.ArrayList;

public class ParticleManager {

    public static ArrayList<ParticleManager> particleManagers = new ArrayList<>();

    private final Location location;
    private final Particle particle;
    private final PlotManager betterPlotID;

    public ParticleManager(Location loc, Particle particle1, PlotManager betterPlot){
        location = loc;
        particle = particle1;
        betterPlotID = betterPlot;

        particleManagers.add(this);

        betterPlot.addPlotParticle(this);
    }

    public Location getLocation() {
        return location;
    }

    public Particle getParticle() {
        return particle;
    }

    public PlotManager getBetterPlotID() {
        return betterPlotID;
    }
//
//    public static void startTimer() {
//        bt = Bukkit.getScheduler().runTaskTimer(MasterBuilders.getPlugin(MasterBuilders.class), () -> {
//
//            particleManagers.forEach(particleManager -> {
//                particleManager.getLocation().getWorld().spawnParticle(particleManager.getParticle(), particleManager.getLocation(), 20, 0.8, 0.8,0.8, 0.00005);
//            });
//
//            Bukkit.getOnlinePlayers().forEach(player -> {
//                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8> §7Partikel entferner")){
//                    particleManagers.forEach(particleManager -> {
//                        player.spawnParticle(Particle.BARRIER, particleManager.getLocation(), 1, 0, 0,0, 0.00005);
//                    });
//                }
//            });
//
//        }, 0L, 20);
//    }
}
