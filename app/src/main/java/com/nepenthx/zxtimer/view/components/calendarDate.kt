package com.nepenthx.zxtimer.view.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.core.CalendarMonth


@Composable
fun CalendarDate(calendarMonth: CalendarMonth)
{
    Text(
        textAlign = TextAlign.Center,
        text = calendarMonth.yearMonth.toString(),
        fontSize = 18.sp,
    )
}