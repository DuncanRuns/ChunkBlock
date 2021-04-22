package me.duncanruns.chunkblock.mixin;

import com.mojang.brigadier.CommandDispatcher;
import me.duncanruns.chunkblock.LocateChunkBlockCommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Shadow
    @Final
    CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void initMixin(CommandManager.RegistrationEnvironment environment, CallbackInfo info) {
        LocateChunkBlockCommand.register(dispatcher);
    }
}
