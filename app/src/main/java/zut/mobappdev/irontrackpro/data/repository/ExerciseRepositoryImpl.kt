package zut.mobappdev.irontrackpro.data.repository

import zut.mobappdev.irontrackpro.data.remote.ExerciseApiService
import zut.mobappdev.irontrackpro.domain.model.Exercise
import zut.mobappdev.irontrackpro.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val apiService: ExerciseApiService
) : ExerciseRepository {

    override suspend fun getExercises(): Result<List<Exercise>> {
        // TODO: Викликати apiService і перетворити ExerciseDto у Exercise
        return Result.success(emptyList())
    }
}