package net.ivangouba.tutorialdos.block.entity;

import net.ivangouba.tutorialdos.util.ElectricNetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CableBlockEntity extends BlockEntity {

    private double voltage = 0.0;

    public CableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CABLE_BE.get(), pPos, pBlockState);
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        if(this.voltage != voltage) {
            this.voltage = voltage;
            setChanged();
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        voltage = pTag.getDouble("Voltage");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if(!level.isClientSide) {
            ElectricNetworkManager.get(level).addNode(worldPosition, this);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if(!level.isClientSide) {
            ElectricNetworkManager.get(level).removeNode(worldPosition);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putDouble("Voltage", voltage);
    }
}
