import java.io.File
import java.util.Random

val random = Random()

fun rand(from: Int, to: Int) : Int {
    return random.nextInt(to - from) + from
}
data class Item(
    val name: String,
    val weight: Double
)

interface SpaceShip {
    fun launch(): Boolean
    fun land(): Boolean
    fun canCarry(newItem: Item): Boolean
    fun carry(newItem: Item)
}

open class Rocket: SpaceShip {
    val cargo: ArrayList<Item> by lazy{
        ArrayList<Item>()
    }
    var weight: Double = 0.0
    var maxWeight: Double = 0.0

    override fun launch(): Boolean{
        return true
    }

    override fun land(): Boolean{
        return true
    }

    override fun canCarry(newItem: Item): Boolean{
        return (weight + newItem.weight) <= maxWeight
    }

    override fun carry(newItem: Item) {
        this.cargo.add(newItem)
        this.weight += newItem.weight
    }

}

class U1 : Rocket() {
    init {
        maxWeight = 18000.0
        weight = 10000.0
    }

    override fun launch(): Boolean {
        val randomNumber = rand(0,8)
        return (0.05 * (weight/maxWeight)) <= randomNumber
    }

    override fun land(): Boolean {
        val randomNumber = rand(0,16)
        return (0.01 * (weight/maxWeight)) <= randomNumber
    }
}

class U2 : Rocket() {
    init {
        maxWeight = 29000.0
        weight = 18000.0
    }

    override fun launch(): Boolean {
        val randomNumber = rand(0,8)
        return (0.04 * (weight/maxWeight)) <= randomNumber
    }

    override fun land(): Boolean {
        val randomNumber = rand(0,16)
        return (0.08 * (weight/maxWeight)) <= randomNumber
    }
}

class Simulation{
    fun loadItems(fileName:String): ArrayList<Item>{
        val listOfElements = ArrayList<Item>()
        val fileContent: List<String> = File(fileName).readLines()
        fileContent.forEach {
            val data = it.split("=")
            if (data[0] != null){
                val newItem = Item(data[0], data[1].toDouble())
                listOfElements.add(newItem)
            }
        }
        return listOfElements
    }

    fun loadU1(itemList: ArrayList<Item>): ArrayList<U1>{
        val listOfU1 = ArrayList<U1>()
        var idx = 0
        var idxStartItemList = 0
        while (idx < itemList.lastIndex){
            val auxU1 = U1()
            for(idxItem in idxStartItemList..itemList.lastIndex){
                if (auxU1.canCarry(itemList[idxItem])){
                    auxU1.carry(itemList[idxItem])
                }else{
                    idxStartItemList = idxItem
                    listOfU1.add(auxU1)
                    break
                }
                idx +=1
            }
        }
        return listOfU1
    }

    fun loadU2(itemList: ArrayList<Item>): ArrayList<U2>{
        val listOfU2 = ArrayList<U2>()
        var idx = 0
        var idxStartItemList = 0
        while (idx < itemList.lastIndex){
            val auxU2 = U2()
            for(idxItem in idxStartItemList..itemList.lastIndex){
                if (auxU2.canCarry(itemList[idxItem])){
                    auxU2.carry(itemList[idxItem])
                }else{
                    idxStartItemList = idxItem
                    listOfU2.add(auxU2)
                    break
                }
                idx +=1
            }
        }
        return listOfU2
    }

    fun runSimulation(rocketsList: ArrayList<Rocket>): Int{
        var totalRockts = 0
        for (aRocket in rocketsList){
            var launchState = aRocket.launch()
            var landState = aRocket.land()
            totalRockts += 1
            while (!launchState){
                launchState = aRocket.launch()
                totalRockts += 1
            }
            while (!landState){
                landState = aRocket.land()
                totalRockts += 1
            }
        }
        return totalRockts
    }
}

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

fun main(){
    var marsProject = MarsProject()
    marsProject.startProject()


}