package com.cwms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.ExternalUserRights;
import com.cwms.entities.UserRights;
import com.cwms.repository.ChildMenuRepository;
import com.cwms.repository.ExternalUserRightsrepo;
import com.cwms.repository.ParentMenuRepository;
import com.cwms.repository.UserRepository;
import com.cwms.repository.UserRightsrepo;

@Service
public class MenuServiceImpl {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ParentMenuRepository pmenu;
	
	@Autowired
	private ChildMenuRepository cmenu;
	
	@Autowired
	private UserRightsrepo userrightsrepo;
	
	@Autowired
	private ExternalUserRightsrepo externaluserrightsrepo;
	
	
	
	public List<UserRights> getUserRightsByUserId(String userId,String cid,String bid) {
	    List<UserRights> userRightsList = userrightsrepo.findByUserId(userId,cid,bid);
	    List<UserRights> allowedUserRightsList = new ArrayList<>();

	    if (userRightsList == null || userRightsList.isEmpty()) {
	        return allowedUserRightsList;
	    }

	    for (UserRights userRights : userRightsList) {
	        if (hasAllowPermission(userRights)) {			
	            allowedUserRightsList.add(userRights);
	        }
	    }

	    return allowedUserRightsList;
	}
	
	public List<ExternalUserRights> getExternalUserRightsByUserId(String userId,String cid,String bid) {
	    List<ExternalUserRights> userRightsList = externaluserrightsrepo.findByExternalUserId(userId,cid,bid);
	    List<ExternalUserRights> allowedUserRightsList = new ArrayList<>();

	    if (userRightsList == null || userRightsList.isEmpty()) {
	        return allowedUserRightsList;
	    }

	    for (ExternalUserRights userRights : userRightsList) {
	        if (hasAllowPermission(userRights)) {			
	            allowedUserRightsList.add(userRights);
	        }
	    }

	    return allowedUserRightsList;
	}
	
	
	private boolean hasAllowPermission(ExternalUserRights userRights) {
		 return "Y".equals(userRights.getAllow_Read()) ||
		            "Y".equals(userRights.getAllow_Create()) ||
		            "Y".equals(userRights.getAllow_Update()) ||
		            "Y".equals(userRights.getAllow_Delete()) ||
		            "Y".equals(userRights.getAllow_Approve());
	}
	
	private boolean hasAllowPermission(UserRights userRights) {
	    return "Y".equals(userRights.getAllow_Read()) ||
	            "Y".equals(userRights.getAllow_Create()) ||
	            "Y".equals(userRights.getAllow_Update()) ||
	            "Y".equals(userRights.getAllow_Delete()) ||
	            "Y".equals(userRights.getAllow_Approve());
	}
	
	
	 public List<Map<String, Object>> getAllParentAndChildMenus() {
	        List<Object[]> combinedData = pmenu.getalldata();
	        List<Map<String, Object>> result = new ArrayList<>();

	        for (Object[] row : combinedData) {
	            Map<String, Object> menuMap = new HashMap<>();
	            menuMap.put("processId", row[0]);
	            menuMap.put("menuName", row[1]);

	            result.add(menuMap);
	        }

	        return result;
	    }
	
	 public List<Map<String, Object>> getAllParentAndChildMenus(String id) {
	        List<Object[]> combinedData = pmenu.getall(id);
	        List<Map<String, Object>> result = new ArrayList<>();

	        for (Object[] row : combinedData) {
	            Map<String, Object> parentMenuMap = mapParentMenu(row);
	            Map<String, Object> childMenuMap = mapChildMenu(row);

	            Map<String, Object> menuMap = new HashMap<>();
	            menuMap.put("parentMenu", parentMenuMap);
	            menuMap.put("childMenu", childMenuMap);

	            result.add(menuMap);
	        }

	        return result;
	    }

	    private Map<String, Object> mapParentMenu(Object[] row) {
	        Map<String, Object> parentMenuMap = new HashMap<>();
	        parentMenuMap.put("processId", row[7]);
	        parentMenuMap.put("child_Menu_Status", row[1]);
	        parentMenuMap.put("pMenu_Id", row[0]);
	        parentMenuMap.put("pMenu_Name", row[2]);
	        parentMenuMap.put("page_status", row[3]);
	        parentMenuMap.put("picons", row[5]);
	        parentMenuMap.put("parent_page_links", row[4]);
	        // ... Map other attributes of ParentMenu accordingly

	        return parentMenuMap;
	    }

	    private Map<String, Object> mapChildMenu(Object[] row) {
	        Map<String, Object> childMenuMap = new HashMap<>();
	        childMenuMap.put("child_Menu_Id", row[8]);
	        childMenuMap.put("processId", row[13]);
	        childMenuMap.put("child_Menu_Name", row[9]);
	        childMenuMap.put("cicons", row[11]);
	        childMenuMap.put("child_page_links", row[10]);
	        childMenuMap.put("pprocess_Id", row[14]);
	        // ... Map other attributes of ChildMenu accordingly

	        return childMenuMap;
	    }
	   
	   
}
