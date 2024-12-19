package com.javapandeng.utils;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页对象
 * @author Administrator
 *
 * @param <T>
 */
public class Pager<T> {

	/**
	 * 分页的大小
	 */
	private int size;

	/**
	 * 页码
	 */
	private int offset;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 分页的数据
	 */
	private List<T> datas;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	/**
	 * 构造方法里面求数据
	 * @param datas
	 */
	//instanceof : 判断 datas 对象是否是 Page 类型的实例(实例：指的是根据类创建的具体对象)
	public Pager(List<T> datas){
		if (datas instanceof Page){
			Page<T> page = (Page<T>) datas;

			setOffset(page.getPageNum());
			setSize(page.getPageSize());
			setTotal(page.getTotal());
			setDatas(datas);

		}
	};

	public Pager(){}
}
