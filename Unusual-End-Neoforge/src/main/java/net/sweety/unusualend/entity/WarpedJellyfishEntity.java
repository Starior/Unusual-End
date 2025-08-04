
package net.sweety.unusualend.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.sweety.unusualend.init.UnusualEndBlocks;
import net.sweety.unusualend.init.UnusualEndItems;
import net.sweety.unusualend.procedures.BucketGlubProcedure;
import net.sweety.unusualend.procedures.WarpedJellyfishOnEntityTickUpdateProcedure;

public class WarpedJellyfishEntity extends Monster {

    public WarpedJellyfishEntity(EntityType<WarpedJellyfishEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new TemptGoal(this, 1, Ingredient.of(UnusualEndItems.WARPED_BERRIES.get()), false));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, (float) 32));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1, 20) {
            @Override
            protected Vec3 getPosition() {
                RandomSource random = WarpedJellyfishEntity.this.getRandom();
                double dir_x = WarpedJellyfishEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
                double dir_y = WarpedJellyfishEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
                double dir_z = WarpedJellyfishEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
                return new Vec3(dir_x, dir_y, dir_z);
            }
        });
        this.goalSelector.addGoal(5, new FollowMobGoal(this, 1, (float) 10, (float) 5));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1, 40));
        this.goalSelector.addGoal(8, new FloatGoal(this));
    }

    @Override
    public Vec3 getPassengerRidingPosition(Entity entity) {
        return super.getPassengerRidingPosition(entity).add(0, -0.1f, 0);
    }

    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(level, source, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(UnusualEndBlocks.WARPED_MOSS.get()));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.GLOW_SQUID_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.GLOW_SQUID_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.GLOW_SQUID_DEATH;
    }

    @Override
    public boolean causeFallDamage(float l, float d, DamageSource source) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.is(DamageTypes.FALL))
            return false;
        if (damagesource.is(DamageTypes.DROWN))
            return false;
        return super.hurt(damagesource, amount);
    }

    @Override
    public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);
        InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
        super.mobInteract(sourceentity, hand);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Entity entity = this;
        Level world = this.level();
        BucketGlubProcedure.execute(world, entity, sourceentity);
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        WarpedJellyfishOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return world.isUnobstructed(this);
    }

    @Override
    public int getAirSupply() {
        return 100;
    }

    @Override
    public boolean isPushedByFluid() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level();
        Entity entity = this;
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 8);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.3);
        builder = builder.add(Attributes.FLYING_SPEED, 0.4);
        builder = builder.add(NeoForgeMod.SWIM_SPEED, 0.4);
        return builder;
    }
}
