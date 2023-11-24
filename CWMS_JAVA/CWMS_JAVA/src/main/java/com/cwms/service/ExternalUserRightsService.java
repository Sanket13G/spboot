
package com.cwms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.ExternalUserRights;
import com.cwms.entities.ParentMenu;
import com.cwms.entities.User;
import com.cwms.entities.UserRights;
import com.cwms.repository.ExternalUserRightsrepo;
import com.cwms.repository.ParentMenuRepository;
import com.cwms.repository.UserRepository;
import com.cwms.repository.UserRightsrepo;

@Service
public class ExternalUserRightsService {

	@Autowired
	private ExternalUserRightsrepo eurights;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ParentMenuRepository parentMenuRepository;
	
//	public User getUser(String bid,String companyid,String branchid) {
//	    List<UserRights> userRightsList = urights.findBybid(bid);
//
//	    if (!userRightsList.isEmpty()) {
//	        UserRights userRights = userRightsList.get(0);
//	        String uid = userRights.getUser_Id();
//	        User user = urepo.findByUserId(uid,companyid,branchid);
//	        return user;
//	    }
//
//	    return null; // Return null or handle the case when no UserRights is found.
//	}
	
	
	public void updateExternalUserrights(String approve,String create,String delete,String read,String update,String userId,String processId ) {
		eurights.updateExternalUserrights(approve, create, delete, read, update, userId,processId);
	}
	
	
	public void updateExternalUserRights(List<ExternalUserRights> externaluserRights, String userId) {
	    for (ExternalUserRights userRight : externaluserRights) {
	        List<ExternalUserRights> existingUserRights = eurights.findByExternalUserIdAndProcessIds(userRight.getUser_Id(), userRight.getProcess_Id(),userRight.getCompany_Id(),userRight.getBranch_Id());
	        if (!existingUserRights.isEmpty()) {
	            ExternalUserRights existingUserRight = existingUserRights.get(0);

	                existingUserRight.setAllow_Read(userRight.getAllow_Read());
	                existingUserRight.setAllow_Create(userRight.getAllow_Create());
	                existingUserRight.setAllow_Update(userRight.getAllow_Update());
	                existingUserRight.setAllow_Delete(userRight.getAllow_Delete());
	                existingUserRight.setAllow_Approve(userRight.getAllow_Approve());
	                existingUserRight.setStatus("A");
	                existingUserRight.setApproved_By(userId);
	                existingUserRight.setApproved_Date(new Date());
	                // Set other properties as needed

	                eurights.save(existingUserRight);
	            
	        }
	    }
	}

