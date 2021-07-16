package be.kauzas.kernel.options;

import org.bukkit.command.CommandSender;

/**
 * Option that add restriction
 * to the implementing class.
 */
public interface Restricted {

    /**
     * Method that define if the player has the permission.
     *
     * @param permissible Entity to check permission.
     * @return true if the use has permission, otherwise false.
     */
    boolean hasPermission(CommandSender permissible);

    /**
     * Method that is executed when a player try to
     * access the feature without the permission.
     *
     * @param permissible Entity that tried to access the feature.
     */
    void onPermissionDenied(CommandSender permissible);

}
