package br.com.imcapp

import androidx.compose.ui.graphics.Color
import kotlin.math.pow

fun CalcularImc(pesoUsuario: Double, alturaUsuario: Double): Double {
    var imc = pesoUsuario / (alturaUsuario / 100).pow(2)
    return imc
}

fun ObterStatusImc(imcUsuario: Double): String {
    return if (imcUsuario < 18.5) {
        "Abaixo do peso"
    } else if (imcUsuario < 25.0) {
        "Peso Ideal"
    } else if (imcUsuario < 30.0){
        "Acima do peso"
    }else if (imcUsuario < 35.0){
        "Obesidade Grau I"
    }else if (imcUsuario < 40.0){
        "Obesidade Grau II"
    }else{
        "Obesidade Grau III"
    }
}

fun mudarCorCard(statusImc: String): Color{
    return if (statusImc.equals("Peso Ideal")) {
        Color(0xFF07AD07)
    } else if (statusImc.equals("Acima do peso")) {
        Color(0xFFFFC107)
    } else{
        Color(0xFFF44336)
    }
}
