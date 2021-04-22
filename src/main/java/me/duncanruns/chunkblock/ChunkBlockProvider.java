package me.duncanruns.chunkblock;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;

import java.util.Random;

public abstract class ChunkBlockProvider {
    public static final BlockState[] commonBlockList = {
            Blocks.STONE.getDefaultState(),
            Blocks.DIRT.getDefaultState(),
            Blocks.AIR.getDefaultState(),
            Blocks.GRAVEL.getDefaultState(),
            Blocks.TNT.getDefaultState()
    };
    public static final BlockState[] blockList = {
            Blocks.SLIME_BLOCK.getDefaultState(),
            Blocks.WATER.getDefaultState(),
            Blocks.LAVA.getDefaultState(),
            Blocks.GRANITE.getDefaultState(),
            Blocks.POLISHED_GRANITE.getDefaultState(),
            Blocks.DIORITE.getDefaultState(),
            Blocks.POLISHED_DIORITE.getDefaultState(),
            Blocks.ANDESITE.getDefaultState(),
            Blocks.POLISHED_ANDESITE.getDefaultState(),
            Blocks.GRASS_BLOCK.getDefaultState(),
            Blocks.COARSE_DIRT.getDefaultState(),
            Blocks.PODZOL.getDefaultState(),
            Blocks.COBBLESTONE.getDefaultState(),
            Blocks.OAK_PLANKS.getDefaultState(),
            Blocks.SPRUCE_PLANKS.getDefaultState(),
            Blocks.BIRCH_PLANKS.getDefaultState(),
            Blocks.JUNGLE_PLANKS.getDefaultState(),
            Blocks.ACACIA_PLANKS.getDefaultState(),
            Blocks.DARK_OAK_PLANKS.getDefaultState(),
            Blocks.BEDROCK.getDefaultState(),
            Blocks.GOLD_ORE.getDefaultState(),
            Blocks.IRON_ORE.getDefaultState(),
            Blocks.COAL_ORE.getDefaultState(),
            Blocks.NETHER_GOLD_ORE.getDefaultState(),
            Blocks.OAK_LOG.getDefaultState(),
            Blocks.SPRUCE_LOG.getDefaultState(),
            Blocks.BIRCH_LOG.getDefaultState(),
            Blocks.JUNGLE_LOG.getDefaultState(),
            Blocks.ACACIA_LOG.getDefaultState(),
            Blocks.DARK_OAK_LOG.getDefaultState(),
            Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(),
            Blocks.STRIPPED_BIRCH_LOG.getDefaultState(),
            Blocks.STRIPPED_JUNGLE_LOG.getDefaultState(),
            Blocks.STRIPPED_ACACIA_LOG.getDefaultState(),
            Blocks.STRIPPED_DARK_OAK_LOG.getDefaultState(),
            Blocks.STRIPPED_OAK_LOG.getDefaultState(),
            Blocks.OAK_WOOD.getDefaultState(),
            Blocks.SPRUCE_WOOD.getDefaultState(),
            Blocks.BIRCH_WOOD.getDefaultState(),
            Blocks.JUNGLE_WOOD.getDefaultState(),
            Blocks.ACACIA_WOOD.getDefaultState(),
            Blocks.DARK_OAK_WOOD.getDefaultState(),
            Blocks.STRIPPED_OAK_WOOD.getDefaultState(),
            Blocks.STRIPPED_SPRUCE_WOOD.getDefaultState(),
            Blocks.STRIPPED_BIRCH_WOOD.getDefaultState(),
            Blocks.STRIPPED_JUNGLE_WOOD.getDefaultState(),
            Blocks.STRIPPED_ACACIA_WOOD.getDefaultState(),
            Blocks.STRIPPED_DARK_OAK_WOOD.getDefaultState(),
            Blocks.SPONGE.getDefaultState(),
            Blocks.WET_SPONGE.getDefaultState(),
            Blocks.GLASS.getDefaultState(),
            Blocks.LAPIS_ORE.getDefaultState(),
            Blocks.LAPIS_BLOCK.getDefaultState(),
            Blocks.DISPENSER.getDefaultState(),
            Blocks.SANDSTONE.getDefaultState(),
            Blocks.CHISELED_SANDSTONE.getDefaultState(),
            Blocks.CUT_SANDSTONE.getDefaultState(),
            Blocks.STICKY_PISTON.getDefaultState(),
            Blocks.PISTON.getDefaultState(),
            Blocks.WHITE_WOOL.getDefaultState(),
            Blocks.ORANGE_WOOL.getDefaultState(),
            Blocks.MAGENTA_WOOL.getDefaultState(),
            Blocks.LIGHT_BLUE_WOOL.getDefaultState(),
            Blocks.YELLOW_WOOL.getDefaultState(),
            Blocks.LIME_WOOL.getDefaultState(),
            Blocks.PINK_WOOL.getDefaultState(),
            Blocks.GRAY_WOOL.getDefaultState(),
            Blocks.LIGHT_GRAY_WOOL.getDefaultState(),
            Blocks.CYAN_WOOL.getDefaultState(),
            Blocks.PURPLE_WOOL.getDefaultState(),
            Blocks.BLUE_WOOL.getDefaultState(),
            Blocks.BROWN_WOOL.getDefaultState(),
            Blocks.GREEN_WOOL.getDefaultState(),
            Blocks.RED_WOOL.getDefaultState(),
            Blocks.BLACK_WOOL.getDefaultState(),
            Blocks.GOLD_BLOCK.getDefaultState(),
            Blocks.IRON_BLOCK.getDefaultState(),
            Blocks.BRICKS.getDefaultState(),
            Blocks.BOOKSHELF.getDefaultState(),
            Blocks.MOSSY_COBBLESTONE.getDefaultState(),
            Blocks.OBSIDIAN.getDefaultState(),
            Blocks.DIAMOND_ORE.getDefaultState(),
            Blocks.DIAMOND_BLOCK.getDefaultState(),
            Blocks.CRAFTING_TABLE.getDefaultState(),
            Blocks.REDSTONE_ORE.getDefaultState(),
            Blocks.SNOW_BLOCK.getDefaultState(),
            Blocks.CLAY.getDefaultState(),
            Blocks.JUKEBOX.getDefaultState(),
            Blocks.PUMPKIN.getDefaultState(),
            Blocks.NETHERRACK.getDefaultState(),
            Blocks.SOUL_SAND.getDefaultState(),
            Blocks.SOUL_SOIL.getDefaultState(),
            Blocks.BASALT.getDefaultState(),
            Blocks.POLISHED_BASALT.getDefaultState(),
            Blocks.GLOWSTONE.getDefaultState(),
            Blocks.CARVED_PUMPKIN.getDefaultState(),
            Blocks.JACK_O_LANTERN.getDefaultState(),
            Blocks.WHITE_STAINED_GLASS.getDefaultState(),
            Blocks.ORANGE_STAINED_GLASS.getDefaultState(),
            Blocks.MAGENTA_STAINED_GLASS.getDefaultState(),
            Blocks.LIGHT_BLUE_STAINED_GLASS.getDefaultState(),
            Blocks.YELLOW_STAINED_GLASS.getDefaultState(),
            Blocks.LIME_STAINED_GLASS.getDefaultState(),
            Blocks.PINK_STAINED_GLASS.getDefaultState(),
            Blocks.GRAY_STAINED_GLASS.getDefaultState(),
            Blocks.LIGHT_GRAY_STAINED_GLASS.getDefaultState(),
            Blocks.CYAN_STAINED_GLASS.getDefaultState(),
            Blocks.PURPLE_STAINED_GLASS.getDefaultState(),
            Blocks.BLUE_STAINED_GLASS.getDefaultState(),
            Blocks.BROWN_STAINED_GLASS.getDefaultState(),
            Blocks.GREEN_STAINED_GLASS.getDefaultState(),
            Blocks.RED_STAINED_GLASS.getDefaultState(),
            Blocks.BLACK_STAINED_GLASS.getDefaultState(),
            Blocks.STONE_BRICKS.getDefaultState(),
            Blocks.MOSSY_STONE_BRICKS.getDefaultState(),
            Blocks.CRACKED_STONE_BRICKS.getDefaultState(),
            Blocks.CHISELED_STONE_BRICKS.getDefaultState(),
            Blocks.INFESTED_STONE.getDefaultState(),
            Blocks.INFESTED_COBBLESTONE.getDefaultState(),
            Blocks.INFESTED_STONE_BRICKS.getDefaultState(),
            Blocks.INFESTED_MOSSY_STONE_BRICKS.getDefaultState(),
            Blocks.INFESTED_CRACKED_STONE_BRICKS.getDefaultState(),
            Blocks.INFESTED_CHISELED_STONE_BRICKS.getDefaultState(),
            Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState(),
            Blocks.RED_MUSHROOM_BLOCK.getDefaultState(),
            Blocks.MELON.getDefaultState(),
            Blocks.MYCELIUM.getDefaultState(),
            Blocks.NETHER_BRICKS.getDefaultState(),
            Blocks.END_STONE.getDefaultState(),
            Blocks.REDSTONE_LAMP.getDefaultState(),
            Blocks.EMERALD_ORE.getDefaultState(),
            Blocks.EMERALD_BLOCK.getDefaultState(),
            Blocks.REDSTONE_BLOCK.getDefaultState(),
            Blocks.NETHER_QUARTZ_ORE.getDefaultState(),
            Blocks.QUARTZ_BLOCK.getDefaultState(),
            Blocks.CHISELED_QUARTZ_BLOCK.getDefaultState(),
            Blocks.QUARTZ_PILLAR.getDefaultState(),
            Blocks.WHITE_TERRACOTTA.getDefaultState(),
            Blocks.ORANGE_TERRACOTTA.getDefaultState(),
            Blocks.MAGENTA_TERRACOTTA.getDefaultState(),
            Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(),
            Blocks.YELLOW_TERRACOTTA.getDefaultState(),
            Blocks.LIME_TERRACOTTA.getDefaultState(),
            Blocks.PINK_TERRACOTTA.getDefaultState(),
            Blocks.GRAY_TERRACOTTA.getDefaultState(),
            Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(),
            Blocks.CYAN_TERRACOTTA.getDefaultState(),
            Blocks.PURPLE_TERRACOTTA.getDefaultState(),
            Blocks.BLUE_TERRACOTTA.getDefaultState(),
            Blocks.BROWN_TERRACOTTA.getDefaultState(),
            Blocks.GREEN_TERRACOTTA.getDefaultState(),
            Blocks.RED_TERRACOTTA.getDefaultState(),
            Blocks.BLACK_TERRACOTTA.getDefaultState(),
            Blocks.BARRIER.getDefaultState(),
            Blocks.PRISMARINE.getDefaultState(),
            Blocks.PRISMARINE_BRICKS.getDefaultState(),
            Blocks.DARK_PRISMARINE.getDefaultState(),
            Blocks.SEA_LANTERN.getDefaultState(),
            Blocks.HAY_BLOCK.getDefaultState(),
            Blocks.TERRACOTTA.getDefaultState(),
            Blocks.COAL_BLOCK.getDefaultState(),
            Blocks.PACKED_ICE.getDefaultState(),
            Blocks.RED_SANDSTONE.getDefaultState(),
            Blocks.CHISELED_RED_SANDSTONE.getDefaultState(),
            Blocks.CUT_RED_SANDSTONE.getDefaultState(),
            Blocks.SMOOTH_STONE.getDefaultState(),
            Blocks.SMOOTH_SANDSTONE.getDefaultState(),
            Blocks.SMOOTH_QUARTZ.getDefaultState(),
            Blocks.SMOOTH_RED_SANDSTONE.getDefaultState(),
            Blocks.PURPUR_BLOCK.getDefaultState(),
            Blocks.PURPUR_PILLAR.getDefaultState(),
            Blocks.END_STONE_BRICKS.getDefaultState(),
            Blocks.MAGMA_BLOCK.getDefaultState(),
            Blocks.NETHER_WART_BLOCK.getDefaultState(),
            Blocks.RED_NETHER_BRICKS.getDefaultState(),
            Blocks.BONE_BLOCK.getDefaultState(),
            Blocks.WHITE_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.ORANGE_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.MAGENTA_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.YELLOW_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.LIME_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.PINK_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.GRAY_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.CYAN_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.PURPLE_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.BLUE_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.BROWN_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.GREEN_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.RED_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.BLACK_GLAZED_TERRACOTTA.getDefaultState(),
            Blocks.WHITE_CONCRETE.getDefaultState(),
            Blocks.ORANGE_CONCRETE.getDefaultState(),
            Blocks.MAGENTA_CONCRETE.getDefaultState(),
            Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(),
            Blocks.YELLOW_CONCRETE.getDefaultState(),
            Blocks.LIME_CONCRETE.getDefaultState(),
            Blocks.PINK_CONCRETE.getDefaultState(),
            Blocks.GRAY_CONCRETE.getDefaultState(),
            Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(),
            Blocks.CYAN_CONCRETE.getDefaultState(),
            Blocks.PURPLE_CONCRETE.getDefaultState(),
            Blocks.BLUE_CONCRETE.getDefaultState(),
            Blocks.BROWN_CONCRETE.getDefaultState(),
            Blocks.GREEN_CONCRETE.getDefaultState(),
            Blocks.RED_CONCRETE.getDefaultState(),
            Blocks.BLACK_CONCRETE.getDefaultState(),
            Blocks.DRIED_KELP_BLOCK.getDefaultState(),
            Blocks.DEAD_TUBE_CORAL_BLOCK.getDefaultState(),
            Blocks.DEAD_BRAIN_CORAL_BLOCK.getDefaultState(),
            Blocks.DEAD_BUBBLE_CORAL_BLOCK.getDefaultState(),
            Blocks.DEAD_FIRE_CORAL_BLOCK.getDefaultState(),
            Blocks.DEAD_HORN_CORAL_BLOCK.getDefaultState(),
            Blocks.BLUE_ICE.getDefaultState(),
            Blocks.WARPED_STEM.getDefaultState(),
            Blocks.STRIPPED_WARPED_STEM.getDefaultState(),
            Blocks.WARPED_HYPHAE.getDefaultState(),
            Blocks.STRIPPED_WARPED_HYPHAE.getDefaultState(),
            Blocks.WARPED_NYLIUM.getDefaultState(),
            Blocks.WARPED_WART_BLOCK.getDefaultState(),
            Blocks.CRIMSON_STEM.getDefaultState(),
            Blocks.STRIPPED_CRIMSON_STEM.getDefaultState(),
            Blocks.CRIMSON_HYPHAE.getDefaultState(),
            Blocks.STRIPPED_CRIMSON_HYPHAE.getDefaultState(),
            Blocks.CRIMSON_NYLIUM.getDefaultState(),
            Blocks.SHROOMLIGHT.getDefaultState(),
            Blocks.CRIMSON_PLANKS.getDefaultState(),
            Blocks.WARPED_PLANKS.getDefaultState(),
            Blocks.TARGET.getDefaultState(),
            Blocks.HONEYCOMB_BLOCK.getDefaultState(),
            Blocks.NETHERITE_BLOCK.getDefaultState(),
            Blocks.ANCIENT_DEBRIS.getDefaultState(),
            Blocks.CRYING_OBSIDIAN.getDefaultState(),
            Blocks.BLACKSTONE.getDefaultState(),
            Blocks.POLISHED_BLACKSTONE.getDefaultState(),
            Blocks.POLISHED_BLACKSTONE_BRICKS.getDefaultState(),
            Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState(),
            Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(),
            Blocks.GILDED_BLACKSTONE.getDefaultState(),
            Blocks.CHISELED_NETHER_BRICKS.getDefaultState(),
            Blocks.CRACKED_NETHER_BRICKS.getDefaultState(),
            Blocks.QUARTZ_BRICKS.getDefaultState()
    };
    private static BlockState currentBlockState = Blocks.STONE.getDefaultState();

