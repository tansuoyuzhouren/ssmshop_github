package com.javapandeng.utils;
/**
 * 用来传递列表对象的ThreadLocal数据
 * @author Administrator
 *
 */
public class SystemContext {
	/**
	 * ThreadLocal<T> : 主要作用就是实现 线程之间的隔离 每个线程都能拥有自己独立的变量副本
	 * 大概就是这张图的意思
	 * threadLocals : 是每个线程的局部变量
	 * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	 * |       Thread 1                     |        Thread 2                   |
	 * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	 * |  ThreadLocalMap(threadLocals)      |      ThreadLocalMap(threadLocals) |
	 * |  threadLocal 1  |        T         |  threadLocal 1   |       T        |
	 * |  threadLocal 2  |        T         |  threadLocal 1   |       T        |
	 * |  threadLocal 3  |        T         |  threadLocal 1   |       T        |
	 * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
	 * */
	/**
	 * 分页大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

	/**
	 * 页码
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();

	/**
	 * 列表的排序字段
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();

	/**
	 * 列表的排序方式
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();

	private static ThreadLocal<String> realPath = new ThreadLocal<String>();

	public static String getRealPath(){
		return realPath.get();
	}

	public static void setRealPath(String _realPath){
		SystemContext.realPath.set(_realPath);
	}

	public static Integer getPageSize() {
		return pageSize.get();
	}

	public static void setPageSize(Integer _pageSize){
		SystemContext.pageSize.set(_pageSize);
	}

	public static Integer getPageOffset(){
		return pageOffset.get();
	}

	public static void setPageOffset(Integer _pageOffset){
		SystemContext.pageOffset.set(_pageOffset);
	}

	public static String getSort(){
		return sort.get();
	}

	public static void setSort(String _sort){
		SystemContext.sort.set(_sort);
	}

	public static String getOrder(){
		return order.get();
	}

	public static void setOrder(String sort){
		SystemContext.order.set(sort);
	}

	//清空线程内容
	public static void removePageSize(){
		pageSize.remove();
	}

	public static void removePageOffset(){
		pageOffset.remove();
	}

	public static void removeSort(){
		sort.remove();
	}

	public static void removeOrder(){
		order.remove();
	}

	public static void removeRealPath(){
		realPath.remove();
	}
}
