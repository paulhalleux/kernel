package be.kauzas.kernel.enchantment;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.Environment;
import be.kauzas.kernel.service.ReflectionService;
import be.kauzas.kernel.service.RegisterResult;
import be.kauzas.kernel.utils.ConsoleColor;
import org.bukkit.enchantments.Enchantment;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantmentService extends ReflectionService<AbstractEnchantment> {

    private final BasePlugin plugin;
    private final List<AbstractEnchantment> enchantments;

    /**
     * Constructor of {@link EnchantmentService} asking for the
     * name of the package that contains the enchantments.
     */
    public EnchantmentService(String pkg, BasePlugin plugin) {
        super(new Reflections(pkg, plugin.getClass().getClassLoader()), plugin);
        this.plugin = plugin;
        this.enchantments = new ArrayList<>();
    }

    /**
     * Method that register an {@link AbstractEnchantment}.
     *
     * @param abstractEnchantment {@link AbstractEnchantment} to register.
     */
    @Override
    public RegisterResult register(AbstractEnchantment abstractEnchantment) {
        if (Arrays.stream(Enchantment.values()).noneMatch(enchantment -> enchantment.getName().equalsIgnoreCase(abstractEnchantment.getName()))) {
            try {
                Field field = Enchantment.class.getDeclaredField("acceptingNew");
                field.setAccessible(true);
                field.set(null, true);
                Enchantment.registerEnchantment(abstractEnchantment);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return RegisterResult.FAILED;
            }
        }
        enchantments.add(abstractEnchantment);
        plugin.getListenerService().register(abstractEnchantment);
        return RegisterResult.SUCCESS;
    }

    /**
     * Get list of registered enchantments.
     *
     * @return List of registered {@link AbstractEnchantment}.
     */
    @Override
    public List<AbstractEnchantment> getItems() {
        return enchantments;
    }

    /**
     * Method that is executed when an enchantment is registered.
     *
     * @param serviceName Registered enchantment.
     * @param result      Result of the registration.
     */
    @Override
    public void onRegister(String serviceName, RegisterResult result) {
        if (result == RegisterResult.FAILED && plugin.getEnvironment() != Environment.PRODUCTION)
            System.out.println(String.format(ConsoleColor.RED + "Enchantment %s could not be registered" + ConsoleColor.RESET, ConsoleColor.YELLOW + serviceName + ConsoleColor.RED));
    }

}
