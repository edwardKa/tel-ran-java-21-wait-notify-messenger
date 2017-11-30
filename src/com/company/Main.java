package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        MessageProcessor messageProcessor = new MessageProcessor();

        while (!messageProcessor.isExitCommand()) {


            Thread user1 = new Thread() {
                @Override
                public void run() {
                    messageProcessor.sender();
                }
            };
            Thread user2 = new Thread() {
                @Override
                public void run() {
                    messageProcessor.receiver();
                }
            };

            user1.start();
            user2.start();

            user1.join();
            user2.join();
        }
    }
}