package zut.mobappdev.irontrackpro.domain.repository

import kotlinx.coroutines.flow.Flow
import zut.mobappdev.irontrackpro.domain.model.Workout

interface WorkoutRepository {
    // We get the history as a flow of pure domain models
    fun getWorkoutHistory(): Flow<List<Workout>>

    // We save the training
    suspend fun saveWorkout(workout: Workout)
}