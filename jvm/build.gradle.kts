plugins {
  id("com.google.cloud.artifactregistry.gradle-plugin") version "2.2.1" apply false
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.google.cloud.artifactregistry.gradle-plugin")

    configure<JavaPluginExtension> {
        withSourcesJar()
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                name = "GoogleArtifactRegistry"
                url = uri("artifactregistry://asia-northeast3-maven.pkg.dev/ftgo-jangjunha/maven-public")
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
