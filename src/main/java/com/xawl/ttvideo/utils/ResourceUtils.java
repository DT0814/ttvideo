package com.xawl.ttvideo.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ResourceUtils {
    public static String upload(MultipartFile mult, String path, HttpServletRequest request) throws IOException {

        String fileName = mult.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;
        Date date = new Date();
        String filePath = path + new SimpleDateFormat("yyyyMMdd").format(date) + "/" + fileName;

        ClassPathResource classPathResource = new ClassPathResource("/");
//        String aStatic = classPathResource.getPath();
//        System.out.println(path1);
//        String aStatic = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String destPath = request.getSession().getServletContext().getRealPath("") + File.separator;

        System.out.println(destPath);
        File file = new File(destPath + "/" + filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        System.out.println(file.getAbsolutePath());
        OutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = mult.getInputStream();
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
        inputStream.close();
        return "../../" + filePath;
    }

    public static void deleteResource(String url, HttpServletRequest request) {
        String destPath = request.getSession().getServletContext().getRealPath("") + File.separator;
        File file = new File(destPath + url.substring(6));
        if (file.exists()) {
            file.delete();
        }
    }
}
