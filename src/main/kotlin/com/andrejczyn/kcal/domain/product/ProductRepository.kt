package com.andrejczyn.kcal.domain.product

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface ProductRepository : ReactiveMongoRepository<Product, String> {
}

@Document("products")
data class Product(
        @Id val id: String?,
        val name: String,
        val calories: Int,
        val protein: Int,
        val fat: Int,
        val carbohydrates: Int,
        val createAt: Instant = Instant.now()
)
