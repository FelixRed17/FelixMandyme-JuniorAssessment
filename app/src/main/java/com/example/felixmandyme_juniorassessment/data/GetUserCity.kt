package com.example.felixmandyme_juniorassessment.data

class GetUserCity(private val locationRepository: UserLocationRepository) {
    suspend operator fun invoke(): String = locationRepository.getCity()
}