package com.andrejczyn.kcal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class KCalApp

fun main(args: Array<String>) {
    runApplication<KCalApp>(*args)
}
