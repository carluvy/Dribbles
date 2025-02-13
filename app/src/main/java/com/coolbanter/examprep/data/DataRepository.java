package com.coolbanter.examprep.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Handles data sources and makes sure to execute on the correct thread.
 */
public class DataRepository {

    private final SmileyDao mDao;
    private final ExecutorService mIoExecutor;
    private static volatile DataRepository sInstance = null;

    public static DataRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    SmileyDatabase database = SmileyDatabase.getInstance(context);
                    sInstance = new DataRepository(database.smileyDao(),
                            Executors.newSingleThreadExecutor());
                }
            }
        }
        return sInstance;
    }

    public DataRepository(SmileyDao dao, ExecutorService executor) {
        mIoExecutor = executor;
        mDao = dao;

    }

    public DataSource.Factory<Integer, Smiley> getSmileys() {

        return mDao.getAll();

    }


    public void insert(Smiley smiley){
        mIoExecutor.execute((() -> mDao.insert(smiley)));


    }

    public LiveData<List<Smiley>> getRandomSmileys(int limit) {
////        mIoExecutor.submit((Runnable) mDao.getRandom(limit).getValue());
//        mIoExecutor.execute(() -> mDao.getRandom(limit));
////        mIoExecutor.submit((Runnable) mDao.getRandom(limit));
        return mDao.getRandom(limit);

    }

    public void delete(Smiley smiley) {
        mIoExecutor.execute(() -> mDao.delete(smiley));
    }

    public Smiley getSmiley() {
        try {
            return mIoExecutor.submit(mDao::getSmiley).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

}
