package com.nepenthx.zxtimer.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.nepenthx.zxtimer.R
import java.time.LocalDateTime
import java.time.LocalTime

@SuppressLint("ResourceAsColor")
@Composable
fun WheelPicker()
{
    WheelTimePicker(
        startTime = LocalTime.now(),
        minTime = LocalTime.of(
            0,0
        ),
        maxTime = LocalTime.of(
            23, 59
        ),
        //timeFormat = TimeFormat.AM_PM,
        size = DpSize(200.dp, 100.dp),
        rowCount = 5,
        textStyle = MaterialTheme.typography.titleSmall,
        textColor = Color(R.color.black),
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = true,
            shape = RoundedCornerShape(0.dp),
            color = Color(0xFFf1faee).copy(alpha = 0.2f),
            border = BorderStroke(2.dp, Color(0xFFf1faee))
        )
    ){  snappedTime -> }
}
