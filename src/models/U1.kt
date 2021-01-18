package models

import lib.rand

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