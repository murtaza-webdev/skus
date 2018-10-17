package com.skus.repository.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skus.entity.MstPincode;

public interface MstPincodeRepository extends JpaRepository<MstPincode, Integer> {
	MstPincode findFirstByPincodeAndIsActive(Integer pincode, Byte isActive);
}
