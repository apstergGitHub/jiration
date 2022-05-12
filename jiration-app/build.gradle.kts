plugins { application }

apply(plugin = "org.jetbrains.kotlin.plugin.spring")

application {
    mainClass.set("com.jiration.ApplicationRunner")
}