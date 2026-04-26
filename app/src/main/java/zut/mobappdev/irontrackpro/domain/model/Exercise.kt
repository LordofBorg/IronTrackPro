package zut.mobappdev.irontrackpro.domain.model

data class Exercise(
    val id: String, // ID from ExerciseDB
    val name: String, // Example: "Bench Press"
    val targetMuscle: String,
    val equipment: String,
    val gifUrl: String // Link for animation on ExerciseDB
)