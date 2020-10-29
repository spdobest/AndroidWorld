package spm.androidworld.all.imageToPdf

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import java.io.*


object FileUtil {
    const val TAG_CLASS_NAME = "FileUtil"
    const val PROFILE_IMAGE_DIR = "MyImages"
    private const val EOF = -1
    private const val DEFAULT_BUFFER_SIZE = 1024 * 4

    fun createProfileImageStorageDirectory(context: Context, fileName: String): File {
        val mediaStorageDir: File? = context.getExternalFilesDir(PROFILE_IMAGE_DIR)
        if (!mediaStorageDir?.exists()!!) {
            mediaStorageDir.mkdirs()
        }
        return File(
            mediaStorageDir.path + File.separator +
                    fileName + ".png"
        )
    }

    private fun isUserProfileImageAvailableInStoragePath(
        context: Context,
        userName: String
    ): Boolean {
        if (userName.isNullOrEmpty()) return false
        val mediaStorageDir: File? = context.getExternalFilesDir(PROFILE_IMAGE_DIR)
        if (!mediaStorageDir?.exists()!!) {
            mediaStorageDir.mkdirs()
        }
        for (f: File in mediaStorageDir.listFiles()) {
            if (f.isFile && f.name.equals("$userName.png", true)) {
                return true
            }
        }
        return false
    }

    fun getUserProfileImagePath(context: Context): File? {
        val mediaStorageDir: File? = context.getExternalFilesDir(PROFILE_IMAGE_DIR)
        if (!mediaStorageDir?.exists()!!) {
            mediaStorageDir.mkdirs()
        }
        return mediaStorageDir
    }

    fun getBitMapForUserProfile(context: Context, userName: String): Bitmap? {
        if (isUserProfileImageAvailableInStoragePath(context, userName)) {
            val profileFile = File(
                getUserProfileImagePath(context)?.path + File.separator +
                        userName + ".png"
            )
            var bitMap = BitmapFactory.decodeFile(profileFile.path)
            bitMap = ImageUtil.rotateImageIfRequired(bitMap, profileFile, true)!!
            return bitMap
        }
        return null
    }

