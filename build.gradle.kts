allprojects {
    group = "de.articdive"
    version = "5.0.0" + (if (System.getenv("SNAPSHOT") == "true") "-SNAPSHOT" else "")
    description = "A Library that allows you to generate noise using different algorithms."
}

val exportedProjects = arrayListOf(
    ":core",
    ":generators",
    ":modifiers",
    ":modules",
    ":pipeline",
    ":transformers"
)

tasks {
    register<Javadoc>("global-javadoc") {
        source = files(exportedProjects.map { project(it).the<SourceSetContainer>()["main"].allJava.asFileTree }).filter {
            !it.name.equals("module-info.java")
        }.asFileTree
        classpath = files(exportedProjects.map { project(it).the<SourceSetContainer>()["main"].compileClasspath })
        setDestinationDir(file("${layout.buildDirectory.get()}/docs/javadoc"))
    }
}