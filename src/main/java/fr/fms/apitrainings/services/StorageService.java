package fr.fms.apitrainings.services;

import fr.fms.apitrainings.dao.FileDataRepository;
import fr.fms.apitrainings.entities.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    FileDataRepository fileDataRepository;

    private final String FOLDER_PATH="C:\\Users\\QuodbachG\\Documents\\trainings\\images";

    public String uploadImageToFileSystem(MultipartFile file) throws Exception{
        String filePath = FOLDER_PATH+file.getOriginalFilename();
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
