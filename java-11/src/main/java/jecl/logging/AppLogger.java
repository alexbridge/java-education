package jecl.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {
    static Level logLevel;

    static {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT %4$s [%3$s] %5$s%6$s%n"
        );
    }

    static {
        logLevel = Level.INFO;
        try {
            String level = System.getProperty("LOGGER_LEVEL");
            if (level != null) {
                logLevel = Level.parse(level);
            }
        } catch (IllegalArgumentException ignore) {}
    }

    public static Logger getLogger(Class<?> clazz ) {
        Logger logger = Logger.getLogger(clazz.getSimpleName());
        logger.setLevel(logLevel);
        return logger;
    }
}
