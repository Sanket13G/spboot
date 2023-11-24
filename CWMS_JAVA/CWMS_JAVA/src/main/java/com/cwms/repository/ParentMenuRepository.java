 package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.*;
@EnableJpaRepositories
public interface ParentMenuRepository extends JpaRepository<ParentMenu, String> {
	
	@Query(value="select p.* from pmenu p where p.company_id=:cid and p.branch_id=:bid",nativeQuery=true)
	public List<ParentMenu> getAllData(@Param("cid") String cid,@Param("bid") String bid);

	
	@Query(value = "select pmenu_name from pmenu",nativeQuery = true)
	public List<String> getparentname();
	
	@Query(value="select p.*,c.* from pmenu p LEFT JOIN cmenu c ON p.process_id = c.pprocess_id where p.process_id=:id OR c.process_id=:id",nativeQuery=true)	
	public List<Object[]> getall(@Param("id") String id);
	
	@Query(value="select * from pmenu p where p.process_id=:id and p.company_id=:cid and p.branch_id=:bid",nativeQuery=true)
	public ParentMenu getallbyprocessId(@Param("id") String id,@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from pmenu where child_menu_status <> 'Y'",nativeQuery=true)
	public List<ParentMenu> getallparent();
	
    @Query(value = "select * from pmenu p where p.child_menu_status = 'Y'",nativeQuery=true)
	public List<String> getParentMenuIdsWithChildStatusY();
    
    
    @Query(value="select p.process_id,p.pmenu_name from pmenu p union select c.process_id,c.child_menu_name from cmenu c",nativeQuery=true)
    public List<Object[]> getalldata();
	  
	  
	  
}
