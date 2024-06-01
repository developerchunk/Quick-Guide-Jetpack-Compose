package com.developerstring.quickguidejetpack.image_color_extracter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

// for more information/reference please visit: // https://developer.android.com/develop/ui/views/graphics/palette-colors
object PaletteGenerator {

    // we are first converting the image url to Bitmap type
    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val imageResult = loader.execute(request = request)
        return if (imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    // here we are extracting the colors from the Bitmap that we got from the image url
    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
        /* This method sets the maximum number of colors in your palette.
        The default value is 16, and the optimal value depends on the source image.
        For landscapes, optimal values range from 8-16, while pictures with faces usually have values from 24-32.
        The Palette.Builder takes longer to generate palettes with more colors.*/
        val maximumColorCount = 10
        return mapOf(
            "vibrant" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().vibrantSwatch
            ),
            "darkVibrant" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().darkVibrantSwatch
            ),
            "onDarkVibrant" to parseBodyColor(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().darkVibrantSwatch?.bodyTextColor
            ),
            "lightVibrant" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().lightVibrantSwatch
            ),
            "domainSwatch" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().dominantSwatch
            ),
            "mutedSwatch" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().mutedSwatch
            ),
            "lightMuted" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().lightMutedSwatch
            ),
            "darkMuted" to parseColorSwatch(
                color = Palette.from(bitmap).maximumColorCount(maximumColorCount)
                    .generate().darkMutedSwatch
            ),
        )
    }

    private fun parseColorSwatch(color: Palette.Swatch?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        } else {
            "#000000"
        }
    }

    private fun parseBodyColor(color: Int?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        } else {
            "#FFFFFF"
        }
    }
}