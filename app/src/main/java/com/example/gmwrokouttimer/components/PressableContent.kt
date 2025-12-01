package com.example.gmwrokouttimer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PressableContent(
    onClick: () -> Unit,
    content: @Composable (isPressed: Boolean) -> Unit
) {
    // 1. Remember the interaction source
    val interactionSource = remember { MutableInteractionSource() }

    // 2. Collect the pressed state
    val isPressed by interactionSource.collectIsPressedAsState()

    // 3. Apply the clickable modifier with the interaction source
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Set indication to null to disable default ripple
                onClick = onClick
            )
    ) {
        // 4. Pass the pressed state to the content
        content(isPressed)
    }
}