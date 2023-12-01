plugins {
    `groovy-gradle-plugin`
    `kotlin-dsl`
}

dependencies {
    implementation(libs.spotless)
    implementation(libs.kotlinJvmPlugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    
}

repositories {
    mavenCentral()
}
