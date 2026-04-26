package zut.mobappdev.irontrackpro.domain.repository

import zut.mobappdev.irontrackpro.domain.model.Exercise

interface ExerciseRepository {
    // Get exercices from API
    suspend fun getExercises(): Result<List<Exercise>>
}