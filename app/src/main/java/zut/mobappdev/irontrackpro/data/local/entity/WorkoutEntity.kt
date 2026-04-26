package zut.mobappdev.irontrackpro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_history")
data class WorkoutEntity(
    @PrimaryKey
    val id: String, // Unique ID of Training
    val title: String, // Example "Chest & Triceps"
    val dateEpoch: Long, // Unix timestamp
    val durationMinutes: Int,
    val supplements: String? // Optional supplement
)