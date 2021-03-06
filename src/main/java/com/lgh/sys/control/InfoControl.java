package com.lgh.sys.control;


import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lgh.common.tools.json.JsonUtil;
import com.lgh.sys.entity.Info;
import com.lgh.sys.entity.User;
import com.lgh.sys.service.InfoService;

@Scope("prototype")
@Controller
@Namespace("/info")
public class InfoControl implements com.opensymphony.xwork2.Action {

	@Autowired
	private InfoService infoService;
	private Info info;
	
	private Integer p = 1;//当前页码 赋默认值1 防止null
	private Integer size = 2; //设置每页显示的信息条数 防止null

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
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

	@Action(value = "pubLost", results = {
			@Result(name = "success", location = "pubSuccess.jsp", type = "redirect"),
			@Result(name = "error", location = "pubError.jsp", type = "redirect") })
	public String pubLost() {
		try {
			User curruser = (User) ServletActionContext.getRequest().getSession().getAttribute("curruser");
			info.setPublishUserId(curruser.getId());
			infoService.save(info);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
			
		}
		return SUCCESS;
	}
	
	@Action(value = "getLostJSON")
	public String getLostJSON() {
		try {
			//List<Info> infos = infoService.findAll(Info.class);
			int fromIndex = (p-1) * size;
			List<Info> infos = infoService.findLostByPage("id", fromIndex, size);
			
			JsonUtil.outToJson(ServletActionContext.getResponse(), infos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Action(value = "getFoundJSON")
	public String getFoundJSON() {
		try {
			int fromIndex = (p-1) * size;
			List<Info> infos = infoService.findFoundByPage("id", fromIndex, size);
			
			JsonUtil.outToJson(ServletActionContext.getResponse(), infos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}