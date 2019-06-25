package com.jonlin.test.LinkedList;

/**
 * description 自定义链表接口定义
 * Author Jonlin
 * Date 2019/6/14 13:18
 **/
public interface ILinkedList<T> {
    /**
     * 功能描述 添加元素
     */
    boolean add(T t);

    /**
     * 打印链表
     */
    void printLink();

    /**
     * 移除元素
     * @param t
     * @return
     */
    T remove(T t);

    /**
     * 设置值
     *
     * @param index
     * @param t
     * @return
     */
    T set(int index, T t);

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 是否含有已知元素
     *
     * @param t
     * @return
     */
    boolean contains(T t);

    /**
     * 转成array
     *
     * @return
     */
    T[] toArray();

    /**
     * 元素个数
     *
     * @return
     */
    int size();

    /**
     * 清空
     *
     * @return
     */
    boolean clean();

}
