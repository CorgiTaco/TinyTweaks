package dev.hephaestus.tweaks.mixin.entity.passive;

import dev.hephaestus.tweaks.entity.ai.goal.GroundFoodMateGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ChickenEntity.class)
public class ChickenEntityMixin extends AnimalEntity {
    @Shadow @Final private static Ingredient BREEDING_INGREDIENT;

    protected ChickenEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    public void addGoal(CallbackInfo ci) {
        this.goalSelector.add(4, new GroundFoodMateGoal(this, BREEDING_INGREDIENT));
    }

    @Shadow
    @Nullable
    @Override
    public ChickenEntity createChild(PassiveEntity mate) {
        return null;
    }
}
