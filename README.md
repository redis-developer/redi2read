# Redi2Read - A Redis + Spring Boot Coding Adventure

A collection of Spring Boot REST services for a mythical online bookstore powered by Redis. It uses:

* [Spring Data Redis](https://spring.io/projects/spring-data-redis)
* [RedisJSON](https://oss.redislabs.com/redisjson/) via [JRedisJSON](https://github.com/RedisJSON/JRedisJSON)
* [RediSearch](https://oss.redislabs.com/redisearch/) via [spring-redisearch](https://github.com/RediSearch/spring-redisearch) and [LettuSearch](https://github.com/RediSearch/lettusearch)
* [RedisGraph](https://oss.redislabs.com/redisgraph/) via [JRedisGraph](https://github.com/RedisGraph/JRedisGraph)
* [RedisBloom](http://redisbloom.io) via [JRedisBloom](https://github.com/RedisBloom/JReBloom)

**Prerequisites:**

* [Java 11](https://sdkman.io/jdks)
* [Maven 3.2+](https://sdkman.io/sdks#maven)
* [Docker](https://www.docker.com/products/docker-desktop)
* [Redis + Modules ](https://hub.docker.com/r/redislabs/redismod) 6.0.1 or greater

**NOTE:** If you're not on Mac or Windows, you may need to [install Docker Compose](https://docs.docker.com/compose/install/) as well.

* [Getting Started](#getting-started)
* [See Also](#see-also)
* [Help](#help)
* [License](#license)
* [Credit](#credit)

## Getting Started

### Clone the Repository w/ Submodules

To install this example application, run the following commands:

```bash
git clone git@github.com:redis-developer/redi2read.git --recurse-submodule
```

### Import into your IDE

You can also import the code straight into your IDE:
* [Visual Studio Code](https://code.visualstudio.com/docs/languages/java)
* [Spring Tool Suite (STS)](https://spring.io/guides/gs/sts)
* [IntelliJ IDEA](https://spring.io/guides/gs/intellij-idea/)

### Start Redis and the Spring Boot Application

1. Start the Docker Compose application:

    ```
    cd redi2read/docker
    docker-compose up
    ```
2. Run the Spring Boot app to build the application.

    ```
    ./mvnw clean spring-boot:run
    ```

## See Also

Quick Tutorial on Redis' Powerful Modules:

* [RedisJSON Tutorial](https://developer.redislabs.com/howtos/redisjson)
* [RediSearch Tutorial](https://developer.redislabs.com/howtos/redisearch)
* [RedisGraph Tutorial](https://developer.redislabs.com/howtos/redisgraph)
* [Getting Started with Redis Streams and Java](https://redislabs.com/blog/getting-started-with-redis-streams-and-java/)
* [RedisBloom Tutorial](https://developer.redislabs.com/howtos/redisbloom)

The following links on Redis and Java may also be helpful:

* [Java and Redis](https://developer.redislabs.com/develop/java/)
* [Accessing Data Reactively with Redis](https://spring.io/guides/gs/spring-data-reactive-redis/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)

## Help

Please post any questions and comments on the [Redis Discord Server](https://discord.gg/redis), and remember to visit our [Redis Developer Page](https://developer.redislabs.com) for awesome tutorials, project and tips. You can also email me bsb@redislabs.com.

## License

[MIT Licence](http://www.opensource.org/licenses/mit-license.html)

## Credit

Created by [Brian Sam-Bodden](https://github.com/bsbodden) @ [Redis Labs](https://redislabs.com)