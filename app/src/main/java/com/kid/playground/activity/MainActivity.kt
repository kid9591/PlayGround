package com.kid.playground.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.kid.playground.R
import com.kid.playground.databinding.ActivityMainBinding
import net.sourceforge.tess4j.ITesseract
import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private val TAG: String = "chi.trinh"
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewmodel = this@MainActivity.viewmodel
            this.lifecycleOwner = this@MainActivity

            // When using Latin script library
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

            // Đọc ảnh từ assets
            val assetManager = assets
            val inputStream: InputStream = assetManager.open("package1.jpg")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            val image = InputImage.fromBitmap(bitmap, 0)

            recognizer.process(image)
                .addOnSuccessListener { result ->
                    // Task completed successfully
                    // ...
                    val resultText = result.text
                    Log.d(TAG, "resultText: $resultText")
                    for (block in result.textBlocks) {
                        val blockText = block.text
                        Log.d(TAG, "blockText: $blockText")
                        val blockCornerPoints = block.cornerPoints
                        val blockFrame = block.boundingBox
                        for (line in block.lines) {
                            val lineText = line.text
                            Log.d(TAG, "lineText: $lineText")
                            val lineCornerPoints = line.cornerPoints
                            val lineFrame = line.boundingBox
                            for (element in line.elements) {
                                val elementText = element.text
                                Log.d(TAG, "elementText: $elementText")
                                val elementCornerPoints = element.cornerPoints
                                val elementFrame = element.boundingBox
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                    // ...
                    Toast.makeText(this@MainActivity, "Fail to recognize text: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }






//            // Đọc ảnh từ assets
//            // Đọc ảnh từ assets
//            val assetManager = assets
//            val inputStream: InputStream = assetManager.open("package.jpg")
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            inputStream.close()
//
//            // Tạo file tạm thời
//            val tempFile = File.createTempFile("package", ".jpg")
//            val out = FileOutputStream(tempFile)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
//            out.close()
//
//            val instance: ITesseract = Tesseract() // JNA Interface Mapping
//
//            // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//            // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//            instance.setDatapath("tessdata") // path to tessdata directory
//
//
//            try {
//                val result: String = instance.doOCR(tempFile)
//                println(result)
//            } catch (e: TesseractException) {
//                System.err.println(e.message)
//            }
        }
    }
}