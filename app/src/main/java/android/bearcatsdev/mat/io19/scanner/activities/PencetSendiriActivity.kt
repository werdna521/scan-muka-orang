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

import android.app.Dialog
import android.bearcatsdev.mat.io19.scanner.R
import android.bearcatsdev.mat.io19.scanner.pojo.ParticipantResponse
import android.bearcatsdev.mat.io19.scanner.pojo.Qr
import android.bearcatsdev.mat.io19.scanner.viewmodel.ParticipantViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class PencetSendiriActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_SCAN_ID = "sekenseken"

        fun createInstance(context: Context, scanId: Int): Intent {
            val intent = Intent(context, PencetSendiriActivity::class.java)
            intent.putExtra(EXTRA_SCAN_ID, scanId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencet_sendiri)


        val scanId = intent.getIntExtra(EXTRA_SCAN_ID, 0)
        val ticketNumberEditText = findViewById<TextInputEditText>(R.id.ticket_number)
        val buttonHebat = findViewById<MaterialButton>(R.id.buttonhebat)
        val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            ParticipantViewModel::class.java)

        buttonHebat.text = if (scanId == 1) "Check In" else "Claim Food"
        buttonHebat.setOnClickListener {
            if (scanId == 1) {
                viewModel.doCheckIn(Qr(ticketNumberEditText?.text.toString() ?: ""))
                    .observe(this, Observer { participantResponse ->
                        when (participantResponse.status) {
                            200 -> showDetailsDialog(participantResponse, 1)
                            else -> {
                                Toast.makeText(
                                    this,
                                    participantResponse.response.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            } else if (scanId == 2) {
                viewModel.doClaimFood(Qr(ticketNumberEditText?.text.toString() ?: ""))
                    .observe(this, Observer { participantResponse ->
                        when (participantResponse.status) {
                            200 -> showDetailsDialog(participantResponse, 2)
                            else -> {
                                Toast.makeText(
                                    this,
                                    participantResponse.response.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }
        }
    }

    private fun showDetailsDialog(participantResponse: ParticipantResponse, scanId: Int) {
        val detailsDialog = Dialog(this, R.style.Scanner_DialogFullscreen)
        detailsDialog.setContentView(R.layout.dialog_details)

        val titleTextView = detailsDialog.findViewById<TextView>(R.id.title)
        val nameTextView = detailsDialog.findViewById<TextView>(R.id.name)
        val nimTextView = detailsDialog.findViewById<TextView>(R.id.nim)
        val emailTextView = detailsDialog.findViewById<TextView>(R.id.email)
        val dietaryTextView = detailsDialog.findViewById<TextView>(R.id.dietary)
        val checkedInTextView = detailsDialog.findViewById<TextView>(R.id.checked_in)
        val takenFoodTextView = detailsDialog.findViewById<TextView>(R.id.taken_food)
        val okButton = detailsDialog.findViewById<MaterialButton>(R.id.ok_button)

        titleTextView.text = participantResponse.response.message
        nameTextView.text = participantResponse.response.name
        nimTextView.text = participantResponse.response.nim
        emailTextView.text = participantResponse.response.email
        dietaryTextView.text = participantResponse.response.dietary
        checkedInTextView.text = if (participantResponse.response.checkedIn == 1) "Yes" else "No"
        takenFoodTextView.text = if (participantResponse.response.takenFood == 1) "Yes" else "No"

        if (scanId == 2 && participantResponse.response.message == "Food has been claimed once") {
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_cross, 0, 0)
        } else {
            titleTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_checked, 0, 0)
        }

        okButton.setOnClickListener {
            detailsDialog.dismiss()
        }

        detailsDialog.show()
    }
}