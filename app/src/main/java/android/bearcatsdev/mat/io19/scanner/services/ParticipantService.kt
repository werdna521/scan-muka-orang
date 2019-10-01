// Copyright 2019 Andrew Cen
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package android.bearcatsdev.mat.io19.scanner.services

import android.bearcatsdev.mat.io19.scanner.pojo.ParticipantResponse
import android.bearcatsdev.mat.io19.scanner.pojo.Qr
import android.bearcatsdev.mat.io19.scanner.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
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

    @POST("/api/v1/claimfood")
    suspend fun claimFood(@Body qr: Qr): ParticipantResponse
}