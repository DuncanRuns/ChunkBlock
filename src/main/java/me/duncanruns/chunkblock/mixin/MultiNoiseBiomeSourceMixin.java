package me.duncanruns.chunkblock.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import java.util.List;
import java.util.Optional;

@Mixin(MultiNoiseBiomeSource.class)
public class MultiNoiseBiomeSourceMixin {

    @Inject(at = @At("HEAD"), method = "method_28467", cancellable = true)
    private static void method_28467Mixin(long l, CallbackInfoReturnable info) {
        ImmutableList<Biome> immutableList = ImmutableList.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST);
        info.setReturnValue(new MultiNoiseBiomeSource(l, (List) immutableList.stream().flatMap((biome) -> {
            return biome.streamNoises().map((point) -> {
                return Pair.of(point, biome);
            });
        }).collect(ImmutableList.toImmutableList()), Optional.of(MultiNoiseBiomeSource.Preset.NETHER)));
    }
}
