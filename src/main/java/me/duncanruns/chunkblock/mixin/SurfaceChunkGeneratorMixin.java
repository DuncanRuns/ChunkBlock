package me.duncanruns.chunkblock.mixin;

import me.duncanruns.chunkblock.ChunkBlockProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SurfaceChunkGenerator.class)
public abstract class SurfaceChunkGeneratorMixin {

    @Shadow
    @Final
    protected ChunkGeneratorType field_24774;

    @Shadow
    @Final
    protected BlockState defaultFluid;

    @Shadow
    @Final
    private long field_24778;


    @Inject(at = @At("HEAD"), method = "getBlockState", cancellable = true)
    protected void getBlockStateMixin(double density, int y, CallbackInfoReturnable info) {
        BlockState blockState;
        if (density > 0.0D) {
            blockState = ChunkBlockProvider.getCurrentBlockState();
            if(y < 60 && blockState.equals(Blocks.GRAVEL.getDefaultState())){
                blockState = Blocks.COBBLESTONE.getDefaultState();
            }
        } else if (y < this.field_24774.method_28561()) {
            blockState = this.defaultFluid;
        } else {
            blockState = Blocks.AIR.getDefaultState();
        }

        info.setReturnValue(blockState);
    }

    @Inject(at = @At("HEAD"), method = "populateNoise")
    public void populateNoiseMixin(WorldAccess world, StructureAccessor accessor, Chunk chunk, CallbackInfo info) {
        ChunkBlockProvider.update(chunk, field_24778, world.getDimension());
    }
}
