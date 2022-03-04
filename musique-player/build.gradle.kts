plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(":alacdecoder"))
    implementation(project(":jaad"))
    implementation(project(":cuelib"))
    api(project(":jaudiotagger"))
    implementation(project(":javaFlacEncoder"))
    implementation(project(":jflac"))
    implementation(project(":tta"))
    implementation(project(":vorbis-java"))
    implementation(project(":wavpack"))
    implementation(project(":jmac"))
    implementation(project(":javalayer"))
    implementation(project(":jorbis"))
    api(project(":discogs"))
}