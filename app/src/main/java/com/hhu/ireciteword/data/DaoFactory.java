package com.hhu.ireciteword.data;

import com.hhu.ireciteword.data.dao.Cet4Dao;
import com.hhu.ireciteword.data.dao.Cet6Dao;
import com.hhu.ireciteword.data.dao.LookUpResultDao;
import com.hhu.ireciteword.data.dao.RecordDao;
import com.hhu.ireciteword.data.dao.impl.Cet4DaoImpl;
import com.hhu.ireciteword.data.dao.impl.Cet6DaoImpl;
import com.hhu.ireciteword.data.dao.impl.LookUpResultDaoImpl;
import com.hhu.ireciteword.data.dao.impl.RecordDaoImpl;

/**
 * @author Ji Rui
 */

public class DaoFactory {
    public static Cet4Dao getCet4DaoInstance() {
        return new Cet4DaoImpl();
    }

    public static Cet6Dao getCet6DaoInstance() {
        return new Cet6DaoImpl();
    }

    public static LookUpResultDao getLookUpResultDaoInstance() {
        return new LookUpResultDaoImpl();
    }

    public static RecordDao getRecordDaoInstance() {
        return new RecordDaoImpl();
    }
}
