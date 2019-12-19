package DesignPatternSingleton;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // TODO
    }

    public static Singleton getInstance() {
        if (instance == null) {
            // block which creates instance is synchronized so that minimum number of threads have to wait
            // and that’s only for first time.
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

// Singleton singleton1 = Singleton.getInstance();

