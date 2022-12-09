package fr.mrlaikz.spartacolor.managers;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.objects.Case;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private SpartaColor plugin;

    public MapManager(SpartaColor plugin) {
        this.plugin = plugin;
    }

    public List<Case> getMapCases(String map) {
        List<Case> ret = new ArrayList<Case>();
        for(int i = 0; i<15; i++) {
            String strMat = SpartaColor.getInstance().getConfig().getString("map." + map + "." + String.valueOf(i) + ".material");
            Material mat = Material.matchMaterial(strMat);
            Location loc1 = SpartaColor.getInstance().getConfig().getLocation("map." + map + "." + String.valueOf(i) + ".loc1");
            Location loc2 = SpartaColor.getInstance().getConfig().getLocation("map." + map + "." + String.valueOf(i) + ".loc2");
            Case c = new Case(i, mat, loc1, loc2);
            ret.add(c);
        }
        return ret;
    }

}
