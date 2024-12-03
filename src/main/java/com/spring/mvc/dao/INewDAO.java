package com.spring.mvc.dao;

import java.util.List;

import com.spring.mvc.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findAll();
}
