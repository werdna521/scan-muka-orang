package android.bearcatsdev.mat.io19.scanner.viewmodel

import android.bearcatsdev.mat.io19.scanner.pojo.Participant
import android.bearcatsdev.mat.io19.scanner.repository.ParticipantRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class ParticipantViewModel: ViewModel() {
    private val repository = ParticipantRepository()

    fun doCheckIn(qrCode: String): LiveData<Participant> {
        return liveData(Dispatchers.IO) {
            try {
                val signedInParticipant = repository.checkIn(qrCode)
                emit(signedInParticipant)
            } catch (e: Exception) {
            }
        }
    }
}