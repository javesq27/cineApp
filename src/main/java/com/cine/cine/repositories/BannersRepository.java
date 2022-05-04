package com.cine.cine.repositories;

import java.util.List;

import com.cine.cine.entities.Banner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannersRepository extends JpaRepository<Banner, Integer> {

    // select * from Banners where estatus = ? order by id desc 
	public List<Banner> findByEstatusOrderByIdDesc(String estatus);
    
}
