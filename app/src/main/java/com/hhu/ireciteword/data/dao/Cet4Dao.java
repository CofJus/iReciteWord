package com.hhu.ireciteword.data.dao;

import com.hhu.ireciteword.data.vo.Cet4;

import java.util.List;

public interface Cet4Dao {

    /**
     * 随机弹出若干个四级单词
     * @param count 设定单词数量，今日目标
     * @return List
     */
    List<Cet4> randomQuery(int count);
}
