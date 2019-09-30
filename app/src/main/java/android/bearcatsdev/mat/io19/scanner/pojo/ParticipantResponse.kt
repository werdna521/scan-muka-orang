package android.bearcatsdev.mat.io19.scanner.pojo

import com.squareup.moshi.Json

data class ParticipantResponse(
    @field:Json(name = "status") val status: Int,
    @field:Json(name = "response") val response: Participant
)