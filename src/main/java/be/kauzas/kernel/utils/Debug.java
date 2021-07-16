package be.kauzas.kernel.utils;

public class Debug implements Debugger {

    private static Debug instance;

    @Override
    public void debug(Object obj) {
        System.out.println(ConsoleColor.RED + "[D] " + obj + ConsoleColor.RESET);
    }

    public static void d(Object obj) {
        if (instance == null) instance = new Debug();
        instance.debug(obj);
    }

}
