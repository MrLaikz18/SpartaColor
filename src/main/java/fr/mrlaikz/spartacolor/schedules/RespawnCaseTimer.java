package fr.mrlaikz.spartacolor.schedules;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.objects.Case;
import fr.mrlaikz.spartacolor.objects.Event;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnCaseTimer extends BukkitRunnable {

    private int timer = SpartaColor.getInstance().getConfig().getInt("timer.respawn_case");
    private Event event;
    private int index;
    private Case c;

    public RespawnCaseTimer(Event event, int index, Case c) {
        this.event = event;
        this.index = index;
        this.c = c;
    }

    @Override
    public void run() {
        if(timer == 0) {
            c.set();
            cancel();
        }
        timer--;
    }
}
