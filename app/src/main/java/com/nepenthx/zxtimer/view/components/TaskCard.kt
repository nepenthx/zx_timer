package com.nepenthx.zxtimer.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskCard(
    taskName: String,
    color: Color,
    expectedDuration: String,
    elapsedDuration: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color, CircleShape)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                // Task name
                Text(
                    text = taskName,
                    color = Color(0XFFDC9A9A),
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Color indicator


                Spacer(modifier = Modifier.height(8.dp))

                // Expected duration
                Text(
                    text = "预计时长: $expectedDuration",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Elapsed duration
                Text(
                    text = "已进行: $elapsedDuration",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun TaskCardExample() {
    TaskCard(
        taskName = "写作业",
        color = Color.Magenta,
        expectedDuration = "2 hours",
        elapsedDuration = "1 hour 30 minutes"
    )
}

