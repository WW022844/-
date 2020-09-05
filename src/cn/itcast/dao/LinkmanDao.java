package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Linkman;

public interface LinkmanDao {

	void save(Linkman linkman);

	List<Linkman> findAll();

	List<Linkman> findByDc(DetachedCriteria dc);

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

	int findcount(DetachedCriteria dc);

	List<Linkman> findLinkmanPage(DetachedCriteria dc, int i, Integer pageSize);

}
