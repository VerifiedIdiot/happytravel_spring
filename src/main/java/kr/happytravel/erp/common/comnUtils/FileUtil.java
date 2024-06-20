package kr.happytravel.erp.common.comnUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtil {
    @Value("${IDPhoto.rootPath}")
    private String rootPath;

    @Value("${IDPhoto.mainPath}")
    private String mainPath;

    @Value("${IDPhoto.subPath}")
    private String subPath;

    public Path getUploadPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String basePath;
        if (os.contains("win")) {
            basePath = "Y:";
            return Paths.get(basePath, mainPath, subPath, File.separator);
        } else if (os.contains("mac")) {
            basePath = "/Volumes";
            return Paths.get(basePath, rootPath, mainPath, subPath, File.separator);
        } else {
            throw new RuntimeException("지원되지 않는 운영 체제입니다: " + os);
        }
    }
}
