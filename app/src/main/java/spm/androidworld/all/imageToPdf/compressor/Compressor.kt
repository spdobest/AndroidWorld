package spm.androidworld.all.imageToPdf.compressor

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import spm.androidworld.all.imageToPdf.compressor.constraint.Compression
import spm.androidworld.all.imageToPdf.compressor.constraint.default
import java.io.File
import kotlin.coroutines.CoroutineContext

object Compressor {
    suspend fun compress(
        context: Context,
        imageFile: File,
        coroutineContext: CoroutineContext = Dispatchers.IO,
        compressionPatch: Compression.() -> Unit = { default() }
    ) = withContext(coroutineContext) {
        val compression = Compression().apply(compressionPatch)
        var result = copyToCache(context, imageFile)
        compression.constraints.forEach { constraint ->
            while (constraint.isSatisfied(result).not()) {
                result = constraint.satisfy(result)
            }
        }
        return@withContext result
    }
}