import io.github.kobylynskyi.graphql.codegen.gradle.GraphQLCodegenGradleTask

plugins {
    id("java")
    id("maven-publish")
    id("io.github.kobylynskyi.graphql.codegen") version "5.5.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // Build context
    compileOnly("javax.validation:validation-api:2.0.1.Final")

    // Test context
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.9.0")
}

tasks.named<GraphQLCodegenGradleTask>("graphqlCodegen") {
    graphqlSchemaPaths = listOf("$projectDir/src/main/resources/schema.graphqls")
    outputDir = File("$buildDir/generated")
    apiPackageName = "com.cunningbird.contractfirst.graphqls.contract.api"
    modelPackageName = "com.cunningbird.contractfirst.graphqls.contract.model"
    generateApis = true
}

sourceSets {
    main {
        java {
            srcDir("${buildDir}/generated")
        }
    }
}

tasks.withType<JavaCompile> {
    dependsOn(tasks.withType<GraphQLCodegenGradleTask>())
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/cunningbird/contract-first-graphql")
            credentials {
                username = System.getProperty("publishRegistryUsername")
                password = System.getProperty("publishRegistryPassword")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cunningbird.contractfirst.graphqls"
            artifactId = "contract"
            version = "1.0.0"
            from(components["java"])
        }
    }
}