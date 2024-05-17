package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String fileName);
}
