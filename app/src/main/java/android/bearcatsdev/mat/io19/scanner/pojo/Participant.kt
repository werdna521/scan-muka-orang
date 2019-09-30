package android.bearcatsdev.mat.io19.scanner.pojo

import com.squareup.moshi.Json

data class Participant(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "nim") val nim: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "dietary") val dietary: String,
    @field:Json(name = "checked_in") val checkedIn: Int,
    @field:Json(name = "taken_food") val takenFood: Int,
    @field:Json(name = "message") val message: String
)