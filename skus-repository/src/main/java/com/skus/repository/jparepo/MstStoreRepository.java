package com.skus.repository.jparepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skus.common.request.StoreRequest;
import com.skus.common.vos.StoreVO;
import com.skus.entity.MstStore;

public interface MstStoreRepository extends JpaRepository<MstStore, Integer> {
	@Query(value = "SELECT new com.skus.common.vos.StoreVO(ms.storeId, ms.name, ms.addressArea, mc.name, mc.state, ms.mstPincode.pincode) "
			+ " FROM MstStore ms INNER JOIN ms.mstCity mc WHERE ms.isActive = :isActive ")
	List<StoreVO> findFirst20Stores(@Param("isActive") Byte isActive);
	
	MstStore findFirstByStoreId(Integer storeId);

	@Query(value = "SELECT new com.skus.common.request.StoreRequest(ms.storeId, ms.addressArea, ms.addressLandMark, ms.addressLine, "
			+ " ms.locationLatitude, ms.locationLongitude, ms.name, mc.name, ms.mstPincode.pincode) "
			+ " FROM MstStore ms INNER JOIN ms.mstCity mc WHERE ms.storeId = :storeId ")
	StoreRequest findFirstStoreByStoreId(@Param("storeId") Integer storeId);
}
