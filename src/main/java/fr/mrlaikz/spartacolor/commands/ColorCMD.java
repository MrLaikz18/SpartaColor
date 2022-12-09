package fr.mrlaikz.spartacolor.commands;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.objects.Event;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ColorCMD implements CommandExecutor {

    private SpartaColor plugin;

    public ColorCMD(SpartaColor plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {

            Player p = (Player) sender;

            if(p.hasPermission("spartacolor.manage")) {

                if(args.length == 1 && args[0].equalsIgnoreCase("start")) {
                    Event e = plugin.getEventManager().getEvent();
                    e.preStart();
                }

                if(args.length == 2 && args[0].equalsIgnoreCase("prepare")) {
                    String map = args[1];
                    Event event = new Event(map);
                    plugin.getEventManager().setEvent(event);
                    plugin.getEventManager().getEvent().prepare();
                }

            }

        }

        return false;
    }
}
