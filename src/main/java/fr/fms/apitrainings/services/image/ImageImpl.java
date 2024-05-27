package fr.fms.apitrainings.services.image;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Training;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import fr.fms.apitrainings.services.image.IImage;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageImpl  implements IImage{

    @Autowired
    private IBusinessImpl iBusiness;

<<<<<<< HEAD
    @Autowired
    private Environment env;

    private String BASE_PATH;

   @PostConstruct
   public void init(){
       String userHome = env.getProperty("app.home");

       BASE_PATH = userHome + File.separator + "Pictures" + File.separator +"trainings" + File.separator +"images";
   }
=======
   private static String BASE_PATH = System.getProperty("user.home") + "\\Pictures\\trainings\\images";
>>>>>>> 6bb4beb7e031dc68e4f978457c07506f16d131f3

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

    public void uploadImage(MultipartFile file) throws IOException {
        if(file.isEmpty()) {
            throw new IllegalArgumentException("Aucun fichier n'a été sélectionné.");
        }
        String filename = file.getOriginalFilename();
        file.transferTo(new File(BASE_PATH + File.separator + filename));
    }

    public String uploadAndAssignImageToTraining(Long idTraining, MultipartFile file) throws IOException {
        Optional<Training> optionalTraining = iBusiness.getTrainingById(idTraining);
        if(optionalTraining.isPresent()) {
            Training training = optionalTraining.get();

            if(file.isEmpty()) {
                throw new IllegalArgumentException("Aucun fichier n'a été sélectionné.");
            }
            // Récupération du nom du fichier
            String filename = file.getOriginalFilename();

            //On transfert l'image dans notre dossier = C:/user/home/images/trainings
            file.transferTo(new File(BASE_PATH + File.separator + filename));

            //On passe le nom de la photo à notre Training
            training.setImg(filename);
            iBusiness.saveTraining(training);
            return filename;
        } else {
            throw new IllegalArgumentException("La formation avec l'identifiant " + idTraining + " n'existe pas.");
        }
    }
}
