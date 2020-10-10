package com.andrejczyn.kcal.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductEndpoint {
    @CrossOrigin
    @GetMapping("/products", produces = arrayOf("application/json"))
    fun getProducts() = ProductsDto(listOf(
            ProductDto("1", "Marchewka", 200),
            ProductDto("2", "Pomidor", 200)
    ))
}
data class ProductsDto(val products: List<ProductDto>)
data class ProductDto(val id: String, val name: String, val calories: Int)
