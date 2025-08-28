package com.nstut.simplyscreens.helpers;

import net.minecraft.network.FriendlyByteBuf;
import java.util.UUID;

public class ImageMetadata {
    private final String name;
    private final String id;
    private final String extension;

    public ImageMetadata(String name, String id, String extension) {
        this.name = name;
        this.id = id;
        this.extension = extension;
    }

    public ImageMetadata(FriendlyByteBuf buf) {
        this.name = buf.readUtf();
        this.id = buf.readUtf();
        this.extension = buf.readUtf();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(name);
        buf.writeUtf(id);
        buf.writeUtf(extension);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getExtension() {
        return extension;
    }

    public ImageMetadata(UUID id, String name) {
        this.name = name;
        this.id = id.toString();
        this.extension = "png"; // デフォルトの拡張子
    }
}