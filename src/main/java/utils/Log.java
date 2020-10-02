package utils;


import org.apache.log4j.Logger;


public class Log {

    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(utils.Log.class.getName()); //log4j 1.21
//	private static Logger Log = LogManager.getLogger(Log.class.getName()); //log4j2

    //We can use it when starting tests
    public static void startLog(String testClassName) {
        Log.info("============================================================");
        Log.info("===== Test Class: " + testClassName + " is Starting... ======");
    }

    //We can use it when ending tests
    public static void endLog(String testClassName) {
        Log.info("===== Test Class: " + testClassName + " is Ending... ======");
    }

    //We can use it when starting tests
    public static void startMethodLog(String testMethodName) {
        Log.info("------------------------------------------------------------");
        Log.info("=== Test Method: " + testMethodName + " is Starting... ====");
    }

    //We can use it when ending tests
    public static void endMethodLog(String testMethodName) {
        Log.info("=== Test Method: " + testMethodName + " is Ending... ====");
    }

    //Info Level Logs
    public static void info(String string) {
        Log.info(string);
    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug(message);
    }
}