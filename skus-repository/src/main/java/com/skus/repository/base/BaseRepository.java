package com.skus.repository.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	T findOne(ID primaryKey);

	List<T> findAll();

	long count();

	void delete(T entity);

	boolean exists(ID primaryKey);
}
