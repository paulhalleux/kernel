package be.kauzas.kernel.utils;

/**
 * Debugger implementation for printing debugs
 * in console.
 */
public class Debug implements Debugger {

    private static Debug instance;

    /**
     * Print the given object on console with [D] prefix and red color.
     *
     * @param obj Object to debug.
     */
    @Override
    public void debug(Object obj) {
        System.out.println(ConsoleColor.RED + "[D] " + obj + ConsoleColor.RESET);
    }

    /**
     * Static method to debug quickly.
     *
     * @param obj Object to debug.
     */
    public static void d(Object obj) {
        if (instance == null) instance = new Debug();
        instance.debug(obj);
    }

}
