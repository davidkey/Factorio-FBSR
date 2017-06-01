package com.demod.fbsr.render;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.demod.factorio.DataTable;
import com.demod.factorio.prototype.EntityPrototype;
import com.demod.fbsr.BlueprintEntity;
import com.demod.fbsr.Renderer;
import com.demod.fbsr.WorldMap;

public class ArithmeticCombinatorRendering extends TypeRendererFactory {
	public static final Map<String, String> operationSprites = new HashMap<>();
	static {
		operationSprites.put("+", "plus_symbol_sprites");
		operationSprites.put("-", "minus_symbol_sprites");
		operationSprites.put("*", "multiply_symbol_sprites");
		operationSprites.put("/", "divide_symbol_sprites");
		operationSprites.put("AND", "and_symbol_sprites");
		operationSprites.put("XOR", "xor_symbol_sprites");
		operationSprites.put("MOD", "modulo_symbol_sprites");// FIXME
		operationSprites.put("%", "modulo_symbol_sprites");// FIXME
	}

	@Override
	public void createRenderers(Consumer<Renderer> register, WorldMap map, DataTable dataTable, BlueprintEntity entity,
			EntityPrototype prototype) {
		// prototype.debugPrint();
		// entity.debugPrint();
		Sprite sprite = getSpriteFromAnimation(
				prototype.lua().get("sprites").get(entity.getDirection().name().toLowerCase()));
		Sprite operatorSprite = getSpriteFromAnimation(prototype.lua()
				.get(operationSprites.get(entity.json().getJSONObject("control_behavior")
						.getJSONObject("arithmetic_conditions").getString("operation")))
				.get(entity.getDirection().name().toLowerCase()));

		register.accept(spriteRenderer(sprite, entity, prototype));
		register.accept(spriteRenderer(operatorSprite, entity, prototype));
	}
}
