package me.duncanruns.chunkblock.mixin;

import me.duncanruns.chunkblock.ChunkBlockProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {
    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void tickMixin(CallbackInfo info) {
        if (!world.isClient()) {
            if (ChunkBlockProvider.getBlock(getPos(), world).equals(Blocks.TNT.getDefaultState())) {
                remove();
                Block.dropStack(world, getBlockPos(), new ItemStack(Items.TNT, 1));
            }
        }
    }
}
