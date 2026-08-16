plugins {
    id("java")
    id("war")
}

group = "com.crimsonlogic.mutualfundinvestmentspringmvc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    // Spring MVC
    implementation ("org.springframework:spring-webmvc:5.3.39")

    // Servlet API - provided by Tomcat
    compileOnly ("javax.servlet:javax.servlet-api:4.0.1")

    // JSP
    compileOnly ("javax.servlet.jsp:javax.servlet.jsp-api:2.3.3")

    // JSTL
    implementation ("javax.servlet:jstl:1.2")

    compileOnly ("javax.servlet:javax.servlet-api:4.0.1")

    implementation("org.springframework:spring-jdbc:5.3.39")

    implementation("org.mybatis:mybatis:3.5.19")
    implementation("org.mybatis:mybatis-spring:2.1.2")

    implementation("com.mysql:mysql-connector-j:8.4.0")

    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    compileOnly("javax.servlet.jsp:javax.servlet.jsp-api:2.3.3")

    implementation("javax.servlet:jstl:1.2")

    testImplementation("org.springframework:spring-test:5.3.39")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("org.mindrot:jbcrypt:0.4")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
}

tasks.test {
    useJUnitPlatform()
}