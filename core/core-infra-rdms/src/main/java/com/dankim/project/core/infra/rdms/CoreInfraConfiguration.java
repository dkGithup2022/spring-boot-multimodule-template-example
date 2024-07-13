package com.dankim.project.core.infra.rdms;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@ComponentScan
@EnableAutoConfiguration
public class CoreInfraConfiguration {
}
