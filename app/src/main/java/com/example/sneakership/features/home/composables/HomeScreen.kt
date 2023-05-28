package com.example.sneakership.features.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakership.R
import com.example.sneakership.features.home.models.HomeToolbarState
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.home.models.SortOptions
import com.example.sneakership.features.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onSneakerClick: (sneaker: Sneaker) -> Unit,
    onCartClick: () -> Unit
) {
    Scaffold(
        topBar = { HomeScreenTopBar(homeViewModel, onCartClick) }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (homeViewModel.homeUiState.sneakers.isNotEmpty()) {
                HomeScreenContent(homeViewModel) {
                    onSneakerClick(it)
                }
            } else {
                EmptySneakers()
            }
        }
    }
}

@Composable
fun EmptySneakers() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "No Sneakers, Try Again!!!",
            color = colorResource(id = R.color.grey),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(homeViewModel: HomeViewModel, onCartClick: () -> Unit) {
    TopAppBar(
        title = {
            if (homeViewModel.homeUiState.toolbarState.value == HomeToolbarState.TITLE) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            } else {
                SearchBar(homeViewModel)
            }
        },
        actions = {
            IconButton(onClick = {
                homeViewModel.homeUiState.toolbarState.value = HomeToolbarState.SEARCH
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = onCartClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBar(homeViewModel: HomeViewModel) {
    val focusManager = LocalFocusManager.current
    TextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            containerColor = colorResource(id = R.color.background)
        ),
        value = homeViewModel.homeUiState.searchText.value,
        onValueChange = {
            homeViewModel.homeUiState.searchText.value = it
        },
        trailingIcon = {
            IconButton(onClick = {
                homeViewModel.onSearchCleared()
            }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        singleLine = true,
        placeholder = {
            Text(text = "search sneaker", color = MaterialTheme.colorScheme.primary)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
            homeViewModel.search()
        })
    )
}

@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel, onSneakerClick: (sneaker: Sneaker) -> Unit) {
    Column {
        Sort(homeViewModel)
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

}

@Composable
private fun Sort(homeViewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Text(
            text = homeViewModel.homeUiState.sortOption.value.value,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterStart),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            var expanded: Boolean by remember { mutableStateOf(false) }
            Text(
                text = "Sort By",
                color = colorResource(id = R.color.grey),
                modifier = Modifier.clickable { expanded = true },
                fontWeight = FontWeight.Bold
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                SortOptions.values().forEach {
                    DropdownMenuItem(text = { Text(text = it.value) },
                        onClick = {
                            homeViewModel.homeUiState.sortOption.value = it
                            expanded = false
                            homeViewModel.sort()
                        }
                    )
                }
            }
        }
    }
}