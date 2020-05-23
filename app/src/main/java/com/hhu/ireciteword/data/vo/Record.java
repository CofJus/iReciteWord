package com.hhu.ireciteword.data.vo;

import com.hhu.ireciteword.data.VocabularyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * created by 沈思彤 on 2020/5/13
 */

@Table(database = VocabularyDatabase.class)
public class Record extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private Integer id;

    @Column
    private String titleName;

    @Column
    private String textBody;

    @Column
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
