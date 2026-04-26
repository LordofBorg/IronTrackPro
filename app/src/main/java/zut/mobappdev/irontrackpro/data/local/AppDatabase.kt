package zut.mobappdev.irontrackpro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import zut.mobappdev.irontrackpro.data.local.dao.WorkoutDao
import zut.mobappdev.irontrackpro.data.local.entity.WorkoutEntity

@Database(
    entities = [WorkoutEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val workoutDao: WorkoutDao
}