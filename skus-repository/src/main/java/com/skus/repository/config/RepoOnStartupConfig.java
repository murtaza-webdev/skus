package com.skus.repository.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RepoOnStartupConfig
{

	private static final Logger LOGGER = LoggerFactory.getLogger(RepoOnStartupConfig.class);

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationStartup()
	{
		LOGGER.info("### STARTED RepoOnStartupConfig.onApplicationStartup");
		LOGGER.info("### END RepoOnStartupConfig.onApplicationStartup");
	}
}
