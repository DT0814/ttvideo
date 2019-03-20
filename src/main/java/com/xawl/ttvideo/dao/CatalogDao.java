package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogDao extends JpaRepository<Catalog, Integer> {
}
