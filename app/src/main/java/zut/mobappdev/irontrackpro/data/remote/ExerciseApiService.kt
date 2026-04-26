package zut.mobappdev.irontrackpro.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import zut.mobappdev.irontrackpro.data.remote.dto.ExerciseDto

interface ExerciseApiService {

    // Example, getting list of Exercices
    @GET("api/v1/exercises")
    suspend fun getAllExercises(
        @Query("limit") limit: Int = 20
    ): Response<List<ExerciseDto>>

    // Searching by name
    @GET("api/v1/exercises/name")
    suspend fun searchExercises(
        @Query("name") name: String
    ): Response<List<ExerciseDto>>
}