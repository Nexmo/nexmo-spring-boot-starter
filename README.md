# Nexmo Spring Boot Starter

[![Maven Central](https://img.shields.io/maven-central/v/com.nexmo/nexmo-spring-boot-starter.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.nexmo%22%20AND%20a:%22nexmo-spring-boot-starter%22)
[![Build Status](https://travis-ci.org/Nexmo/nexmo-spring-boot-starter.svg?branch=master)](https://travis-ci.org/Nexmo/nexmo-spring-boot-starter)

This Spring Boot Starter has been provided to help with integrating the [Nexmo Java SDK](https://github.com/Nexmo/nexmo-java) into your [Spring Boot](https://spring.io/projects/spring-boot) project.

## Installation

For Gradle:

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.nexmo:nexmo-spring-boot-starter:1.0.0'
}
```

For Maven:

```xml
<dependency>
  <groupId>com.nexmo</groupId>
  <artifactId>nexmo-spring-boot-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage

The Nexmo Spring Boot Starter will automatically configure instances of the `NexmoClient.Builder`, `NexmoClient`, and any of the other sub-clients (`AccountClient`, `VoiceClient`, etc..) once you have provided the required configuration values.

* For help understanding our APIs, check out our [developer portal](https://developer.nexmo.com/).
* There are also **many useful code samples** in our [nexmo/nexmo-java-code-snippets](https://github.com/Nexmo/nexmo-java-code-snippets) repository.

### Configuration

The following configuration values are used to configure the starter. Check out the [Application Property Files](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-application-property-files) and [Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config) reference in the Spring Boot documentation for how to provide configuration values.

#### API Key and Secret Configuration

```
nexmo.creds.api-key=your-api-key
nexmo.creds.secret=your-api-secret
```

This will give you access to automatically wire in the `NexmoClient`, `AccountClient`, `ApplicationClient`, `ConversionClient`, `InsightClient`, `NumbersClient`, `RedactClient`, `SmsClient`, `SnsClient`, and `VerifyClient`.

You can also provide a signature secret:

```
nexmo.creds.api-key=your-api-key
nexmo.creds.signature=signature
```

#### Application ID and Private Key Configuration
To gain access to the `VoiceClient` you will need to provide a [Nexmo Application ID]() and location or contents of a private key file associated with the Nexmo Application.

Here is an example providing the path to the key:
```
nexmo.creds.application-id=application-id
nexmo.creds.private-key-path=/path/to/your/private.key
```

Here is an example providing the contents of the key:
```
nexmo.creds.application-id=application-id
nexmo.creds.private-key-contents=contents-of-the-key
```

### Customizing the `NexmoClient`

By default, the auto configuration will create a `NexmoClient.Builder` using the information provided in your `application.properties`, `application.yml`, or other external configuration source. However, when registering the `NexmoClient` and all of the other sub-clients, the auto configuration can opt to use a pre-registered `NexmoClient.Builder`.

For example, if you would like to customize the base URI that the library uses, you can register a custom version of the `NexmoClient.Builder` in your configuration class:

```java
@Autowired
NexmoCredentialsProperties nexmoCredentialsProperties;

@Bean
public NexmoClient.Builder customNexmoBuilder() {
    return NexmoClient.builder()
            .apiKey(nexmoCredentialsProperties.getApiKey())
            .apiSecret(nexmoCredentialsProperties.getSecret())
            .httpConfig(HttpConfig.builder().baseUri("https://example.com").build());
}
```
> Note that you must include your credentials as shown in this example. This builder completely replaces the automatically configured one.

## Customize Nexmo Client Version

By default, the Nexmo Spring Boot Starter will transitively define Nexmo Client to the latest version upon its release. You can override this by adding a dependency on the Nexmo Client, bringing in `4.2.0` for example:

For Gradle:

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.nexmo:client:4.2.0'
}
```

For Maven:

```xml
<dependency>
  <groupId>com.nexmo</groupId>
  <artifactId>client</artifactId>
  <version>4.2.0</version>
</dependency>
```

Bringing in older versions of the supported Nexmo Client, may result in some unforseen consequences and build errors. As a result here is a list of each version targeted by each version of the starter:

| Nexmo Spring Boot Starter | Nexmo Java Client |
|---|---|
| v1.0.0 | v4.3.0 |
| v1.0.1 | v4.3.1 |