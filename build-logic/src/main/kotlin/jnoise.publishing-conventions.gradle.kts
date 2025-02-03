plugins {
    `maven-publish`
    id("jnoise.common-conventions")
}

publishing {
    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Ultreon/JNoiswJDK11")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = "jnoise-${project.name}"
            version = project.version as String

            from(components["java"])
            pom {
                name.set("JNoise")
                description.set(project.description)
                url.set("https://github.com/Articdive/JNoise")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("Articdive")
                        name.set("Articdive")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/Articdive/JNoise.git")
                    developerConnection.set("scm:git:ssh://github.com/Articdive/JNoise.git")
                    url.set("https://github.com/Articdive/JNoise/tree/main")
                }
            }
        }
    }
}
