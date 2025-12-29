FROM gradle:9.2-jdk21-alpine AS builder

WORKDIR /app

COPY commons ./commons

RUN gradle publishToMavenLocal -x test --no-daemon -p commons

COPY insight/build.gradle.kts insight/settings.gradle.kts ./

RUN gradle dependencies --no-daemon

COPY insight/src ./src

RUN gradle clean build -x test --no-daemon

RUN cp build/libs/insight-0.0.1-SNAPSHOT.jar insight.jar

RUN jar xf insight.jar

RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    insight.jar > deps.info

FROM eclipse-temurin:21-jdk-noble AS jre-builder

COPY --from=builder /app/deps.info .

RUN $JAVA_HOME/bin/jlink \
    --verbose \
    --compress=2 \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --add-modules $(cat deps.info) \
    --add-modules java.base \
    --add-modules jdk.crypto.ec \
    --output /jdk-21

FROM debian:12-slim

RUN apt-get update && apt-get install -y curl

ARG OWNER=raijin
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

RUN addgroup --system $OWNER && \
    adduser --system $OWNER --ingroup $OWNER

WORKDIR /app
RUN chown -R $OWNER:$OWNER /app

COPY --from=jre-builder /jdk-21 $JAVA_HOME

COPY --from=builder --chown=$OWNER:$OWNER /app/insight.jar .

USER $OWNER

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "insight.jar"]