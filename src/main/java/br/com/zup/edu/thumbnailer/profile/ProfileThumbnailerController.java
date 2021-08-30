package br.com.zup.edu.thumbnailer.profile;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

@RestController
public class ProfileThumbnailerController {

    @PostMapping("/profiles/{id}/thumbnail-it")
    public ResponseEntity<?> createThumbnail(@PathVariable UUID id, @Valid @RequestBody ProfilePhotoRequest photo) throws IOException {

        BufferedImage originalImage = photo.toImage();
        BufferedImage thumbnail = Thumbnails.of(originalImage)
                                            .size(200, 200)
                                            .outputFormat("png")
                                            .asBufferedImage();

        ProfilePhotoResponse response = ProfilePhotoResponse.of(photo.getFileName(), thumbnail);
        return ResponseEntity.ok(response);
    }

}
