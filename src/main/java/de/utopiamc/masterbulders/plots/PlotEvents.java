package de.utopiamc.masterbulders.plots;

import de.utopiamc.framework.api.stereotype.Controller;

@Controller
public class PlotEvents {

//    private final PlotManager plotManagers;
//
//    @Inject
//    public PlotEvents(PlotManager plotManager) {
//        this.plotManagers = plotManager;
//    }
//
//    @Subscribe(event = BlockBreakEvent.class)
//    public void onBreak(@Event BlockBreakEvent e){
//        if(plotManagers.getPlotManagers().containsKey(e.getPlayer().getUniqueId())){
//            final int centerX = plotManagers.getPlotManagers().get(e.getPlayer().getUniqueId()).getX();
//            final int centerZ = plotManagers.getPlotManagers().get(e.getPlayer().getUniqueId()).getZ();
//
//            if(e.getBlock().getLocation().getBlockX() >= centerX - 13 && e.getBlock().getLocation().getBlockX() <= centerX + 13) {
//                e.setCancelled(true);
//            }
//            if (e.getBlock().getLocation().getBlockZ() >= centerZ - 13 && e.getBlock().getLocation().getBlockZ() <= centerZ + 13) {
//                e.setCancelled(true);
//            }
//        }
//    }

}
