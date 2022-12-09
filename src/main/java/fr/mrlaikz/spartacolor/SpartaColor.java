package fr.mrlaikz.spartacolor;

import fr.mrlaikz.spartacolor.commands.ColorCMD;
import fr.mrlaikz.spartacolor.listeners.MoveListener;
import fr.mrlaikz.spartacolor.managers.EventManager;
import fr.mrlaikz.spartacolor.managers.MapManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpartaColor extends JavaPlugin {

    public static SpartaColor instance;
    private EventManager eventManager;
    private MapManager mapManager;

    @Override
    public void onEnable() {
        //VARS
        saveDefaultConfig();
        this.instance = this;
        this.eventManager = new EventManager(this);
        this.mapManager = new MapManager(this);

        //LISTENERS
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);

        //COMMANDS
        getCommand("color").setExecutor(new ColorCMD(this));

        //MISC
        System.out.println("Plugin Actif");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Innactif");
    }

    public String strConfig(String path) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(path));
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public static SpartaColor getInstance() {
        return instance;
    }

}
