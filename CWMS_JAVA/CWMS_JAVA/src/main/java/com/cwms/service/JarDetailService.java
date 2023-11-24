package com.cwms.service;

import java.util.List;

import com.cwms.entities.JarDetail;

import jakarta.transaction.Transactional;

public interface JarDetailService {

	JarDetail saveJarDetail(JarDetail jarDetail);
	
	List<JarDetail> list(String cid);
	
	List<JarDetail> listByJarId(String jarId,String cid);
	
	JarDetail findJarDetail(String id,String cid);

}