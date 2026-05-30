@file:Suppress("DEPRECATION")

package com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.planificador

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.MenuAnchorType
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanificadorPresupuestoScreen() {

    var diasTexto by remember { mutableStateOf("") }
    var presupuestoTexto by remember { mutableStateOf("") }
    var tipoAlojamiento by remember { mutableStateOf("Estándar") }
    var expanded by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf<String?>(null) }

    var errorDias by remember { mutableStateOf<String?>(null) }
    var errorPresupuesto by remember { mutableStateOf<String?>(null) }

    val opcionesAlojamiento = listOf(
        Triple("Económico", 0.8, "Viaje económico y accesible"),
        Triple("Estándar", 1.0, "Viaje con comodidades estándar"),
        Triple("Premium", 1.5, "Viaje de lujo y alta gama")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Planificador de Presupuesto",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Campo días
        OutlinedTextField(
            value = diasTexto,
            onValueChange = {
                diasTexto = it
                errorDias = null
                resultado = null
            },
            label = { Text("Cantidad de días") },
            isError = errorDias != null,
            supportingText = { if (errorDias != null) Text(errorDias!!) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo presupuesto diario
        OutlinedTextField(
            value = presupuestoTexto,
            onValueChange = {
                presupuestoTexto = it
                errorPresupuesto = null
                resultado = null
            },
            label = { Text("Presupuesto diario (S/.)") },
            isError = errorPresupuesto != null,
            supportingText = { if (errorPresupuesto != null) Text(errorPresupuesto!!) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown tipo de alojamiento
        Text(
            text = "Tipo de alojamiento:",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = tipoAlojamiento,
                onValueChange = {},
                readOnly = true,
                label = { Text("Alojamiento") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opcionesAlojamiento.forEach { (nombre, _, _) ->
                    DropdownMenuItem(
                        text = { Text(nombre) },
                        onClick = {
                            tipoAlojamiento = nombre
                            expanded = false
                            resultado = null
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón calcular
        Button(
            onClick = {
                val dias = diasTexto.trim().toIntOrNull()
                val presupuesto = presupuestoTexto.trim().toDoubleOrNull()
                var valido = true

                when {
                    diasTexto.isBlank() -> { errorDias = "Este campo es obligatorio"; valido = false }
                    dias == null -> { errorDias = "Ingresa un número entero válido"; valido = false }
                    dias <= 0 -> { errorDias = "Los días deben ser mayor a cero"; valido = false }
                }
                when {
                    presupuestoTexto.isBlank() -> { errorPresupuesto = "Este campo es obligatorio"; valido = false }
                    presupuesto == null -> { errorPresupuesto = "Ingresa un valor numérico válido"; valido = false }
                    presupuesto <= 0 -> { errorPresupuesto = "El presupuesto debe ser mayor a cero"; valido = false }
                }

                if (valido && dias != null && presupuesto != null) {
                    val factor = opcionesAlojamiento.first { it.first == tipoAlojamiento }.second
                    val descripcion = opcionesAlojamiento.first { it.first == tipoAlojamiento }.third
                    val total = dias * presupuesto * factor
                    resultado = " Presupuesto total: S/. %.2f\n\n Escenario: $descripcion\n Alojamiento: $tipoAlojamiento (factor $factor)\n $dias días × S/. %.2f × $factor".format(total, presupuesto)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular presupuesto")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Resultado
        resultado?.let {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
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