/**

 * @author Mr Tang

 * @version Create Time:2018年10月2日 下午3:08:43
 * com.tang.filter
 *

 */
package com.aiit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: CharacterEncodingFilter
 * @Description: 此过滤器用来解决解决get、post请求方式下的中文乱码问题
 * @author: Mr tang
 * @date: 2018-8-31 下午11:09:37
 *
 */ 
public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig = null;
	//设置默认的字符编码
	private String defaultCharset = "UTF-8";

	public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//得到在web.xml中配置的字符编码
		String charset = filterConfig.getInitParameter("charset");
		if(charset==null){
			charset = defaultCharset;
		}
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);

		MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(request);
		//放行
		chain.doFilter(requestWrapper, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//得到过滤器的初始化配置信息
		this.filterConfig = filterConfig;
		System.out.println("Filter初始化");
	}

	/**
	 * Filter对象创建后会驻留在内存，当web应用移除或服务器停止时才被销毁。
	 * 该方法在Filter的生命周期中仅执行一次。在这个方法中，可以释放过滤器使用的资源。
	 */
	public void destroy() {
		System.out.println("Filter销毁，完成资源回收");
	}
}

/**
 * @ClassName: MyCharacterEncodingRequest
 * @Description: Servlet API中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper,
 * (HttpServletRequestWrapper类实现了request接口中的所有方法，但这些方法的内部实现都是仅仅调用了一下所包装的的 request对象的对应方法)
 * 以避免用户在对request对象进行增强时需要实现request接口中的所有方法。
 * 所以当需要增强request对象时，只需要写一个类继承HttpServletRequestWrapper类，然后在重写需要增强的方法即可
 * @author: Mr Tang
 * @date: 2018-10-2 下午10:42:57
 *   1.实现与被增强对象相同的接口 
     2、定义一个变量记住被增强对象
     3、定义一个构造函数，接收被增强对象
     4、覆盖需要增强的方法
     5、对于不想增强的方法，直接调用被增强对象（目标对象）的方法
 */ 
class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
	//定义一个变量记住被增强对象(request对象是需要被增强的对象)
	private HttpServletRequest request;
	//定义一个构造函数，接收被增强对象
	public MyCharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	/* 覆盖需要增强的getParameter方法
	 * 对此方法对获取到的字符进行中文编码，起到过滤器的作用。
	 * 只要调用getParameter方法，此程序段就会执行。
	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		try{
			//获取参数的值
			String value= this.request.getParameter(name);
			//存在获取的值为空的情况，需要考虑进来
			if(value==null){
				return null;
			}
			//如果不是以get方式提交数据的，就直接返回获取到的值
			if(!this.request.getMethod().equalsIgnoreCase("get")) {
				return value;
			}else{
				//如果是以get方式提交数据的，就对获取到的值进行转码处理
				value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
				return value;
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}


