package com.axperty.storagedelight.registry;

import com.axperty.storagedelight.StorageDelight;
import com.axperty.storagedelight.block.CabinetGlassDoorsBlock;
import com.axperty.storagedelight.block.DrawerBlock;
import com.axperty.storagedelight.block.GlassCabinetBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum BlocksRegistry {

    // Drawers
    OAK_DRAWER("oak_drawer", DrawerBlock::new),
    BIRCH_DRAWER("birch_drawer", DrawerBlock::new),
    SPRUCE_DRAWER("spruce_drawer", DrawerBlock::new),
    JUNGLE_DRAWER("jungle_drawer", DrawerBlock::new),
    ACACIA_DRAWER("acacia_drawer", DrawerBlock::new),
    DARK_OAK_DRAWER("dark_oak_drawer", DrawerBlock::new),
    MANGROVE_DRAWER("mangrove_drawer", DrawerBlock::new),
    CHERRY_DRAWER("cherry_drawer", DrawerBlock::new),
    BAMBOO_DRAWER("bamboo_drawer", DrawerBlock::new),
    CRIMSON_DRAWER("crimson_drawer", DrawerBlock::new),
    WARPED_DRAWER("warped_drawer", DrawerBlock::new),

    // Glass Cabinets
    GLASS_OAK_CABINET("glass_oak_cabinet", GlassCabinetBlock::new),
    GLASS_BIRCH_CABINET("glass_birch_cabinet", GlassCabinetBlock::new),
    GLASS_SPRUCE_CABINET("glass_spruce_cabinet", GlassCabinetBlock::new),
    GLASS_JUNGLE_CABINET("glass_jungle_cabinet", GlassCabinetBlock::new),
    GLASS_ACACIA_CABINET("glass_acacia_cabinet", GlassCabinetBlock::new),
    GLASS_DARK_OAK_CABINET("glass_dark_oak_cabinet", GlassCabinetBlock::new),
    GLASS_MANGROVE_CABINET("glass_mangrove_cabinet", GlassCabinetBlock::new),
    GLASS_CHERRY_CABINET("glass_cherry_cabinet", GlassCabinetBlock::new),
    GLASS_BAMBOO_CABINET("glass_bamboo_cabinet", GlassCabinetBlock::new),
    GLASS_CRIMSON_CABINET("glass_crimson_cabinet", GlassCabinetBlock::new),
    GLASS_WARPED_CABINET("glass_warped_cabinet", GlassCabinetBlock::new),

    // Cabinets with Glass Doors
    OAK_CABINET_WITH_GLASS_DOORS("oak_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    BIRCH_CABINET_WITH_GLASS_DOORS("birch_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    SPRUCE_CABINET_WITH_GLASS_DOORS("spruce_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    JUNGLE_CABINET_WITH_GLASS_DOORS("jungle_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    ACACIA_CABINET_WITH_GLASS_DOORS("acacia_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    DARK_OAK_CABINET_WITH_GLASS_DOORS("dark_oak_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    MANGROVE_CABINET_WITH_GLASS_DOORS("mangrove_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    CHERRY_CABINET_WITH_GLASS_DOORS("cherry_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    BAMBOO_CABINET_WITH_GLASS_DOORS("bamboo_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    CRIMSON_CABINET_WITH_GLASS_DOORS("crimson_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new),
    WARPED_CABINET_WITH_GLASS_DOORS("warped_cabinet_with_glass_doors", CabinetGlassDoorsBlock::new);


    private final String pathName;
    private final Supplier<Block> blockSupplier;
    private final boolean isCutout;
    private Block block;

    BlocksRegistry(String pathName, Supplier<Block> blockSupplier) {
        this(pathName, blockSupplier, false, new FlammableBlockRegistry.Entry(0, 0));
    }

    BlocksRegistry(String pathName, Supplier<Block> blockSupplier, boolean isCutout, FlammableBlockRegistry.Entry flammableRate) {
        this.pathName = pathName;
        this.blockSupplier = blockSupplier;
        this.isCutout = isCutout;
    }

    public static void registerAll() {
        for (BlocksRegistry value : values()) {
            Block block = value.get();
            Registry.register(Registries.BLOCK, new Identifier(StorageDelight.MOD_ID, value.pathName), block);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenderLayer() {
        for (BlocksRegistry value : values()) {
            if (value.isCutout) {
                BlockRenderLayerMap.INSTANCE.putBlock(value.get(), RenderLayer.getCutout());
            }
        }
    }

    public Block get() {
        if (block == null) {
            block = blockSupplier.get();
        }

        return block;
    }

    public String getId() {
        return Registries.BLOCK.getId(get()).toString();
    }
}
