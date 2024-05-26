package com.nepenthx.zxtimer.view.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nepenthx.zxtimer.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TaskCardPlus(
    number:Int,
    cycle:Int,//是否完成
    advanceCompletionBoolean:Int,//是否正计时
    taskName: String,
    color: Color,
    startTime:String,
    expectedDuration: String,
    elapsedDuration: String,
    remarks:String
) {

    var visible by rememberSaveable {
        mutableStateOf(false)
    }
    Column()
    {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .height(56.dp)
                .clickable { visible = !visible },
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp), contentAlignment = Alignment.Center)
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(24.dp)
                            .border(1.dp,color, CircleShape)

                            //.background(color, CircleShape)
                    )
                    {
                        Text(
                            text = number.toString(),
                            color=color
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = taskName,
                        color = Color(0xFF413A3A),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier=Modifier.height(24.dp)){
                        Row (verticalAlignment = Alignment.CenterVertically){

                            Text(
                                text = startTime,
                                color = Color(0xFF413A3A),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.height(24.dp),
                                )
                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Expand Icon",
                                tint = Color(0xFF413A3A),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                }
            }
        }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 2.dp)
                        .animateEnterExit(
                            // Slide in/out the inner box.
                            enter = slideInVertically(),
                            exit = slideOutVertically()
                        )

                ) {
                        Row (
                            modifier=Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                        Card(modifier = Modifier
                            .weight(2f)
                            .height(120.dp)) {
                            Column {
                                Column {
                                    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp)) {
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = if(advanceCompletionBoolean==1)"正计时" else " 倒计时",
                                            color = Color(0xEE3156D3),
                                            style = MaterialTheme.typography.bodyMedium

                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "预计:$expectedDuration",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "进行中:$elapsedDuration",
                                            style = MaterialTheme.typography.bodyMedium
                                        )

                                    }
                                    if (remarks != "") {
                                    var expanded by remember { mutableStateOf(false) }
                                    Surface(
                                        modifier = Modifier.padding(12.dp),
                                        color = if (expanded) Color(0xFFA5CCE0) else Color.Transparent,
                                        onClick = { expanded = !expanded }
                                    ) {

                                            AnimatedContent(
                                                targetState = expanded,
                                                transitionSpec = {
                                                    fadeIn(animationSpec = tween(150, 150)) with
                                                            fadeOut(animationSpec = tween(150)) using
                                                            SizeTransform { initialSize, targetSize ->
                                                                if (targetState) {
                                                                    keyframes {
                                                                        // Expand horizontally first.
                                                                        IntSize(
                                                                            targetSize.width,
                                                                            initialSize.height
                                                                        ) at 150
                                                                        durationMillis = 300
                                                                    }
                                                                } else {
                                                                    keyframes {
                                                                        // Shrink vertically first.
                                                                        IntSize(
                                                                            initialSize.width,
                                                                            targetSize.height
                                                                        ) at 150
                                                                        durationMillis = 300
                                                                    }
                                                                }
                                                            }
                                                }, label = ""
                                            ) { targetExpanded ->
                                                if (targetExpanded) {
                                                    RemarkDialog(remarks,onDismissRequest={expanded=false})

                                                } else {
                                                    UnExpandedRemarkView(remarks)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(120.dp)
                                    .padding(horizontal = 3.dp, vertical = 2.dp)
                            ){
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    // 第一行
                                    Row {
                                        Column (
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.start),
                                                contentDescription = "Favorite Icon",
                                                modifier = Modifier.size(32.dp)
                                            )
                                            Text("开始待办", fontSize = 9.sp)
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column (
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Favorite Icon",
                                                modifier = Modifier.size(32.dp)
                                            )
                                            Text("删除待办", fontSize = 9.sp)
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    // 第二行
                                    Row {
                                        Column (
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ){
                                            Icon(
                                                painter = painterResource(R.drawable.edit),
                                                contentDescription = "Favorite Icon",
                                                modifier = Modifier.size(32.dp)
                                            )
                                            Text("编辑待办", fontSize = 9.sp)
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column (
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ){
                                            Icon(
                                                painter = painterResource(R.drawable.complete),
                                                contentDescription = "Favorite Icon",
                                                modifier = Modifier.size(32.dp)
                                            )
                                            Text("完成待办", fontSize = 9.sp)
                                        }
                                    }


                                }
                            }

//稍微区分一下


                    }
                }
            }
                Spacer(modifier = Modifier.width(20.dp))
        }

    }

@Composable
fun UnExpandedRemarkView(remark:String)
{
    Box(modifier = Modifier
        .height(20.dp)
        .width(80.dp))
    {
        Text(
            text = remark,
            color = Color(0xFF3C6AAA),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ExpandedRemarkView(remark:String)
{
    Box(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth())
    {
        Text(
            text = remark,
            color = Color(0xFF3C6AAA),
            style = MaterialTheme.typography.bodyMedium
        )
    }


}

@Preview
@Composable
fun TaskCardPlusExample() {
    TaskCardPlus(
        1,
        cycle=1,
        advanceCompletionBoolean=1,
        taskName = "写作业",
        color = Color.Magenta,
        startTime = "10:00-12:00",
        expectedDuration = "2 hours",
        elapsedDuration = "1 hour 30 minutes",
        remarks="这段代码实现了一个可扩展的表面（Surface），点击表面时可以展开或收缩内容。让我逐步解释代码的功能：\n" +
                "\n" +
                "    var expanded by remember { mutableStateOf(false) }：\n" +
                "        定义了一个名为 expanded 的可变状态变量，初始值为 false，用于跟踪内容是否展开。\n" +
                "\n" +
                "    Surface(...)：\n" +
                "        创建了一个表面（Surface）组件，其颜色为当前 Material 主题中定义的主色（primary），并且定义了一个点击事件，点击时将 expanded 的值取反，以切换内容的展开状态。\n" +
                "\n" +
                "    AnimatedContent(...)：\n"
    )
}

