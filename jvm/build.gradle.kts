subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    configure<JavaPluginExtension> {
        withSourcesJar()
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                name = "GithubPackages"
                url = uri("https://maven.pkg.github.com/jangjunha/ftgo-proto")
                credentials {
                    username = project.findProperty("gpr.user") as? String ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as? String ?: System.getenv("TOKEN")
                }
            }
        }
        publications {
            register<MavenPublication>("gpr") {
                from(components["java"])
            }
        }
    }
}
