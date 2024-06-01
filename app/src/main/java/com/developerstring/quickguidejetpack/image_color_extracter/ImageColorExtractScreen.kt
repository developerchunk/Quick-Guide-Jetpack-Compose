package com.developerstring.quickguidejetpack.image_color_extracter

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.developerstring.quickguidejetpack.image_color_extracter.PaletteGenerator.extractColorsFromBitmap
import com.developerstring.quickguidejetpack.image_color_extracter.PaletteGenerator.convertImageUrlToBitmap

// Require INTERNET permission in the manifest file so that you can load the image from url.

@Composable
fun ImageColorExtractScreen() {

    val context = LocalContext.current

    // setting the image url
    val imageUrl = "https://images.pexels.com/photos/3848158/pexels-photo-3848158.jpeg"
    // here we are storing the extracted Map into the variable
    var colors by remember {
        mutableStateOf<Map<String, String>>(mapOf())
    }

    LaunchedEffect(Unit) {
        convertImageUrlToBitmap(imageUrl = imageUrl, context =  context)?.let { imageBitmap ->
            colors = extractColorsFromBitmap(imageBitmap)
        }
    }

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var lightVibrant by remember { mutableStateOf("#000000") }
    var domainSwatch by remember { mutableStateOf("#000000") }
    var mutedSwatch by remember { mutableStateOf("#000000") }
    var lightMutedSwatch by remember { mutableStateOf("#000000") }
    var darkMutedSwatch by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    // now we are extracting the colors from the Map
    LaunchedEffect(colors) {
        try {
            vibrant = colors["vibrant"]!!
            darkVibrant = colors["darkVibrant"]!!
            lightVibrant = colors["lightVibrant"]!!
            domainSwatch = colors["domainSwatch"]!!
            mutedSwatch = colors["mutedSwatch"]!!
            lightMutedSwatch = colors["lightMuted"]!!
            darkMutedSwatch = colors["darkMuted"]!!
            onDarkVibrant = colors["onDarkVibrant"]!!
        } catch (_: Exception) {}
    }

    val brushColor: List<Color> = listOf(Color(parseColor(vibrant)).copy(alpha = 0.5f), Color.Transparent)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = imageUrl,
            contentDescription = "image_url",
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(parseColor(darkMutedSwatch)))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = brushColor,
                            startY = 30f,
                            endY = 230f
                        )
                    )
            )

            Text(
                modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp),
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                color = Color(
                    parseColor(onDarkVibrant)
                )
            )

        }

    }

}