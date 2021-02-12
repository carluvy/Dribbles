package com.coolbanter.examprep.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.coolbanter.dribble.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;

@Database(entities = Smiley.class, version = 5, exportSchema = false)
public abstract class SmileyDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();

    public abstract SmileyDao smileyDao();

    private static volatile SmileyDatabase sInstance = null;
    private static volatile SmileyDatabase INSTANCE = null;


    /**
     * Returns an instance of Room Database.
     *
     * @param context application context
     * @return The singleton SmileyDatabase
     */
    @NonNull
    public static synchronized SmileyDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            SmileyDatabase.class, DataSmileyName.TABLE_NAME)
//                                .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);


                                    Executors.newSingleThreadScheduledExecutor().execute(() -> {
                                        fillWithDemoData(context.getApplicationContext());
//                                        getInstance(context).smileyDao()
//                                                .getSmiley();
//
//

                                    });


                                }

                            })


                            .build();


//                        fillWithDemoData(context.getApplicationContext());


                    //TODO create a database instance and fill with data from JSON

                }

            }

        }

        return sInstance;

    }

    @WorkerThread
    private static void fillWithDemoData(Context context) {
        SmileyDao dao = getInstance(context).smileyDao();
        JSONArray emoji = loadJsonArray(context);
        try {
            for (int i = 0; i < emoji.length(); i++) {
                JSONObject item = emoji.getJSONObject(i);
                dao.insert(new Smiley(item.getString("code"),
                        item.getString("name"),
                        item.getString("char")));
            }
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private static JSONArray loadJsonArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.emoji);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("emojis");

        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
