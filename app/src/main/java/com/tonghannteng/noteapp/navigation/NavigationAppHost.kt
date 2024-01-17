package com.tonghannteng.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tonghannteng.noteapp.presentation.detail.NoteItemDetail
import com.tonghannteng.noteapp.presentation.home.NoteItemScreen

/**
 * @author: Tonghann Teng
 * @since: 1/14/24
 */
@Composable
fun NavigationAppHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = Route.HOME
    ) {
        composable(Route.HOME) { backStackEntry ->
            NoteItemScreen(
                navController = navController,
                onItemClicked = { noteId ->
                    navController.navigate("${Route.DETAIL}/${noteId}")
                })
        }

        composable(
            route = "${Route.DETAIL}/{${Argument.NOTE_ID}}",
            arguments = listOf(navArgument(Argument.NOTE_ID) { type = NavType.IntType })
        ) {
            NoteItemDetail()
        }
    }

}

object Route {
    const val HOME = "home"
    const val DETAIL = "detail"
}

object Argument {
    const val NOTE_ID = "noteId"
}
