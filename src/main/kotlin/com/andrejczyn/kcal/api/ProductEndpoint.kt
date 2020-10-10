package com.andrejczyn.kcal.api

import com.andrejczyn.kcal.domain.product.Product
import com.andrejczyn.kcal.domain.product.ProductRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
class ProductEndpoint(val productRepository: ProductRepository) {
    @CrossOrigin
    @GetMapping("/products", produces = arrayOf("application/json"))
    fun getProducts(): Mono<ProductsDto> {
        return productRepository.findAll()
                .map { ProductDto(it.id, it.name, it.calories) }
                .buffer()
                .map { ProductsDto(it) }
                .toMono()
    }

    @PostMapping("/products", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun saveProduct(@RequestBody product: ProductDto): Mono<ProductDto> {
        return productRepository.save(Product(null, product.name, product.calories))
                .map { ProductDto(it.id, it.name, it.calories) }
    }
}

data class ProductsDto(val products: List<ProductDto>)
data class ProductDto(val id: String?, val name: String, val calories: Int)
