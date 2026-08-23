import dev.deftu.gradle.utils.GameSide
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.java

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven("https://repo.polyfrost.cc/releases")
    maven("https://repo.spongepowered.org/repository/maven-public")
    maven("https://repo.hypixel.net/repository/Hypixel/")
}

plugins {
    java
    val dgt = "2.73.0"
    id("dev.deftu.gradle.tools") version dgt
    for (tool in listOf(
        "java",
        "minecraft.loom",
        "bloom",
        "resources",
        "shadow"
    )) id("dev.deftu.gradle.tools.$tool") version dgt
}

val shadeOptionally = configurations.create("shadeOptionally")
configurations.named("implementation") {
    extendsFrom(shadeOptionally)
}

dependencies {
    shadeOptionally("cc.polyfrost:oneconfig-wrapper-launchwrapper:${sc.properties.getAs<String>("versions.oneconfigwrapper")}")
    compileOnly("cc.polyfrost:oneconfig-${mcData.version}-${mcData.loader}:${sc.properties.getAs<String>("versions.oneconfig")}")
    compileOnly("org.spongepowered:mixin:0.7.11-SNAPSHOT")

    modImplementation("net.hypixel:mod-api-forge:${sc.properties.getAs<String>("versions.hypixelmodapi")}")
    shadeOptionally("net.hypixel:mod-api-forge-tweaker:${sc.properties.getAs<String>("versions.hypixelmodapi")}")
}

toolkitLoomHelper {
    disableRunConfigs(GameSide.SERVER)

    useTweaker("at.yedel.dreamersdeluxe.launch.DreamersDeluxeTweaker")
    useForgeMixin("mixins.dreamersdeluxe.json5", true)
    useMixinRefMap("mixins.dreamersdeluxe.refmap.json5", true)

    useDevAuth(sc.properties.getAs<String>("versions.devauth"))
    useArgument("--version", "DreamersDeluxe", GameSide.BOTH)
    val resourcePackDir: String? = System.getenv("minecraft.resourcePackDir")
    if (!resourcePackDir.isNullOrBlank()) {
        println("Using resource pack directory $resourcePackDir from environment variable minecraft.resourcePackDir")
        useArgument("--resourcePackDir", resourcePackDir, GameSide.BOTH)
    }
}

tasks {
    processResources {
        fun MutableMap<String, String>.register(key: String, value: String) {
            inputs.property(key, value)
            set(key, value)
        }
        exclude("fabric.mod.json")
        filesMatching("mixins.dreamersdeluxe.json5") { expand("mixinJava" to "JAVA_8", "mixinMin" to "0.7.11") }

        outputs.upToDateWhen { false }
    }

    register<Copy>("buildAndCollect") {
        group = "build"

        from(remapJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs"))
        dependsOn("build")
    }

    jar {
        archiveFileName = "DreamersDeluxe-$version+${mcData}.jar"
        manifest.attributes(
            mapOf(
                "Main-Class" to "at.yedel.dreamersdeluxe.launch.DreamersDeluxeWindow",
                "ModSide" to "CLIENT",
            )
        )
    }
    fatJar {
        configurations = listOf(shadeOptionally)
        relocate("net.hypixel.modapi.tweaker", "at.yedel.dreamersdeluxe.launch")
    }
}


