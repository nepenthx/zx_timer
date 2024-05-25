package com.nepenthx.zxtimer.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nepenthx.zxtimer.ViewModel.UiViewModel
import java.time.LocalDate

@SuppressLint("InvalidColorHexValue")
@Composable
fun AddEvent(viewModel:UiViewModel)
{
    val showAddEventDialog=remember{mutableStateOf(false)}
    Box(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()
        .clickable{
            showAddEventDialog.value=true
        }

        .border(width = 1.dp, color = Color(0x0F1F1A1A))
    )
    {
        Text(text = "点击新建任务",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            fontFamily = FontFamily.Cursive
            )
    }
    if(showAddEventDialog.value)
    {
        TaskDialog(viewModel,onDismissRequest={showAddEventDialog.value=false})
    }
}

