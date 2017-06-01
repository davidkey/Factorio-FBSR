package com.demod.fbsr.render;

import java.util.function.Consumer;

import com.demod.factorio.DataTable;
import com.demod.factorio.prototype.EntityPrototype;
import com.demod.fbsr.BlueprintEntity;
import com.demod.fbsr.Renderer;
import com.demod.fbsr.WorldMap;

public class RoboportRendering extends TypeRendererFactory {
	@Override
	public void createRenderers(Consumer<Renderer> register, WorldMap map, DataTable dataTable, BlueprintEntity entity,
			EntityPrototype prototype) {
		register.accept(spriteRenderer(getSpriteFromAnimation(prototype.lua().get("base")), entity, prototype));
		register.accept(
				spriteRenderer(getSpriteFromAnimation(prototype.lua().get("door_animation_down")), entity, prototype));
		register.accept(
				spriteRenderer(getSpriteFromAnimation(prototype.lua().get("door_animation_up")), entity, prototype));
	}
}
