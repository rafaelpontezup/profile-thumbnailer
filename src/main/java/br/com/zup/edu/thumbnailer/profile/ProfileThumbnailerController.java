package br.com.zup.edu.thumbnailer.profile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
public class ProfileThumbnailerController {

    @Autowired
    private Watermark watermark;

    @PostMapping("/api/users/{id}/thumbnails/create")
    public ResponseEntity<?> createThumbnail(@PathVariable UUID id, @Valid @RequestBody ProfilePhotoRequest photo) throws IOException {

        BufferedImage originalImage = photo.toImage();
        BufferedImage thumbnail = Thumbnails.of(originalImage)
                                            .size(200, 200)
                                            .outputFormat("png")
                                            .watermark(Positions.BOTTOM_RIGHT, watermark.getWatermarkImage(), 0.5f)
                                            .asBufferedImage();

        ProfilePhotoResponse response = ProfilePhotoResponse.of(photo.getFileName(), thumbnail);
        return ResponseEntity.ok(response);
    }

    @GetMapping(
        value = "/api/users/{id}/thumbnails/preview",
        produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] previewThumbnail(@PathVariable UUID id, @Valid @RequestBody ProfilePhotoRequest photo) {

        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {

            BufferedImage originalImage = photo.toImage();
            Thumbnails.of(originalImage)
                    .size(200, 200)
                    .outputFormat("png")
                    .watermark(Positions.BOTTOM_RIGHT, watermark.getWatermarkImage(), 0.5f)
                    .toOutputStream(output);

            return output.toByteArray();

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
