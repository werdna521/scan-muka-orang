package android.bearcatsdev.mat.io19.scanner.pojo

import com.google.gson.annotations.SerializedName

data class ParticipantResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("response") val response: Participant
)