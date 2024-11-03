/**
 * 
 */
package com.rohan.grocery_booking.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author rrohan419@gmail.com
 */
@EnableAsync
@Configuration
public class AppConfig {

	/**
	 * Model mapper bean used to perform object mapping
	 * 
	 * @author rrohan419@gmail.com
	 * @return {@link ModelMapper}
	 */
	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper;
	}
}
