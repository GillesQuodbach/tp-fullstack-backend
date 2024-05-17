package fr.fms.apitrainings.services;

import fr.fms.apitrainings.dao.FileDataRepository;
import fr.fms.apitrainings.entities.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    FileDataRepository fileDataRepository;

//    private final String FOLDER_PATH="C:\\Users\\QuodbachG\\Documents\\Dev\\tp-fullstack\\tp-fullstack-backend\\uploaded_images";
    private final Path FOLDER_PATH = Paths.get("trainings_images/").toAbsolutePath().normalize();

    public String uploadImageToFileSystem(MultipartFile file) throws Exception{
        // Création du dossier si absent
        if (!Files.exists(FOLDER_PATH)){
            Files.createDirectories(FOLDER_PATH);
        }

//        String filePath = FOLDER_PATH+file.getOriginalFilename();
        String filePath = FOLDER_PATH.resolve(file.getOriginalFilename()).toString();
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null){
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws Exception {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
