package com.xawl.ttvideo.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ResourceUtils {
    public static String upload(MultipartFile mult, String path) throws IOException {

        String fileName = mult.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;
        Date date = new Date();
        String filePath = path + new SimpleDateFormat("yyyyMMdd").format(date) + "/" + fileName;

        String aStatic = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        File file = new File(aStatic + "/" + filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        System.out.println(file.getAbsolutePath());
        OutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = mult.getInputStream();
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
        inputStream.close();
        return "../" + filePath;
    }

    public static void deleteResource(String url) {
        String aStatic = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        File file = new File(aStatic + url.substring(3, url.length()));
        if (file.exists()) {
            file.delete();
        }
    }


}
