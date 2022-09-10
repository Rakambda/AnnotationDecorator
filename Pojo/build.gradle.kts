plugins {
    `java-library`
}

dependencies {
    compileOnly(libs.jetbrainsAnnotations)
    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)

    api(project(":PojoApi"))
}