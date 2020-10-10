package com.andrejczyn.kcal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KCalApp

fun main(args: Array<String>) {
    runApplication<KCalApp>(*args)
}
