package be.kauzas.kernel.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class FileUtils {

    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void mkdir(File file) {
        try {
            file.mkdir();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static File loadFile(JavaPlugin plugin, String name) {
        if (!plugin.getDataFolder().exists()) {
            mkdir(plugin.getDataFolder());
        }

        File f = new File(plugin.getDataFolder(), name);
        if (!f.exists()) {
            copy(plugin.getResource(name), f);
        }

        return f;
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        if (file == null) return;
        if (inputStream == null) return;
        if (inputStream.available() == 0) return;
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[inputStream.available()];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }

}