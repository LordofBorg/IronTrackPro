package zut.mobappdev.irontrackpro.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import zut.mobappdev.irontrackpro.data.local.dao.WorkoutDao
import zut.mobappdev.irontrackpro.domain.model.Workout
import zut.mobappdev.irontrackpro.domain.repository.WorkoutRepository
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val dao: WorkoutDao
) : WorkoutRepository {

    override fun getWorkoutHistory(): Flow<List<Workout>> {
        // TODO: Зробити мапінг з WorkoutEntity у Workout
        return emptyFlow()
    }

    override suspend fun saveWorkout(workout: Workout) {
        // TODO: Зробити мапінг з Workout у WorkoutEntity та зберегти через dao
    }
}