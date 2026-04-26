package zut.mobappdev.irontrackpro.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExerciseDto(
    @SerializedName("exerciseId") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("targetMuscles") val targetMuscles: List<String>,
    @SerializedName("equipments") val equipments: List<String>,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("videoUrl") val videoUrl: String? // Can be null
)