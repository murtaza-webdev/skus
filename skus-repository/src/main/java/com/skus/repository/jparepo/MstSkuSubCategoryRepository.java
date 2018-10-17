package com.skus.repository.jparepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skus.common.vos.SubCategoryVO;
import com.skus.entity.MstSkuSubCategory;

public interface MstSkuSubCategoryRepository extends JpaRepository<MstSkuSubCategory, Integer> {
	@Query(value = "SELECT new com.skus.common.vos.SubCategoryVO(mssc.skySubCategoryId, mssc.name) "
			+ " FROM MapStoreDepartmentSku msds INNER JOIN msds.mstSku ms "
			+ " INNER JOIN ms.mstSkuSubCategory mssc "
			+ " INNER JOIN ms.mapStoreDepartmentSkus msds INNER JOIN msds.mapStoreDepartment msd WHERE "
			+ " msd.mstStore.storeId = :storeId AND msd.mstDepartment.departmentId = :departmentId "
			+ " AND mssc.mstSkuCategory.skuCategoryId = :skuCategoryId "
			+ " AND mssc.isActive = :isActive GROUP BY mssc.skySubCategoryId ")
	List<SubCategoryVO> findAllSubCategories(@Param("storeId") Integer storeId, @Param("departmentId") Integer departmentId,
			@Param("skuCategoryId") Integer skuCategoryId, @Param("isActive") Byte isActive);
}
