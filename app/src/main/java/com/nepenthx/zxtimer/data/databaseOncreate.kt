package com.nepenthx.zxtimer.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * 后端数据库和前端数据库是一样的才方便同步吧
 */
class databaseOncreate (val context: Context, name: String, version: Int): SQLiteOpenHelper(context,name,null,version) {


    private val createTasks = "CREATE TABLE tasks (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "taskName TEXT," +
            "createdate DATETIME," +
            "cycle INTEGER," +
            "endTime DATETIME," +
            "expectedTime INTEGER," +
            "advanceCompletionBoolean INTEGER," +
            "advanceCompletionTime INTEGER," +
            "emergencyDegree INTEGER," +
            "remarks TEXT)";
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTasks)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}