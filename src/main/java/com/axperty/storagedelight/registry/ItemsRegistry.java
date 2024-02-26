package com.axperty.storagedelight.registry;

import com.axperty.storagedelight.StorageDelight;
import com.axperty.storagedelight.item.ModBlockItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

public enum ItemsRegistry {
    // Cherry Cabinet
    CHERRY_CABINET("cherry_cabinet", () -> new ModBlockItem(BlocksRegistry.CHERRY_CABINET.get())),
    BAMBOO_CABINET("bamboo_cabinet", () -> new ModBlockItem(BlocksRegistry.BAMBOO_CABINET.get())),

    // Drawers
    OAK_DRAWER("oak_drawer", () -> new ModBlockItem(BlocksRegistry.OAK_DRAWER.get())),
    BIRCH_DRAWER("birch_drawer", () -> new ModBlockItem(BlocksRegistry.BIRCH_DRAWER.get())),
    SPRUCE_DRAWER("spruce_drawer", () -> new ModBlockItem(BlocksRegistry.SPRUCE_DRAWER.get())),
    JUNGLE_DRAWER("jungle_drawer", () -> new ModBlockItem(BlocksRegistry.JUNGLE_DRAWER.get())),
    ACACIA_DRAWER("acacia_drawer", () -> new ModBlockItem(BlocksRegistry.ACACIA_DRAWER.get())),
    DARK_OAK_DRAWER("dark_oak_drawer", () -> new ModBlockItem(BlocksRegistry.DARK_OAK_DRAWER.get())),
    MANGROVE_DRAWER("mangrove_drawer", () -> new ModBlockItem(BlocksRegistry.MANGROVE_DRAWER.get())),
    CHERRY_DRAWER("cherry_drawer", () -> new ModBlockItem(BlocksRegistry.CHERRY_DRAWER.get())),
    BAMBOO_DRAWER("bamboo_drawer", () -> new ModBlockItem(BlocksRegistry.BAMBOO_DRAWER.get())),
    CRIMSON_DRAWER("crimson_drawer", () -> new ModBlockItem(BlocksRegistry.CRIMSON_DRAWER.get())),
    WARPED_DRAWER("warped_drawer", () -> new ModBlockItem(BlocksRegistry.WARPED_DRAWER.get())),

    // Glass Cabinets
    GLASS_OAK_CABINET("glass_oak_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_OAK_CABINET.get())),
    GLASS_BIRCH_CABINET("glass_birch_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_BIRCH_CABINET.get())),
    GLASS_SPRUCE_CABINET("glass_spruce_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_SPRUCE_CABINET.get())),
    GLASS_JUNGLE_CABINET("glass_jungle_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_JUNGLE_CABINET.get())),
    GLASS_ACACIA_CABINET("glass_acacia_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_ACACIA_CABINET.get())),
    GLASS_DARK_OAK_CABINET("glass_dark_oak_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_DARK_OAK_CABINET.get())),
    GLASS_MANGROVE_CABINET("glass_mangrove_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_MANGROVE_CABINET.get())),
    GLASS_CHERRY_CABINET("glass_cherry_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_CHERRY_CABINET.get())),
    GLASS_BAMBOO_CABINET("glass_bamboo_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_BAMBOO_CABINET.get())),
    GLASS_CRIMSON_CABINET("glass_crimson_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_CRIMSON_CABINET.get())),
    GLASS_WARPED_CABINET("glass_warped_cabinet", () -> new ModBlockItem(BlocksRegistry.GLASS_WARPED_CABINET.get()));


    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private Item item;

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier) {
        this(pathName, itemSupplier, null);
    }

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier, Integer burnTime) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
    }

    public static void registerAll() {
        for (ItemsRegistry value : values()) {
            Registry.register(Registries.ITEM, new Identifier(StorageDelight.MOD_ID, value.pathName), value.get());
        }
        ItemGroupEvents.modifyEntriesEvent(StorageDelight.ITEM_GROUP).register(entries -> entries.addAll(
                Arrays.stream(values()).map(item -> item.get().getDefaultStack()).toList()));
    }

    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public String getId() {
        return Registries.ITEM.getId(get()).toString();
    }
}
