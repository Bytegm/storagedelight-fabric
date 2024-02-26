package com.axperty.storagedelight.registry;

import com.axperty.storagedelight.StorageDelight;
import com.axperty.storagedelight.block.entity.DefaultCabinetBlockEntity;
import com.axperty.storagedelight.block.entity.DrawerBlockEntity;
import com.axperty.storagedelight.block.entity.GlassCabinetBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

public enum BlockEntityTypesRegistry {
    DRAWER("drawer", DrawerBlockEntity.class, DrawerBlockEntity::new,
            BlocksRegistry.OAK_DRAWER, BlocksRegistry.BIRCH_DRAWER, BlocksRegistry.SPRUCE_DRAWER,
            BlocksRegistry.JUNGLE_DRAWER, BlocksRegistry.ACACIA_DRAWER, BlocksRegistry.DARK_OAK_DRAWER,
            BlocksRegistry.MANGROVE_DRAWER, BlocksRegistry.CHERRY_DRAWER, BlocksRegistry.BAMBOO_DRAWER, BlocksRegistry.CRIMSON_DRAWER, BlocksRegistry.WARPED_DRAWER),

    GLASS_CABINET("glass_cabinet", GlassCabinetBlockEntity.class, GlassCabinetBlockEntity::new,
            BlocksRegistry.GLASS_OAK_CABINET, BlocksRegistry.GLASS_BIRCH_CABINET,
            BlocksRegistry.GLASS_SPRUCE_CABINET, BlocksRegistry.GLASS_JUNGLE_CABINET,
            BlocksRegistry.GLASS_ACACIA_CABINET, BlocksRegistry.GLASS_DARK_OAK_CABINET,
            BlocksRegistry.GLASS_MANGROVE_CABINET, BlocksRegistry.GLASS_CHERRY_CABINET,
            BlocksRegistry.GLASS_BAMBOO_CABINET, BlocksRegistry.GLASS_CRIMSON_CABINET,
            BlocksRegistry.GLASS_WARPED_CABINET),

    DEFAULT_CABINET("default_cabinet", DefaultCabinetBlockEntity.class, DefaultCabinetBlockEntity::new,
                   BlocksRegistry.CHERRY_CABINET, BlocksRegistry.BAMBOO_CABINET);

    private final String pathName;
    private final Class<? extends BlockEntity> blockEntityClass;
    private final Supplier<BlockEntityType<? extends BlockEntity>> blockEntityTypeSupplier;
    private BlockEntityType<? extends BlockEntity> blockEntityType;

    BlockEntityTypesRegistry(String pathName, Class<? extends BlockEntity> blockEntityClass,
                             FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity> blockEntitySupplier, BlocksRegistry... blockRegistryArray) {
        this.pathName = pathName;
        this.blockEntityClass = blockEntityClass;
        this.blockEntityTypeSupplier = () -> FabricBlockEntityTypeBuilder.create(blockEntitySupplier, Arrays.stream(blockRegistryArray)
                .map(BlocksRegistry::get).toArray(Block[]::new)).build(null);
    }

    public static void registerAll() {
        for (BlockEntityTypesRegistry value : values()) {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(StorageDelight.MOD_ID, value.pathName), value.get());
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityType<T> get() {
        return (BlockEntityType<T>) get(blockEntityClass);
    }

    @SuppressWarnings({"unchecked","unused"})
    private <T extends BlockEntity> BlockEntityType<T> get(Class<T> clazz) {
        if (blockEntityType == null) {
            blockEntityType = blockEntityTypeSupplier.get();
        }

        return (BlockEntityType<T>) blockEntityType;
    }

    public String getId() {
        return Registries.BLOCK_ENTITY_TYPE.getId(get()).toString();
    }
}
