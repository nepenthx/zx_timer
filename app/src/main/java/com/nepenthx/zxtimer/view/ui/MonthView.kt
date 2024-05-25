package com.nepenthx.zxtimer.view.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nepenthx.zxtimer.ViewModel.UiViewModel
import com.nepenthx.zxtimer.data.selectTaskData
import com.nepenthx.zxtimer.ui.theme.ZxtimerTheme
import com.nepenthx.zxtimer.view.components.AddEvent
import com.nepenthx.zxtimer.view.components.MonthView
import com.nepenthx.zxtimer.view.components.TaskCardExample
import com.nepenthx.zxtimer.view.components.TaskCardList
import java.time.LocalDate
import java.util.Date

@Composable
fun MonthViewOfTasks(viewModel: UiViewModel)
{
    Column (
        modifier = Modifier
            .height(700.dp)
    ){


        Spacer(modifier = Modifier.height(16.dp))
        MonthView(viewModel)
        Spacer(modifier = Modifier.height(30.dp))
        AddEvent(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        TaskCardList(viewModel)

    }

}