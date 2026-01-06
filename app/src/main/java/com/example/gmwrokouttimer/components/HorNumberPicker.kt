package com.example.gmwrokouttimer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalResources
import com.example.gmwrokouttimer.R

// # Horizontal Number picker
// https://github.com/marcauberer/compose-number-picker



@Preview(name = "Horizontal number picker")
@Composable
fun HorizontalNumberPicker(
//    modifier: Modifier = Modifier,
    height: Dp = 45.dp,
    min: Int = 0,
    max: Int = 10,
    default: Int = min,
    onValueChange: (Int) -> Unit = {}
) {
    val number = remember { mutableIntStateOf(default) }

    Row {
        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_left,
            enabled = number.intValue > min,
            onClick = {
                if (number.intValue > min) number.intValue--
                onValueChange(number.intValue)
            }
        )
        Text(
            text = number.intValue.toString(),
            fontSize = (height.value / 2).sp,
            modifier = Modifier.padding(10.dp)
                .height(IntrinsicSize.Max)
                .align(CenterVertically)
        )
        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_right,
            enabled = number.intValue < max,
            onClick = {
                if (number.intValue < max) number.intValue++
                onValueChange(number.intValue)
            }
        )
    }
}

@Composable
fun PickerButton(
    size: Dp = 45.dp,
    @DrawableRes drawable: Int = R.drawable.ic_arrow_left,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val contentDescription = LocalResources.current.getResourceName(drawable)
//    val backgroundColor = if (enabled) MaterialTheme.colorScheme.secondary else Color.LightGray
    val backgroundColor =  MaterialTheme.colorScheme.secondary

    Image(
        painter = painterResource(id = drawable),
        contentDescription = contentDescription,
        modifier = Modifier.padding(8.dp).background(backgroundColor, CircleShape)
            .clip(CircleShape)
            .width(size).height(size)
            .clickable (
                enabled = enabled,
                onClick = { onClick() }
            )
    )
}