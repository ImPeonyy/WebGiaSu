package com.spring.mvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.mvc.dao.INewDAO;
import com.spring.mvc.mapper.NewMapper;
import com.spring.mvc.model.NewModel;

@Repository
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	
	@Override
	public List<NewModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		return query(sql.toString(), new NewMapper());
	}
}
