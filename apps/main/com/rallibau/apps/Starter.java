package com.rallibau.apps;

import com.rallibau.apps.products.ProductsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

import java.util.HashMap;

public class Starter {
    public static void main(String[] args) {
        if (args.length < 2) {
            args = new String[2];
            args[0] = "products";
            args[1] = "server";
        }

        String applicationName = args[0];
        String commandName = args[1];
        boolean isServerCommand = commandName.equals("server");

        ensureApplicationExist(applicationName);
        ensureCommandExist(applicationName, commandName);

        Class<?> applicationClass = applications().get(applicationName);

        SpringApplication app = new SpringApplication(applicationClass);

        if (!isServerCommand) {
            app.setWebApplicationType(WebApplicationType.NONE);
        }

        app.run(args);


    }

    private static void ensureApplicationExist(String applicationName) {
        if (!applications().containsKey(applicationName)) {
            throw new RuntimeException(String.format(
                "The application <%s> doesn't exist. Valids:\n- %s",
                applicationName,
                String.join("\n- ", applications().keySet())
            ));
        }
    }

    private static void ensureCommandExist(String applicationName, String commandName) {
        if (!"server".equals(commandName) && !existCommand(applicationName, commandName)) {
            throw new RuntimeException(String.format(
                "The command <%s> for application <%s> doesn't exist. Valids (application.command):\n- api\n- %s",
                commandName,
                applicationName,
                String.join("\n- ", commands().get(applicationName).keySet())
            ));
        }
    }

    private static HashMap<String, Class<?>> applications() {
        HashMap<String, Class<?>> applications = new HashMap<>();

        applications.put("products", ProductsApplication.class);

        return applications;
    }

    private static HashMap<String, HashMap<String, Class<?>>> commands() {
        HashMap<String, HashMap<String, Class<?>>> commands = new HashMap<>();


        return commands;
    }

    private static Boolean existCommand(String applicationName, String commandName) {
        HashMap<String, HashMap<String, Class<?>>> commands = commands();

        return commands.containsKey(applicationName) && commands.get(applicationName).containsKey(commandName);
    }
}
