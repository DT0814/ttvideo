package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.Resources;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.service.ResourceService;
import com.xawl.ttvideo.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("resource")
public class ResourcesController {
    private static String uploadPath = "resonrces/";
    @Autowired
    private ResourceService service;

    @RequestMapping("add")
    public Result add(MultipartFile resourceFile, MultipartFile coursewareFile, Resources resources, HttpServletRequest request) {
        try {
            if (null != resourceFile && resourceFile.getSize() != 0) {
                try {
                    resources.setUrl(ResourceUtils.upload(resourceFile, uploadPath,request));
                    if (null != coursewareFile && coursewareFile.getSize() != 0) {
                        resources.setCoursewareUrl(ResourceUtils.upload(coursewareFile, uploadPath,request));
                        resources.setHaveCourseware(1);
                    } else {
                        resources.setHaveCourseware(0);
                    }
                    resources.setIntime(new Date());
                    System.out.println(resources);
                    service.add(resources);
                    return Result.success(resources);
                } catch (IOException e) {
                    e.printStackTrace();
                    return Result.err(401, "上传资源出错");
                }
            } else {
                return Result.err(401, "请上传视频资源文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(401, "添加资源失败");
        }
    }

    @RequestMapping("delete")
    public Result delete(Integer rid, String url,HttpServletRequest request) {
        try {
            service.delete(rid);
            ResourceUtils.deleteResource(url,request);
            return Result.success(200, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success(401, "删除失败");
        }

    }

    @RequestMapping("update")
    public Result update(Resources resources, MultipartFile resourceFile, MultipartFile coursewareFile,HttpServletRequest request) {
        if (null != resourceFile && resourceFile.getSize() != 0) {
            try {
                ResourceUtils.deleteResource(resources.getUrl(),request);
                resources.setUrl(ResourceUtils.upload(resourceFile, uploadPath,request));

            } catch (IOException e) {
                e.printStackTrace();
                return Result.err(301, "更新文件出错");
            }
        }
        if (null != coursewareFile && coursewareFile.getSize() != 0) {
            try {
                ResourceUtils.deleteResource(resources.getCoursewareUrl(),request);
                resources.setCoursewareUrl(ResourceUtils.upload(coursewareFile, uploadPath,request));
            } catch (IOException e) {
                e.printStackTrace();
                return Result.err(301, "更新文件出错");
            }
        }
        resources = service.update(resources);
        return Result.success(200, "", resources);
    }


    @GetMapping("findByCid")
    public Result findByCid(Integer cid) {
        List<Resources> res = service.findByCid(cid);
        return Result.success(res);
    }

    @RequestMapping("/download")
    private String download(HttpServletResponse response, Integer rid,HttpServletRequest request) throws UnsupportedEncodingException {
        Resources res = service.getOneById(rid);
        String destPath = request.getSession().getServletContext().getRealPath("") + File.separator;

        File file = new File(destPath + "/" + res.getCoursewareUrl().substring(6));
        String fileName = res.getRname() + res.getCoursewareUrl().substring(res.getCoursewareUrl().lastIndexOf("."));
        fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
        System.out.println(fileName);
        if (file.exists()) {
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }

}
