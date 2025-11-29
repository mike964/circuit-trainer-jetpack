package com.example.gmwrokouttimer.components.shared

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.gmwrokouttimer.R

@Composable
fun GifImage( ){
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://i0.wp.com/post.healthline.com/wp-content/uploads/2020/06/400x400_How_to_do_Zac_Efrons_Baywatch_Workout_Floor_Dumbbell_Chest_Press.gif")
            .build(),
        imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()
    )

    Image(
        painter = painter,
        contentDescription = stringResource(R.string.description)
    )
}

//                        AsyncImage(
////                            model = "https://free-images.com/sm/2692/sunset_countryside_nature_932243.jpg",
//                            model = "https://i0.wp.com/post.healthline.com/wp-content/uploads/2020/06/400x400_How_to_do_Zac_Efrons_Baywatch_Workout_Floor_Dumbbell_Chest_Press.gif",
////                           model = "https://cdn.shopify.com/s/files/1/1950/1891/files/1_4fb9ab72-7e4a-446e-90c7-1e7cc063fc21.gif?v=1580799063",
//                            contentDescription = "Image",
//                            modifier = Modifier.width(200.dp).height(200.dp)
//                        )