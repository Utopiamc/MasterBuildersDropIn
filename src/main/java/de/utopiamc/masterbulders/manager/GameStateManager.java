package de.utopiamc.masterbulders.manager;

import de.utopiamc.masterbulders.Enums.GameStateEnum;

public interface GameStateManager {
    void setGameState(GameStateEnum gameStateEnum);
    void startGame();
    void startBuilding();
    GameStateEnum getGameState();
}
