package zut.mobappdev.irontrackpro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import zut.mobappdev.irontrackpro.ui.dictionary.ExerciseDictionaryScreen
import zut.mobappdev.irontrackpro.ui.home.DashboardScreen
import zut.mobappdev.irontrackpro.ui.login.LoginScreen
import zut.mobappdev.irontrackpro.ui.map.GymMapScreen
import zut.mobappdev.irontrackpro.ui.progress.ProgressChartsScreen
import zut.mobappdev.irontrackpro.ui.workout.WorkoutLoggerScreen

// Всі твої роути (шляхи) зібрані в одному місці, щоб легко міняти назви
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object WorkoutLogger : Screen("workout_logger")
    object ExerciseDictionary : Screen("exercise_dictionary")
    object GymMap : Screen("gym_map")
    object ProgressCharts : Screen("progress_charts")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // 1. Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Переходимо на Dashboard і видаляємо Login з історії, щоб не можна було повернутись кнопкою "Назад"
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // 2. Dashboard / Home
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onStartWorkoutClick = { navController.navigate(Screen.WorkoutLogger.route) },
                onNavigateToMap = { navController.navigate(Screen.GymMap.route) },
                onNavigateToProgress = { navController.navigate(Screen.ProgressCharts.route) }
            )
        }

        // 3. Workout Logger
        composable(Screen.WorkoutLogger.route) {
            WorkoutLoggerScreen(
                onAddExtraExerciseClick = { navController.navigate(Screen.ExerciseDictionary.route) },
                onFinishWorkoutClick = { navController.popBackStack() }, // Повертає на попередній екран (Dashboard)
                onBackClick = { navController.popBackStack() }
            )
        }

        // 4. Exercise Dictionary
        composable(Screen.ExerciseDictionary.route) {
            ExerciseDictionaryScreen(
                onExerciseSelected = { navController.popBackStack() }, // Повертає в Logger після вибору
                onBackClick = { navController.popBackStack() }
            )
        }

        // 5. Gym Map
        composable(Screen.GymMap.route) {
            GymMapScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                },
                onNavigateToProgress = { navController.navigate(Screen.ProgressCharts.route) }
            )
        }

        // 6. Progress Charts
        composable(Screen.ProgressCharts.route) {
            ProgressChartsScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                },
                onNavigateToMap = { navController.navigate(Screen.GymMap.route) }
            )
        }
    }
}