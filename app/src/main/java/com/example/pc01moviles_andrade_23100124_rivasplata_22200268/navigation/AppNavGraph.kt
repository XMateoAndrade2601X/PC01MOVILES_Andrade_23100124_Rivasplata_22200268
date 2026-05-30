package com.example.pc01moviles_andrade_23100124_rivasplata_22200268.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc01moviles_andrade_23100124_rivasplata_22200268.home.HomeScreen
import com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.calculadoraequipaje.CalculadoraEquipajeScreen
import com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.catalogo.CatalogoDestinosScreen
import com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.permisoubicacion.PermisoUbicacionScreen
import com.example.pc01moviles_andrade_23100124_rivasplata_22200268.presentation.planificador.PlanificadorPresupuestoScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen()
            }
        }
        composable("calculadora") {
            DrawerScaffold(navController) {
                CalculadoraEquipajeScreen()
            }
        }
        composable("planificador") {
            DrawerScaffold(navController) {
                PlanificadorPresupuestoScreen()
            }
        }
        composable("catalogo") {
            DrawerScaffold(navController) {
                CatalogoDestinosScreen()
            }
        }
        composable("permisos") {
            DrawerScaffold(navController) {
                PermisoUbicacionScreen()
            }
        }
    }
}