package android.bearcatsdev.mat.io19.scanner

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var mZXingScannerView: ZXingScannerView

    companion object {
        const val CAMERA_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mZXingScannerView = findViewById(R.id.zxing)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
        } else {
            mZXingScannerView.setResultHandler(this)
            mZXingScannerView.startCamera()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    mZXingScannerView.setResultHandler(this)
                    mZXingScannerView.startCamera()
                }
                return
            }
            else -> {
            }
        }
    }


    override fun onStop() {
        super.onStop()
        mZXingScannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(this, rawResult?.text, Toast.LENGTH_SHORT).show()
        mZXingScannerView.resumeCameraPreview(this)
    }
}
