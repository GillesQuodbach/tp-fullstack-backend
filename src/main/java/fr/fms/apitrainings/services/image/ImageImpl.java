package fr.fms.apitrainings.services.image;

import fr.fms.apitrainings.entities.Training;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageImpl {
    private static final String BASE_PATH = "C:/user/home/images/trainings";

    public Resource loadImageAsResource(String imgName) throws Exception {
        Path imagePath = Paths.get(BASE_PATH).resolve(imgName);
        Resource resource = new UrlResource(imagePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new Exception("La lecture du fichier est impossible");
        }
    }

    public String getContentType(String imgName) throws Exception {
        Path path = Paths.get(BASE_PATH).resolve(imgName);
        String contentype = Files.probeContentType(path);
        if(contentype == null) {
            contentype = "application/octet-stream";
        }
        return contentype;
    }
// GILLES - UPDATE IMAGE
/*    public Training uploadAndAssociateImageToTraining(Long trainingId, MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + File.separator + file.getOriginalFilename();
        Files.createDirectories(Paths.get(FOLDER_PATH)); // Assure que le dossier existe
        file.transferTo(new File(filePath));

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build();

        imageRepository.save(image);

        Optional<Training> optionalTraining = trainingRepository.findById(trainingId);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            training.setImage(image);
            return trainingRepository.save(training);
        } else {
            throw new IllegalArgumentException("Training not found");
        }
    }*/

}
