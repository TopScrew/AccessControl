package com.screw.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivilegeFilter implements Filter{
	private Properties properties = new Properties();

	@Override
	public void destroy() {
		properties=null;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//获取privilegeFile配置文件的相对路径 ===》 fileName: /WEB-INF/privilege.properties
		String fileName=filterConfig.getInitParameter("privilegeFile");
		//获取配置文件的绝对路径  realPath: E:\code\Work2018\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\AccessControl\WEB-INF\privilege.properties
		String realPath = filterConfig.getServletContext().getRealPath(fileName);
		try
		{
			properties.load(new FileInputStream(realPath));
		}
		catch(Exception e)
		{
			filterConfig.getServletContext().log("读取权限控制文件失败",e);
		}
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		//获取项目名后的路径  如：admin/index
		String requestUri=request.getRequestURI().replace(request.getContextPath()+"/", "");
		String role=(String)request.getSession().getAttribute("role");
		role=role==null?"guest":role;
		boolean authen=false;
		//properties.keySet()  配置文件中的key
		for(Object obj:properties.keySet())
		{
			String key=(String)obj;
			//将网站路径和配置文件中的key对应起来
			if(requestUri.indexOf(key)!=-1)
			{	
				//查看对应路径权限是否正确
				if(((String) properties.get(key)).indexOf( role)!=-1)
				{
					authen=true;
					break;
				}
			}
		}
		if(!authen)
		{
			throw new RuntimeException("您无权访问该页面，请以合适的身份登录后查看。");
		}
		chain.doFilter(request, response);
	}	
}
