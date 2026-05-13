package zut.mobappdev.irontrackpro.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zut.mobappdev.irontrackpro.domain.model.Workout
import javax.inject.Inject

data class DashboardUiState(
    val isLoading: Boolean = true,
    val workouts: List<Workout> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(DashboardUiState())
    val state = _state.asStateFlow()

    init {
        loadWorkouts()
    }

    fun loadWorkouts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                delay(1000)
                val mockWorkouts = listOf(
                    Workout(
                        id = "1",
                        title = "Chest & Triceps",
                        dateEpoch = System.currentTimeMillis() - 86_400_000,
                        durationMinutes = 75,
                        supplements = "Creatine 5g"
                    ),
                    Workout(
                        id = "2",
                        title = "Back & Biceps",
                        dateEpoch = System.currentTimeMillis() - 172_800_000,
                        durationMinutes = 65,
                        supplements = "Creatine 5g"
                    ),
                    Workout(
                        id = "3",
                        title = "Legs & Shoulders",
                        dateEpoch = System.currentTimeMillis() - 345_600_000,
                        durationMinutes = 80,
                        supplements = null
                    ),
                    Workout(
                        id = "4",
                        title = "Push Day",
                        dateEpoch = System.currentTimeMillis() - 518_400_000,
                        durationMinutes = 60,
                        supplements = "BCAA 10g"
                    ),
                    Workout(
                        id = "5",
                        title = "Pull Day",
                        dateEpoch = System.currentTimeMillis() - 604_800_000,
                        durationMinutes = 70,
                        supplements = "Creatine 5g, Protein 30g"
                    )
                )
                _state.update { it.copy(isLoading = false, workouts = mockWorkouts) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }
}