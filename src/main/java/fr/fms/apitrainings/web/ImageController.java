package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.services.image.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
public class ImageController {

    @Autowired
    private ImageImpl iImageImpl;

    @Autowired
    private IBusinessImpl iBusiness;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadImg(@PathVariable("id") Long idTraining) throws Exception {
        Optional<Training> optionalTraining = iBusiness.getTrainingById(idTraining);
        if(optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            String imgName = training.getImg();
            Resource resource = iImageImpl.loadImageAsResource(imgName);
            String contentType = iImageImpl.getContentType(imgName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

// GILLE - UPDATE IMAGES
/*    @PostMapping("/trainings/{id}/image")
    public ResponseEntity<Training> uploadImage(@PathVariable("id") Long trainingId, @RequestParam("file") MultipartFile file) {
        try {
            Training training = iImageImpl.uploadAndAssociateImageToTraining(trainingId, file);
            return ResponseEntity.ok(training);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }*/
}
