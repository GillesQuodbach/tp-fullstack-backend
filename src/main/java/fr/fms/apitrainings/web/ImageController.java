package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.services.image.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
