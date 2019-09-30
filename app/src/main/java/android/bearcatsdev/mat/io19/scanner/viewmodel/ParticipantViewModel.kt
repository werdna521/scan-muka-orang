package android.bearcatsdev.mat.io19.scanner.viewmodel

import android.bearcatsdev.mat.io19.scanner.pojo.ParticipantResponse
import android.bearcatsdev.mat.io19.scanner.pojo.Qr
import android.bearcatsdev.mat.io19.scanner.repository.ParticipantRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class ParticipantViewModel: ViewModel() {
    private val repository = ParticipantRepository()

    fun doCheckIn(qrCode: Qr): LiveData<ParticipantResponse> {
        return liveData(Dispatchers.IO) {
            try {
                val checkedInParticipant = repository.checkIn(qrCode)
                emit(checkedInParticipant)
            } catch (e: Exception) {}
        }
    }
}