package com.example.booklibrary.data.datasources.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.booklibrary.data.models.BookModel
import com.example.booklibrary.domain.entities.Statistic
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Upsert
    suspend fun upsertBook(bookModel: BookModel)

    @Query("DELETE FROM books_table WHERE id = :bookId")
    suspend fun deleteBookById(bookId: String)

    @Upsert
    suspend fun upsertBooks(books: List<BookModel>)

    @Query(
        """
        SELECT * FROM books_table 
        WHERE (:status IS NULL OR readStatus = :status)
        ORDER BY 
            CASE WHEN :sortType = 'RATING' THEN rating END DESC,
            CASE WHEN :sortType = 'YEAR' THEN year END DESC,
            CASE WHEN :sortType = 'AUTHOR' THEN author END DESC
        """
    )
    fun getAllBooks(status: String?, sortType: String?): Flow<List<BookModel>>

    @Query(
        """ 
        SELECT 
            COUNT(CASE WHEN readStatus = 'прочитана' THEN 1 END) AS countOfBooks,
            AVG(rating) AS  meanRating,
            AVG(julianday(endDate) - julianday(startDate)) AS  meanTimeOfReading   
        FROM books_table
    """
    )
    fun getStatistic(): Flow<Statistic>

    @Query(
        """
            SELECT EXISTS(
                SELECT 1
                FROM books_table
                WHERE readStatus = 'в процессе'
            )
        """
    )
    suspend fun isReadingBooks(): Boolean
}