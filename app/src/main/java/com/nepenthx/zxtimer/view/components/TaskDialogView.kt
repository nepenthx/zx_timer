package com.nepenthx.zxtimer.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TaskDialogView()
{
    val isShowed= remember { mutableStateOf(false) }
    val str= remember { mutableStateOf("点击此处输入任务名称：") }

    Column {
        Text(
            text = "新建任务",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        if(isShowed.value)
        {
            EditDialog(onDismissRequest = {isShowed.value=false},
                callBackValue = {inputValue-> str.value=inputValue
                isShowed.value=false
                })
        }
        Spacer(modifier = Modifier.height(6.dp))
        //这里是新任务的名称
        Box(modifier = Modifier
            .height(30.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clickable { isShowed.value = true }
            .border(
                width = 1.dp, color = Color.Gray
            )
        )
        {
            Text(
                text = str.value,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "请选择预期时间：",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier
            .wrapContentSize(Alignment.Center),
        )
        {
            WheelPicker()
        }

    }
}

