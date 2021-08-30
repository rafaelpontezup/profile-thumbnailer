package br.com.zup.edu.thumbnailer.profile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ProfilePhotoResponse {

    private String fileName;
    private String thumbnail;

    public ProfilePhotoResponse(String fileName, byte[] thumbnail) {
        this.fileName = "thumbnail-" + fileName;
        this.thumbnail = Base64.getEncoder().encodeToString(thumbnail);
    }

    public String getFileName() {
        return fileName;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public static ProfilePhotoResponse of(String fileName, BufferedImage thumbnail) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(thumbnail, "png", baos);
            return new ProfilePhotoResponse(fileName, baos.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException("Error while building response: " + e.getMessage());
        }

    }

}
