package models

import lib.rand

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