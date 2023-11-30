package utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ImageIconAdapter implements JsonSerializer<ImageIcon>, JsonDeserializer<ImageIcon> {

	@Override
	public ImageIcon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		//Get Base64 string from JSON element
        String encodedImage = json.getAsString();

        //Convert a Base64 string to a byte array
        byte[] bytes = Base64.getDecoder().decode(encodedImage);

        //Convert a byte array to an image
        ImageIcon imageIcon = new ImageIcon(bytes);

        return imageIcon;
	}

	@Override
	public JsonElement serialize(ImageIcon src, Type typeOfSrc, JsonSerializationContext context) {
		//Get image from ImageIcon
        Image image = src.getImage();

        //Convert image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write((BufferedImage) image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();

        //Convert byte array to Base64 string
        String encodedImage = Base64.getEncoder().encodeToString(bytes);

        return new JsonPrimitive(encodedImage);
	}
	
}
