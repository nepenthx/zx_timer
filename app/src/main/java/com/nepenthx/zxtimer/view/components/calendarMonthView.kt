package com.nepenthx.zxtimer.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.nepenthx.zxtimer.R
import com.nepenthx.zxtimer.ViewModel.UiViewModel
import com.nepenthx.zxtimer.data.selectTaskData
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

/**
 * data class TaskData(
 *     val taskName: String,
 *     val createDate: String,
 *     val cycle: Int,
 *     val expectedTime: Int,
 *     val advanceCompletionBoolean: Int,
 *     val advanceCompletionTime: Int,
 *     val remarks: String,
 *     val emergencyDegree: Int
 * )
 */
@Composable
fun MonthView(viewModel: UiViewModel) {

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val daysOfWeek= daysOfWeek(firstDayOfWeek= DayOfWeek.MONDAY)
    val firstDayOfWeek = daysOfWeek.first()// Available from the library
    val context= LocalContext.current
    val zxDate = LocalDate.of(1997, 12, 16)
    val today= remember { LocalDate.now() }
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(day, today, isSelected = viewModel.selectDate.value == day.date) { day ->
                        viewModel.selectDate.value = if (viewModel.selectDate.value == day.date) zxDate else day.date
                        //viewModel.taskData.value= selectTaskData(context,viewModel.selectDate.value.toString())
                        viewModel.fetchTasks(context)
                }
            },
            monthHeader = {
                month ->
                    Column {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            CalendarDate(month)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        DaysOfWeekTitle(daysOfWeek = daysOfWeek) // Use the title as month header
                    }
            }
        )



}
@Composable
fun Day(day: CalendarDay,today:LocalDate, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = if (isSelected) Color(0xFFAE83FC) else Color.Transparent)
            .border(
                width = if (day.date == today) 1.dp else 0.dp,
                color = if (day.date == today) Color.Gray else Color.Transparent,
                shape = CircleShape
            )

            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onClick(day) }
            ),
        contentAlignment = Alignment.Center,
    )
    {
        val textColor = when (day.position) {
            // Color.Unspecified will use the default text color from the current theme
            DayPosition.MonthDate -> if (isSelected) Color.White else Color.Unspecified
            DayPosition.InDate, DayPosition.OutDate -> colorResource(R.color.inactive_text_color)
        }

        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 14.sp,
        )
    }
}
