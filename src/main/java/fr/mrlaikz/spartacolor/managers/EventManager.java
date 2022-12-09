package fr.mrlaikz.spartacolor.managers;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.objects.Case;
import fr.mrlaikz.spartacolor.objects.Event;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private SpartaColor plugin;
    private Event event;

    public EventManager(SpartaColor plugin) {
        this.plugin = plugin;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

}
