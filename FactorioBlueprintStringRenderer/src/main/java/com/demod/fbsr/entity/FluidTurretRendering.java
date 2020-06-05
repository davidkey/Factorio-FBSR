package com.demod.fbsr.entity;

import java.util.function.Consumer;

import org.luaj.vm2.LuaValue;

import com.demod.factorio.DataTable;
import com.demod.factorio.prototype.EntityPrototype;
import com.demod.fbsr.BlueprintEntity;
import com.demod.fbsr.Direction;
import com.demod.fbsr.EntityRendererFactory;
import com.demod.fbsr.RenderUtils;
import com.demod.fbsr.Renderer;
import com.demod.fbsr.Sprite;
import com.demod.fbsr.WorldMap;

public class FluidTurretRendering extends EntityRendererFactory {
	@Override
	public void createRenderers(Consumer<Renderer> register, WorldMap map, DataTable dataTable, BlueprintEntity entity,
			EntityPrototype prototype) {
		Sprite baseSprite = RenderUtils.getSpriteFromAnimation(prototype.lua().get("base_picture")
				.get(entity.getDirection().name().toLowerCase()).get("layers").get(1));
		register.accept(RenderUtils.spriteRenderer(baseSprite, entity, prototype));
		LuaValue turretLayers = prototype.lua().get("folded_animation").get(entity.getDirection().name().toLowerCase())
				.get("layers");
		Sprite turretSprite = RenderUtils.getSpriteFromAnimation(turretLayers.get(1));
		register.accept(RenderUtils.spriteRenderer(turretSprite, entity, prototype));
	}

	@Override
	public void populateWorldMap(WorldMap map, DataTable dataTable, BlueprintEntity entity, EntityPrototype prototype) {
		Direction dir = entity.getDirection();
		map.setPipe(dir.right().offset(dir.back().offset(entity.getPosition()), 0.5), dir.right());
		map.setPipe(dir.left().offset(dir.back().offset(entity.getPosition()), 0.5), dir.left());
	}
}
