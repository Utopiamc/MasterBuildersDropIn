package de.utopiamc.masterbulders.anticheat;

import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import org.bukkit.event.player.PlayerMoveEvent;

@Controller
public class AntiCheat {

    @Subscribe(event = PlayerMoveEvent.class)
    public void onMove(@Event PlayerMoveEvent e){
        if (e.getFrom().getWorld() == e.getTo().getWorld() && e.getFrom().distance(e.getTo()) > 1.152){
            if (e.getPlayer().isFlying()) {
                e.setCancelled(true);
            }
        }
    }
}
