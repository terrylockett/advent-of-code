plugins {
    `groovy-gradle-plugin`
    `kotlin-dsl`
}

dependencies {
    implementation(libs.spotless)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

repositories {
    mavenCentral()
}
