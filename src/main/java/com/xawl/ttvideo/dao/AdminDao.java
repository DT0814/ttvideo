package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
}
