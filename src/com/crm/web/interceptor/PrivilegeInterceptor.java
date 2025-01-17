package com.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Ȩ��������
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// �ж�session���Ƿ��е�¼����
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("δ��¼�����ȵ�¼");
			return actionSupport.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}
