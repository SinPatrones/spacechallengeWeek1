package models

import data.Item
import interfaces.SpaceShip

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