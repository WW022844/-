package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;

public interface CustomerDao {

	void save(Customer customer);

	List<BaseDict> findLevel(String code);

	List<BaseDict> findSource(String code);

	List<BaseDict> findIndustry(String code);

	List<Customer> findAll();

	List<Customer> conditionFind(DetachedCriteria dc);

	Customer findById(Integer cust_id);

	void update(Customer customer);

	void delete(Customer customer);

}
