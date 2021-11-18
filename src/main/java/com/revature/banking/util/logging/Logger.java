package com.revature.banking.util.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {

    private static Logger logger;
    private final boolean printToConsole;

    private Logger(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public static Logger getLogger(boolean printToConsole) {
        if (logger == null) {
            logger = new Logger(printToConsole);
        }
        return logger;
    }

    public void log(String message, Object... args) {
        try (Writer logWriter = new FileWriter("src/main/resources/logs/transactions.log", true)) {

            String formattedMsg = String.format(message, args);
            logWriter.write(formattedMsg + "\n");

            if(printToConsole) {
                System.out.println(formattedMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
