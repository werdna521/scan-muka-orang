package android.bearcatsdev.mat.io19.scanner.pojo

import com.squareup.moshi.Json

data class Participant(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "nim") val nim: String
)