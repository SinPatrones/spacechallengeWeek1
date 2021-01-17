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
        return 0.05 * (weight/maxWeight) >= 0.5
    }

    override fun land(): Boolean {
        return 0.01 * (weight/maxWeight) >= 0.01
    }
}

class U2 : Rocket() {
    init {
        maxWeight = 29000.0
        weight = 18000.0
    }

    override fun launch(): Boolean {
        return 0.04 * (weight/maxWeight) >= 0.4
    }

    override fun land(): Boolean {
        return 0.08 * (weight/maxWeight) >= 0.08
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

    fun runSimulation(rocketsList: ArrayList<Rocket>){

    }
}

fun main(){
    println("--------------------------------------------------------")
    val simulation = Simulation()
    val listOfItems = simulation.loadItems("D:\\Codigos\\Kotlin\\Space Challenge\\src\\phase-1.txt")
    val listOfU1s = simulation.loadU1(listOfItems)
    val listOfU2s = simulation.loadU2(listOfItems)

    for(u1 in listOfU1s){
        println(u1)
    }

    for(u2 in listOfU2s){
        println(u2)
    }

}