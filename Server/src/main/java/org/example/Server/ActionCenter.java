package org.example.Server;

import java.io.IOException;

public class ActionCenter extends Configuration {

    private final ServerSender sender = new ServerSender();

    public void execute(String input) throws IOException {
        var filteredInput = input.split(" ", 3);
        if (filteredInput.length != 2) {
            System.out.println("Invalid input!!! \n -h for help");
            return;
        }

        switch (filteredInput[0]) {
            case "-u" -> sendMessage(filteredInput);
            case "-c" -> executeCommand(filteredInput);
            case "-h" -> printHelp();
            default -> System.out.println("Invalid syntax! \n use -h for help");
        }
    }

    private void printHelp() {
        System.out.println("""
                -u [id] [message]
                -c [command]
                -h Print help message & exit
                """);
    }

    private void executeCommand(String[] filteredInput) {
        if (filteredInput.length != 2) {
            System.out.println("Invalid command syntax");
            System.out.println("-h for help");
            return;
        }

        var command = filteredInput[1].toLowerCase();
        switch (command) {
            case "stop" -> session.set(false);
            case "something" -> System.out.println("Do something here");
            default -> System.out.println("Invalid command \n use -h for help");
        }
    }

    private void sendMessage(String[] filteredInput) throws IOException {
        if (filteredInput.length != 3) {
            System.out.println("Invalid message sending syntax");
            System.out.println("-h for help");
            return;
        }

        var id = Integer.parseInt(filteredInput[1]);
        var message = filteredInput[2];
        sender.send(id, message);
    }
}
