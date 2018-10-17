package com.skus.webservice.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skus.common.enums.ErrorCodeEnum;
import com.skus.common.request.DepartmentRequest;
import com.skus.common.request.SearchSKURequest;
import com.skus.common.request.StoreRequest;
import com.skus.common.vos.ErrorVO;
import com.skus.webservice.service.ISkusDataService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("skus-web/api/v1/")
public class WebserviceGatewayController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebserviceGatewayController.class);

	@Autowired
	private ISkusDataService skusDataService;

	public ISkusDataService getSkusDataService() {
		return skusDataService;
	}

	@RequestMapping(value = "isRunning", method = RequestMethod.GET)
	public String checkStatus() {
		return "Skus Webservice is UP";
	}

	@ApiOperation(value = "get all locations", notes = " Get all locations ")
	@RequestMapping(value = "location", 
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStores() {
		LOGGER.info("WebserviceGatewayController.getStores request");
		return new ResponseEntity<>(getSkusDataService().getAllStores(), HttpStatus.OK);
	}
	
	@ApiOperation(value = " Get all department of specified location ", notes = " Get all department of specified location ")
	@RequestMapping(value = "location/{locationId}/department", 
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDepartments(@PathVariable Integer locationId) {
		LOGGER.info("WebserviceGatewayController.getSkusDetailsParamWise request location : {} department : {} category : {} subcategory : {} ", locationId);
		if(locationId != null && locationId > 0) {
			return new ResponseEntity<>(getSkusDataService().getDepartments(locationId), HttpStatus.OK);
		} else {
			ErrorVO errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
			return new ResponseEntity<>(errorVo, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = " Get all categories of specified location and department ", notes = " Get all categories of specified location and department ")
	@RequestMapping(value = "location/{locationId}/department/{departmentId}/category", 
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCategories(@PathVariable Integer locationId, @PathVariable Integer departmentId) {
		LOGGER.info("WebserviceGatewayController.getCategories request location : {} department : {} ", locationId, departmentId);
		if(locationId != null && locationId > 0 && departmentId != null && departmentId > 0) {
			return new ResponseEntity<>(getSkusDataService().getCategories(locationId, departmentId), HttpStatus.OK);
		} else {
			ErrorVO errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
			return new ResponseEntity<>(errorVo, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = " Get all Sub-Categories of specified location, department and category ", notes = " Get all Sub-Categories of specified location, department and category ")
	@RequestMapping(value = "location/{locationId}/department/{departmentId}/category/{categoryId}/subcategory", 
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSubCategories(@PathVariable Integer locationId, @PathVariable Integer departmentId, @PathVariable Integer categoryId) {
		LOGGER.info("WebserviceGatewayController.getSubCategories request location : {} department : {} category : {} ", locationId, departmentId, categoryId);
		if(locationId != null && locationId > 0 && departmentId != null && departmentId > 0 && categoryId != null && categoryId > 0) {
			return new ResponseEntity<>(getSkusDataService().getSubCategories(locationId, departmentId, categoryId), HttpStatus.OK);
		} else {
			ErrorVO errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
			return new ResponseEntity<>(errorVo, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = " Get all SKUs of specified location, department, category and sub-category", notes = " Get all SKUs of specified location, department, category and sub-category")
	@RequestMapping(value = "location/{locationId}/department/{departmentId}/category/{categoryId}/subcategory/{subcategoryId}", 
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSkusDetailsParamWise(@PathVariable Integer locationId,
			@PathVariable Integer departmentId, @PathVariable Integer categoryId, @PathVariable Integer subcategoryId) {
		LOGGER.info("WebserviceGatewayController.getSkusDetailsParamWise request location : {} department : {} category : {} subcategory : {} ", locationId, departmentId, categoryId, subcategoryId);
		if(locationId != null && locationId > 0 && departmentId != null && departmentId > 0 && categoryId != null && categoryId > 0 && subcategoryId != null && subcategoryId > 0) {
			return new ResponseEntity<>(getSkusDataService().getSkus(locationId, departmentId, categoryId, subcategoryId), HttpStatus.OK);
		} else {
			ErrorVO errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
			return new ResponseEntity<>(errorVo, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "get specific location by id", notes = "get specific location by id")
	@RequestMapping(value = "location/{locationId}/", 
			method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStoreById(@PathVariable Integer locationId) {
		LOGGER.info("WebserviceGatewayController.getStoreById request : {} ", locationId);
		StoreRequest storeReq = new StoreRequest();
		if(locationId != null) {
			storeReq = getSkusDataService().getStoreById(locationId);
		}
		LOGGER.info("WebserviceGatewayController.getStoreById response : {} ", storeReq);
		return new ResponseEntity<>(storeReq, HttpStatus.OK);
	}
	
	@ApiOperation(value = " delete location by id", notes = " delete location by id")
	@RequestMapping(value = "location/{locationId}/", 
			method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deActivateStoreById(@PathVariable Integer locationId) {
		LOGGER.info("WebserviceGatewayController.deActivateStoreById request : {} ", locationId);
		ErrorVO errorVo = new ErrorVO();
		if(locationId != null) {
			 if(getSkusDataService().deactivateStoreById(locationId)) {
				 errorVo.setError(ErrorCodeEnum.SUCCESS);
			 } else {
				 errorVo.setError(ErrorCodeEnum.FAIL);
			 }
		} else {
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
		}
		LOGGER.info("WebserviceGatewayController.deActivateStoreById response : {} ", errorVo);
		return new ResponseEntity<>(errorVo, HttpStatus.OK);
	}
	
	@ApiOperation(value = " Create new Location / Store ", notes = " Create new Location / Store ")
	@RequestMapping(value = "location", 
			method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createStore(@RequestBody StoreRequest storeRequest) {
		LOGGER.info("WebserviceGatewayController.createStore request : {} ", storeRequest);
		ErrorVO errorVo = null;
		if(storeRequest == null || StringUtils.isBlank(storeRequest.getName()) 
				|| storeRequest.getStoreId() != null || StringUtils.isBlank(storeRequest.getCityName())) {
			errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
		} else {
			errorVo = getSkusDataService().saveStore(storeRequest);			
		}
		LOGGER.info("WebserviceGatewayController.createStore response : {} ", errorVo);
		return new ResponseEntity<>(errorVo, HttpStatus.OK);
	}

	@ApiOperation(value = "Update Location by id", notes = " Update Location by id")
	@RequestMapping(value = "location", 
			method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStore(@RequestBody StoreRequest storeRequest) {
		LOGGER.info("WebserviceGatewayController.updateStore request : {} ", storeRequest);
		ErrorVO errorVo = null;
		if(storeRequest == null || storeRequest.getStoreId() == null) {
			errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
		} else {
			errorVo = getSkusDataService().updateStore(storeRequest);
		}
		LOGGER.info("WebserviceGatewayController.updateStore response : {} ", errorVo);
		return new ResponseEntity<>(errorVo, HttpStatus.OK);
	}

	@ApiOperation(value = " Create new Department and assign specified location  ", notes = " Create new Department and assign specified location  ")
	@RequestMapping(value = "location/{locationId}/department", 
			method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest departmentRequest, @PathVariable Integer locationId) {
		LOGGER.info("WebserviceGatewayController.createDepartment request : {} ", departmentRequest);
		ErrorVO errorVo = null;
		if(departmentRequest == null || StringUtils.isBlank(departmentRequest.getName()) 
				|| departmentRequest.getDepartmentId() != null || locationId == null) {
			errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
		} else {
			errorVo = getSkusDataService().saveDepartment(departmentRequest, locationId);
		}
		LOGGER.info("WebserviceGatewayController.createDepartment response : {} ", errorVo);
		return new ResponseEntity<>(errorVo, HttpStatus.OK);
	}

	@ApiOperation(value = " Update deparment by id and assign to specified location ", notes = " Update deparment by id and assign to specified location ")
	@RequestMapping(value = "location/{locationId}/department", 
			method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateDepartment(@RequestBody DepartmentRequest departmentRequest, @PathVariable Integer locationId) {
		LOGGER.info("WebserviceGatewayController.updateDepartment request : {} ", departmentRequest);
		ErrorVO errorVo = null;
		if(departmentRequest == null || departmentRequest.getDepartmentId() == null || locationId == null) {
			errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
		} else {
			errorVo = getSkusDataService().updateDepartment(departmentRequest, locationId);
		}
		LOGGER.info("WebserviceGatewayController.updateDepartment response : {} ", errorVo);
		return new ResponseEntity<>(errorVo, HttpStatus.OK);
	}
	
	@ApiOperation(value = " Search SKU by Location, Department, Category, SubCategory ", notes = " Search SKU by Location, Department, Category, SubCategory ")
	@RequestMapping(value = "searchSkus", 
			method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchSkusDetails(@RequestBody SearchSKURequest searchSKURequest) {
		LOGGER.info("WebserviceGatewayController.searchSkusDetails request : {} ", searchSKURequest);
		if(StringUtils.isNotBlank(searchSKURequest.getLocation()) || StringUtils.isNotBlank(searchSKURequest.getDepartment())
				|| StringUtils.isNotBlank(searchSKURequest.getCategory())
				|| StringUtils.isNotBlank(searchSKURequest.getSubCategory())) {
			return new ResponseEntity<>(getSkusDataService().searchSkus(searchSKURequest), HttpStatus.OK);
		} else {
			ErrorVO errorVo = new ErrorVO();
			errorVo.setError(ErrorCodeEnum.INVALID_DATA_REQUEST);
			return new ResponseEntity<>(errorVo, HttpStatus.BAD_REQUEST);
		}
	}
}
