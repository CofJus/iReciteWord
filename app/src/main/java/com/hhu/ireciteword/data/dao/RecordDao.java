package com.hhu.ireciteword.data.dao;

import com.hhu.ireciteword.data.vo.Record;

public interface RecordDao {

    /**
     * 插入单词笔记
     * @param record record
     */
    void insert(Record record);
}
