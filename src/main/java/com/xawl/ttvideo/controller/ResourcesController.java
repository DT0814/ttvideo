package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.Resources;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.service.ResourceService;
import com.xawl.ttvideo.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("resource")
public class ResourcesController {
    private static String uploadPath = "resonrces/";
    @Autowired
    private ResourceService service;

    @RequestMapping("add")
    public Result add(MultipartFile resourceFile, MultipartFile coursewareFile, Resources resources) {
        try {
            if (null != resourceFile && resourceFile.getSize() != 0) {
                try {
                    resources.setUrl(ResourceUtils.upload(resourceFile, uploadPath));
                    if (null != coursewareFile && coursewareFile.getSize() != 0) {
                        resources.setCoursewareUrl(ResourceUtils.upload(coursewareFile, uploadPath));
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
    public Result delete(Integer rid, String url) {
        try {
            service.delete(rid);
            ResourceUtils.deleteResource(url);
            return Result.success(200, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success(401, "删除失败");
        }

    }

    @RequestMapping("update")
    public Result update(Resources resources, MultipartFile resourceFile, MultipartFile coursewareFile) {
        if (null != resourceFile && resourceFile.getSize() != 0) {
            try {
                ResourceUtils.deleteResource(resources.getUrl());
                resources.setUrl(ResourceUtils.upload(resourceFile, uploadPath));

            } catch (IOException e) {
                e.printStackTrace();
                return Result.err(301, "更新文件出错");
            }
        }
        if (null != coursewareFile && coursewareFile.getSize() != 0) {
            try {
                ResourceUtils.deleteResource(resources.getCoursewareUrl());
                resources.setCoursewareUrl(ResourceUtils.upload(coursewareFile, uploadPath));
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
        for (Resources resources : res) {
            System.out.println(resources);
        }
        return Result.success(res);
    }

}
