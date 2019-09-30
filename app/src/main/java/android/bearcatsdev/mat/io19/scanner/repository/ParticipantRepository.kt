package android.bearcatsdev.mat.io19.scanner.repository

import android.bearcatsdev.mat.io19.scanner.services.ParticipantService

class ParticipantRepository {
    private val participantService = ParticipantService.getService()

    suspend fun signUp(qrCode: String) = participantService.signUp(qrCode)
}