package me.duncanruns.chunkblock;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class ChunkBlockHelper {
    private final World world;

    public ChunkBlockHelper(World world) {
        this.world = world;
    }

    public BlockState getChunkBlock(ChunkPos pos) {
        return ChunkBlockProvider.getBlock(pos, world);
    }

    public BlockState getChunkBlock(BlockPos pos) {
        return ChunkBlockProvider.getBlock(pos, world);
    }

    public BlockState getChunkBlock(Vec3d pos) {
        return ChunkBlockProvider.getBlock(pos, world);
    }

    public SearchResult searchChunkBlock(BlockState blockState, ChunkPos pos) {
        if (getChunkBlock(pos).equals(blockState)) {
            return new SearchResult(pos, true);
        } else {
            for (int i = 1; i < 100; i++) {
                for (int x = -i; x <= i; x++) {
                    for (int z = -i; z <= i; z++) {
                        if (Math.abs(x) == i || Math.abs(z) == i) {
                            ChunkPos newChunkPos = new ChunkPos(x + pos.x, z + pos.z);
                            if (getChunkBlock(newChunkPos).equals(blockState)) {
                                return new SearchResult(newChunkPos, true);
                            }
                        } else {
                            z = i - 1;
                        }
                    }
                }
            }
        }
        return new SearchResult(new ChunkPos(0, 0), false);
    }

}
