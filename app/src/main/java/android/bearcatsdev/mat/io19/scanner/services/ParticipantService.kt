package android.bearcatsdev.mat.io19.scanner.services

import android.bearcatsdev.mat.io19.scanner.pojo.Participant
import android.bearcatsdev.mat.io19.scanner.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST

interface ParticipantService {

    companion object {
        fun getService(): ParticipantService {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ParticipantService::class.java)
        }
    }

    @POST("api/v1/signup")
    suspend fun signUp(): Participant
}