import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.embeddedKotlinVersion

object Versions {
  object Build {
    val kotlin = embeddedKotlinVersion
    val javaVersion = JavaVersion.VERSION_1_8
    val java = javaVersion.toString()
  }


  val springBoot = "2.2.1.RELEASE"
}