	 public void insertExternalUserRights(List<ExternalUserRights> externalUserRights, String userId) {
	        for (ExternalUserRights userRight : externalUserRights) {
	        	 List<ExternalUserRights> existingUserRights = eurights.findByExternalUserIdAndProcessId(userRight.getUser_Id(), userRight.getProcess_Id());
	 	        if (!existingUserRights.isEmpty()) {
	 	            // User with the same userId and processId exists, update the user rights
	 	            ExternalUserRights existingUserRight = existingUserRights.get(0); // Assuming there's only one matching record
	 	            
	 	            existingUserRight.setAllow_Read(userRight.getAllow_Read());
	 	            existingUserRight.setAllow_Create(userRight.getAllow_Create());
	 	            existingUserRight.setAllow_Update(userRight.getAllow_Update());
	 	            existingUserRight.setAllow_Delete(userRight.getAllow_Delete());
	 	            existingUserRight.setAllow_Approve(userRight.getAllow_Approve());
	 	        
	 	            // Check if any permission flag is 'Y', and update the status accordingly
	 	            if (hasPermissionFlagY(existingUserRight)) {
	 	                existingUserRight.setStatus("A");
//	 	                existingUserRight.setEdited_By(userId);
	 	                existingUserRight.setEdited_Date(new Date());
	 	            } else {
	 	                existingUserRight.setStatus("");
	 	            }

	 	            // Set other properties as needed
	 	            eurights.save(existingUserRight);
	 	        } else {
	 	            // UserRights does not exist, this is a new record
	 	            ParentMenu parentMenu = parentMenuRepository.getallbyprocessId(userRight.getProcess_Id(),userRight.getCompany_Id(),userRight.getBranch_Id());
	 	            if (parentMenu != null && parentMenu.getChild_Menu_Status() == 'Y') {
	 	                // Set default permissions to 'Y' for allow_Read, allow_Create, allow_Update, allow_Delete, allow_Approve
	 	                userRight.setAllow_Read("Y");
	 	                userRight.setAllow_Create("Y");
	 	                userRight.setAllow_Update("Y");
	 	                userRight.setAllow_Delete("Y");
	 	                userRight.setAllow_Approve("Y");
	 	            }
//	 	            userRight.setCreated_By(userId);
	                 userRight.setCreated_Date(new Date());
	               
	 	            // Check if any permission flag is 'Y', and set the status accordingly
	 	            if (hasPermissionFlagY(userRight)) {
	 	                userRight.setStatus("A");
	 	               
	 	            } else {
	 	                userRight.setStatus("");
	 	            }

	 	            eurights.save(userRight);
	 	        }
	 	    }
	 	}
//	public void insertExternalUserRights(List<ExternalUserRights> externaluserRights, String userId) {
//	    for (ExternalUserRights userRight : externaluserRights) {
//	        List<ExternalUserRights> existingUserRights = eurights.findByExternalUserIdAndProcessId(userRight.getUser_Id(), userRight.getProcess_Id());
//	        if (!existingUserRights.isEmpty()) {
//	            // User with the same userId and processId exists, update the user rights
//	            ExternalUserRights existingUserRight = existingUserRights.get(0); // Assuming there's only one matching record
//	            
//	            existingUserRight.setAllow_Read(userRight.getAllow_Read());
//	            existingUserRight.setAllow_Create(userRight.getAllow_Create());
//	            existingUserRight.setAllow_Update(userRight.getAllow_Update());
//	            existingUserRight.setAllow_Delete(userRight.getAllow_Delete());
//	            existingUserRight.setAllow_Approve(userRight.getAllow_Approve());
//
//	            // Check if any permission flag is 'Y', and update the status accordingly
//	            if (hasPermissionFlagY(existingUserRight)) {
//	                existingUserRight.setStatus("E");
//	                existingUserRight.setEdited_By(userId);
//	                existingUserRight.setEdited_Date(new Date());
//	            } else {
//	                existingUserRight.setStatus("");
//	            }
//
//	            // Set other properties as needed
//	            eurights.save(existingUserRight);
//	        } else {
//	            // UserRights does not exist, this is a new record
//	            ParentMenu parentMenu = parentMenuRepository.getallbyprocessId(userRight.getProcess_Id(),userRight.getCompany_Id(),userRight.getBranch_Id());
//	            if (parentMenu != null && parentMenu.getChild_Menu_Status() == 'Y') {
//	                // Set default permissions to 'Y' for allow_Read, allow_Create, allow_Update, allow_Delete, allow_Approve
//	                userRight.setAllow_Read("Y");
//	                userRight.setAllow_Create("Y");
//	                userRight.setAllow_Update("Y");
//	                userRight.setAllow_Delete("Y");
//	                userRight.setAllow_Approve("Y");
//	            }
//	            userRight.setCreated_By(userId);
//                userRight.setCreated_Date(new Date());
//	            // Check if any permission flag is 'Y', and set the status accordingly
//	            if (hasPermissionFlagY(userRight)) {
//	                userRight.setStatus("N");
//	               
//	            } else {
//	                userRight.setStatus("");
//	            }
//
//	            eurights.save(userRight);
//	        }
//	    }
//	}

	// Helper function to check if any permission flag is 'Y'
	private boolean hasPermissionFlagY(ExternalUserRights existingUserRight) {
	    return "Y".equals(existingUserRight.getAllow_Read()) ||
	           "Y".equals(existingUserRight.getAllow_Create()) ||
	           "Y".equals(existingUserRight.getAllow_Update()) ||
	           "Y".equals(existingUserRight.getAllow_Delete()) ||
	           "Y".equals(existingUserRight.getAllow_Approve());
	}



}
