package com.kimentiy.watertracker.domain

class UserPreferences(
    val dailyAim: AmountOfWater,
    val waterContainers: List<WaterContainer>
)