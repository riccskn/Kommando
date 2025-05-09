package com.br.riccskn.kommando;

import cn.nukkit.command.CommandSender;

public abstract class BaseSubCommand {


    public BaseSubCommand() {
        prepare();
    }

    protected abstract void prepare();

    public void execute(CommandSender sender, String[] args){
        BaseSubCommand subCommand = this;

        subCommand.execute(sender, args);
    }

}
