package com.weatherapp.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.weatherapp.mainviewmodel.MainViewModel
import com.weatherapp.model.City


@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Rounded.FavoriteBorder,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = modifier.weight(1f)) {
            Text(modifier = Modifier,
                text = city.name,
                fontSize = 24.sp)
            Text(modifier = Modifier,
                text = city.weather,
                fontSize = 16.sp)
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun ListPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context
) {
    val context = LocalContext.current
    val cityList = viewModel.cities

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(cityList) { city ->
            CityItem(
                city = city,
                onClose = {
                    showToast("Cidade ${city.name} removida")
                    viewModel.remove(city)
                },
                onClick = {
                    showToast("Cidade ${city.name} clicada")
                    // TO DO: Implementar lógica para lidar com o clique na cidade
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewListPage() {
    // Mock ViewModel and Context for Preview
    val mockViewModel = MainViewModel()
    val mockContext = LocalContext.current

    ListPage(
        viewModel = mockViewModel,
        context = mockContext
    )
}