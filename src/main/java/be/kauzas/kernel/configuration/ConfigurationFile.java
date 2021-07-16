package be.kauzas.kernel.configuration;

import be.kauzas.kernel.utils.FileUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Represent a custom configuration file.
 */
public class ConfigurationFile {

    private final JavaPlugin plugin;
    private final String name;
    private FileConfiguration fileConfiguration;
    private File file;

    /**
     * Constructor of {@link ConfigurationFile}.
     *
     * @param plugin Main plugin class.
     * @param name   Name of the configuration file with extension. (commonly YAML)
     */
    public ConfigurationFile(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        load();
    }

    /**
     * Load the file without updating fields.
     */
    public void load() {
        load(false, false);
    }

    /**
     * Load the file updating the existing fields and
     * updating the new one if specified.
     *
     * @param update true to add non existing field to file.
     */
    public void load(boolean update) {
        load(update, true);
    }

    /**
     * Load the file.
     *
     * @param updateNew true to add non existing field to file.
     * @param updateOld true to remove unused field from file.
     */
    public void load(boolean updateNew, boolean updateOld) {
        if (!this.plugin.getDataFolder().exists()) {
            FileUtils.mkdir(this.plugin.getDataFolder());
        }

        File f = new File(this.plugin.getDataFolder(), this.name);
        if (!f.exists()) {
            if (this.plugin.getResource(this.name) != null)
                FileUtils.copy(Objects.requireNonNull(this.plugin.getResource(this.name)), f);

        }

        this.file = f;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(f);
        this.fileConfiguration.options().copyDefaults(true);

        if (updateNew || updateOld) {
            File origin = new File(this.name);
            try {
                FileUtils.copyInputStreamToFile(this.plugin.getResource(this.name), origin);
                if (origin.exists()) {
                    FileConfiguration originConfig = YamlConfiguration.loadConfiguration(origin);
                    if (updateNew && originConfig.getKeys(false).size() > 0)
                        for (String key : originConfig.getKeys(false)) {
                            verifyExist(key, originConfig);
                        }
                    if (updateOld && fileConfiguration.getKeys(false).size() > 0)
                        for (String key : fileConfiguration.getKeys(false)) {
                            verifyNotExist(key, originConfig, fileConfiguration);
                        }
                    save();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            origin.delete();
        }
    }

    /**
     * Check if a field exist in field and create it if not.
     *
     * @param key    Field to check.
     * @param origin Original file from plugin.
     */
    private void verifyExist(String key, FileConfiguration origin) {
        if (!fileConfiguration.isSet(key)) {
            fileConfiguration.set(key, origin.get(key));
        }
        ConfigurationSection section = origin.getConfigurationSection(key);
        if (section != null) {
            for (String deepKey : section.getKeys(false)) {
                verifyExist(key + "." + deepKey, origin);
            }
        }
    }

    /**
     * Check if a field exist in field and remove it if exist.
     *
     * @param key    Field to check.
     * @param origin Original file from plugin.
     * @param file   Actual file from the data folder.
     */
    private void verifyNotExist(String key, FileConfiguration origin, FileConfiguration file) {
        if (!origin.isSet(key)) {
            file.set(key, null);
        }
        ConfigurationSection section = file.getConfigurationSection(key);
        if (section != null) {
            for (String deepKey : section.getKeys(false)) {
                verifyNotExist(key + "." + deepKey, origin, file);
            }
        }
    }

    /**
     * Save the file.
     */
    public void save() {
        try {
            this.fileConfiguration.options().copyDefaults(true);
            this.fileConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the {@link FileConfiguration} for the file.
     *
     * @return Configuration file object.
     */
    public FileConfiguration getConfig() {
        return this.fileConfiguration;
    }

}