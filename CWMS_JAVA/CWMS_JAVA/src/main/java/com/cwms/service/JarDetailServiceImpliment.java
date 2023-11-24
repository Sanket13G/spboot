package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.JarDetail;
import com.cwms.repository.JarDetailRepository;

import jakarta.transaction.Transactional;
@Service
public class JarDetailServiceImpliment implements JarDetailService{

@Autowired
JarDetailRepository jarDetailRepository;
	
	@Override
	public JarDetail saveJarDetail(JarDetail jarDetail) {
		// TODO Auto-generated method stub
		return jarDetailRepository.saveAndFlush(jarDetail);
	}

	@Override
	public List<JarDetail> list(String cid) {
		// TODO Auto-generated method stub
		return jarDetailRepository.getalldata(cid);
	}

	@Override
	public JarDetail findJarDetail(String id,String cid) {
		// TODO Auto-generated method stub
		return jarDetailRepository.findJarDetailByID(id,cid);
	}

	@Override
	public List<JarDetail> listByJarId(String jarId,String cid) {
		// TODO Auto-generated method stub
		return jarDetailRepository.findByID(jarId,cid);
	}
	

	
	
}