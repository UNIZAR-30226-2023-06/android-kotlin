package com.example.mycatan.others

/*val timer = object : CountDownTimer(60000, 1000) { // 60 segundos, 1 segundo por intervalo
        override fun onTick(millisUntilFinished: Long) {
            // Aquí puedes actualizar la UI con el tiempo restante
        }

        override fun onFinish() {

        }
    }*/

fun turnos(turn_phase: String, playerTurn: String, turn_time: Int){
    // TODO: Hacer un if para cada fase ya que en cada uno se haran acciones diferentes
    // Además hay que comprobar que es tu turno de juego
    if ( turn_phase == "INITIAL_TURN1" && playerTurn == Globals.Id ){
        // TODO: MOSTRAR POP-UP (O ALGO ASI) CON ALGO DEL ESTILO: "ES TU TURNO, COLOCA UNA CARRETERA Y UN PUEBLO"

        // GET del tablero si no eres el primero
        // Colocar pueblo y carretera
        // POST del tablero con las modificaciones que has hecho
        // Pasar turno al siguiente jugador

    } else if(turn_phase == "OBTENCION RECURSOS" && playerTurn == Globals.Id){
        // TODO: MOSTRAR POP-UP: "ES TU TURNO, TIRA LOS DADOS PARA OBTENER RECURSOS" (POP-UP CON UNOS DADOS PARA CLICAR)

        // GET del tablero,  las siguientes 4 fases van seguidas, no pasas de TURNO hasta que no terminas la 4
        // Tirar dados
        // Si tenias alguna contruccion según numero y tipo de terreno obtienes recursos (funciones del backend)
        // Mostrar directamente POP-UP con los recursos obtenidos
        // Al pasar el turn_time se pasa a la siguiente fase

    } else if(turn_phase == "USO CARTAS DESARROLLO" && playerTurn == Globals.Id){
        // TODO: MOSTRAR POP-UP: "¿QUIERES USAR UNA CARTA DE DESARROLLO?" (POP-UP CON LAS CARTAS QUE TIENES DISPONIBLES)

        // Si se usa una carta se hace un POST al backend para usarla
        // Si no tienes cartas o no quieres usarlas, click CONTINUAR y se pasa a la siguiente fase

    } else if(turn_phase == "NEGOCIACIÓN" && playerTurn == Globals.Id){
        // TODO: MOSTRAR POP-UP: "PIDE RECURSOS A OTROS USUARIOS" (POP-UP PARA ELEGIR EL USUARIO Y PODER INDICAR LOS RECURSOS QUE QUIERES INTERCAMBIAR)

        // Si se hace una negociación se hace un POST al backend para realizarla
        // Esperar a que el jugador ACEPTE o RECHACE el intercambio, y se pasa de fase

    } else if(turn_phase == "COMPRA/CONSTRUCCION" && playerTurn == Globals.Id){
        // TODO: MOSTRAR POP-UP: "COLOCAR CONSTRUCCIONES, CAMINOS, CIUDADES Y POBLADOS" , ESTOS MAS O MENOS ESTAN HECHOS

        // Como ya se ha hecho un GET en la fase 1, se puede usar el tablero que ya se tiene
        // Contruir donde se quiera/pueda
        // POST del tablero con las modificaciones que has hecho

        // Pasar turno al siguiente jugador

    } else {
        // TODO: SINO SE PUEDE MOSTRAR UN POP UP DE: "ES EL TURNO DE PLAYERTURN, ESPERA A QUE TERMINE"
    }
}