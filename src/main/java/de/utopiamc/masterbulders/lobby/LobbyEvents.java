package de.utopiamc.masterbulders.lobby;

import com.google.inject.Inject;
import de.utopiamc.framework.api.event.Subscribe;
import de.utopiamc.framework.api.event.qualifier.Event;
import de.utopiamc.framework.api.stereotype.Controller;
import de.utopiamc.masterbulders.Enums.GameStateEnum;
import de.utopiamc.masterbulders.config.LobbyMetaConfig;
import de.utopiamc.masterbulders.manager.GameStateManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

@Controller
public class LobbyEvents {
    
    private final GameStateManager gameStateManager;

    @Inject
    public LobbyEvents(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Subscribe(event = PlayerJoinEvent.class)
    public void onJoin(@Event PlayerJoinEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -127.5, 107, 212.5, 180, 0));
            e.setJoinMessage(LobbyMetaConfig.prefix + "§7Der Spieler §e" + e.getPlayer().getName() + " §7hat das Spiel betreten!");
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }

    }

    @Subscribe(event = PlayerQuitEvent.class)
    public void onQuit(@Event PlayerQuitEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.setQuitMessage(LobbyMetaConfig.prefix + "§7Der Spieler §e" + e.getPlayer().getName() + " §7hat das Spiel verlassen!");
        }

    }

    @Subscribe(event = EntityDamageEvent.class)
    public void onDammage(@Event EntityDamageEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.setCancelled(true);
        }

    }

    @Subscribe(event = PlayerInteractEvent.class)
    public void onInteract(@Event PlayerInteractEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.setCancelled(true);
        }

    }

    @Subscribe(event = PlayerPickupItemEvent.class)
    public void onPickUp(@Event PlayerPickupItemEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.setCancelled(true);
        }

    }

    @Subscribe(event = PlayerDropItemEvent.class)
    public void onDrop(@Event PlayerDropItemEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.setCancelled(true);
        }

    }

    @Subscribe(event = PlayerArmorStandManipulateEvent.class)
    public void onPlayerArmorStandManipulateEvent(@Event PlayerArmorStandManipulateEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            e.setCancelled(true);
        }

    }


    @Subscribe(event = PlayerMoveEvent.class)
    public void onMove(@Event PlayerMoveEvent e){
        if (gameStateManager.getGameState().equals(GameStateEnum.Lobby)){
            if (e.getTo().getY() < 85){
                e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -127.5, 107, 212.5, 180, 0));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
            }
        }

    }


}
