package com.hhu.ireciteword.data.dao;

import com.hhu.ireciteword.data.vo.LookUpResult;

import java.util.List;

public interface LookUpResultDao {

    /**
     * 插入查词获得的信息
     * @param lookUpResult vo
     */
    void insert(LookUpResult lookUpResult);


    /**
     * 随机弹出单词
     * @param count 单词数量
     * @return LookUpResult
     */
    List<LookUpResult> randomQuery(int count);

    /**
     * 优先在查询历史中查找
     * @param word 目标单词
     * @return 返回信息
     */
    LookUpResult queryByHistory(String word);

    /**
     * 删除所有查询历史
     */
    void deleteAll();
}
