package parking

class Park {
    private var parkSize: Int = 0
    private var freeSpot: Int = 0
    private var spot: MutableList<String> = mutableListOf()

    fun create(cmd: List<String>) {
        parkSize = cmd[1].toInt()
        spot = List(parkSize+1) {""}.toMutableList()
        println("Created a parking lot with $parkSize spots.")
    }

    fun park(cmd: List<String>){
        if (!isCreated()) return

        freeSpot = getFreeSpot()

        if (freeSpot != 0) {
            println("${cmd[2]} car parked in spot $freeSpot.")
            spot[freeSpot] = "" + freeSpot + " " + cmd[1] + " " + cmd[2]
        } else {
            println("Sorry, the parking lot is full.")
        }
    }

    fun leave(cmd: List<String>) {
        if (!isCreated()) return

        if (spot[cmd[1].toInt()] == "") {
            println("There is no car in spot ${cmd[1]}.")
        } else {
            println("Spot ${cmd[1]} is free.")
            spot[cmd[1].toInt()] = ""
        }
    }

    fun status() {
        if (!isCreated()) return

        var empty = true
        for (n in spot.indices) {
            if (spot[n] != "") {
                println(spot[n])
                empty = false
            }
        }

        if (empty) println("Parking lot is empty.")
    }

    fun regByColor(cmd: List<String>) {
        if (!isCreated()) return

        var noColor = true
        for (n in spot.indices) {

            if (spot[n] == "") continue

            var tmp = spot[n].split(" ")

            if (tmp[2].uppercase() == cmd[1].uppercase()) {
                if (!noColor) print(", ")
                print(tmp[1])
                noColor = false
            }
        }

        if (noColor) println("No cars with color ${cmd[1]} were found.") else println()
    }

    fun spotByColor(cmd:List<String>) {
        if (!isCreated()) return

        var noColor = true
        for (n in spot.indices) {

            if (spot[n] == "") continue

            var tmp = spot[n].split(" ")

            if (tmp[2].uppercase() == cmd[1].uppercase()) {
                if (!noColor) print(", ")
                print(tmp[0])
                noColor = false
            }
        }

        if (noColor) println("No cars with color ${cmd[1]} were found.") else println()
    }

    fun spotByReg(cmd:List<String>) {
        if (!isCreated()) return

        var noReg = true
        for (n in spot.indices) {

            if (spot[n] == "") continue

            var tmp = spot[n].split(" ")

            if (tmp[1].uppercase() == cmd[1].uppercase()) {

                println(tmp[0])
                noReg = false
                break
            }
        }

        if (noReg) println("No cars with registration number ${cmd[1]} were found.")
    }

    private fun getFreeSpot(): Int {
        for(index in 1 until spot.size){
            if (spot[index] == "") return index
        }
        return 0
    }

    private fun isCreated(): Boolean {
        return if (parkSize != 0 ) {
            true
        } else {
            println("Sorry, a parking lot has not been created.")
            false
        }
    }
}

fun main() {
    var cmd: List<String>
    var cPark = Park()

    do{
        cmd = readln().split(" ")
        
        when(cmd[0]) {
            "create" -> {
                cPark = Park()
                cPark.create(cmd)
            }
            "park" -> {
                cPark.park(cmd)
            }
            "status" -> {
                cPark.status()
            }
            "reg_by_color" -> {
                cPark.regByColor(cmd)
            }
            "spot_by_color" -> {
                cPark.spotByColor(cmd)
            }
            "spot_by_reg" -> {
                cPark.spotByReg(cmd)
            }
            "leave" -> {
                cPark.leave(cmd)
            }
        }
    } while(cmd[0] != "exit")
}


