package com.lgh.sys.service;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgh.common.service.BaseService;
import com.lgh.sys.dao.AdminDao;
import com.lgh.sys.entity.Admin;
import com.lgh.sys.entity.User;

/**
 * AdminService
 * @author liugh
 *
 */
@Service
public class AdminService extends BaseService<Admin> {
	@Autowired
	private AdminDao adminDao;

	/**
	 * 根据用户名和密码查找管理员是否存在
	 * @param admin
	 * @return Admin/null 存在返回Admin，不存在返回null
	 */
	@SuppressWarnings("rawtypes")
	public Admin findAdmin(Admin admin){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Admin.class);
		detachedCriteria.add(Restrictions.eq("name", admin.getName()));
		detachedCriteria.add(Restrictions.eq("password", admin.getPassword()));
		List list = adminDao.findByCriteria(detachedCriteria);
		
		if(list.isEmpty()){
			return null;
		}else if(list.size() == 1){
			return (Admin) list.get(0);
		}else{
			return null;
		}
	
	}

	//根据用户名来查询管理员是否存在
	@SuppressWarnings("rawtypes")
	public boolean existAdminName(String name) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		List list = adminDao.findByCriteria(detachedCriteria);
		if(list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	

}