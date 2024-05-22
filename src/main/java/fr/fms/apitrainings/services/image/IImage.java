package fr.fms.apitrainings.services.image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface IImage {

    Resource loadImageAsResource(String imgName) throws Exception;
    String getContentType(String imgName) throws Exception;
}
