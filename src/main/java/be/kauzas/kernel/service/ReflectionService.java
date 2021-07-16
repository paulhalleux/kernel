package be.kauzas.kernel.service;

import be.kauzas.kernel.BasePlugin;
import be.kauzas.kernel.Environment;
import be.kauzas.kernel.KernelPlugin;
import be.kauzas.kernel.utils.ConsoleColor;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract class implementing {@link Service} interface
 * and that can automatically register items from a package.
 *
 * @param <T> Type of the handled items.
 */
public abstract class ReflectionService<T> implements Service<T> {

    private final Reflections reflections;
    private final BasePlugin plugin;

    /**
     * Constructor of {@link ReflectionService} asking for the package.
     * Items in sub packages will be registered too.
     *
     * @param reflections Package that contains the items to register.
     */
    public ReflectionService(Reflections reflections, BasePlugin plugin) {
        this.reflections = reflections;
        this.plugin = plugin;
    }

    /**
     * Register automatically all the items from the
     * given package that match with the generic type.
     */
    @SuppressWarnings("unchecked")
    public void register() {
        try {
            Set<Class<? extends T>> items = reflections.getSubTypesOf((Class) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0]);

            int amount = 0;
            for (Class<? extends T> t : items.stream().filter(o -> !o.isAnnotationPresent(IgnoreService.class)).collect(Collectors.toList())) {
                T item = createInstance(t);
                if (item == null) {
                    onRegister(t.getSimpleName(), RegisterResult.FAILED);
                } else {
                    RegisterResult registerResult = register(item);
                    if (registerResult == null) registerResult = RegisterResult.FAILED;
                    onRegister(item.getClass().getSimpleName(), registerResult);
                    if (registerResult == RegisterResult.SUCCESS) amount++;
                }
            }

            if (plugin.getEnvironment() != Environment.PRODUCTION)
                System.out.println(ConsoleColor.GREEN + "Registered " + ConsoleColor.YELLOW + amount + ConsoleColor.GREEN + " item(s) for " + ConsoleColor.YELLOW + this.getClass().getSimpleName() + ConsoleColor.RESET);
        } catch (Exception e) {
            if (plugin.getEnvironment() != Environment.PRODUCTION)
                System.out.println(ConsoleColor.RED + "Could not register any item for " + ConsoleColor.YELLOW + this.getClass().getSimpleName() + ConsoleColor.RESET);
        }
    }

    @SuppressWarnings("unchecked")
    private T createInstance(Class<? extends T> t) {
        try {
            return t.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            try {
                return t.getDeclaredConstructor(KernelPlugin.class).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                return null;
            }
        }
    }

}
