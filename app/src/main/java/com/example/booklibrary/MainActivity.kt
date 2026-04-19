package com.example.booklibrary

import com.example.booklibrary.data.datasources.local.BooksLocalDataSourceImpl
import com.example.booklibrary.presentation.viewmodels.BooksViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.booklibrary.data.BookRepositoryImpl
import com.example.booklibrary.data.datasources.local.db.BooksDao
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSourceImpl
import com.example.booklibrary.domain.usecases.AddBookUseCase
import com.example.booklibrary.domain.usecases.DeleteBookUseCase
import com.example.booklibrary.domain.usecases.GetBooksUseCase
import com.example.booklibrary.domain.usecases.RedactionBookUseCase
import com.example.booklibrary.domain.usecases.RefreshBooksUseCase
import com.example.booklibrary.navigation.Navigation
import com.example.booklibrary.ui.theme.BookLibraryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = applicationContext as App

        val booksLocalDataSource = BooksLocalDataSourceImpl(
            booksDao = app.booksDataBase.dao
        )

        val booksRemoteDataSource = BooksRemoteDataSourceImpl()

        val bookRepository = BookRepositoryImpl(
            booksLocalDataSource = booksLocalDataSource,
            booksRemoteDataSource = booksRemoteDataSource
        )
        val addBookUseCase = AddBookUseCase(
            bookRepository = bookRepository
        )
        val deleteBookUseCase = DeleteBookUseCase(
            bookRepository = bookRepository
        )
        val getBooksUseCase = GetBooksUseCase(
            bookRepository = bookRepository
        )
        val redactionBookUseCase = RedactionBookUseCase(
            bookRepository = bookRepository
        )
        val refreshBooksUseCase = RefreshBooksUseCase(
            bookRepository = bookRepository
        )
        val booksViewModel = BooksViewModel(
            addBookUseCase = addBookUseCase,
            deleteBookUseCase = deleteBookUseCase,
            getBooksUseCase = getBooksUseCase,
            redactionBookUseCase = redactionBookUseCase,
            refreshBooksUseCase = refreshBooksUseCase
        )

        setContent {
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
