package com.example.pc01moviles_andrade_23100124_rivasplata_22200268.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "✈️",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Travel Companion App",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tu asistente de viaje personal",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("calculadora") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(" Calculadora de Equipaje")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("planificador") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(" Planificador de Presupuesto")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("catalogo") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(" Catálogo de Destinos")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("permisos") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(" Permiso de Ubicación")
        }
    }
}