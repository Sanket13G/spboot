package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.*;


@EnableJpaRepositories
public interface ChildMenuRepository extends JpaRepository<ChildMenu, String> {

	@Query(value = "select child_menu_id,child_menu_name,child_page_links,cicons,pmenu_id_pmenu_id from cmenu as c where c.pmenu_id_pmenu_id=:pmenuid", nativeQuery = true)
	public List<ChildMenu> getChildByPid(@Param("pmenuid") String pmenuid);
	
	@Query(value = "select * from cmenu as c where c.pprocess_id=:pmenuid  and c.company_id=:cid and c.branch_id=:bid", nativeQuery = true)
	public List<ChildMenu> getChildByProcessid(@Param("pmenuid") String pmenuid,@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value = "select * from cmenu as c where c.process_id=:pmenuid and c.company_id=:cid and c.branch_id=:bid", nativeQuery = true)
	public ChildMenu getChildByprocessid(@Param("pmenuid") String pmenuid,@Param("cid") String cid,@Param("bid") String bid);
	

	@Query(value="select p.* from cmenu p where p.company_id=:cid and p.branch_id=:bid",nativeQuery=true)
	public List<ChildMenu> getAllData(@Param("cid") String cid,@Param("bid") String bid);
	
}
