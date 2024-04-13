package com.gentalha.moviechallenge.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gentalha.moviechallenge.R
import com.gentalha.moviechallenge.ui.components.FavoriteMovieItem
import com.gentalha.moviechallenge.ui.components.FeedbackState
import com.gentalha.moviechallenge.ui.components.PrimaryButton
import com.gentalha.moviechallenge.ui.model.Movie
import com.gentalha.moviechallenge.ui.state.FavoriteUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    onFavoriteOnClick: (Movie) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            FavoriteUiState.Loading -> {
                FeedbackState(
                    modifier = Modifier.fillMaxSize(),
                    isLoading = true
                )
            }

            FavoriteUiState.Empty -> {
                FeedbackState(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.wishlist_icon),
                            contentDescription = "feedback icon",
                            modifier = Modifier.size(130.dp)
                        )
                    },
                    title = R.string.favorite,
                    message = R.string.favorite
                ) {

                }
            }

            is FavoriteUiState.Failure -> {
                FeedbackState(
                    title = R.string.favorite,
                    message = R.string.generic_error_message
                ) {
                    PrimaryButton(
                        label = "Tentar novamente",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                    }
                }
            }

            is FavoriteUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(uiState.movies) { movie ->
                        FavoriteMovieItem(
                            modifier = Modifier
                                .animateItemPlacement()
                                .padding(8.dp),
                            movie = movie,
                            movieOnclick = {},
                            favoriteOnClick = onFavoriteOnClick::invoke
                        )
                    }
                }
            }
        }
    }
}
