package me.duncanruns.chunkblock;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkBlock implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "ChunkBlock";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

}