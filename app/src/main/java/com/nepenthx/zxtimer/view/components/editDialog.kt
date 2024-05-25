package com.nepenthx.zxtimer.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nepenthx.zxtimer.R

@Composable
fun EditDialog(showText:String,onDismissRequest: () -> Unit,
callBackValue:(String)->Unit)
{
    val str = remember {
        mutableStateOf(" ")
    }

    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White

            )

        ) {
            Box(modifier = Modifier.padding(start = 20.dp,end=20.dp),contentAlignment = Alignment.Center)
            {
                Column {
                    TextField(showText){inputValue->  str.value=inputValue }
                    Spacer(modifier = Modifier.height(6.dp))
                    ButtonUI(giveBackString = { callBackValue(str.value) })
                }
            }
        }
    }
}

@Composable
fun TextField(showText: String,callBackValue: (String) -> Unit) {
    val textValue = remember { mutableStateOf("") }

    val primaryColor = colorResource(id = R.color.teal_200)

    OutlinedTextField(
        label = { Text(text = showText) },
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
            callBackValue(it)
        },
    )
}

@Composable
fun ButtonUI(giveBackString: () -> Unit )
{
    Box(modifier = Modifier
        .padding(start = 30.dp,end=30.dp)
        .height(80.dp)
    )
    {
            Button(
                onClick = { giveBackString()},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9A82C4),
                    //containerColor = Color(0xFF298FA3),
                    contentColor = Color.White
                )
            ) {
                Text(text = "确认")
            }

        }
    }
