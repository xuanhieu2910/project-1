package vuonghieu.project.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class FileUploadService {

    public static final String dik ="C:\\Users\\XUAN HIEU\\Desktop\\ManagerLibrary\\target\\classes\\static\\images\\";
    public void uploadMultiPart(MultipartFile file) throws IOException {
        String fileNameAdmin = file.getOriginalFilename();
        file.transferTo(new File(dik+fileNameAdmin));
    }

//    public File  convertMultipartToFile(MultipartFile file){
//        File fileConvert = new File(file.getOriginalFilename());
//        try {
//            file.transferTo(fileConvert);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileConvert;
//    }
}
