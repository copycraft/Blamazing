package org.copycraftDev.blamazing.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class BlamaceItem extends SwordItem {
    private static final float BASE_DAMAGE = 50.0f;     // effectively infinite
    private static final double VELOCITY_SCALE = 3.0;     // extra punch!
    private static final double MIN_FALL_DISTANCE = 5.0;  // only trigger if fallen > 3 blocks
    private static final float KNOCKBACK_STRENGTH = 1000.0f; // how hard player is knocked back

    public BlamaceItem(ToolMaterial material, int extraDamage, float attackSpeed, FabricItemSettings settings) {
        super(material, extraDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof PlayerEntity player)) {
            return super.postHit(stack, target, attacker);
        }

        if (player.fallDistance > MIN_FALL_DISTANCE) {
            World world = player.getWorld();

            Vec3d vel = player.getVelocity();
            double speed = vel.length();
            float bonus = (float)(VELOCITY_SCALE * speed);
            float totalDamage = BASE_DAMAGE + bonus;

            // 2. Apply the hit (one-shot)
            DamageSource src = world
                    .getDamageSources()
                    .playerAttack(player);
            target.damage(src, totalDamage);

            if (!world.isClient() && world instanceof ServerWorld serverWorld) {
                double px = target.getX();
                double py = target.getY() + target.getHeight() * 0.5;
                double pz = target.getZ();
                serverWorld.spawnParticles(
                        ParticleTypes.FLAME,
                        px, py, pz,
                        20,            // count
                        0.3, 0.3, 0.3, // offset X, Y, Z
                        0.1            // speed
                );
            }

            world.playSound(
                    null,                          // all players hear it
                    target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
                    SoundCategory.PLAYERS,
                    1.0f, 1.0f
            );

            player.setVelocity(Vec3d.ZERO);

            Vec3d direction = player.getPos().subtract(target.getPos()).normalize();
            player.takeKnockback(
                    KNOCKBACK_STRENGTH,
                    direction.x,
                    direction.z
            );
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
