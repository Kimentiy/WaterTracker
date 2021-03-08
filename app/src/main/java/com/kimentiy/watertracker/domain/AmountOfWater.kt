package com.kimentiy.watertracker.domain

class AmountOfWater(val valueInMl: Int) {

    operator fun plus(other: AmountOfWater): AmountOfWater {
        return AmountOfWater(valueInMl + other.valueInMl)
    }

    operator fun minus(other: AmountOfWater): AmountOfWater {
        return AmountOfWater((valueInMl - other.valueInMl).takeIf { it >= 0 } ?: 0)
    }
}
