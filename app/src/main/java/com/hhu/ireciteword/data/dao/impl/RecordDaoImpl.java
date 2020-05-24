package com.hhu.ireciteword.data.dao.impl;

import com.hhu.ireciteword.data.dao.RecordDao;
import com.hhu.ireciteword.data.vo.Record;

public class RecordDaoImpl implements RecordDao {
    @Override
    public void insert(Record record) {
        record.setCreateTime(new java.sql.Date(new java.util.Date().getTime()).toString());
        record.save();
    }
}