    private static BlockState randomBlock(long seed, DimensionType dimensionType) {
        Random random = new Random();
        random.setSeed(seed);
        BlockState blockState = (random.nextInt(15)) == 0 ? randomCommonBlock(random) : randomOtherBlock(random);
        if (!dimensionType.equals(DimensionType.getOverworldDimensionType()) && blockState.equals(Blocks.GRAVEL.getDefaultState())) {
            return randomOtherBlock(random);
        } else {
            return blockState;
        }
    }

    private static BlockState randomCommonBlock(Random random) {
        return ChunkBlockProvider.commonBlockList[random.nextInt(ChunkBlockProvider.commonBlockList.length)];
    }

    private static BlockState randomOtherBlock(Random random) {
        return ChunkBlockProvider.blockList[random.nextInt(ChunkBlockProvider.blockList.length)];
    }

    private static void updateChunk(ChunkPos chunkPos, long dimensionSeed, DimensionType dimensionType) {

        if (chunkPos.x == 0 && chunkPos.z == 0 && getDimensionSeed(0L, dimensionType) == 1L) {
            ChunkBlockProvider.setCurrentBlockState(Blocks.END_STONE.getDefaultState());
        } else {
            long seed = getChunkSeed(chunkPos, dimensionSeed);
            ChunkBlockProvider.setCurrentBlockState(randomBlock(ChunkBlockProvider.getChunkSeed(chunkPos, dimensionSeed), dimensionType));
        }
    }

