package de.utopiamc.masterbulders.timer;

import com.google.inject.Inject;
import de.utopiamc.framework.api.stereotype.Service;
import de.utopiamc.framework.api.tasks.Task;
import de.utopiamc.framework.api.tasks.TaskService;
import de.utopiamc.masterbulders.config.LobbyMetaConfig;
import de.utopiamc.masterbulders.scoreboard.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

@Service
public class Timer {

    private final TaskService taskService;
    private final Scoreboard scoreboard;
    private Task task;
    private int time;

    @Inject
    public Timer(TaskService taskService, Scoreboard scoreboard){
        this.taskService = taskService;
        this.scoreboard = scoreboard;
    }

    public void startTimer(Integer time, Runnable runnable){
        if (task != null) {
            task.cancel();
        }
        taskService.runSync(() -> {
            this.time = time;
            task = taskService.repeat(() -> {
                scoreboard.getTimer().set(generateTimerTitle(this.time));
                if (this.time == 0) {
                    runnable.run();
                    task.cancel();
                }
                if (this.time == 360 || this.time == 280 || this.time == 120 || this.time == 60 || this.time == 30 || this.time == 10 || this.time == 5){
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.sendMessage(LobbyMetaConfig.prefix + "Noch §e" + generateTimerTitle(this.time) + " §7Minuten");
                    });
                }
                if (this.time < 6 && this.time > 0){
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.sendTitle("§7Zeit verbleibend§8: §c", "§a§l" + this.time, 0, 20, 0);
                        player.sendMessage(LobbyMetaConfig.prefix + "Noch §e" + this.time + " §7Sekunden");
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    });
                }
                this.time--;
            }, 20);
        });
    }

    private static String generateTimerTitle(Integer time){
        return String.format("%02d:%02d", Double.valueOf(Math.floor(time / 60.0)).intValue(), time % 60);
    }
}
