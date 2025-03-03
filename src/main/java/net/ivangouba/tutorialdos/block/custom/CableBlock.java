package net.ivangouba.tutorialdos.block.custom;

import net.ivangouba.tutorialdos.block.entity.CableBlockEntity;
import net.ivangouba.tutorialdos.block.entity.GeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CableBlock extends Block implements EntityBlock {


    public CableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CableBlockEntity(blockPos, blockState);
    }

    public void updateVoltage(LevelReader world, BlockPos pos) {
        BlockEntity entity = world.getBlockEntity(pos);
        if(entity instanceof CableBlockEntity cable) {
            double oldVoltage = cable.getVoltage();
            double newVoltage = calcularNuevoVoltaje(world, pos);

            if(oldVoltage != newVoltage) {
                cable.setVoltage(newVoltage);
                System.out.println("Cable en " + pos + " tiene voltaje: " + cable.getVoltage());
            }
        }
    }

    private double calcularNuevoVoltaje(LevelReader world, BlockPos pos) {
        double maxVoltage = 0;
        for(Direction dir : Direction.values()) {
            BlockEntity neighborEntity = world.getBlockEntity(pos.relative(dir));
            if(neighborEntity instanceof CableBlockEntity neighborCable) {
                maxVoltage = Math.max(maxVoltage, neighborCable.getVoltage());
            } else if(neighborEntity instanceof GeneratorBlockEntity generator) {
                maxVoltage = Math.max(maxVoltage, generator.getVoltage());
            }
        }
        return maxVoltage;
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        updateVoltage(level, pos);
    }
}
