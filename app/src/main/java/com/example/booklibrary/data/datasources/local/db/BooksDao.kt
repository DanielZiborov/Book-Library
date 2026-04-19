package com.example.booklibrary.data.datasources.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.booklibrary.data.models.BookModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Upsert
    suspend fun upsertBook(bookModel: BookModel)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): Flow<List<BookModel>>

    @Query("DELETE FROM books_table WHERE id = :bookId")
    suspend fun deleteBookById(bookId: String)

    @Upsert
    suspend fun upsertBooks(books: List<BookModel>)

    @Query("SELECT * FROM books_table ORDER BY rating DESC")
    fun sortByRating(): Flow<List<BookModel>>

    @Query("SELECT * FROM books_table ORDER BY year DESC")
    fun sortByYear(): Flow<List<BookModel>>

    @Query("SELECT * FROM books_table WHERE readStatus = :status")
    fun filterOfStatus(status: String): Flow<List<BookModel>>

    @Query("""
        SELECT * FROM books_table 
        WHERE (:status IS NULL OR readStatus = :status)
        ORDER BY 
            CASE WHEN :sortType = 'RATING' THEN rating END DESC,
            CASE WHEN :sortType = 'YEAR' THEN year END DESC
        """
    )
    fun getBooks(status: String?, sortType: String?): Flow<List<BookModel>>
}