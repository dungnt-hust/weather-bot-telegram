package app.coreproject.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
    public static String uploadFile(MultipartFile file, String rootFolder, String uploadFolder) {
        DateFormat dfDate = new SimpleDateFormat
                ("yyyyMMdd");

//        DateFormat dfTime = new SimpleDateFormat
//                ("yyyyMMddHHmmSS");
        Date now = new Date();
        if (file.isEmpty()) {
        }
        try {
            String[] str = file.getOriginalFilename().split("\\.");
            String extension = str[str.length - 1];
            String newfileName = UUID.randomUUID().toString();
            String fileName = newfileName + "." + extension;
            if (Files.notExists(Paths.get(rootFolder + uploadFolder + dfDate.format(now)))) {
                File f = new File(rootFolder + uploadFolder + dfDate.format(now));
                f.mkdir();
            }
            InputStream is = file.getInputStream();
            Files.copy(is, Paths.get(rootFolder + uploadFolder + dfDate.format(now) + "/" + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("FILE: " + uploadFolder + dfDate.format(now) + "/" + fileName);
            return uploadFolder + dfDate.format(now) + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "Error";
        }


    }
}
