package com.skus.repository.jparepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skus.common.vos.SkuDetailsVO;
import com.skus.entity.MstSku;

public interface MstSkuRepository extends JpaRepository<MstSku, Integer> {
	@Query(value = "SELECT new com.skus.common.vos.SkuDetailsVO(ms.skuId, ms.name, md.name, msc.name, mssc.name, msd.mstStore.storeId) "
			+ " FROM MapStoreDepartmentSku msds INNER JOIN msds.mstSku ms "
			+ " INNER JOIN ms.mstSkuSubCategory mssc INNER JOIN mssc.mstSkuCategory msc "
			+ " INNER JOIN msds.mapStoreDepartment msd "
			+ " INNER JOIN msd.mstDepartment md " + " WHERE "
			+ " msd.mstStore.storeId = :storeId AND md.departmentId = :departmentId "
			+ " AND  msc.skuCategoryId = :skuCategoryId AND mssc.skySubCategoryId = :skySubCategoryId "
			+ " AND ms.isActive = :isActive ")
	List<SkuDetailsVO> findAllSkus(@Param("storeId") Integer storeId, @Param("departmentId") Integer departmentId,
			@Param("skuCategoryId") Integer skuCategoryId, @Param("skySubCategoryId") Integer skySubCategoryId,
			@Param("isActive") Byte isActive);
	
	
	@Query(value = "SELECT new com.skus.common.vos.SkuDetailsVO(ms.skuId, ms.name, md.name, msc.name, mssc.name, msd.mstStore.storeId) "
			+ " FROM MapStoreDepartmentSku msds INNER JOIN msds.mstSku ms "
			+ " INNER JOIN ms.mstSkuSubCategory mssc INNER JOIN mssc.mstSkuCategory msc "
			+ " INNER JOIN msds.mapStoreDepartment msd "
			+ " INNER JOIN msd.mstStore mStore "
			+ " INNER JOIN msd.mstDepartment md " + " WHERE "
			+ " mStore.name LIKE %:storeName% AND md.name LIKE %:departmentName% "
			+ " AND  msc.name LIKE %:skuCategoryName% AND mssc.name LIKE %:skySubCategoryName% "
			+ " AND ms.isActive = :isActive ")
	List<SkuDetailsVO> findAllSkusByNames(@Param("storeName") String storeName, @Param("departmentName") String departmentName,
			@Param("skuCategoryName") String skuCategoryName, @Param("skySubCategoryName") String skySubCategoryName,
			@Param("isActive") Byte isActive);
}
