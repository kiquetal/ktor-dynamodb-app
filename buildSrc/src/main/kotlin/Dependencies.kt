object Deps {
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}" }
    val ktor_core by lazy { "io.ktor:ktor-server-core:${Versions.ktor_version}"}
    val ktor_auth by lazy { "io.ktor:ktor-auth:${Versions.ktor_version}"}
    val ktor_jwt by lazy { "io.ktor:ktor-auth-jwt:${Versions.ktor_version}" }
    val ktor_gson by lazy { "io.ktor:ktor-gson:${Versions.ktor_version}" }
    val ktor_netty by lazy { "io.ktor:ktor-server-netty:${Versions.ktor_version}"}
    val aws_bom by lazy { "software.amazon.awssdk:bom:${Versions.aws_bom}" }
    val aws_dynamo by lazy { "software.amazon.awssdk:dynamodb"}
    val aws_dynamo_enhanced by lazy { "software.amazon.awssdk:dynamodb-enhanced"}
    val logback by lazy { "ch.qos.logback:logback-classic:${Versions.logback_version}"}
    val ktor_test by lazy { "io.ktor:ktor-server-tests:${Versions.ktor_version}" }
    val kotlin_test by lazy { "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin_version}"}
}
