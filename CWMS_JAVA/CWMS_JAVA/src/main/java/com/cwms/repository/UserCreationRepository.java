package com.cwms.repository;

import java.text.AttributedString;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.User;
public interface UserCreationRepository extends JpaRepository<User,Integer>{

	   @Query(value="select * from userinfo as u where u.user_id=:uid", nativeQuery=true)
	    public User findByUser_Id(@Param("uid") String userId);

	   @Query(value="select * from userinfo u where u.status<>'D' and company_id=:cid and branch_id=:bid",nativeQuery=true)
	   public List<User> findAlldata(@Param("cid") String cid,@Param("bid") String bid);
}
