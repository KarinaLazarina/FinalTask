package com.epam.FinalTask;

import com.epam.FinalTask.command.DoctorListCommand;
import com.epam.FinalTask.command.LoginCommand;
import com.epam.FinalTask.command.ShowHomePageCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("showHomePage", new ShowHomePageCommand());
        commands.put("doctorCommand", new DoctorListCommand());

    }

    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
//            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
