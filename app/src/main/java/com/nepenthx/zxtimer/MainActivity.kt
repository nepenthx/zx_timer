package com.nepenthx.zxtimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nepenthx.zxtimer.ViewModel.UiViewModel

import com.nepenthx.zxtimer.data.databaseOncreate
import com.nepenthx.zxtimer.data.selectTaskData
import com.nepenthx.zxtimer.view.navigation.BottomNav
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val dbHelper = databaseOncreate(this, "createTasks.db", 1)
                dbHelper.writableDatabase
                val viewModel: UiViewModel = viewModel()
                viewModel.taskData.value= selectTaskData(this, LocalDate.now().toString())
                BottomNav(viewModel)
            }
        }
    }
}
