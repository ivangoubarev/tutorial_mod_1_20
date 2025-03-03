package net.ivangouba.tutorialdos.block.entity;

import net.ivangouba.tutorialdos.TutorialDos;
import net.ivangouba.tutorialdos.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialDos.MOD_ID);

    public static final RegistryObject<BlockEntityType<MaceratorBlockEntity>> MACERATOR_BE =
            BLOCK_ENTITIES.register("macerator_be", () ->
                    BlockEntityType.Builder.of(MaceratorBlockEntity::new,
                            ModBlocks.MACERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BE =
            BLOCK_ENTITIES.register("generator_block", () ->
                    BlockEntityType.Builder.of(GeneratorBlockEntity::new,
                            ModBlocks.GENERATOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<CableBlockEntity>> CABLE_BE =
            BLOCK_ENTITIES.register("cable_block", () ->
                    BlockEntityType.Builder.of(CableBlockEntity::new,
                            ModBlocks.CABLE_BLOCK.get()).build(null));

    public static void register(IEventBus eventbus) {
        BLOCK_ENTITIES.register(eventbus);
    }

}
