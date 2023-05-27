package com.example.sneakership.features.sneakerDetails.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakership.R
import com.example.sneakership.features.home.models.Sneaker
import com.example.sneakership.features.sneakerDetails.models.SneakerDetailUiState
import com.example.sneakership.features.sneakerDetails.viewmodel.SneakerDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerDetailScreen(
    sneakerDetailViewModel: SneakerDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCartClick: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            SneakerDetailTopBar(
                sneakerDetailViewModel.sneakerDetailUiState.sneaker.name,
                onBackClick,
                onCartClick
            )
        }) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            ScreenDetailContent(sneakerDetailViewModel.sneakerDetailUiState) {
                if (sneakerDetailViewModel.canAddToCart()) {
                    sneakerDetailViewModel.addToCart()
                    scope.launch {
                        snackBarHostState.showSnackbar("Added to cart Successfully!")
                    }
                } else {
                    scope.launch {
                        snackBarHostState.showSnackbar("Please select Size and Color to add to cart")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerDetailTopBar(title: String, onBackClick: () -> Unit, onCartClick: () -> Unit) {
    TopAppBar(title = {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
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
fun ScreenDetailContent(sneakerDetailUiState: SneakerDetailUiState, onAddToCartClick: () -> Unit) {
    val sneaker = sneakerDetailUiState.sneaker
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        SneakerImage(modifier = Modifier.weight(1f, fill = true), sneakerDetailUiState)
        ImageIndicators(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            sneakerDetailUiState
        )
        Spacer(modifier = Modifier.height(24.dp))
        SneakerInfo(sneaker, sneakerDetailUiState, onAddToCartClick)
    }
}

@Composable
fun SneakerImage(modifier: Modifier, uiState: SneakerDetailUiState) {
    Box(modifier = modifier) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        Surface(
            modifier = Modifier
                .size(screenWidth - 120.dp)
                .align(Alignment.Center),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondary
        ) {}
        Surface(
            modifier = Modifier
                .size(screenWidth - 180.dp)
                .align(Alignment.Center),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary
        ) {}
        Image(
            painter = painterResource(id = R.drawable.sneaker),
            contentDescription = null,
            modifier = Modifier
                .padding(80.dp)
                .align(Alignment.Center)
        )
        IconButton(
            onClick = {
                if (uiState.currentImagePos.value != 1) {
                    --uiState.currentImagePos.value
                }
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null,
                modifier = Modifier
                    .size(60.dp),
                tint = colorResource(id = if (uiState.currentImagePos.value == 1) R.color.light_grey else R.color.black)
            )
        }

        IconButton(
            onClick = {
                if (uiState.currentImagePos.value != uiState.imageCnt) {
                    ++uiState.currentImagePos.value
                }
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null,
                modifier = Modifier
                    .size(60.dp),
                tint = colorResource(id = if (uiState.currentImagePos.value == uiState.imageCnt) R.color.light_grey else R.color.black)
            )
        }
    }
}

@Composable
fun ImageIndicators(modifier: Modifier, uiState: SneakerDetailUiState) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        for (i in 1..uiState.imageCnt) {
            Spacer(
                modifier = Modifier
                    .size(width = 48.dp, height = 4.dp)
                    .background(
                        colorResource(
                            id = if (uiState.currentImagePos.value == i) R.color.black else R.color.light_grey
                        ),
                        RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}

@Composable
private fun SneakerInfo(
    sneaker: Sneaker,
    sneakerDetailUiState: SneakerDetailUiState,
    onAddToCartClick: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            SneakerTitle(sneaker)
            Spacer(modifier = Modifier.height(4.dp))
            SneakerBrand(sneaker)
            Spacer(modifier = Modifier.height(36.dp))
            SneakerSize(sneaker, sneakerDetailUiState.selectedSize.value) {
                sneakerDetailUiState.selectedSize.value = it
            }
            Spacer(modifier = Modifier.height(36.dp))
            SneakerColor(sneaker, sneakerDetailUiState.selectedColor.value) {
                sneakerDetailUiState.selectedColor.value = it
            }
            Spacer(modifier = Modifier.height(36.dp))
            SneakerPrice(sneaker = sneaker, onAddToCartClick)
        }
    }
}

@Composable
private fun SneakerTitle(sneaker: Sneaker) {
    Text(
        text = sneaker.name,
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun SneakerBrand(sneaker: Sneaker) {
    Text(
        text = sneaker.brand,
        color = colorResource(id = R.color.grey),
        fontSize = 16.sp
    )
}

@Composable
fun SneakerSize(sneaker: Sneaker, selectedSize: Int, onSizeSelected: (size: Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Size (uk) :",
            color = colorResource(id = R.color.grey),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sneaker.sizes) { size ->
                Text(
                    text = size.toString(),
                    color = if (selectedSize == size) Color.White else MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .background(
                            color = if (selectedSize == size) MaterialTheme.colorScheme.primary else Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .clickable {
                            onSizeSelected(size)
                        }
                )
            }
        }
    }
}

@Composable
fun SneakerColor(
    sneaker: Sneaker,
    selectedColor: String,
    onColorSelected: (color: String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Colour :    ",
            color = colorResource(id = R.color.grey),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sneaker.colors) { color ->
                Box(
                    modifier = Modifier
                        .size(50.dp, 30.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#80$color")),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            onColorSelected(color)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedColor == color) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SneakerPrice(sneaker: Sneaker, onAddToCartClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Price : ",
            color = colorResource(id = R.color.grey),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Rs.${sneaker.price}",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(36.dp))
        AddToCart(onAddToCartClick)
    }
}

@Composable
private fun AddToCart(onAddToCartClick: () -> Unit) {
    Text(
        text = "Add to Cart",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable {
                onAddToCartClick()
            }
    )
}