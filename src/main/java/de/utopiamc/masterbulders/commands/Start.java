package de.utopiamc.masterbulders.commands;

import com.google.inject.Inject;
import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.masterbulders.manager.GameStateManager;

@Command(value = "start", aliases = {"s"})
public class Start {

    private final GameStateManager gameStateManager;

    @Inject
    public Start(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @MapRoute("")
    public void Command() {
        gameStateManager.startGame();
    }

}
