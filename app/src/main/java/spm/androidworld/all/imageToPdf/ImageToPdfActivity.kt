package spm.androidworld.all.imageToPdf

import android.Manifest
import android.content.ClipData
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.itextpdf.text.Chunk
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_image_to_pdf.*
import spm.androidworld.all.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStream
import java.text.DecimalFormat
import kotlin.math.pow

class ImageToPdfActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_PICK_MULTIPLE_IMAGE = 1234
    private val REQUEST_CAPTURE_IMAGE = 1235
    private var selectedImageList = ArrayList<String>()
    lateinit var appFolder: File
    private var outputFileUri: Uri? = null
    private var outputImagePath: String = ""
    private var convertedPdfFilePath: String = ""
    private var actualImage: File? = null
    private var compressedImage: File? = null
    private val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val REQUEST_PERMISSION_ALL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_to_pdf)

        askForMultiplePermissions()

        buttonCamera.setOnClickListener(this)
        buttonGallery.setOnClickListener(this)
        buttonConvertToPdf.setOnClickListener(this)
        buttonOpenPdf.setOnClickListener(this)

        appFolder = File(Environment.getExternalStorageDirectory().path, "MyPdfEditor")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonConvertToPdf -> {
                convertImageToPdf()
            }
            R.id.buttonCamera -> {
                captureImage()
            }
            R.id.buttonGallery -> {
                pickImagesFromGallery()
            }
            R.id.buttonOpenPdf -> {

            }
        }
    }

    private fun hasPermission(permission: String?): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission!!
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askForMultiplePermissions() {
        val cameraPermissin = Manifest.permission.CAMERA
        val readExternalStoragePermission =
            Manifest.permission.READ_EXTERNAL_STORAGE
        val writeExternalStoragePermission =
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        val permissionList = ArrayList<String>()


        if (!hasPermission(cameraPermissin)) {
            permissionList.add(cameraPermissin)
        }
        if (!hasPermission(readExternalStoragePermission)) {
            permissionList.add(readExternalStoragePermission)
        }
        if (!hasPermission(writeExternalStoragePermission)) {
            permissionList.add(writeExternalStoragePermission)
        }

        if (permissionList.isNotEmpty()) {
            val permissions: Array<String> =
                permissionList.toArray<String>(PERMISSIONS)
            requestPermissions(
                permissions,
                REQUEST_PERMISSION_ALL
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_ALL) {
            if (grantResults.isEmpty()) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val cameraAccepted =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                val storageAccepted =
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
                val writeStorageAccepted =
                    grantResults[2] == PackageManager.PERMISSION_GRANTED

                if (!cameraAccepted) {
                    Toast.makeText(
                        this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!storageAccepted) {
                    Toast.makeText(
                        this,
                        "Location Permission Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!writeStorageAccepted) {
                    Toast.makeText(
                        this,
                        "Write Permission Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun captureImage() {

        val fileFolder = File(
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + "MyPdfEditor"
        )
        if (!fileFolder.exists()) {
            fileFolder.mkdir()
        }


        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val file = File(
                    fileFolder.absolutePath + File.separator +
                            "000Image${System.currentTimeMillis()}.png"
                )
                outputImagePath = file.absolutePath
                actualImage = file
                outputFileUri = FileProvider.getUriForFile(
                    this,
                    "spm.androidworld.all.provider",  //(use your app signature + ".provider" )
                    file
                )
                // Continue only if the File was successfully created

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
                startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE)
            }
        }
    }

    private fun pickImagesFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            REQUEST_PICK_MULTIPLE_IMAGE
        )
    }

    private fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (kotlin.math.log10(size.toDouble()) / kotlin.math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }

    private fun setCompressedImage() {
        compressedImage?.let {
            compressedImageView.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
            compressedSizeTextView.text =
                String.format("Size : %s", getReadableFileSize(it.length()))
            Toast.makeText(this, "Compressed image save in " + it.path, Toast.LENGTH_LONG).show()
            Log.d("Compressor", "Compressed image save in " + it.path)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAPTURE_IMAGE) {

            var imageStream: InputStream?

            val fileFolder = File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + "MyPdfEditor"
            )

            if (!fileFolder.exists()) {
                fileFolder.mkdir()
            }

            val file = File(
                fileFolder.absolutePath + File.separator +
                        "222Image${System.currentTimeMillis()}.png"
            )

            try {
                outputFileUri?.let {
                    imageStream = contentResolver?.openInputStream(it)
                    var bitMap: Bitmap = BitmapFactory.decodeStream(imageStream)
                    val fileOutPutStram =
                        FileOutputStream(file)
                    bitMap = ImageUtil.rotateImageIfRequired(bitMap, file, true)!!
                    bitMap.compress(Bitmap.CompressFormat.PNG, 100, fileOutPutStram)

                    fileOutPutStram.close()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            file.delete()

            val newFilePath = ImageManager().compressImage(this, outputImagePath)
//            compressedSizeTextView.text = String.format("Size : %s", getReadableFileSize(File(newFilePath).length()))
            Log.i("PATH111", newFilePath)
            selectedImageList.add(newFilePath)
        }

        if (resultCode == RESULT_OK && null != data) {
            selectedImageList.clear()

            if (requestCode == REQUEST_PICK_MULTIPLE_IMAGE) {
                val filePathColumn =
                    arrayOf(MediaStore.Images.Media.DATA)

                var path: String? = ""

                val mClipData: ClipData? = data.clipData
                if (mClipData != null) {
                    mClipData.let { clipData ->
                        val count = clipData.itemCount
                        for (i in 0 until count) {
                            val item = mClipData.getItemAt(i)
                            val uri = item.uri
//                            Log.i("PATH", "${ImageManager.getImageSize(uri)}")
//                            val newFilePath = ImageManager().compressImage(this, uri.encodedPath)
//                            selectedImageList.add(newFilePath)
                            path = ImageFilePath.getPath(this, uri)
                            Log.i("PATH", "$path")
                            path?.let { selectedImageList.add(it) }
                        }
                    }
                } else {
                    val imagePath = data.data
                    val selectedImageUri = data.data
                    Log.i("ImagePath2", "PATH ${getRealFilePathFromUri(this, selectedImageUri)}")
                    selectedImageUri?.let { selectedImageList.add(selectedImageUri.lastPathSegment!!) }
                }
            }
        }
    }

    private fun getRealPathFromURI(contentURI: Uri): String? {
        val cursor = contentResolver.query(contentURI, null, null, null, null)
        return if (cursor == null) { // Source is Dropbox or other similar local file path
            contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }

    private fun convertImageToPdf() {
        showProgressAndHideText(true)
        val document = Document()

        convertedPdfFilePath =
            "${appFolder.absolutePath}/ConvertedPdf${System.currentTimeMillis()}.pdf"

        PdfWriter.getInstance(
            document,
            FileOutputStream(convertedPdfFilePath)
        )

        document.open()
        document.add(Chunk(""))
        selectedImageList.forEach {
            val image: Image =
                Image.getInstance(it /*"$directoryPath/example.jpg"*/) // Change image's name and extension.
            val scaler: Float = (document.pageSize.width - document.leftMargin()
                    - document.rightMargin() - 0) / image.width * 100 // 0 means you have no indentation. If you have any, change it.

            image.scalePercent(scaler)
            image.alignment = Image.ALIGN_CENTER or Image.ALIGN_TOP
            document.add(image)
        }
        showProgressAndHideText(false)
        document.close()
    }

    private fun getRealFilePathFromUri(context: Context, uri: Uri?): String? {
        if (null == uri) return null
        val scheme = uri.scheme
        var data: String? = null
        if (scheme == null) data = uri.path else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.path
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            val cursor: Cursor = context.getContentResolver()
                .query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    val index: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor.getString(index)
                    }
                }
                cursor.close()
            }
        }
        return data
    }

    private fun showProgressAndHideText(isProgress: Boolean) {
        if (isProgress) {
            buttonOpenPdf.visibility = View.GONE
            progressBarLoading.visibility = View.VISIBLE
        } else {
            buttonOpenPdf.visibility = View.VISIBLE
            progressBarLoading.visibility = View.GONE
        }
    }
}