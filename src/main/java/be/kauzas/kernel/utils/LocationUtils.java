package be.kauzas.kernel.utils;

import org.bukkit.Location;

public class LocationUtils {

    public static double distance(Location first, Location second) {
        return square(first.getX() - second.getX()) + square(first.getY() - second.getY()) + square(first.getZ() - second.getZ());
    }

    private static double square(double val) {
        return Math.pow(val, 2);
    }

    public static String toString(Location location) {
        return "x: " + location.getBlockX() + " y: " + location.getBlockY() + " z: " + location.getBlockZ();
    }

    public static String toStringWithWorld(Location location) {
        return toString(location) + " (" + location.getWorld().getName() + ")";
    }

}
