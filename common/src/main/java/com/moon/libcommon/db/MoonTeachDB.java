package com.moon.libcommon.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author ry
 * @date 2019-09-29
 */
@Database(entities = {}, version = 1, exportSchema = false)
public abstract class MoonTeachDB extends RoomDatabase {

}
