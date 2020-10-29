package spm.androidworld.all.imageToPdf

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object ImageUtil {
    fun changeTintColor(
        ctx: Context?,
        icon: Drawable,
        color: Int
    ): Drawable {
        icon.setColorFilter(ContextCompat.getColor(ctx!!, color), PorterDuff.Mode.SRC_ATOP)
        return icon
    }

    fun getFileExtension(context: Context, uri: Uri?): String? {
        val contentResolver = context.contentResolver
        var mimeType =
            MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri!!))
        if (mimeType.isNullOrEmpty()) {
            mimeType = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
                val cr: ContentResolver = context.contentResolver
                cr.getType(uri)
            } else {
                val fileExtension = MimeTypeMap.getFileExtensionFromUrl(
                    uri.toString()
                )
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase()
                )
            }
        }
        return "jpg"
    }

    @Throws(IOException::class)
    fun createTempImageFile(context: Context): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "WorkSafe_$timeStamp", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    fun addPicToGallery(context: Context, path: String) {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(path)
            mediaScanIntent.data = Uri.fromFile(f)
            context.sendBroadcast(mediaScanIntent)
        }
    }

    @Throws(IOException::class)
    fun rotateImageIfRequired(
        bitmap: Bitmap,
        storageFile: File,
        save: Boolean = false
    ): Bitmap? {
        val ei = ExifInterface(storageFile.toString())
        val orientation: Int =
            ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
        val rotatedBitmap = rotateBitmap(bitmap, orientation)
        if (save && bitmap != rotatedBitmap) {
            storageFile.delete()
            val fileOutputStream = FileOutputStream(storageFile)
            rotatedBitmap?.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream)
        }
        return rotatedBitmap
    }

    private fun rotateBitmap(bitmap: Bitmap, orientation: Int): Bitmap? {
        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_NORMAL -> return bitmap
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.setScale(
                -1f,
                1f
            )
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(
                180f
            )
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> {
                matrix.setRotate(180f)
                matrix.postScale(-1f, 1f)
            }
            ExifInterface.ORIENTATION_TRANSPOSE -> {
                matrix.setRotate(90f)
                matrix.postScale(-1f, 1f)
            }
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.setRotate(90f)
            ExifInterface.ORIENTATION_TRANSVERSE -> {
                matrix.setRotate(-90f)
                matrix.postScale(-1f, 1f)
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.setRotate(-90f)
            else -> return bitmap
        }
        return try {
            val bmRotated = Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                matrix,
                true
            )
            bitmap.recycle()
            bmRotated
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            null
        }
    }
}