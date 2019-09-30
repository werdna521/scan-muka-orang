package android.bearcatsdev.mat.io19.scanner.services

import android.bearcatsdev.mat.io19.scanner.pojo.ParticipantResponse
import android.bearcatsdev.mat.io19.scanner.pojo.Qr
import android.bearcatsdev.mat.io19.scanner.utils.Constants
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ParticipantService {

    companion object {
        fun getService(): ParticipantService {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ParticipantService::class.java)
        }
    }

    @POST("/api/v1/checkin")
    suspend fun checkIn(@Body qr: Qr): ParticipantResponse
}