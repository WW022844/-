package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;

public interface CustomerService {

	void save(Customer customer);

	List<BaseDict> findLevel(String string);

	List<BaseDict> findSource(String string);

	List<BaseDict> findIndustry(String string);

	List<Customer> findAll();

	List<Customer> conditionFind(DetachedCriteria dc);

	Customer findById(Integer cust_id);

	void update(Customer customer);

	void delete(Customer customer);

}
