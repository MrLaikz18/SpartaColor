package fr.mrlaikz.spartacolor.interfaces;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public interface PlayerListener {

    void onJoin(PlayerJoinEvent e);
    void onLeave(PlayerQuitEvent e);

}
