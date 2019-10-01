// Copyright 2019 Andrew Cen
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package android.bearcatsdev.mat.io19.scanner.activities

import android.Manifest
import android.bearcatsdev.mat.io19.scanner.R
import android.bearcatsdev.mat.io19.scanner.pojo.Qr
import android.bearcatsdev.mat.io19.scanner.viewmodel.ParticipantViewModel
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var mZXingScannerView: ZXingScannerView
    private lateinit var mParticipantViewModel: ParticipantViewModel

    companion object {
        const val CAMERA_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        mZXingScannerView = findViewById(R.id.zxing)
        mParticipantViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(ParticipantViewModel::class.java)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
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

    override fun onResume() {
        super.onResume()
        mZXingScannerView.startCamera()
    }

    override fun handleResult(rawResult: Result?) {
        mParticipantViewModel.doCheckIn(Qr(rawResult?.text ?: "")).observe(this, Observer { participantResponse ->
            when (participantResponse.status) {
                200 -> showDetailsDialog()
                else -> Toast.makeText(this, participantResponse.response.message, Toast.LENGTH_SHORT).show()
            }
        })
        mZXingScannerView.resumeCameraPreview(this)
    }

    private fun showDetailsDialog() {
        // TODO start new full screen dialog to show participant info
    }
}
