package com.br.labquest.quadradomgico.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val levels = listOf(
        Pair(
            arrayOf(
                intArrayOf(8, 0, 0),
                intArrayOf(1, 5, 0),
                intArrayOf(6, 0, 2)
            ),
            15
        ),
        Pair(
            arrayOf(
                intArrayOf(16, 3, 2, 13),
                intArrayOf(5, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
            ),
            34
        ),
    )

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(
                onNavigateToFreeMode = {
                    navController.navigate("free_mode")
                },
                onNavigateToStoryMode = {
                    navController.navigate("story_mode")
                })
        }
        composable("free_mode") {
            FreeMode(navController = navController)
        }
        composable("story_mode") {
            StoryMode(navController = navController, levels)
        }
        composable(
            route = "level/{level}",
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            val (square, constantSum) = levels.getOrNull(level - 1) ?: Pair(arrayOf(), 0)
            LevelSquare(navController = navController, square = square, level = level, constantSum = constantSum)
        }
    }
}