package com.hhu.ireciteword.data.dao.impl;

import com.hhu.ireciteword.data.dao.LookUpResultDao;
import com.hhu.ireciteword.data.vo.LookUpResult;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * @author Ji Rui
 */
public class LookUpResultDaoImpl implements LookUpResultDao {

    @Override
    public void insert(LookUpResult lookUpResult){
        lookUpResult.save();
    }

    @Override
    public List<LookUpResult> randomQuery(int count){
        // SELECT * FROM LookUpResult order by RANDOM() limit 1;
        return SQLite.select().
                from(LookUpResult.class).
                orderBy(OrderBy.fromString("RANDOM()")).
                limit(count).
                queryList();
    }

    @Override
    public LookUpResult queryByHistory(String word) {
        return SQLite.select().
                from(LookUpResult.class).
                //where(LookUpResult_Table.word.eq(word)).
                queryList().get(0);
    }

    @Override
    public void deleteAll() {
        SQLite.delete(LookUpResult.class).execute();
    }
}
