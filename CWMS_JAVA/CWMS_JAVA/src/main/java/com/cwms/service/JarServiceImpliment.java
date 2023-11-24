package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Jar;
import com.cwms.entities.JarId;
import com.cwms.repository.JarRepository;

import jakarta.transaction.Transactional;
@Service
public class JarServiceImpliment  implements JarService{

	@Autowired
	JarRepository jarRepository;
	
	@Override
	public List<Jar> getlist(String cid) {
		return jarRepository.getAlldata(cid);
	}

	@Override
	public Jar saveJar(Jar jar) {
		return jarRepository.saveAndFlush(jar);
	}

	
	@Override
	@Transactional
	public Jar findByJarID(String id,String cid) {
		// TODO Auto-generated method stub
		return jarRepository.findByjarid(id, cid);
	}

	
}