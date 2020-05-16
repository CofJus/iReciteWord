package com.hhu.ireciteword.data.dao.impl;

import com.hhu.ireciteword.data.dao.Cet6Dao;
import com.hhu.ireciteword.data.vo.Cet6;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class Cet6DaoImpl implements Cet6Dao {
    @Override
    public List<Cet6> randomQuery(int count) {
        return SQLite.select().
                from(Cet6.class).
                orderBy(OrderBy.fromString("RANDOM()")).
                limit(count).
                queryList();
    }
}
