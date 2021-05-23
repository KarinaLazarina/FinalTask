package com.epam.FinalTask;

import com.epam.FinalTask.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("showHomePage", new ShowHomePageCommand());
        commands.put("addUser", new AddUserCommand());
        commands.put("getForm", new GetFormCommand());
        commands.put("getUsers", new GetUsersCommand());

    }

    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
//            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
