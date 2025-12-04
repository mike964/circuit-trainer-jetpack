package com.example.gmwrokouttimer.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Philipp Lack
@Composable
fun CircularProgressBar(
    percentage: Float, number: Int?,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp, color: Color = Color(0xFF4ABE1A),
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(animDuration, animDelay)
    )
//    var  curPercentage by remember { mutableFloatStateOf(percentage) }

    LaunchedEffect(true) {
        animationPlayed = true
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 3f)
    ) {
        Canvas(Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage.value,
                useCenter = false,
//                size = TODO(),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            )
        }
        number?.let {
            Text(
//               text = (curPercentage.value  * number).toInt().toString(),
                text = number.toString(),
                color = Color(0xFF0A620F),
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }

}