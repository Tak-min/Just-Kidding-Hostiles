package com.github.tak-min.orpheus.client.render.curio;

import com.github.tak-min.orpheus.core.Registries;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class CurioRenderers {
    public static void register() {
        CuriosRendererRegistry.register(Registries.RUBBER_DUCK_ITEM.get(), () -> new RubberDuckCurioRenderer());
    }
}
