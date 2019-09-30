package android.bearcatsdev.mat.io19.scanner.pojo

import com.google.gson.annotations.SerializedName

data class Participant(
    @SerializedName("name") val name: String,
    @SerializedName("nim") val nim: String,
    @SerializedName("email") val email: String,
    @SerializedName("dietary") val dietary: String,
    @SerializedName("checked_in") val checkedIn: Int,
    @SerializedName("taken_food") val takenFood: Int,
    @SerializedName("message") val message: String
)