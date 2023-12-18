package com.trnk.thika_road_nyumba_kumi

import com.trnk.thika_road_nyumba_kumi.repos.MechanicRepo
import org.apache.catalina.core.ApplicationFilterChain
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class ThikaRoadNyumbaKumiApplication
fun main(args: Array<String>) {
    runApplication<ThikaRoadNyumbaKumiApplication>(*args)
}