    private static long getChunkSeed(ChunkPos chunkPos, long dimensionSeed) {
        Random random = new Random();
        random.setSeed(dimensionSeed);
        long l = random.nextLong() | 1L;
        long m = random.nextLong() | 1L;
        return (long) chunkPos.x * l + (long) chunkPos.z * m ^ dimensionSeed;

    }

    private static void updateChunk(Chunk chunk, long dimensionSeed, DimensionType dimensionType) {
        updateChunk(chunk.getPos(), dimensionSeed, dimensionType);
    }

    private static long getDimensionSeed(long worldSeed, DimensionType dimensionType) {
        if (dimensionType.equals(DimensionType.getOverworldDimensionType())) {
            return worldSeed;
        } else if (dimensionType.hasCeiling()) {
            return worldSeed - 1;
        } else {
            return worldSeed + 1;
        }
    }

    public static void update(Chunk chunk, long worldSeed, DimensionType dimensionType) {
        long dimensionSeed = ChunkBlockProvider.getDimensionSeed(worldSeed, dimensionType);
        ChunkBlockProvider.updateChunk(chunk, dimensionSeed, dimensionType);
    }

    private static long getWorldSeed(World world) {
        return world.getServer().getSaveProperties().getGeneratorOptions().getSeed();
    }

    public static BlockState getBlock(Vec3d pos, World world) {
        return getBlock(new BlockPos(pos), world);
    }

    public static BlockState getBlock(BlockPos pos, World world) {
        return getBlock(new ChunkPos(pos), world);
    }

    public static BlockState getBlock(ChunkPos pos, World world) {
        DimensionType dimensionType = world.getDimension();
        if (getDimensionSeed(0L, dimensionType) == 1L && pos.x == 0 && pos.z == 0) {
            return Blocks.END_STONE.getDefaultState();
        } else {
            return randomBlock(getChunkSeed(pos, getDimensionSeed(getWorldSeed(world), dimensionType)), dimensionType);
        }
    }

    public static void setCurrentBlockState(BlockState blockState) {
        ChunkBlockProvider.currentBlockState = blockState;
    }

    public static BlockState getCurrentBlockState() {
        return ChunkBlockProvider.currentBlockState;
    }
}
