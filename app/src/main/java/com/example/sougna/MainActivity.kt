package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.sougna.model.Category
import com.example.sougna.model.Product
import com.example.sougna.ui.theme.SougnaTheme
import com.example.sougna.viewmodel.CategoryViewModel
import com.example.sougna.viewmodel.ProductViewModel

/**
 * Main activity for the app
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SougnaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val productViewModel = viewModel<ProductViewModel>()
                    val categoryViewModel = viewModel<CategoryViewModel>()
                    MyScreen(
                        productViewModel,
                        categoryViewModel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Composable function that displays a list of categories
 * @param categories The list of categories to display
 * @param modifier Modifier for the layout
 */
@Composable
fun CategoryList(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display category icon
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Display category name
                Text(text = category.name)
            }
        }
    }
}

/**
 * Composable function that displays a list of products in card format
 * @param products The list of products to display
 * @param modifier Modifier for the layout
 */
@Composable
fun ProductList(
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(products) { product ->
            // Card container for each product
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Product image
                    AsyncImage(
                        model = product.thumbnailUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Product name
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Product description
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Product price
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

/**
 * Main screen composable that displays both categories and products
 * @param productViewModel ViewModel for product data
 * @param categoryViewModel ViewModel for category data
 * @param modifier Modifier for the layout
 */
@Composable
fun MyScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier
) {
    // Collect state from ViewModels
    val productState by productViewModel.uiState.collectAsState()
    val categoryState by categoryViewModel.categoryState.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        // Display category list
        CategoryList(
            categories = categoryState.categories,
            modifier = Modifier.fillMaxWidth()
        )

        // Handle product state
        when {
            productState.isLoading -> {
                // Show loading indicator
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            productState.error != null -> {
                // Show error message
                Text(
                    text = "Error: ${productState.error}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                // Display product list
                ProductList(
                    products = productState.products,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
