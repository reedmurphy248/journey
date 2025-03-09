package org.example.journey.app

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
//    val context = LocalContext.current
//    val contentResolver: ContentResolver = context.contentResolver
//    val galleryLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//            uri?.let {
//                onResult.invoke(SharedImage(uriToBitmap(context, uri)))
//            }
//        }
//    return remember {
//        GalleryManager(onLaunch = {
//            galleryLauncher.launch(
//                PickVisualMediaRequest(
//                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
//                )
//            )
//        })
//    }
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            onResult(SharedImage(uri))
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    // Include only one of the following calls to launch(), depending on the types
    // of media that you want to let the user choose from.

    // Launch the photo picker and let the user choose images and videos.
    return remember {
        GalleryManager(onLaunch = {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        })
    }
}

actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            // For older versions, you might use BitmapFactory, but it's less efficient
            // and more prone to OutOfMemoryErrors.  ImageDecoder is strongly preferred
            // if possible.  If you *must* support older versions, research
            // BitmapFactory.decodeStream and its options for scaling and memory management.
            // Example (less recommended):
            // val inputStream = context.contentResolver.openInputStream(uri)
            // BitmapFactory.decodeStream(inputStream)
            null // Indicate that we can't handle it on older versions
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}