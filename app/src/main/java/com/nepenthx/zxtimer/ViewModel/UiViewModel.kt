package com.nepenthx.zxtimer.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nepenthx.zxtimer.data.TaskData
import com.nepenthx.zxtimer.data.selectTaskData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class UiViewModel: ViewModel(){
     val selectDate = mutableStateOf(LocalDate.now())
     @SuppressLint("MutableCollectionMutableState")
     val taskData = mutableStateOf(mutableListOf<TaskData>())
     fun fetchTasks(context: Context) {
          viewModelScope.launch {
               val createdateString =selectDate.value.toString()
               val tasks = withContext(Dispatchers.IO) {
                    selectTaskData(context, createdateString)
               }
               taskData.value = tasks
          }
     }
}