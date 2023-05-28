package com.example.sneakership.features.cart.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakership.R
import com.example.sneakership.features.cart.models.CartUiState
import com.example.sneakership.features.cart.viewmodel.CartViewModel
import com.example.sneakership.features.sneakerDetails.models.SneakerDetail

/**
 * This composable acts as root to cart screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cartViewModel: CartViewModel = hiltViewModel(), onBackClick: () -> Unit) {
    Scaffold(topBar = { CartScreenTopBar(onBackClick) }) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (cartViewModel.cartUiState.cartItems.isNotEmpty()) {
                CartScreenContent(cartViewModel.cartUiState) {
                    cartViewModel.deleteCartItem(it)
                }
            } else {
                EmptyCart()
            }
        }
    }
}

/**
 * This composable displays message when there are no sneakers in cart.
 */
@Composable
private fun EmptyCart() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Cart is Empty!!!\nAdd sneakers to cart.",
            color = colorResource(id = R.color.grey),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * This composable displays the top bar in cart screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartScreenTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Cart",
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
        }
    )
}

/**
 * This composable acts as root to cart screen content.
 */
@Composable
private fun CartScreenContent(
    cartUiState: CartUiState,
    onDeleteClick: (cartItem: SneakerDetail) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        CartItems(Modifier.weight(1f, fill = true), cartUiState.cartItems, onDeleteClick)
        Spacer(modifier = Modifier.height(4.dp))
        OrderDetails(cartUiState)
    }
}

/**
 * This composable displays the list of sneakers added to cart.
 */
@Composable
private fun CartItems(
    modifier: Modifier,
    cartItems: List<SneakerDetail>,
    onDeleteClick: (cartItem: SneakerDetail) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(cartItems) {
            CartItem(cartItem = it, onDeleteClick)
        }
    }
}

/**
 * This composable displays the sneaker added to cart.
 */
@Composable
private fun CartItem(cartItem: SneakerDetail, onDeleteClick: (cartItem: SneakerDetail) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CartItemImage()
                Spacer(modifier = Modifier.width(16.dp))
                CartItemInfo(cartItem)
            }
        }
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .offset(x = 8.dp, y = -(8.dp))
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .align(
                    Alignment.TopEnd
                )
                .clickable {
                    onDeleteClick(cartItem)
                }
        )
    }
}

/**
 * This composable displays the sneaker info like name, size etc.
 */
@Composable
private fun CartItemInfo(cartItem: SneakerDetail) {
    Column {
        Text(text = cartItem.name, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "size: ${cartItem.size}",
            fontSize = 14.sp,
            color = colorResource(id = R.color.grey)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Color:  ",
                fontSize = 14.sp,
                color = colorResource(id = R.color.grey)
            )
            Spacer(
                modifier = Modifier
                    .size(20.dp, 10.dp)
                    .background(
                        color = Color(android.graphics.Color.parseColor("#80${cartItem.color}")),
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "price: Rs.${cartItem.price}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/**
 * This composable displays the sneaker image.
 */
@Composable
private fun CartItemImage() {
    Box(contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondary
        ) {}
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary
        ) {}
        Image(
            painter = painterResource(id = R.drawable.sneaker),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
    }
}

/**
 * This composable displays the order details.
 */
@Composable
private fun OrderDetails(cartUiState: CartUiState) {
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
            OrderDetailsTitle()
            Spacer(modifier = Modifier.height(24.dp))
            SubTotal(cartUiState.subTotal)
            Spacer(modifier = Modifier.height(16.dp))
            Tax(cartUiState.tax)
            Spacer(modifier = Modifier.height(24.dp))
            TotalPrice(totalPrice = cartUiState.total)
        }
    }
}

/**
 * This composable displays the order details title.
 */
@Composable
private fun OrderDetailsTitle() {
    Text(
        text = "Order Details",
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

/**
 * This composable displays the subtotal of order.
 */
@Composable
private fun SubTotal(subTotal: Int) {
    Text(
        text = "Subtotal : Rs.$subTotal",
        color = colorResource(id = R.color.grey),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

/**
 * This composable displays the tax of order.
 */
@Composable
private fun Tax(tax: Int) {
    Text(
        text = "Tax and Charges : Rs.$tax",
        color = colorResource(id = R.color.grey),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

/**
 * This composable displays the total price of order.
 */
@Composable
private fun TotalPrice(totalPrice: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Total : ",
            color = colorResource(id = R.color.grey),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Rs.$totalPrice",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(36.dp))
        CheckOut()
    }
}

/**
 * This composable displays the checkout button.
 */
@Composable
private fun CheckOut() {
    Text(
        text = "CheckOut",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}