package com.lin.pager;

/**
 * 
 * @ClassName: PageInfo 
 * @Description: 分页器
 * @author xuelin 
 * @date Jun 30, 2015 4:45:09 PM 
 *
 */
public final class PageInfo {
	/**
	 * 单页记录总数
	 */
	private int pageSize;
	/**
	 * 当前页码
	 * 
	 */
	private int pageNow;
	/**
	 * 总记录总数
	 * 
	 */
	private long count;
	
	public PageInfo() {
		super();
	}
	
	/**
	 * 默认当前为第1页   每页展示10条
	 * 
	 * @param count
	 */
	public PageInfo(long count) {
		this(1, count);
	}

	/**
	 *  默认每页展示10条
	 * 
	 * @param pageNow
	 * @param count
	 */
	public PageInfo(int pageNow, long count) {
		this(10, pageNow, count);
	}
	
	public PageInfo(int pageSize, int pageNow, long count) {
		super();
		
		if(0 == pageSize || 0 == pageNow){
			throw new IllegalArgumentException("参数错误，创建分页器失败！");
		}
		
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.count = count;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNow() {
		return pageNow;
	}
	
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount(){
		int val = (int) (this.count / this.pageSize);
		int mod = (int) (this.count % this.pageSize);
		
		if(0 == mod){
			return val;
		}
		
		return val + 1;
	}
	
	/**
	 * 获取记录开始下表
	 * 
	 * @return
	 */
	public int getOffset(){
		if(this.pageNow > 0){
			return (this.pageNow - 1) * this.pageSize;
		}
		
		return 0;
	}
}
