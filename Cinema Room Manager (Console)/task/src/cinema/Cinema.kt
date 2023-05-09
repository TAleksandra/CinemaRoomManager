package cinema

import java.util.*
import kotlin.math.floor

fun showMenu() {
    println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
}

fun showTheSeats(seatsInRow: Int, rows: Int, seats: MutableList<MutableList<String>>) {
    var seatsNumbers = "1"
    for (i in 2..seatsInRow) {
        seatsNumbers += " $i"
    }

    println("Cinema:\n  $seatsNumbers")

    for (i in 0 until rows) {
        print(i+1)
        for (j in 0 until seatsInRow) {
            print(seats[i][j])
        }
        println(" ")
    }
}

fun buyATicket(firstHalf: Double, totalSeats: Float, chosenRow: Int): Int {
//    val firstHalf = floor((rows/2).toDouble())
//    println("first half: $firstHalf")

    val ticketPrice: Int = if (totalSeats <= 60){
        10
    } else {
        if (chosenRow + 1 <= firstHalf) {
            10
        } else {
            8
        }
    }
    println("Ticket price: $$ticketPrice\n")
    return ticketPrice
}

fun choseSeats(chosenSeat: MutableList<Int>, seats: MutableList<MutableList<String>>, seatsInRow: Int, rows: Int){
    while(true){
        println("Enter a row number:")
        chosenSeat[0] = readln().toInt() - 1
        println("Enter a seat number in that row:")
        chosenSeat[1] = readln().toInt() - 1



        try {
            if (seats[chosenSeat[0]][chosenSeat[1]] == " B"){
                println("That ticket has already been purchased!\n")
            } else {
                seats[chosenSeat[0]][chosenSeat[1]] = " B"
                break
            }
        }
        catch (e: Exception) {
            println("Wrong input!\n")
        }
    }
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()
    val totalSeats = (rows * seatsInRow).toFloat()
    val chosenSeat = mutableListOf(0,0)
    val seats : MutableList<MutableList<String>> = mutableListOf()
    val firstHalf = floor((rows/2).toDouble())
    var currentIncome = 0
    var numberOfPurchasedTickets = 0.0F
    val totalIncome = if (totalSeats <= 60) (rows * seatsInRow * 10) else ((firstHalf * seatsInRow * 10) + ((rows - firstHalf) * seatsInRow * 8)).toInt()



    for (i in 1 .. rows) {
        val lista = mutableListOf<String>()
         for (j in 1 .. seatsInRow) {
             lista.add(" S")
         }
        seats.add(lista)
    }


    while (true) {
        showMenu()
        when (readln().toInt()) {
            1 -> {
                showTheSeats(seatsInRow, rows, seats)
            }
            2 -> {
                choseSeats(chosenSeat, seats, seatsInRow, rows)
                currentIncome += buyATicket(firstHalf, totalSeats, chosenSeat[0])
                numberOfPurchasedTickets += 1
            }
            3 -> {
                val percentage: Float = (100 * numberOfPurchasedTickets / totalSeats)
                val formatPercentage = String.format(locale = Locale.ENGLISH,"%.2f", percentage)
                println("Number of purchased tickets: ${numberOfPurchasedTickets.toInt()}")
                println("Percentage: $formatPercentage%")
                println("Current income: $$currentIncome")
                println("Total income: $$totalIncome\n")


            }
            0 -> break
            else -> {
                println("Unknown operation. Try again.")
            }
        }
    }

}