    fun createAndStoreImagesDirectory(
        context: Context,
        dirName: String,
        fileName: String,
        bitmap: Bitmap
    ) {
        val mediaStorageDir: File? = context.getExternalFilesDir(dirName)
        if (!mediaStorageDir?.exists()!!) {
            mediaStorageDir.mkdirs()
        }
        var file = File(mediaStorageDir.path + File.separator + fileName + ".png")
        try {
            var fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun getImagePath(context: Context, dirName: String): File? {
        val mediaStorageDir: File? = context.getExternalFilesDir(dirName)
        if (!mediaStorageDir?.exists()!!) {
            mediaStorageDir.mkdirs()
        }
        return mediaStorageDir
    }

    private fun isImageAvailableInStoragePath(
        context: Context,
        dirName: String,
        imageName: String
    ): Boolean {
        if (imageName.isNullOrEmpty()) return false
        val mediaStorageDir: File? = context.getExternalFilesDir(dirName)
        if (!mediaStorageDir?.exists()!!) {
            mediaStorageDir.mkdirs()
        }
        for (f: File in mediaStorageDir.listFiles()) {
            if (f.isFile && f.name.equals("$imageName.png", true)) {
                return true
            }
        }
        return false
    }

    fun getBitMapForImage(context: Context, dirName: String, imageName: String): Bitmap? {
        if (isImageAvailableInStoragePath(context, dirName, imageName)) {
            val hseImageFile = File(
                getImagePath(context, dirName)?.path + File.separator +
                        imageName + ".png"
            )
            return BitmapFactory.decodeFile(hseImageFile.path)
        }
        return null
    }

    fun clearAppInternalImageData(context: Context, dirName: String) {
        val mediaStorageDir: File? = context.getExternalFilesDir(dirName)
        if (mediaStorageDir?.exists()!!) {
            for (file: File in mediaStorageDir.listFiles()) {
                file.delete()
            }
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    private fun isExternalStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
    }

    fun readFromFileExternalStorage(file: File): String {
        var ret = ""


        //Is external storage available?
        if (!isExternalStorageReadable()) {
            Log.e(
                TAG_CLASS_NAME,
                "readFromFileExternalStorage:External Storage Not Available for read"
            )
            return ret
        }
        try {
            val fileInputStream: FileInputStream
            fileInputStream = FileInputStream(file)
            val bytesRead = ByteArray(file.length().toInt())
            fileInputStream.read(bytesRead)
            fileInputStream.close()
            ret = String(bytesRead)
        } catch (e: FileNotFoundException) {
            Log.e(
                TAG_CLASS_NAME, "readFromFileExternalStorage-FileNotFoundException" + e.message
            )
        } catch (e: IOException) {
            Log.e(
                TAG_CLASS_NAME, "readFromFileExternalStorage-IOException" + e.message
            )
        }
        return ret
    }


    fun writeCrashReportToAppStorage(
        filename: String?,
        data: String
    ): Boolean {
        var successful = false

        //validate filename is not null
        if (filename.isNullOrEmpty()) {
            Log.e(
                TAG_CLASS_NAME, "writeCrashReportToAppStorage:Filename is null"
            )
            return successful
        }

        //Is external storage available?
        if (!isExternalStorageWritable()) {

            Log.e(
                TAG_CLASS_NAME,
                "writeCrashReportToAppStorage:External Storage Not Available for writing"
            )
            return successful
        }
        try {
            val sdcard = Environment.getExternalStorageDirectory()
            val folder =
                File(sdcard.toString(), "WorkSafeCrashReport")
            if (!folder.exists()) {
                folder.mkdirs()
            }
            val file = File(folder.toString(), filename)

            var dataAppendToFile = true
            var finalContent: String
            val totalDataSize = file.length() + data.length
            val noOfTimes = 1 // no. of times to trim down the log file.
            if (totalDataSize >= 1048576) {
                finalContent =
                    readFromFileExternalStorage(file).substring(noOfTimes * data.length)
                finalContent = """
                    $finalContent
                    $data
                    """.trimIndent()
                dataAppendToFile = false
            } else {
                finalContent = data
            }
            //if dataAppendToFile is true - indicates appending data to existing file
            val fos = FileOutputStream(file, dataAppendToFile)
            val OutDataWriter = OutputStreamWriter(fos)
            OutDataWriter.write(finalContent)
            OutDataWriter.close()
            fos.flush()
            fos.close()
            successful = true
        } catch (e: FileNotFoundException) {
            Log.e(
                TAG_CLASS_NAME, "writeCrashReportToAppStorage-FileNotFoundException" + e.message
            )
        } catch (e: IOException) {
            Log.e(
                TAG_CLASS_NAME, "writeCrashReportToAppStorage-IOException" + e.message
            )
        }
        return successful
    }

    fun getImageScalingPublicDir(): File {
        return File(
            Environment.getExternalStorageDirectory()
                .toString() + "/" + "ImageScale"
        )
    }

    @Throws(IOException::class)
    fun from(context: Context, uri: Uri?): File? {
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName: String? = getFileName(context, uri)
        val splitName: Array<String>? = splitFileName(fileName)
        var tempFile = File.createTempFile(splitName?.get(0), splitName?.get(1))
        tempFile = rename(tempFile, fileName)
        tempFile.deleteOnExit()
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(tempFile)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        if (inputStream != null) {
            copy(inputStream, out)
            inputStream.close()
        }
        out?.close()
        return tempFile
    }

    private fun splitFileName(fileName: String?): Array<String>? {
        var name = fileName
        var extension: String? = ""
        val i = fileName?.lastIndexOf(".")
        if (i != -1) {
            name = i?.let { fileName?.substring(0, it) }
            extension = i?.let { fileName?.substring(it) }
        }
        return arrayOf(name!!, extension!!)
    }

    private fun getFileName(context: Context, uri: Uri?): String? {
        var result: String? = null
        if (uri?.scheme == "content") {
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
        }
        if (result == null) {
            result = uri?.path
            val cut = result?.lastIndexOf(File.separator)
            if (cut != -1) {
                result = cut?.plus(1)?.let { result?.substring(it) }
            }
        }
        return result ?: ""
    }

    private fun rename(file: File, newName: String?): File? {
        val newFile = File(file.parent, newName)
        if (newFile != file) {
            if (newFile.exists() && newFile.delete()) {
                Log.d("FileUtil", "Delete old $newName file")
            }
            if (file.renameTo(newFile)) {
                Log.d("FileUtil", "Rename file to $newName")
            }
        }
        return newFile
    }

    @Throws(IOException::class)
    private fun copy(input: InputStream, output: OutputStream?): Long {
        var count: Long = 0
        var n: Int
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        while (EOF != input.read(buffer).also { n = it }) {
            output?.write(buffer, 0, n)
            count += n.toLong()
        }
        return count
    }

}