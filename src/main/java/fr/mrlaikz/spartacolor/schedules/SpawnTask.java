package fr.mrlaikz.spartacolor.schedules;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.objects.Event;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnTask extends BukkitRunnable {

    private int timer = SpartaColor.getInstance().getConfig().getInt("timer.spawn");
    private Event event;

    public SpawnTask(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        if(timer == 0) {
            event.pick();
            cancel();
        }
        timer--;
    }
}
