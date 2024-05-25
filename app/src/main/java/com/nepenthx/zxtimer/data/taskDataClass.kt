package com.nepenthx.zxtimer.data

data class TaskData(
    val taskName: String,
    val createDate: String,
    val cycle: Int,
    val expectedTime: Int,
    val advanceCompletionBoolean: Int,
    val advanceCompletionTime: Int,
    val remarks: String,
    val emergencyDegree: Int
)