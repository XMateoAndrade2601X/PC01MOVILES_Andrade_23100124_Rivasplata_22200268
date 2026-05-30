package com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.calculadoraequipaje

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CalculadoraEquipajeScreen() {

    var pesoTexto by remember { mutableStateOf("") }
    var tipoVuelo by remember { mutableStateOf("Nacional") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var errorPeso by remember { mutableStateOf<String?>(null) }

    val opciones = listOf("Nacional", "Internacional")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Calculadora de Equipaje",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Campo peso
        OutlinedTextField(
            value = pesoTexto,
            onValueChange = {
                pesoTexto = it
                errorPeso = null
                resultado = null
            },
            label = { Text("Peso de la maleta (kg)") },
            isError = errorPeso != null,
            supportingText = {
                if (errorPeso != null) Text(errorPeso!!)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Selección tipo de vuelo
        Text(
            text = "Tipo de vuelo:",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        opciones.forEach { opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = tipoVuelo == opcion,
                    onClick = {
                        tipoVuelo = opcion
                        resultado = null
                    }
                )
                Text(
                    text = if (opcion == "Nacional") "Nacional (máx. 23 kg)"
                    else "Internacional (máx. 32 kg)"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón calcular
        Button(
            onClick = {
                val peso = pesoTexto.trim().toDoubleOrNull()
                when {
                    pesoTexto.isBlank() -> errorPeso = "Este campo es obligatorio"
                    peso == null -> errorPeso = "Ingresa un valor numérico válido"
                    peso <= 0 -> errorPeso = "El peso debe ser mayor a cero"
                    else -> {
                        errorPeso = null
                        val limite = if (tipoVuelo == "Nacional") 23.0 else 32.0
                        resultado = if (peso <= limite) {
                            "Equipaje dentro del límite permitido ($limite kg)."
                        } else {
                            val exceso = peso - limite
                            "Excede el límite de $limite kg.\nKg excedidos: %.2f kg".format(exceso)
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Resultado
        resultado?.let {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (it.startsWith("Bien"))
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}