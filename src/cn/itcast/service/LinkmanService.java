package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Linkman;
import cn.itcast.domain.PageBean;

public interface LinkmanService {

	void save(Linkman linkman);

	List<Linkman> findAll();

	List<Linkman> findByDc(DetachedCriteria dc);

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

	PageBean findByDc(DetachedCriteria dc, Integer pageNumber, Integer pageSize);

}
