plugins {
    `java-library`
}

dependencies {
    compileOnly(libs.jetbrainsAnnotations)
    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)

    api(libs.byteBuddy)
    api(libs.reflections)

    api(project(":CatalogApi"))
}