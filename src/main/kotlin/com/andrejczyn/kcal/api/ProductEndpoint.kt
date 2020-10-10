package com.andrejczyn.kcal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductEndpoint {
    @GetMapping("/products", produces = arrayOf("application/json"))
    fun getProducts() = arrayOf(
            ProductDto("1", "Marchewka", 200),
            ProductDto("2", "Pomidor", 200)
    )
}

data class ProductDto(val id: String, val name: String, val kilocalories: Int)
