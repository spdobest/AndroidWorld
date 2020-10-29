package spm.androidworld.all.imageToPdf.compressor.constraint

import android.graphics.Bitmap
import spm.androidworld.all.imageToPdf.compressor.compressFormat
import spm.androidworld.all.imageToPdf.compressor.loadBitmap
import spm.androidworld.all.imageToPdf.compressor.overWrite
import java.io.File

class FormatConstraint(private val format: Bitmap.CompressFormat) : Constraint {

    override fun isSatisfied(imageFile: File): Boolean {
        return format == imageFile.compressFormat()
    }

    override fun satisfy(imageFile: File): File {
        return overWrite(imageFile, loadBitmap(imageFile), format)
    }
}

fun Compression.format(format: Bitmap.CompressFormat) {
    constraint(FormatConstraint(format))
}