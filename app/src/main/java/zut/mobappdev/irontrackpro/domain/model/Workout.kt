package zut.mobappdev.irontrackpro.domain.model

data class Workout(
    val id: String, // Unique ID for Firebase/Room
    val title: String, // Example: "Back & Biceps"
    val dateEpoch: Long, // Unix timestamp date
    val durationMinutes: Int,
    val supplements: String? // Example: "Creatine 5g"
)