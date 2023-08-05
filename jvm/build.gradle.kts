subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    configure<JavaPluginExtension> {
        withSourcesJar()
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                name = "gpr"
                url = uri("https://maven.pkg.github.com/jangjunha/ftgo-proto")
                credentials {
                    username = project.findProperty("gpr.user") as? String ?: System.getenv("GPR_USERNAME")
                    password = project.findProperty("gpr.key") as? String ?: System.getenv("GPR_TOKEN")
                }
            }
            maven {
                name = "fury"
                url = uri("https://maven.fury.io/jangjunha/")
                authentication {
                    create<BasicAuthentication>("basic")
                }
                credentials {
                    username = System.getenv("FURY_TOKEN")
                    password = "NOPASS"
                }
            }
        }
        publications {
            register<MavenPublication>("lib") {
                pom {
                    name.set(project.name)
                    url.set("https://github.com/jangjunha/ftgo-proto")
                    developers {
                        developer {
                            id.set("jangjunha")
                            name.set("jangjunha")
                        }
                    }
                }
                from(components["java"])
            }
        }
    }
}
