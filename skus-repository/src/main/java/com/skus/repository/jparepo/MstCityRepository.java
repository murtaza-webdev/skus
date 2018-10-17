package com.skus.repository.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skus.entity.MstCity;

public interface MstCityRepository extends JpaRepository<MstCity, Integer> {
	MstCity findFirstByNameAndIsActive(String name, Byte isActive);
}
