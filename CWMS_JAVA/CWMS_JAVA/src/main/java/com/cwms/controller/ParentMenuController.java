package com.cwms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ChildMenu;
import com.cwms.entities.ParentMenu;
import com.cwms.repository.ChildMenuRepository;
import com.cwms.repository.ParentMenuRepository;
import com.cwms.service.MenuServiceImpl;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@ComponentScan("com.repo.menu")
public class ParentMenuController {

	@Autowired
	public ParentMenuRepository prepo;

    @Autowired
	public ChildMenuRepository crepo;
    
    @Autowired
    public  MenuServiceImpl menuService;
    
    @GetMapping("/parent-menus/{cid}/{bid}")
    public List<ParentMenu> getParentMenus(@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
    
        return prepo.getAllData(cid,bid);
    }
    
    @GetMapping("/parentMenus")
    public List<ParentMenu> getAllParent(){
    	return prepo.getallparent();
    }
    
    @GetMapping("/child-menus/{cid}/{bid}")
    public List<ChildMenu> getChildmenus(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
    	
    	return crepo.getAllData(cid, bid);
    }
    
    
    @GetMapping("/child-menus/{id}")
    public List<ChildMenu> getChildMenus(@PathVariable("id") String pmenuid) {
    
    	return crepo.getChildByPid(pmenuid);
    }
    
    
    @GetMapping("/pmenu")
    public List<String> getParentName(){
    	
    	return prepo.getparentname();
    	
    }
    
    @GetMapping("/cm/{id}/{cid}/{bid}")
    public List<ChildMenu> getChildMenusByProcess(@PathVariable("id") String pmenuid,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
    	
    	return crepo.getChildByProcessid(pmenuid,cid,bid);
    }
//    
//    @GetMapping("/all-menu")
//    public List<ParentMenu> getallmenu(){
//    	return prepo.getall();
//    }
    
    
    @GetMapping("/child/{id}/{cid}/{bid}")
    public ChildMenu getChildMenusByProcessID(@PathVariable("id") String pmenuid,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {

    	return crepo.getChildByprocessid(pmenuid,cid,bid);
    }
    
    
    @GetMapping("/parent/{id}/{cid}/{bid}")
    public ParentMenu getParentMenusbyprocessId(@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
    	
    	return prepo.getallbyprocessId(id,cid,bid);
    }
    
    @GetMapping("/alldata")
    public ResponseEntity<List<Map<String, Object>>> getAllParentAndChildMenus() {
        List<Map<String, Object>> menuData = menuService.getAllParentAndChildMenus();
        return ResponseEntity.ok(menuData);
    }
    
}
