package com.example.booklibrary.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booklibrary.domain.entities.BookInfoEntity
import com.example.booklibrary.navigation.Destination

@Composable
fun BookCard(bookInfo: BookInfoEntity, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(
                    Destination.Details.createRoute(bookInfo.id)
                ){
                    launchSingleTop = true
                }
            }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Name of book: ${bookInfo.bookEntity.nameOfBook}",
                modifier = Modifier.padding(
                    horizontal = 30.dp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                "Author: ${bookInfo.bookEntity.author}",
                modifier = Modifier.padding(
                    horizontal = 30.dp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                "Year: ${bookInfo.bookEntity.year}"
            )

            Text(
                "Description: ${bookInfo.bookEntity.description}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
    }
}
