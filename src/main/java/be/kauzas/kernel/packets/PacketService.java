package be.kauzas.kernel.packets;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.Environment;
import be.kauzas.kernel.service.ReflectionService;
import be.kauzas.kernel.service.RegisterResult;
import be.kauzas.kernel.utils.ConsoleColor;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

/**
 * Packet listener service that handle
 * automatic listener registration.
 */
public class PacketService extends ReflectionService<PacketListener> {

    private final List<PacketListener> items;
    private final BasePlugin basePlugin;

    /**
     * Constructor of {@link PacketService} asking for the
     * name of the package that contains the packets listeners.
     */
    public PacketService(String pkg, BasePlugin plugin) {
        super(new Reflections(pkg, plugin.getClass().getClassLoader()), plugin);
        this.items = new ArrayList<>();
        this.basePlugin = plugin;
    }

    /**
     * Method that register a {@link PacketListener}.
     *
     * @param packetListener {@link PacketListener} to register.
     */
    @Override
    public RegisterResult register(PacketListener packetListener) {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(basePlugin, packetListener.getType()) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                packetListener.listen(basePlugin, event);
            }
        });
        items.add(packetListener);
        return RegisterResult.SUCCESS;
    }

    /**
     * Get list of registered listeners.
     *
     * @return List of registered {@link PacketListener}.
     */
    @Override
    public List<PacketListener> getItems() {
        return items;
    }

    /**
     * Method that is executed when a listener is registered.
     *
     * @param serviceName Registered listener.
     * @param result      Result of the registration.
     */
    @Override
    public void onRegister(String serviceName, RegisterResult result) {
        if (result == RegisterResult.FAILED && basePlugin.getEnvironment() != Environment.PRODUCTION)
            System.out.println(String.format(ConsoleColor.RED + "Packet listener %s could not be registered" + ConsoleColor.RESET, ConsoleColor.YELLOW + serviceName + ConsoleColor.RED));
    }

}
