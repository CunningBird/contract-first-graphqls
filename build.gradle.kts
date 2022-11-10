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
    compileOnly("io.github.kobylynskyi:graphql-java-codegen:5.5.0")
    compileOnly("javax.validation:validation-api:2.0.1.Final")

    // Test context
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.9.0")

    testImplementation("io.github.kobylynskyi:graphql-java-codegen:5.5.0")
    testImplementation("javax.validation:validation-api:2.0.1.Final")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.5")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux:2.7.5")
    testImplementation("org.springframework.boot:spring-boot-starter-graphql:2.7.5")
}

tasks.named<GraphQLCodegenGradleTask>("graphqlCodegen") {
    graphqlSchemaPaths = listOf("$projectDir/src/main/resources/graphql/schema.graphqls")
    outputDir = File("$buildDir/generated")
    apiPackageName = "com.cunningbird.contractfirst.graphqls.contract.api"
    modelPackageName = "com.cunningbird.contractfirst.graphqls.contract.model"
    customTypesMapping = mutableMapOf(
        "ID" to "java.lang.Long"
    )
    generateApis = true
    generateClient = true
}

sourceSets {
    main {
        java {
            srcDir("${buildDir}/generated")
        }
    }
    test {
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