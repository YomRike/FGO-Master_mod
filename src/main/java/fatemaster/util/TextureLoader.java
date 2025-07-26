package fatemaster.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class TextureLoader {
    public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());
    private static final HashMap<String, Texture> textures = new HashMap<>();

    public static Texture getTexture(String textureString) {
        if (textures.get(textureString) == null) {
            try {
                loadTexture(textureString);
            } catch (GdxRuntimeException e) {
                logger.error("Could not find texture: {}", textureString);
                return getTexture("fgoMasterResources/images/missing_texture.png");
            }
        }
        return textures.get(textureString);
    }

    private static void loadTexture(String textureString) throws GdxRuntimeException {
        logger.info("Loading Texture: {}", textureString);
        Texture texture = new Texture(textureString);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textures.put(textureString, texture);
    }
}
