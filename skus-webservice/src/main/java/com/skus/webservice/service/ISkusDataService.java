package com.skus.webservice.service;

import java.util.List;

import com.skus.common.request.DepartmentRequest;
import com.skus.common.request.SearchSKURequest;
import com.skus.common.request.StoreRequest;
import com.skus.common.vos.CategoryVO;
import com.skus.common.vos.DepartmentVO;
import com.skus.common.vos.ErrorVO;
import com.skus.common.vos.SkuDetailsVO;
import com.skus.common.vos.StoreVO;
import com.skus.common.vos.SubCategoryVO;

public interface ISkusDataService {

	public List<StoreVO> getAllStores();

	public List<DepartmentVO> getDepartments(Integer storeId);

	public List<CategoryVO> getCategories(Integer storeId, Integer departmentId);

	public List<SubCategoryVO> getSubCategories(Integer storeId, Integer departmentId, Integer skuCategoryId);

	public List<SkuDetailsVO> getSkus(Integer storeId, Integer departmentId, Integer skuCategoryId,
			Integer skySubCategoryId);

	public ErrorVO saveStore(StoreRequest storeReq);

	public ErrorVO updateStore(StoreRequest storeReq);

	public ErrorVO saveDepartment(DepartmentRequest departmentReq, Integer locationId);

	public ErrorVO updateDepartment(DepartmentRequest departmentReq, Integer locationId);

	public StoreRequest getStoreById(Integer storeId);

	public boolean deactivateStoreById(Integer storeId);

	public List<SkuDetailsVO> searchSkus(SearchSKURequest searchSKURequest);
}
