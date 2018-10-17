package com.skus.repository.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skus.repository.jparepo.MapStoreDepartmentRepository;
import com.skus.repository.jparepo.MstCityRepository;
import com.skus.repository.jparepo.MstDepartmentRepository;
import com.skus.repository.jparepo.MstPincodeRepository;
import com.skus.repository.jparepo.MstSkuCategoryRepository;
import com.skus.repository.jparepo.MstSkuRepository;
import com.skus.repository.jparepo.MstSkuSubCategoryRepository;
import com.skus.repository.jparepo.MstStoreRepository;

/**
 * 
 * @author DivyaShree
 *
 */
@Service
public class FetchRepositoryService {

	@Autowired
	private MstDepartmentRepository mstDepartmentRepository;

	@Autowired
	private MstStoreRepository mstStoreRepository;

	@Autowired
	private MstSkuCategoryRepository mstSkuCategoryRepository;

	@Autowired
	private MstSkuSubCategoryRepository mstSkuSubCategoryRepository;

	@Autowired
	private MstSkuRepository mstSkuRepository;

	@Autowired
	private MstCityRepository mstCityRepository;

	@Autowired
	private MstPincodeRepository mstPincodeRepository;

	@Autowired
	private MapStoreDepartmentRepository mapStoreDepartmentRepository;
	
	public MstDepartmentRepository getMstDepartmentRepository() {
		return mstDepartmentRepository;
	}

	public MstStoreRepository getMstStoreRepository() {
		return mstStoreRepository;
	}

	public MstSkuRepository getMstSkuRepository() {
		return mstSkuRepository;
	}

	public MstSkuCategoryRepository getMstSkuCategoryRepository() {
		return mstSkuCategoryRepository;
	}

	public MstSkuSubCategoryRepository getMstSkuSubCategoryRepository() {
		return mstSkuSubCategoryRepository;
	}

	public MstCityRepository getMstCityRepository() {
		return mstCityRepository;
	}

	public MstPincodeRepository getMstPincodeRepository() {
		return mstPincodeRepository;
	}

	public MapStoreDepartmentRepository getMapStoreDepartmentRepository() {
		return mapStoreDepartmentRepository;
	}	
}