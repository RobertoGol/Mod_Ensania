package com.trendy.bta.entity.custom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
public class UltraLocomotiveEntity extends MinecartFurnace {
    public boolean hasRedstoneBoost = false;
    public UltraLocomotiveEntity(EntityType<? extends MinecartFurnace> type, Level level) { super(type, level); }
    @Override public void tick() {
        super.tick();
        if (this.hasFuel() && !this.level().isClientSide) {
            double speed = hasRedstoneBoost ? 2.6D : 2.3D; 
            Vec3 movement = new Vec3(this.pushX, 0.0D, this.pushZ).normalize().scale(speed);
            if (movement.lengthSqr() > 0.001D) {
                this.setDeltaMovement(movement.x, this.getDeltaMovement().y, movement.z);
                this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            }
        }
    }
}
