package me.agblacky.toolset;

import me.agblacky.toolset.commands.OperatorCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Toolset implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(OperatorCommand::register);
    }
}
