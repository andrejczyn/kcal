package com.andrejczyn.kcal.api

import com.andrejczyn.kcal.domain.product.Product
import com.andrejczyn.kcal.domain.product.ProductRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
class ProductEndpoint(val productRepository: ProductRepository) {

    @GetMapping("/products", produces = arrayOf("application/json"))
    fun getProducts(): Mono<ProductsDto> {
        return productRepository.findAll()
                .map { toDto(it) }
                .buffer()
                .map { ProductsDto(it) }
                .toMono()
    }

    @PostMapping("/products", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun createProduct(@RequestBody product: ProductDto): Mono<ProductDto> {
        return productRepository.save(Product(
                null,
                product.name,
                product.calories,
                product.protein,
                product.fat,
                product.carbohydrates
        )).map { toDto(it) }
    }

    @PutMapping("/products/{id}", consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun createProduct(@RequestBody product: ProductDto, @PathVariable id: String): Mono<ProductDto> {
        return productRepository.findById(id)
                .map { populateValues(it, product) }
                .flatMap { productRepository.save(it) }
                .map { toDto(it) }
    }

    fun populateValues(source: Product, dto: ProductDto): Product {
        return source.copy(
                name = dto.name,
                calories = dto.calories,
                protein = dto.protein,
                fat = dto.fat,
                carbohydrates = dto.carbohydrates
        )
    }

    fun toDto(product: Product): ProductDto {
        return ProductDto(product.id, product.name, product.calories, product.protein, product.fat, product.carbohydrates)
    }
}

data class ProductsDto(val products: List<ProductDto>)
data class ProductDto(
        val id: String?,
        val name: String,
        val calories: Int,
        val protein: Int,
        val fat: Int,
        val carbohydrates: Int
)
