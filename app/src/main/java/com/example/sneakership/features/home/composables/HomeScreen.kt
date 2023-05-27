package com.example.sneakership.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakership.R
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(), onSneakerClick: (sneaker: Sneaker) -> Unit
) {
    Scaffold(
        topBar = { HomeScreenTopBar() }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            HomeScreenContent(homeViewModel) {
                onSneakerClick(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
            Spacer(modifier = Modifier.width(24.dp))
            Image(painter = painterResource(id = R.drawable.ic_cart), contentDescription = null)
            Spacer(modifier = Modifier.width(24.dp))
        }
    )
}

@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel, onSneakerClick: (sneaker: Sneaker) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(homeViewModel.homeUiState.sneakers) {
            Sneaker(sneaker = it) {
                onSneakerClick(it)
            }
        }
    }
}