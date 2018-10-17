package com.skus.webservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skus.common.enums.CommonStatus;
import com.skus.common.enums.ErrorCodeEnum;
import com.skus.common.request.DepartmentRequest;
import com.skus.common.request.SearchSKURequest;
import com.skus.common.request.StoreRequest;
import com.skus.common.vos.CategoryVO;
import com.skus.common.vos.DepartmentVO;
import com.skus.common.vos.ErrorVO;
import com.skus.common.vos.SkuDetailsVO;
import com.skus.common.vos.StoreVO;
import com.skus.common.vos.SubCategoryVO;
import com.skus.entity.MapStoreDepartment;
import com.skus.entity.MstCity;
import com.skus.entity.MstDepartment;
import com.skus.entity.MstPincode;
import com.skus.entity.MstStore;
import com.skus.repository.util.FetchRepositoryService;

@Service
public class SkusDataService implements ISkusDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkusDataService.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private FetchRepositoryService fetchRepositoryService;

	public FetchRepositoryService getFetchRepositoryService() {
		return fetchRepositoryService;
	}

	@Override
	public List<StoreVO> getAllStores() {
		return getFetchRepositoryService().getMstStoreRepository().findFirst20Stores(CommonStatus.ACTIVE.getStatusCode());
	}

	@Override
	public List<DepartmentVO> getDepartments(Integer storeId) {
		return getFetchRepositoryService().getMstDepartmentRepository().findAllDepartments(storeId, CommonStatus.ACTIVE.getStatusCode());
	}

	@Override
	public List<CategoryVO> getCategories(Integer storeId, Integer departmentId) {
		return getFetchRepositoryService().getMstSkuCategoryRepository().findAllCategories(storeId, departmentId, CommonStatus.ACTIVE.getStatusCode());
	}

	@Override
	public List<SubCategoryVO> getSubCategories(Integer storeId, Integer departmentId, Integer skuCategoryId) {
		return getFetchRepositoryService().getMstSkuSubCategoryRepository().findAllSubCategories(storeId, departmentId, skuCategoryId, CommonStatus.ACTIVE.getStatusCode());
	}
	
	@Override
	public List<SkuDetailsVO> getSkus(Integer storeId, Integer departmentId, Integer skuCategoryId,
			Integer skySubCategoryId) {
		return getFetchRepositoryService().getMstSkuRepository().findAllSkus(storeId, departmentId, skuCategoryId,
				skySubCategoryId, CommonStatus.ACTIVE.getStatusCode());
	}

	@Override
	public ErrorVO saveStore(StoreRequest storeReq) {
		ErrorVO errorVo = new ErrorVO();
		MstPincode mstPincode = null;
		MstCity mstCity = null;
		try {
			mstCity = getFetchRepositoryService().getMstCityRepository().findFirstByNameAndIsActive(storeReq.getCityName(), CommonStatus.ACTIVE.getStatusCode());
			if(mstCity == null) {
				errorVo.setError(ErrorCodeEnum.CITY_NOT_FOUND);
				return errorVo;
			}
			if(storeReq.getPincode() != null) {
				mstPincode = getFetchRepositoryService().getMstPincodeRepository().findFirstByPincodeAndIsActive(storeReq.getPincode(), CommonStatus.ACTIVE.getStatusCode());
				if(mstPincode == null) {
					errorVo.setError(ErrorCodeEnum.PINCODE_NOT_FOUND);
					return errorVo;
				}
			}
			MstStore mstStore = MstStore.createNewEntity(storeReq);
			mstStore.setMstCity(mstCity);
			mstStore.setMstPincode(mstPincode);
			getFetchRepositoryService().getMstStoreRepository().save(mstStore);
			errorVo.setError(ErrorCodeEnum.SUCCESS);
			return errorVo;
		} catch (Exception e) {
			LOGGER.error("Error occurs SkusDataService.saveStore requestData : " +  storeReq, e);
			errorVo.setError(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
			return errorVo;
		}
	}

	@Override
	public ErrorVO updateStore(StoreRequest storeReq) {
		ErrorVO errorVo = new ErrorVO();
		MstPincode mstPincode = null;
		MstCity mstCity = null;
		try {
			
			if (StringUtils.isNotBlank(storeReq.getCityName())) {
				mstCity = getFetchRepositoryService().getMstCityRepository()
						.findFirstByNameAndIsActive(storeReq.getCityName(), CommonStatus.ACTIVE.getStatusCode());
				if (mstCity == null) {
					errorVo.setError(ErrorCodeEnum.CITY_NOT_FOUND);
					return errorVo;
				} 
			}
			if(storeReq.getPincode() != null) {
				mstPincode = getFetchRepositoryService().getMstPincodeRepository().findFirstByPincodeAndIsActive(storeReq.getPincode(), CommonStatus.ACTIVE.getStatusCode());
				if(mstPincode == null) {
					errorVo.setError(ErrorCodeEnum.PINCODE_NOT_FOUND);
					return errorVo;
				}
			}
			MstStore mstStore = null;
			if(storeReq.getStoreId() != null) {
				mstStore = getFetchRepositoryService().getMstStoreRepository().findFirstByStoreId(storeReq.getStoreId());
				if(mstStore == null) {
					errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
					return errorVo;
				}
				mstStore.updateEntity(storeReq);
			} else {
				errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
				return errorVo;
			}
			if(mstCity != null) {
				mstStore.setMstCity(mstCity);
			}
			if(mstPincode != null) {
				mstStore.setMstPincode(mstPincode);
			}
			getFetchRepositoryService().getMstStoreRepository().save(mstStore);
			errorVo.setError(ErrorCodeEnum.SUCCESS);
			return errorVo;
		} catch (Exception e) {
			LOGGER.error("Error occurs SkusDataService.saveStore requestData : " +  storeReq, e);
			errorVo.setError(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
			return errorVo;
		}
	}
	
	@Override
	public ErrorVO saveDepartment(DepartmentRequest departmentReq, Integer locationId) {
		ErrorVO errorVo = new ErrorVO();
		MstStore mstStore = null;
		try {
			mstStore = getFetchRepositoryService().getMstStoreRepository().findFirstByStoreId(locationId);
			if(mstStore == null) {
				errorVo.setError(ErrorCodeEnum.STORE_NOT_FOUND);
				return errorVo;
			}
			MstDepartment mstDepartment = MstDepartment.createNewEntity(departmentReq);
			getFetchRepositoryService().getMstDepartmentRepository().save(mstDepartment);
			
			MapStoreDepartment mapStoreDepartment = new MapStoreDepartment();
			mapStoreDepartment.setMstDepartment(mstDepartment);
			mapStoreDepartment.setMstStore(mstStore);
			mapStoreDepartment.setIsActive(CommonStatus.ACTIVE.getStatusCode());
			mapStoreDepartment.setCreatedDate(LocalDateTime.now());
			getFetchRepositoryService().getMapStoreDepartmentRepository().save(mapStoreDepartment);
			errorVo.setError(ErrorCodeEnum.SUCCESS);
			return errorVo;
		} catch (Exception e) {
			LOGGER.error("Error occurs SkusDataService.saveDepartment requestData : " +  departmentReq, e);
			errorVo.setError(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
			return errorVo;
		}
	}

	@Override
	public ErrorVO updateDepartment(DepartmentRequest departmentReq, Integer locationId) {
		ErrorVO errorVo = new ErrorVO();
		MstStore mstStore = null;
		try {
			mstStore = getFetchRepositoryService().getMstStoreRepository().findFirstByStoreId(locationId);
			if(mstStore == null) {
				errorVo.setError(ErrorCodeEnum.STORE_NOT_FOUND);
				return errorVo;
			}
			MstDepartment mstDepartment = getFetchRepositoryService().getMstDepartmentRepository().findFirstByDepartmentId(departmentReq.getDepartmentId());
			if(mstDepartment == null) {
				errorVo.setError(ErrorCodeEnum.DEPARTMENT_NOT_FOUND);
				return errorVo;
			}
			mstDepartment.updateEntity(departmentReq);

			getFetchRepositoryService().getMstDepartmentRepository().save(mstDepartment);

			MapStoreDepartment mapStoreDepartment = getFetchRepositoryService().getMapStoreDepartmentRepository()
					.findFirstByDepartmentIdAndStoreId(locationId, mstStore.getStoreId());
			if(mapStoreDepartment == null) {
				mapStoreDepartment = new MapStoreDepartment();
			}
			mapStoreDepartment.setMstDepartment(mstDepartment);
			mapStoreDepartment.setMstStore(mstStore);
			mapStoreDepartment.setIsActive(CommonStatus.ACTIVE.getStatusCode());
			mapStoreDepartment.setUpdatedDate(LocalDateTime.now());
			getFetchRepositoryService().getMapStoreDepartmentRepository().save(mapStoreDepartment);
			errorVo.setError(ErrorCodeEnum.SUCCESS);
			return errorVo;
		} catch (Exception e) {
			LOGGER.error("Error occurs SkusDataService.updateDeparment requestData : " +  departmentReq, e);
			errorVo.setError(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
			return errorVo;
		}
	}
	
	@Override
	public StoreRequest getStoreById(Integer storeId) {
		StoreRequest storeReq = new StoreRequest();
		try {
			if(storeId != null) {
				storeReq = getFetchRepositoryService().getMstStoreRepository().findFirstStoreByStoreId(storeId);
				return storeReq;
			}
		} catch (Exception e) {
			LOGGER.error("Error occurs SkusDataService.getStoreById requestData : " +  storeId, e);			
		}
		return storeReq;
	}
	
	@Override
	public boolean deactivateStoreById(Integer storeId) {
		boolean isDeactivated = false;
		try {
			if(storeId != null) {
				MstStore mstStore = getFetchRepositoryService().getMstStoreRepository().findFirstByStoreId(storeId);
				if(mstStore != null) {
					mstStore.setIsActive(CommonStatus.INACTIVE.getStatusCode());
					getFetchRepositoryService().getMstStoreRepository().save(mstStore);
					isDeactivated = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurs SkusDataService.getStoreById requestData : " +  storeId, e);
		}
		return isDeactivated;
	}
	
	@Override
	public List<SkuDetailsVO> searchSkus(SearchSKURequest searchSKURequest) {
		return getFetchRepositoryService().getMstSkuRepository().findAllSkusByNames(searchSKURequest.getLocation(), searchSKURequest.getDepartment(), 
				searchSKURequest.getCategory(),
				searchSKURequest.getSubCategory(), CommonStatus.ACTIVE.getStatusCode());
	}
}
