package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.User;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {


  
    
    @Query(value="select * from userinfo as u where u.user_id=:uid", nativeQuery=true)
    public User findByUser_Id(@Param("uid") String userId);
    
    @Query(value="select * from userinfo as u where u.user_id=:uid and u.company_id=:cid and u.branch_id=:bid", nativeQuery=true)
    public User findByUserId(@Param("uid") String userId,@Param("cid") String cid,@Param("bid") String bid);
    
    
    @Query(value="select * from userinfo as u where u.branch_id=:bid",nativeQuery=true)
	public List<User> findBybranchid(@Param("bid") String bid);
    
    @Query(value="SELECT DISTINCT u.* FROM userinfo u " +
    	       "LEFT JOIN userrights ur ON u.branch_id = ur.branch_id " +
    	       "LEFT JOIN pmenu p ON ur.process_id = p.process_id " +
    	       "LEFT JOIN cmenu c ON p.process_id = c.pprocess_id " +
    	       "WHERE u.branch_id = :uid  and u.role = 'ROLE_USER' and u.status <> 'D' and u.status='A' and u.company_id=:cid and u.branch_id=:bid", nativeQuery=true)
    	public List<User> findbyuid(@Param("uid") String uid,@Param("cid") String cid,@Param("bid") String bid);

    @Query(value="select mobile from userinfo where branch_id=:bid and user_id=:uid",nativeQuery=true)
   public String getmobileno(@Param("bid") String bid,@Param("uid") String uid);
    
    @Query(value="select * from userinfo as u where u.user_id=:uid and u.branch_id=:bid", nativeQuery=true)
    public User findByUser_Idandbranch(@Param("uid") String userId,@Param("bid") String bid);
    
    public User findByLogintypeid(String partyId);

	
	 @Transactional
	    void deleteByLogintypeid(String partyId);

	public User getByLogintypeid(String partyId);
}
