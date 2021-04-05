/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Nguyen_Toan
 */
@Slf4j
public class FileUtils {

//    public static final String UPLOAD_DIR = System.getProperty("user.home") + "/FileUploadAdfilex";
    public static final String UPLOAD_DIR = "upload/FileUploadAdfilex";

    public static List<String> saveUploadedFiles(MultipartFile[] files)
            throws IOException {
        // Make sure directory not exists!
//                File uploadDir = new File(org.apache.commons.lang3.StringUtils.isEmpty(folderDis) ? UPLOAD_DIR : folderDis);
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        List<String> listPath = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String uploadFilePath
                    = new StringBuilder(UPLOAD_DIR + vn.osp.adfilex.Departmentfrequency.utils.StringUtils.SLASH_RIGHT + file.getOriginalFilename()).toString();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
            log.info("UPLOAD FILE :" + uploadFilePath);
            listPath.add(uploadFilePath);
        }
        return listPath;
    }
}
