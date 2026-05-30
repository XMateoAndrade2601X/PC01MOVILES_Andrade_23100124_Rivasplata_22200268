package com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.catalogo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class Destination(
    val pais: String,
    val ciudad: String,
    val costoPromedio: Double,
    val imageUrl: String
)

val destinosMock = listOf(
    Destination(
        pais = "Perú",
        ciudad = "Cusco",
        costoPromedio = 250.0,
        imageUrl = "https://www.boletomachupicchu.com/gutblt/wp-content/uploads/2025/11/viaje-cusco-full.jpg"
    ),
    Destination(
        pais = "Argentina",
        ciudad = "Buenos Aires",
        costoPromedio = 180.0,
        imageUrl = "https://blog.skyairline.com/wp-content/uploads/2021/07/skyairline_skyairline_image_182.jpeg"
    ),
    Destination(
        pais = "Colombia",
        ciudad = "Cartagena",
        costoPromedio = 200.0,
        imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/f6/92/cd/cartagena-colombia.jpg?w=400&h=300&s=1"
    ),
    Destination(
        pais = "México",
        ciudad = "Ciudad de México",
        costoPromedio = 220.0,
        imageUrl = "https://www.segurilatam.com/wp-content/uploads/sites/5/2025/06/plaza-del-zocalo-y-catedral-metropolitana-cdmx.jpg"
    ),
    Destination(
        pais = "Chile",
        ciudad = "Santiago",
        costoPromedio = 160.0,
        imageUrl = "https://images.visitchile.com/destinos/615_COSTANERA_CENTER.jpg"
    )
)

@Composable
fun CatalogoDestinosScreen() {

    val totalDestinos = destinosMock.size
    val sumaCostos = destinosMock.sumOf { it.costoPromedio }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text(
                text = "Catálogo de Destinos",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(destinosMock) { destino ->
            DestinoCard(destino)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "📊 Resumen",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Total de destinos: $totalDestinos",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Suma total de costos: S/. %.2f".format(sumaCostos),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DestinoCard(destino: Destination) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            AsyncImage(
                model = destino.imageUrl,
                contentDescription = destino.ciudad,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = destino.ciudad,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "🌍 ${destino.pais}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "💵 S/. %.2f / día".format(destino.costoPromedio),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}