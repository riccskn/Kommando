package com.br.riccskn.kommando;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.br.riccskn.commands.framework.enums.ErrorCodeEnum;
import com.br.riccskn.commands.framework.exception.SubCommandException;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class BaseCommand extends Command {

    private CommandSender commandSender;

    private Map<String, BaseSubCommand> subCommands = new HashMap<>();

    List<String> aliases;

    private Map<ErrorCodeEnum, String> errorMessages;


    public BaseCommand(String name) {
        super(name);
        initErrorMessages();
        prepare();
    }

    private void initErrorMessages() {
        errorMessages = new HashMap<>();

        errorMessages.put(ErrorCodeEnum.SUBCOMMAND_NOT_FOUND, "§cSubcommand not found");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        this.commandSender = commandSender;

        if (args.length > 0 ){
            if (subCommands.containsKey(args[0])) {
                BaseSubCommand subCommand = subCommands.get(args[0]);

                args = Arrays.copyOfRange(args, 1, args.length);
                subCommand.execute(commandSender, args);
            }else {
                commandSender.sendMessage(errorMessages.get(ErrorCodeEnum.SUBCOMMAND_NOT_FOUND));
            }
        }else {
            onRun(commandSender, args);
        }

        return true;
    }

     public void onRun(CommandSender commandSender, String[] args) {
        this.commandSender = commandSender;
        BaseCommand command = this;
        if (!subCommands.isEmpty()) {
            subCommands.forEach((name, subCommand) -> {
                subCommand.execute(commandSender, args);
            });
        }

        command.onRun(commandSender, args);
     }

    public void registerSubCommand(String subCommandName, BaseSubCommand subCommand) {
        if (subCommands.containsKey(subCommandName)) {
            throw new SubCommandException("Subcommand already exists");
        }
       subCommands.put(subCommandName, subCommand);
    }

    public void unregisterSubCommand(String subCommandName, BaseSubCommand subCommand) {
        subCommands.put(subCommandName, subCommand);
    }

    public void registerErrorMessage(ErrorCodeEnum errorCodeEnum, String message) {
        errorMessages.put(errorCodeEnum, message);
    }

    protected abstract void prepare();

}
