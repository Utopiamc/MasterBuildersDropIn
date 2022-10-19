package de.utopiamc.masterbulders.manager;

import com.google.inject.Inject;
import de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetHelper;
import de.utopiamc.framework.api.stereotype.Service;
import de.utopiamc.masterbulders.Enums.GameStateEnum;
import de.utopiamc.masterbulders.Enums.Subjects;
import de.utopiamc.masterbulders.plots.PlotManager;
import de.utopiamc.masterbulders.scoreboard.Scoreboard;
import de.utopiamc.masterbulders.themevote.ThemeVoting;
import de.utopiamc.masterbulders.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

@Service
public class GameStateManagerImpl implements GameStateManager {

    public static final int BUILDING_MINUTES = 8;
    private final Timer timer;
    private final Scoreboard scoreboard;
    private final ThemeVoting themeVoting;
    private final PlotManager plotManager;
    private GameStateEnum gameState = GameStateEnum.Lobby;

    @Inject
    public GameStateManagerImpl(Timer timer, Scoreboard scoreboard, ThemeVoting themeVoting, PlotManager plotManager) {
        this.timer = timer;
        this.scoreboard = scoreboard;
        this.themeVoting = themeVoting;
        this.plotManager = plotManager;
    }

    public void setGameState(GameStateEnum gameStateEnum){
        gameState = gameStateEnum;
    }

    public void startGame(){
        setTheIdAutomatic();
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(plotManager.getHomeLocation(player.getUniqueId()));
        });

        BukkitCloudNetHelper.changeToIngame();
        gameState = GameStateEnum.ThemeVoting;
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle("§7Teleportiere..", "§7lade Welt", 10, 20*6,0);
        });

        scoreboard.getTimerTitle().set("§fZeit§8: ");
        scoreboard.getTimer().set("0:20");

        scoreboard.getSubjectTitle().set("§fThema§8:");
        scoreboard.getSubject().set("§aVoting...");

        themeVoting.openInvAll();

        timer.startTimer(20, this::startBuilding);
    }

    private void getHighestSubject(){
        Map<Subjects, Integer> votes = new HashMap<>();

        for (Map.Entry<UUID, Subjects> uuidSubjectsEntry : themeVoting.getPlayerVotes().entrySet()) {
            if (votes.containsKey(uuidSubjectsEntry.getValue())) {
                votes.put(uuidSubjectsEntry.getValue(), votes.get(uuidSubjectsEntry.getValue()) + 1);
            } else {
                votes.put(uuidSubjectsEntry.getValue(), 1);
            }
        }

        int highest = 0;
        Subjects subject = null;

        for (Map.Entry<Subjects, Integer> subjectsIntegerEntry : votes.entrySet()) {
            if (subjectsIntegerEntry.getValue() > highest) {
                highest = subjectsIntegerEntry.getValue();
                subject = subjectsIntegerEntry.getKey();
            }
        }

        themeVoting.setThema(subject);
    }

    public void startBuilding(){

        getHighestSubject();
        gameState = GameStateEnum.Building;
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.closeInventory();
            player.sendTitle("§7Thema§8: §a" + themeVoting.getThema(), "§7Bauzeit§8: §a8 Minuten", 10, 20*6,0);
            player.setGameMode(GameMode.CREATIVE);
        });
        scoreboard.getSubject().set("$p" + themeVoting.getThema());
        timer.startTimer(BUILDING_MINUTES * 60, this::startVoting);
    }

    public void startVoting(){
        gameState = GameStateEnum.Voting;
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.closeInventory();
            player.sendTitle("§7Bauzeit§8:", "§aVorbei!", 10, 20*6,0);
            player.setGameMode(GameMode.SPECTATOR);
        });
        timer.startTimer( 5, () -> {

        });
    }

    public void endGame(){
        gameState = GameStateEnum.End;
        timer.startTimer(10, Bukkit::shutdown);
    }


    public void setTheIdAutomatic(){
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

        int i1 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i1 < players.size()) {
                    Player p = players.get(i1);

                    new PlotManager(i, j, new Location(Bukkit.getWorld("world1"), ((i * 100) + 3.5),99.01, ((j * 100) - 0.5)), List.of(p.getUniqueId()));

                    i1++;
                }
            }
        }
    }

    public GameStateEnum getGameState() {
        return gameState;
    }
}
