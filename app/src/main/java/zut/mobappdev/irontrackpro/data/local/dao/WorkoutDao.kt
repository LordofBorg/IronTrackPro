package zut.mobappdev.irontrackpro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import zut.mobappdev.irontrackpro.data.local.entity.WorkoutEntity

@Dao
interface WorkoutDao {

    // Getting a list of workouts in Flow form (for reactive UI)
    @Query("SELECT * FROM workout_history ORDER BY dateEpoch DESC")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    // Saving a new workout (or updating an existing one)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    // Clearing old history (optional)
    @Query("DELETE FROM workout_history")
    suspend fun clearHistory()
}