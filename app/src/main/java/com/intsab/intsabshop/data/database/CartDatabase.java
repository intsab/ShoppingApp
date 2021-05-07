package com.intsab.intsabshop.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.intsab.intsabshop.data.Constants.Constants;
import com.intsab.intsabshop.data.Models.CartItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by intsabhaider
 * on 07,May,2021
 */

@Database(entities = {CartItem.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    private static volatile CartDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CartDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (CartDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, Constants.DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}