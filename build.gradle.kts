plugins {
    base
    idea
}


allprojects {
    group = "org.springbootcamp.playground.testcontainers"
    version = "0.1-SNAPSHOT"

    apply {
        from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
    }

    configurations.all {
        with(resolutionStrategy) {
            cacheChangingModulesFor(0, TimeUnit.SECONDS)
            cacheDynamicVersionsFor(0, TimeUnit.SECONDS)
        }
    }
}

subprojects {

}

dependencies {
    subprojects.forEach {
        archives(it)
    }
}

//
//idea {
////    module {
////        excludeDirs = [file(".gradle")]
////        ["classes", "docs", "dependency-cache", "libs", "reports", "resources", "test-results", "tmp"].each {
////            excludeDirs << file("$buildDir/$it")
////        }
////    }
//}
