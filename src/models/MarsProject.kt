package models

class MarsProject{
    fun startProject(){
        val simulation = Simulation()
        val listOfItemsPhase1 = simulation.loadItems("D:\\Codigos\\Kotlin\\Space Challenge\\src\\phase-1.txt")
        val listOfItemsPhase2 = simulation.loadItems("D:\\Codigos\\Kotlin\\Space Challenge\\src\\phase-2.txt")

        val listOfU1sPhase1 = simulation.loadU1(listOfItemsPhase1)
        val listOfU1sPhase2 = simulation.loadU1(listOfItemsPhase2)


        val totalRocketsU1 = simulation.runSimulation(listOfU1sPhase1 as ArrayList<Rocket>) + simulation.runSimulation(listOfU1sPhase2 as ArrayList<Rocket>)

        val listOfU2sPhase1 = simulation.loadU2(listOfItemsPhase1)
        val listOfU2sPhase2 = simulation.loadU2(listOfItemsPhase2)

        val totalRocketsU2 = simulation.runSimulation(listOfU2sPhase1 as ArrayList<Rocket>) + simulation
            .runSimulation(listOfU2sPhase2 as ArrayList<Rocket>)

        println("--------------------------------------")
        println("We'll need $totalRocketsU1 U1 Rockets to complete successfully this mision.")
        println("We'll need $totalRocketsU2 U2 Rockets to complete successfully this mision.")
        println("--------------------------------------")
    }
}