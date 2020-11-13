package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.ResourceDao;
import com.xawl.ttvideo.pojo.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    @Autowired
    private ResourceDao dao;

    public Resources add(Resources resources) {
        dao.saveAndFlush(resources);
        return resources;
    }

    public void delete(Integer cid) {
        dao.deleteById(cid);
    }

    public Resources update(Resources resources) {
        Resources one = getOneById(resources.getRid());
        if (resources.getRank() != null) {
            one.setRank(resources.getRank());
        }
        if (resources.getHaveCourseware() != null) {
            one.setHaveCourseware(resources.getHaveCourseware());
        }
        if (resources.getRname() != null) {
            one.setRname(resources.getRname());
        }
        if (resources.getUrl() != null) {
            one.setUrl(resources.getUrl());
        }
        if (resources.getCoursewareUrl() != null) {
            one.setCoursewareUrl(resources.getCoursewareUrl());
        }
        dao.saveAndFlush(one);
        return null;
    }

    public Resources getOneById(Integer rid) {
        ExampleMatcher matcher = ExampleMatcher.matching().
                withIgnorePaths("cid", "url", "rname", "order", "intime", "haveCourseware")
                .withMatcher("rid", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Resources> example = Example.of(new Resources(rid), matcher);
        Optional<Resources> one = dao.findOne(example);
        boolean present = one.isPresent();
        return present ? one.get() : null;
    }


    public List<Resources> findByCid(Integer cid) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase("rid", "url", "rname", "order", "intime", "haveCourseware")
                .withMatcher("cid", ExampleMatcher.GenericPropertyMatchers.contains());
        Resources resources = new Resources();
        resources.setCid(cid);
        Example<Resources> example = Example.of(resources, exampleMatcher);
        Sort sort = Sort.by(Sort.Direction.ASC, "rank");
        List<Resources> all = dao.findAll(example, sort);
        return all;
    }
}
