package fr.mrlaikz.spartacolor.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Case {

    private int id;
    private Material material;
    private Location loc1;
    private Location loc2;

    public Case(int id, Material material, Location loc1, Location loc2) {
        this.id = id;
        this.material = material;
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    //LOCATIONS
    public void set() {
        for(int i = (int)loc1.getX(); i<(int)loc2.getX(); i++) {
            for(int j = (int)loc1.getZ(); j<(int)loc2.getZ(); j++) {
                Location loc = new Location(Bukkit.getWorld("world"), i, loc1.getY(), j);
                loc.getBlock().setType(material);
            }
        }
    }

    public void remove() {
        for(int i = (int)loc1.getX(); i<(int)loc2.getX(); i++) {
            for(int j = (int)loc1.getZ(); j<(int)loc2.getZ(); j++) {
                Location loc = new Location(Bukkit.getWorld("world"), i, loc1.getY(), j);
                loc.getBlock().setType(Material.AIR);
            }
        }
    }

}
