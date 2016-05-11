package com.data.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 分页通用类
 * 本类实现了Iterable接口，可以用foreach来遍历
 * 
 * @author xianwen.tan
 * @param <T>
 *            分页列表项类型
 */
public class Page<T> implements Iterable<T>, Serializable {

    public static final int MAX_PAGE_SIZE = 1000;

    private static final long serialVersionUID = 8102012822572335930L;
    protected int page = 1;
    protected int pageSize = 13;
    // protected String sort;
    protected int totalCount = 0;
    protected List<T> data;

    public static <V> Page<V> create() {
        return new Page<V>();
    }

    public static <V> Page<V> create(int page, int pageSize) {
        return (new Page<V>()).setPage(page).setPageSize(pageSize);
    }

    public static <V> Page<V> create(int page, int pageSize, int totalCount) {
        return (new Page<V>()).setPage(page).setPageSize(pageSize).
                setTotalCount(totalCount);
    }

    public static <V> Page<V> create(int page, int pageSize, int totalCount, List<V> data) {
        return (new Page<V>()).setPage(page).setPageSize(pageSize).
                setTotalCount(totalCount).setData(data);
    }

    /**
     * 将当前分页对象转成另一个分页对象，例如把Company的Page对象转成CompanyVO的Page对象
     * 
     * @param data
     * @return
     */
    public <VO> Page<VO> cast(List<VO> data) {
        return (new Page<VO>()).setPage(page).setPageSize(pageSize).
                setTotalCount(totalCount).setData(data);
    }

    /**
     * 
     * eg:
     * 
     * <pre>
     * <code>
     *  Page&lt;Long&gt; p = findPage(1, 20);
     *         Page&lt;Integer&gt; voPage = p.cast();
     *         if (p.hasData()) {
     *             List&lt;Integer&gt; voList = new ArrayList&lt;Integer&gt;(p.size());
     *             for (Long item : p.getData()) {
     *                 Integer vo = convert(item);
     *                 voList.add(vo);
     *             }
     *             voPage.setData(voList);
     *             //或者voPage = p.cast(voList);
     *         }
     *         return voPage;
     * </code>
     * </pre>
     * 
     * @return
     */
    public <VO> Page<VO> cast() {
        return (new Page<VO>()).setPage(page).setPageSize(pageSize).
                setTotalCount(totalCount);
    }

    /**
     * @return 返回值永远>=1
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return 返回值永远>=0
     */
    @JSONField(serialize=false)
    public int getOffset() {
        return (page - 1) * pageSize;
    }

    /**
     * 是否有上一页
     * 
     * @return
     */
    public boolean hasPrevious() {
        return page > 1;
    }

    /**
     * 获取总页数
     * 
     * @return
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) totalCount / (double) pageSize);
    }

    /**
     * 获取总记录数
     * 
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 判断是否有内容
     * 
     * @return
     */
    public boolean hasData() {
        return null != data && !data.isEmpty();
    }

    @JSONField(serialize=false)
    public boolean isFirst() {
        return !hasPrevious();

    }

    @JSONField(serialize=false)
    public boolean isLast() {
        return !hasNext();
    }

    public boolean hasNext() {
        return page + 1 < getTotalPages();
    }

    public int getPage() {
        return page;
    }

    public List<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        return data;
    }

    public int size() {
        if (null == data) {
            return 0;
        }
        return data.size();
    }

    public Page<T> setPage(int page) {
        this.page = page < 1 ? 1 : page;
        return this;
    }

    public Page<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount > 0 ? totalCount : 0;
        return this;
    }

    public Page<T> setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {// 防止拖死数据库
            this.pageSize = MAX_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
        return this;
    }

    public Page<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        if (null == data) {
            return Collections.emptyIterator();
        }
        return data.iterator();
    }
}
