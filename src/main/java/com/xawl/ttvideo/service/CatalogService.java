package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.CatalogDao;
import com.xawl.ttvideo.pojo.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    @Autowired
    private CatalogDao dao;

    public void add(Catalog catalog) {
        dao.saveAndFlush(catalog);
    }

    public void delete(Integer cid) {
        dao.deleteById(cid);
    }

    public List<Catalog> find(Catalog catalog) {
        List<Catalog> all = dao.findAll();
        return all;
    }

    public Catalog findByCid(Integer cid) {
        Optional<Catalog> byId = dao.findById(cid);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    public Catalog update(Catalog catalog) {
        Catalog one = getOneById(catalog.getCid());
        if (catalog.getName() != null) {
            one.setName(one.getName());
        }
        if (catalog.getDel() != null) {
            one.setDel(catalog.getDel());
        }
        dao.saveAndFlush(one);
        return catalog;
    }

    public Catalog getOneById(Integer cid) {
        ExampleMatcher matcher = ExampleMatcher.matching().
                withIgnorePaths("name", "del")
                .withMatcher("cid", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Catalog> example = Example.of(new Catalog(cid), matcher);
        Optional<Catalog> one = dao.findOne(example);
        boolean present = one.isPresent();
        return present ? one.get() : null;
    }

    public String findCanameBycaid(Integer cid) {
        return findByCid(cid).getName();
    }
}
