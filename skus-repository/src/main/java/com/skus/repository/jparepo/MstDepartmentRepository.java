package com.skus.repository.jparepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skus.common.vos.DepartmentVO;
import com.skus.entity.MstDepartment;

public interface MstDepartmentRepository extends JpaRepository<MstDepartment, Integer> {
	@Query(value = "SELECT new com.skus.common.vos.DepartmentVO(md.departmentId, md.name) "
			+ " FROM MapStoreDepartment msd INNER JOIN msd.mstDepartment md "
			+ " WHERE msd.mstStore.storeId = :storeId AND msd.isActive = :isActive AND md.isActive = :isActive ")
	List<DepartmentVO> findAllDepartments(@Param("storeId") Integer storeId, @Param("isActive") Byte isActive);
	
	MstDepartment findFirstByDepartmentId(Integer departmentId);
}
