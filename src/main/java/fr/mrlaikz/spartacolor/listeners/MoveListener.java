package fr.mrlaikz.spartacolor.listeners;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.interfaces.FallListener;
import fr.mrlaikz.spartacolor.objects.Event;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    private SpartaColor plugin;

    public MoveListener(SpartaColor plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        final Location from = e.getFrom();
        final Location to = e.getTo();

        int x = Math.abs(from.getBlockX() - to.getBlockX());
        int y = Math.abs(from.getBlockY() - to.getBlockY());
        int z = Math.abs(from.getBlockZ() - to.getBlockZ());

        if (x == 0 && y == 0 && z == 0) return;

        Event event = plugin.getEventManager().getEvent();

        if (event instanceof FallListener) {
            ((FallListener) event).onFall(e);
        }
    }

}
