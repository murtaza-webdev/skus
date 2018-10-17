package com.skus.repository.jparepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skus.common.vos.DepartmentVO;
import com.skus.entity.MapStoreDepartment;

public interface MapStoreDepartmentRepository extends JpaRepository<MapStoreDepartment, Integer> {
	
	
	@Query(value = "SELECT new com.skus.common.vos.DepartmentVO(md.departmentId, md.name) "
			+ " FROM MapStoreDepartment msd INNER JOIN msd.mstDepartment md "
			+ " WHERE msd.mstStore.storeId = :storeId AND msd.isActive = :isActive AND md.isActive = :isActive ")
	List<DepartmentVO> findAllDepartments(@Param("storeId") Integer storeId, @Param("isActive") Byte isActive);

	@Query(value = "SELECT msd "
			+ " FROM MapStoreDepartment msd "
			+ " WHERE msd.mstDepartment.departmentId = :departmentId AND msd.mstStore.storeId = :storeId ")
	MapStoreDepartment findFirstByDepartmentIdAndStoreId(@Param("departmentId") Integer departmentId, @Param("storeId") Integer storeId);
	
}
