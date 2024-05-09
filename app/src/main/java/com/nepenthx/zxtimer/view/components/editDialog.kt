package com.nepenthx.zxtimer.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nepenthx.zxtimer.R

@Composable
fun EditDialog(onDismissRequest: () -> Unit)
{
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            TextField()
        }
    }
}

@Composable
fun TextField() {
    val textValue = remember { mutableStateOf("") }

    val primaryColor = colorResource(id = R.color.teal_200)

    OutlinedTextField(
        label = { Text(text = "点击输入任务名称～") },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = primaryColor,
            focusedBorderColor = primaryColor,
            focusedLabelColor = primaryColor,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType
        = KeyboardType.Email),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
    )
}