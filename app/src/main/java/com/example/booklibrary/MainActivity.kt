package com.example.booklibrary

import com.example.booklibrary.presentation.viewmodels.BooksViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.booklibrary.navigation.Navigation
import com.example.booklibrary.ui.theme.BookLibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val booksViewModel: BooksViewModel = hiltViewModel()
            BookLibraryTheme {
                Surface {
                    Navigation(booksViewModel)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    BookLibraryTheme {
//        val navController = rememberNavController()
//        Surface {
////            DetailScreen(
////                navController = navController,
////                booksViewModel = booksViewModel,
////            )
////            BookListScreen(
////                booksViewModel = booksViewModel,
////                navController = navController,
////            )
////            AddBookScreen(
////                booksViewModel: BooksViewModel = viewModel(),
////                navController = navController
////            )
//        }
//    }
//}
