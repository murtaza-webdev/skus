package com.skus.repository.jparepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skus.common.vos.CategoryVO;
import com.skus.entity.MstSkuCategory;

public interface MstSkuCategoryRepository extends JpaRepository<MstSkuCategory, Integer> {
	@Query(value = "SELECT new com.skus.common.vos.CategoryVO(msc.skuCategoryId, msc.name) "
			+ " FROM MapStoreDepartmentSku msds INNER JOIN msds.mstSku ms "
			+ " INNER JOIN ms.mstSkuSubCategory mssc INNER JOIN mssc.mstSkuCategory msc "
			+ " INNER JOIN ms.mapStoreDepartmentSkus msds INNER JOIN msds.mapStoreDepartment msd WHERE "
			+ " msd.mstStore.storeId = :storeId AND msd.mstDepartment.departmentId = :departmentId "
			+ " AND msc.isActive = :isActive GROUP BY msc.skuCategoryId ")
	List<CategoryVO> findAllCategories(@Param("storeId") Integer storeId, @Param("departmentId") Integer departmentId,
			@Param("isActive") Byte isActive);
}
