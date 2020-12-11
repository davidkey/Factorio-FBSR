package com.demod.fbsr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONException;

public class EasyBlueprintRender {

	public static byte[] renderBlueprint(final String rawBlueprintString) throws JSONException, IOException {
		final BufferedImage img = renderBlueprintToBufferedImage(rawBlueprintString);

		final byte[] imageBytes;
		try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(img, "png", baos);
			baos.flush();
			imageBytes = baos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return imageBytes;
	}
	
	public static BufferedImage renderBlueprintToBufferedImage(final String rawBlueprintString) throws JSONException, IOException {
		final TaskReporting taskReporting = new TaskReporting();
		final Blueprint bp = new Blueprint(BlueprintStringData.decode(rawBlueprintString));
		final BufferedImage img = FBSR.renderBlueprint(bp, taskReporting);

		return img;
	}
}
