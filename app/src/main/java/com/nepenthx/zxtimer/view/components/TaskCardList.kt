package com.nepenthx.zxtimer.view.components


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.nepenthx.zxtimer.ViewModel.UiViewModel
import com.nepenthx.zxtimer.data.TaskData
import kotlin.math.exp

/*
data class TaskData(
    val taskName: String, 1
    val createDate: String,  1
    val cycle: Int,  1
    val expectedTime: Int,  1
    val advanceCompletionBoolean: Int,  1
    val advanceCompletionTime: Int,   1
    val remarks: String,   1
    val emergencyDegree: Int 1 //Color
)
 */

/*
fun TaskCardExample() {
    TaskCard(
        cycle=1,
        advanceCompletionBoolean=1,
        taskName = "写作业",
        color = Color.Magenta,
        expectedDuration = "2 hours",
        elapsedDuration = "1 hour 30 minutes",
        remarks=""
    )
}
 */
@Composable
fun TaskCardList(viewModel: UiViewModel){
    LazyColumn (
        modifier= Modifier
            .height(280.dp)
    ){
        items(viewModel.taskData.value.size){index->
            val taskData=viewModel.taskData.value[index]
            TaskCard(
                taskName = taskData.taskName,
                cycle = taskData.cycle,
                advanceCompletionBoolean = taskData.advanceCompletionBoolean,
                expectedDuration = getRightTime(taskData.expectedTime),
                remarks = taskData.remarks,
                // 如果你的 TaskCard 组件接受颜色参数，你可以在这里传递颜色值
                color = emergencyDegreeColor(taskData.emergencyDegree),
                elapsedDuration = getRightTime(taskData.advanceCompletionTime),
            )
        }

    }

}

fun getRightTime( expectedDuration:Int):String
{
    var result=""
        val hour = expectedDuration / 60 // 计算小时
        val minute = expectedDuration % 60 // 计算分钟
        val hourString = if (hour > 1) "hours" else "hour"
        val minuteString = if (minute > 1) "minutes" else "minute"

        // 构建结果字符串
        result = when {
            hour > 0 && minute > 0 -> "$hour $hourString $minute $minuteString"
            hour > 0 -> "$hour $hourString"
            minute > 0 -> "$minute $minuteString"
            hour==0 && minute==0 ->"wait to start"
            else -> "Less than a minute"
        }

return result
}
fun emergencyDegreeColor(emergencyDegree:Int): Color
{
    var color=Color.Gray
    when(emergencyDegree){
        0->{
             color=Color.Gray
        }
        1->{
            //最重要
            color=Color(0xDDF70505)
        }
        2->{
            color=Color(0xFFE7E751)
        }
        3->{
            color=Color(0xFF3E83AA)
        }
        4->{
            color=Color(0xFF98E96D)
        }

        else->{
             color = Color.Gray
        }
    }
    return color
}