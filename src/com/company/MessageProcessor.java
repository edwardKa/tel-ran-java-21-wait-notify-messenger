package com.company;

import java.util.Scanner;

/**
 * @author Edward Kats
 */
public class MessageProcessor {

    private static final String EXIT_KEYWORD = "exit";
    private volatile boolean isExitCommand = false;

    public boolean isExitCommand() {
        return isExitCommand;
    }

    private boolean isExit(String message) {
        boolean res = message.equalsIgnoreCase(EXIT_KEYWORD);
        if (res) {
            this.isExitCommand = true;
        }
        return res;
    }



    public void sender() {
        synchronized (this) {
            Scanner scanner = new Scanner(System.in);
            if (isExitCommand()) {
                return;
            }

            System.out.print("User 1: ");
            String message = scanner.nextLine();
            if (isExit(message)) {
                return;
            }
            System.out.println(message);
            try { wait(); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void receiver() {
        synchronized (this) {
            if (isExitCommand()) {
                return;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("User 2: ");
            String message = scanner.nextLine();
            if (isExit(message)) {
                notify();
                return;
            }
            System.out.println(message);
            try { Thread.sleep(100); }
            catch (InterruptedException e) { e.printStackTrace(); }
            notify();
        }

    }
}
