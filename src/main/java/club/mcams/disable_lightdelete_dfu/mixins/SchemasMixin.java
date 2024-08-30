/*
 * This file is part of the DisableLightDeleteDFU project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024  WenDavid and contributors
 *
 * DisableLightDeleteDFU is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DisableLightDeleteDFU is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DisableLightDeleteDFU.  If not, see <https://www.gnu.org/licenses/>.
 */

package club.mcams.disable_lightdelete_dfu.mixins;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.datafixer.Schemas;
import net.minecraft.datafixer.fix.ChunkDeleteLightFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Schemas.class)
public class SchemasMixin {
    @WrapWithCondition(
            method = "build",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/datafixers/DataFixerBuilder;addFixer(Lcom/mojang/datafixers/DataFix;)V"
            ),
            remap = false
    )
    private static boolean onBuild(DataFixerBuilder instance, DataFix fix) {
        return ! (fix instanceof ChunkDeleteLightFix);
    }
}
