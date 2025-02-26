package africa.digitalhusters.composewhatsapp.ui.shared.components

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import africa.digitalhusters.composewhatsapp.ui.theme.LightGrey
import africa.digitalhusters.composewhatsapp.ui.theme.PaleGrey
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SegmentedCircularProfilePicture(
    profilePictureUrl: String,
    progress: Float, // 0f to 1f
    segments: Int,
    segmentColor: Color = Green, // Default to green
    backgroundColor: Color = LightGrey, // Default background color
    segmentSpacing: Float = 10f, // Spacing between segments
    segmentWidth: Float = 4f,
    animDuration: Int = 1000, // Animation duration in milliseconds
    modifier: Modifier = Modifier,
) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = animDuration)
    ).value

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(Dimensions.ProfilePictureSize) // Adjust size as needed
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val segmentAngle = 360f / segments
            val startAngle = -90f // Start from the top

            for (i in 0 until segments) {
                drawArc(
                    color = backgroundColor,
                    startAngle = startAngle + i * segmentAngle,
                    sweepAngle = if (segments == 1) {
                        segmentAngle
                    } else {
                        segmentAngle - segmentSpacing // Subtract spacing
                    },
                    useCenter = false,
                    style = Stroke(width = segmentWidth, cap = StrokeCap.Round)
                )

                // Foreground segment (green, if within progress)
                val segmentProgress = if (i < segments * animatedProgress) 1f else 0f
                val sweepAngle = if (segments == 1) {
                    segmentAngle * segmentProgress
                } else {
                    (segmentAngle - segmentSpacing) * segmentProgress
                }

                if (sweepAngle > 0) { // Only draw if there's progress
                    drawArc(
                        color = segmentColor,
                        startAngle = startAngle + i * segmentAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(width = segmentWidth, cap = StrokeCap.Round)
                    )
                }

            }
        }

        AsyncImage(
            model = profilePictureUrl,
            contentDescription = stringResource(R.string.profile_picture_content_description),
            placeholder = painterResource(R.drawable.icn_default_profile_picture),
            error = painterResource(R.drawable.icn_default_profile_picture),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(3.dp)
                .then(modifier)
                .clip(shape = CircleShape)
                .background(color = PaleGrey)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SegmentedCircularProgressBarPreview() {
    var progress by remember { mutableFloatStateOf(0.3f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SegmentedCircularProfilePicture(
            profilePictureUrl = "",
            progress = progress,
            segments = 10,
            segmentColor = Green,
            backgroundColor = LightGrey,
            segmentSpacing = 10f,
            segmentWidth = 10f,
            modifier = Modifier.size(265.dp)
        )

        // Example control to change the progress (for testing)
        androidx.compose.material3.Slider(
            value = progress,
            onValueChange = { progress = it },
            valueRange = 0f..1f
        )
    }
}