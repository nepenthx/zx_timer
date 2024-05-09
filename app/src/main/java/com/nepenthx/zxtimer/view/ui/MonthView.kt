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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nepenthx.zxtimer.ui.theme.ZxtimerTheme
import com.nepenthx.zxtimer.view.components.MonthView
import com.nepenthx.zxtimer.view.components.TaskCardExample

@Composable
fun MonthViewOfTasks()
{
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        MonthView()
        Spacer(modifier = Modifier.height(30.dp))

        TaskCardExample()
    }

}