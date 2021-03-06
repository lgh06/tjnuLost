package com.lgh.sys.control;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lgh.common.tools.json.JsonUtil;
import com.lgh.sys.entity.Department;
import com.lgh.sys.service.DepartService;

@Scope("prototype")
@Controller
@Namespace("/admin/depart")
public class DepartControl implements com.opensymphony.xwork2.Action {

	@Autowired
	private DepartService departService;
	private Department depart;
	private Integer p = 1;//当前页码 赋默认值1 防止null
	private Integer size = 5; //设置每页显示的信息条数 防止null
	
	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * 获取所有部门信息 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "getDepartJSON", results = { @Result(name = "success", location = "aa.jsp", type = "redirect") })
	public String getDepartJSON() throws Exception {
		int fromIndex = (p - 1) * size;
		List<Department> list = departService.findAllByPageAndOrder(
				Department.class, "id", "desc", fromIndex, size);
		JsonUtil.outToJson(ServletActionContext.getResponse(), list);
		return null;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}