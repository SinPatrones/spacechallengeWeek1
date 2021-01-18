package interfaces
import data.Item

interface SpaceShip {
    fun launch(): Boolean
    fun land(): Boolean
    fun canCarry(newItem: Item): Boolean
    fun carry(newItem: Item)
}