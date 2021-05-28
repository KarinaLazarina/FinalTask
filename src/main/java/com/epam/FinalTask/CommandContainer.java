package com.epam.FinalTask;

import com.epam.FinalTask.command.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("showHomePage", new ShowHomePageCommand());
        commands.put("showErrorPage", new ShowErrorPageCommand());
        commands.put("addUser", new AddUserCommand());
        commands.put("getForm", new GetFormCommand());
        commands.put("getUsers", new GetUsersCommand());
        commands.put("getEditForm", new GetEditFormCommand());
        commands.put("editUser", new EditUserCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());

    }

    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
//            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
