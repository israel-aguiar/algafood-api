package com.algaworks.algafood.core.log;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("logging.loggly")
public class LogglyProperties {

	private String token;
}
