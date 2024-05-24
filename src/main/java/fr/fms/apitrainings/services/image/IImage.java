package fr.fms.apitrainings.services.image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface IImage {

    Resource loadImageAsResource(String imgName) throws Exception;
    String getContentType(String imgName) throws Exception;
    String uploadAndAssignImageToTraining(Long idTraining, MultipartFile file) throws IOException;
    void uploadImage(MultipartFile file) throws IOException;
}
