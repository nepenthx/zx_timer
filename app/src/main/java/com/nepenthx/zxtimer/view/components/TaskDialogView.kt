package com.nepenthx.zxtimer.view.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nepenthx.zxtimer.ViewModel.UiViewModel
import com.nepenthx.zxtimer.data.addNewTask
import com.nepenthx.zxtimer.data.selectTaskData
import java.time.LocalDate

/**
 * fun addNewTask(
 * context: Context,
 * taskName:String, 1
 * createDate:LocalDate, 1
 * cycle:Boolean, 1
 * endTime:LocalDate, 1
 * exceptedTime:Int, 1
 * advanceCompletionBoolean:Boolean, 1
 * emergencyDegree:Int,
 * remarks:String
 * )
 */
@Composable
fun TaskDialogView(viewModel: UiViewModel,onDismissRequest: () -> Unit)
{
    val isShowed= remember { mutableStateOf(false) }
    val showRemark= remember { mutableStateOf(false) }
    val str= remember { mutableStateOf("点击此处输入待办名称：") }
    val remark= remember { mutableStateOf("点此添加备注") }
    var time by remember { mutableIntStateOf(0) }
    val advanceCompletionBoolean=remember{ mutableStateOf(true) }
    val context = LocalContext.current

    Column {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "新建待办",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        if(isShowed.value)
        {

            EditDialog("点击输入任务名称~",onDismissRequest = {isShowed.value=false},
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
                width = 1.dp, color = Color(0xFFDADA29)
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
            text = "预期完成时间：",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        )
        {
            WheelPicker{
                   selectTime->
                time=60*selectTime.hour+selectTime.minute
            }
        }
        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier

                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .background(
                            color = if (advanceCompletionBoolean.value) Color(0xFF9A82C4) else Color.White,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clickable { advanceCompletionBoolean.value = true }
                        .height(38.dp)
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "正计时",
                        color = if (advanceCompletionBoolean.value) Color.White else Color.Black,)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .background(
                            color = if (!advanceCompletionBoolean.value) Color(0xFF9A82C4) else Color.White,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clickable { advanceCompletionBoolean.value = false }
                        .height(38.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "倒计时",
                        color = if (!advanceCompletionBoolean.value) Color.White else Color.Black)
                }
            }
        }
        if(showRemark.value)
        {
            EditDialog("点击添加备注",onDismissRequest = {showRemark.value=false},
                callBackValue = {inputValue-> remark.value=inputValue
                    showRemark.value=false
                })
        }
        Spacer(modifier = Modifier.height(6.dp))

        Box(modifier = Modifier
            .height(90.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clickable { showRemark.value = true }
            .border(
                width = 1.dp, color = Color(0xFF38C0A0)
            )
        )
        {
            Text(
                text = remark.value,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
ButtonUI {
if(str.value!="" && str.value!="点击此处输入待办名称：")
{
    Log.d("Insert date","${str.value}success")
    addNewTask(context,str.value, viewModel.selectDate.value,true, LocalDate.now(),time,advanceCompletionBoolean.value,4,remark.value)
    //viewModel.taskData.value= selectTaskData(context,viewModel.selectDate.value.toString())
    viewModel.fetchTasks(context)
}
    onDismissRequest()
}
    }
}
