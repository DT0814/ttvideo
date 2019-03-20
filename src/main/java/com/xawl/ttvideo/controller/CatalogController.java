package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Catalog;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {
    @Autowired
    private CatalogService service;

    @RequestMapping("add")
    public Result add(Catalog catalog) {
        catalog.setDel(false);
        service.add(catalog);
        return Result.success(200, "");
    }

    @RequestMapping("delete")
    public Result delete(Integer cid) {
        service.delete(cid);
        return Result.success(200, "");
    }

    @RequestMapping("update")
    public Result update(Catalog catalog) {
        catalog = service.update(catalog);
        return Result.success(200, "", catalog);
    }

    @RequestMapping("find")
    public Result find(Catalog catalog) {
        List<Catalog> catalogs = service.find(catalog);
        return Result.success(200, "", catalogs);
    }
}
