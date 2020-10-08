package com.shopping.boservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.botodao.mapper.DepartmentBOtoRepositoryDAOMapper;
import com.shopping.daotobo.mapper.DepartmentRepositoryDAOtoBOMapper;
import com.shopping.repository.DepartmentRepository;
import com.shopping.repository.dao.DepartmentDAO;

@Component
public class DepartmentBOServices {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DepartmentBOtoRepositoryDAOMapper bOtoRepositoryDAOMapper;
	@Autowired
	private DepartmentRepositoryDAOtoBOMapper daOtoBOMapper;

	public DepartmentBO create(DepartmentBO bo) {
		DepartmentDAO dao = bOtoRepositoryDAOMapper.mapObject(bo);
		DepartmentDAO respDAO = departmentRepository.create(dao);
		DepartmentBO respBO = daOtoBOMapper.mapObject(respDAO);

		return respBO;
	}

	public List<DepartmentBO> getAll() {
		List<DepartmentDAO> daoList = departmentRepository.getAll();
		List<DepartmentBO> boList = new ArrayList<>();
		for (DepartmentDAO departmentDAO : daoList) {
			DepartmentBO respBO = daOtoBOMapper.mapObject(departmentDAO);
			boList.add(respBO);
		}
		return boList;
	}

	public DepartmentBO getById(int id) {
		DepartmentDAO respDAO = departmentRepository.getById(id);
		DepartmentBO respBO = daOtoBOMapper.mapObject(respDAO);

		return respBO;
	}

	public DepartmentBO update(DepartmentBO departmentBO, int id) {
		DepartmentDAO dao = bOtoRepositoryDAOMapper.mapObject(departmentBO);
		DepartmentDAO respDAO = departmentRepository.update(dao, id);
		DepartmentBO respBO = daOtoBOMapper.mapObject(respDAO);

		return respBO;
	}

	public int deleteById(int id) {
		return departmentRepository.deleteById(id);
	}
}
