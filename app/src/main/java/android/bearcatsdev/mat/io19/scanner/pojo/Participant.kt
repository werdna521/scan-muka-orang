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

package android.bearcatsdev.mat.io19.scanner.pojo

import com.google.gson.annotations.SerializedName

data class Participant(
    @SerializedName("name") val name: String,
    @SerializedName("nim") val nim: String,
    @SerializedName("email") val email: String,
    @SerializedName("dietary") val dietary: String,
    @SerializedName("checked_in") val checkedIn: Int,
    @SerializedName("taken_food") val takenFood: Int,
    @SerializedName("message") val message: String,
    @SerializedName("checked_in_time") val checkedInTime: String,
    @SerializedName("taken_food_time") val takenFoodTime: String
)