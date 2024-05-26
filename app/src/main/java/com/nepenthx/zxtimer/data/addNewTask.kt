package com.nepenthx.zxtimer.data

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import java.time.LocalDate

/**
 *   private val createTasks = "CREATE TABLE tasks (" +
 *             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
 *             "taskName TEXT," +
 *             "createdate DATETIME," +
 *             "cycle INTEGER," +
 *             "endTime DATETIME," +
 *             "expectedTime INTEGER," +
 *             "advanceCompletionBoolean INTEGER," +//是否是正计时
 *             "advanceCompletionTime INTEGER," +//超过/提前时间
 *             "emergencyDegree INTEGER," +
 *             "remarks TEXT)";
 */
fun addNewTask(context: Context, taskName:String, createDate:LocalDate, startTime:String,cycle:Boolean, endTime:LocalDate, exceptedTime:Int, advanceCompletionBoolean:Boolean, emergencyDegree:Int, remarks:String)
{
    val dbHelper = databaseOncreate(context, "createTasks.db", 1)
    val db=dbHelper.writableDatabase
    val values1=ContentValues().apply {
        put("taskName",taskName)
        put("createDate",createDate.toString())
        put("cycle", if (cycle) 1 else 0)
        put("endTime", endTime.toString())
        put("startTime",startTime)
        put("expectedTime", exceptedTime)
        put("advanceCompletionBoolean", if (advanceCompletionBoolean) 1 else 0)
        // 设置其他字段的值
        put("advanceCompletionTime", 0) // 默认值为 0
        put("emergencyDegree", emergencyDegree)
        put("remarks", remarks)
    }
    db.insert("tasks",null,values1)
    Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()
    db.close()
}