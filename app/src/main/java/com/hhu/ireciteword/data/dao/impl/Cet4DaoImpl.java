package com.hhu.ireciteword.data.dao.impl;

import com.hhu.ireciteword.data.dao.Cet4Dao;
import com.hhu.ireciteword.data.vo.Cet4;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class Cet4DaoImpl implements Cet4Dao {

    @Override
    public List<Cet4> randomQuery(int count) {
        return SQLite.select().
                from(Cet4.class).
                orderBy(OrderBy.fromString("RANDOM()")).
                limit(count).
                queryList();
    }
}
