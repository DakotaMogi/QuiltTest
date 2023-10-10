package com.example.example_mod.mixin;

import com.example.example_mod.DakotasTests;
import com.example.example_mod.items.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements com.example.example_mod.mixin.ItemRendererAccessor {

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useHammerModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (stack.isOf(ModItems.hammerItem) && renderMode != ModelTransformationMode.GUI) {
			return this.getModels().getModelManager().getModel(new ModelIdentifier(DakotasTests.MOD_ID, "hammer_3d", "inventory"));
		}
		return value;
	}
}
