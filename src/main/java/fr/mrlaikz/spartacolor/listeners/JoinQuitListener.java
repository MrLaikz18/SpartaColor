package fr.mrlaikz.spartacolor.listeners;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.interfaces.PlayerListener;
import fr.mrlaikz.spartacolor.objects.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener {

    private SpartaColor plugin;

    public JoinQuitListener(SpartaColor plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Event event = plugin.getEventManager().getEvent();
        if(event instanceof PlayerListener) {
            ((PlayerListener) event).onJoin(e);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Event event = plugin.getEventManager().getEvent();
        if(event instanceof PlayerListener) {
            ((PlayerListener) event).onLeave(e);
        }
    }

}
