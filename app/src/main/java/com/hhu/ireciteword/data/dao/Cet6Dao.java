package com.hhu.ireciteword.data.dao;

import com.hhu.ireciteword.data.vo.Cet6;

import java.util.List;

public interface Cet6Dao {

    /**
     * 随机弹出若干个六级单词
     * @param count 设定单词数量，今日目标
     * @return List
     */
    List<Cet6> randomQuery(int count);
}
