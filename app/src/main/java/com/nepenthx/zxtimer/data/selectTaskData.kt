package com.nepenthx.zxtimer.data

import android.annotation.SuppressLint
import android.content.Context

/**
 *   private val createTasks = "CREATE TABLE tasks (" +
 *             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
 *             "taskName TEXT," + 1
 *             "createdate DATETIME," +1
 *             "cycle INTEGER," + 1 用来标记是否完成算了
 *             "endTime DATETIME," +  暂时用不到
 *             "expectedTime INTEGER," + 1
 *             "advanceCompletionBoolean INTEGER," +//是否是正计时 1
 *             "advanceCompletionTime INTEGER," +//超过/提前时间 1
 *             "emergencyDegree INTEGER," +
 *             "remarks TEXT)";
 */


@SuppressLint("Range")
fun selectTaskData(context: Context, createdate:String):MutableList<TaskData>{
    val dbHelper = databaseOncreate(context, "createTasks.db", 1)

    val db=dbHelper.writableDatabase
    val cursor=db.rawQuery("SELECT * FROM tasks WHERE createdate = '$createdate'",null)
    val mutableTask:MutableList<TaskData> = mutableListOf()

    try {
        if(cursor.moveToFirst()){
            do{
                val taskName = cursor.getString(cursor.getColumnIndex("taskName"))
                val createDate = cursor.getString(cursor.getColumnIndex("createdate"))
                val cycle=cursor.getInt(cursor.getColumnIndex("cycle"))
                val expectedTime = cursor.getInt(cursor.getColumnIndex("expectedTime"))
                val advanceCompletionBoolean = cursor.getInt(cursor.getColumnIndex("advanceCompletionBoolean"))
                val advanceCompletionTime = cursor.getInt(cursor.getColumnIndex("advanceCompletionTime"))
                val remarks = cursor.getString(cursor.getColumnIndex("remarks"))
                val emergencyDegree = cursor.getInt(cursor.getColumnIndex("emergencyDegree"))
                val taskFrame=TaskData(taskName, createDate, cycle, expectedTime, advanceCompletionBoolean, advanceCompletionTime, remarks, emergencyDegree)
                mutableTask.add(taskFrame)

            } while (cursor.moveToNext())
        }

    }finally {
        cursor?.close()
        db.close()

    }
    return mutableTask
}