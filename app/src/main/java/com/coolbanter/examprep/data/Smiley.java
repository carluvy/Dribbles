package com.coolbanter.examprep.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * A Model class that holds information about the emoji.
 * Class defines a table for the Room database with primary key the {@see #mCode}.
 */
@Entity
public class Smiley {

    @NonNull
    @PrimaryKey()
    private String mCode;

    @ColumnInfo(name = DataSmileyName.COL_NAME)
    private String mName;

    @ColumnInfo(name = DataSmileyName.COL_EMOJI)
    private String mEmoji;

    public Smiley(String mCode, String mName, String mEmoji) {
        this.mCode = mCode;
        this.mName = mName;
        this.mEmoji = mEmoji;
    }



    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmoji() {
        return mEmoji;
    }

    public void setmEmoji(String mEmoji) {
        this.mEmoji = mEmoji;
    }

    @NonNull
    public String getCode() {
        return mCode;
    }

    public String getName() {
        return mName;
    }

    public String getEmoji() {
        return mEmoji;
    }

}
