package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends JpaRepository<Resources, Integer> {
}
