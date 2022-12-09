package fr.mrlaikz.spartacolor.schedules;

import fr.mrlaikz.spartacolor.SpartaColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private SpartaColor plugin;
    private int timer;

    public StartTask(SpartaColor plugin) {
        this.plugin = plugin;
        this.timer = plugin.getConfig().getInt("timer.start");
    }

    @Override
    public void run() {

        if(plugin.getConfig().getIntegerList("timer.broadcast").contains(timer)) {
            Bukkit.broadcastMessage(plugin.strConfig("broadcast.start_in")
                    .replace("%time%", String.valueOf(timer)));
        }

        if(timer == 0) {
            plugin.getEventManager().getEvent().start();
            cancel();
        }

        timer--;
    }

}
