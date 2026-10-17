package com.example.ejercicio_controlaforo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejercicio_controlaforo.ui.theme.EjercicioControlAforoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioControlAforoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ControlAforo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ControlAforo(modifier: Modifier = Modifier) {
    var personasAdentro by remember { mutableStateOf(0) }
    var ultimoRegistrado by remember { mutableStateOf("Ninguno") }
    var nombreEscrito by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Control de aforo")
        Text("Personas adentro: $personasAdentro")
        Text("Último registrado: $ultimoRegistrado")

        TextField(
            value = nombreEscrito,
            onValueChange = { nombreEscrito = it },
            label = { Text("Nombre") },
            singleLine = true
        )

        Button(onClick = {
            if (nombreEscrito.isNotBlank()) {
                ultimoRegistrado = nombreEscrito.trim()
                personasAdentro++
                nombreEscrito = ""
            }
        }) {
            Text("Registrar e ingresar")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                if (personasAdentro > 0) {
                    personasAdentro--
                }
            }) {
                Text("Salida (-1)")
            }
            Button(onClick = {
                personasAdentro = 0
                ultimoRegistrado = "Ninguno"
            }) {
                Text("Reiniciar")
            }
        }
    }
}


