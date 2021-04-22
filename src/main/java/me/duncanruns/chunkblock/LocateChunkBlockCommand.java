package me.duncanruns.chunkblock;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;

public class LocateChunkBlockCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandManager.literal("locatechunkblock").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermissionLevel(2);
        })).then(CommandManager.argument("block", BlockStateArgumentType.blockState()).executes((commandContext) -> {
            return execute((ServerCommandSource) commandContext.getSource(), BlockStateArgumentType.getBlockState(commandContext, "block"));
        })));
    }

    private static int execute(ServerCommandSource source, BlockStateArgument block) throws CommandSyntaxException {
        BlockState blockState = block.getBlockState().getBlock().getDefaultState();
        String blockNameSpace = Registry.BLOCK.getId(block.getBlockState().getBlock()).toString();
        if (!Arrays.stream(ChunkBlockProvider.commonBlockList).anyMatch(blockState::equals) && !Arrays.stream(ChunkBlockProvider.blockList).anyMatch(blockState::equals)) {
            source.sendError(Text.method_30163("There are no chunks made out of " + blockNameSpace + "!"));
            return 0;
        }//new TranslatableText("chat.coordinates.tooltip")
        ChunkBlockHelper chunkBlockHelper = new ChunkBlockHelper(source.getWorld());
        SearchResult searchResult = chunkBlockHelper.searchChunkBlock(blockState, new ChunkPos(new BlockPos(source.getPosition())));
        if (searchResult.success) {
            ChunkPos chunkPos = (ChunkPos) searchResult.result;
            double distance = Math.sqrt(Math.pow((double) (chunkPos.x * 16 + 8) - source.getPosition().x, 2.0) + Math.pow((double) (chunkPos.z * 16 + 8) - source.getPosition().z, 2.0));
            MutableText text = (MutableText) Text.method_30163("The nearest " + blockNameSpace + " chunk is at [" + (chunkPos.x * 16 + 8) + ", ~, " + (chunkPos.z * 16 + 8) + "] (" + (int) distance + " blocks away)");

            text.setStyle(text.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + (chunkPos.x * 16 + 8) + " ~ " + (chunkPos.z * 16 + 8))).withColor(Formatting.GREEN).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableText("chat.coordinates.tooltip"))));
            source.sendFeedback((Text) text, false);
            return 1;
        } else {
            source.sendError(Text.method_30163("Could not find a chunk made out of " + blockNameSpace + "!"));
            return 0;
        }
    }
}
