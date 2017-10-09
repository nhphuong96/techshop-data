package com.techshop.api.dao;

import java.util.List;

import com.techshop.api.util.Result;

public interface GenericDAO<T, K> {
	Result<T> save(T t);
	Result<T> findOne(K id);
	Result<List<T>> findAll();
	Result<T> update(T t);
	Result<K> delete(K t);
}
