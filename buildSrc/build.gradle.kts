plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
  `kotlin-dsl-precompiled-script-plugins`
}

apply {
  from("../gradle/repositories.gradle.kts")
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}


dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
}
