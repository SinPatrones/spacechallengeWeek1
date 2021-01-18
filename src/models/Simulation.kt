package models

import data.Item
import java.io.File

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
            while (!launchState && !landState){
                launchState = aRocket.launch()
                landState = aRocket.land()
                totalRockts += 1
            }
        }
        return totalRockts
    }
}