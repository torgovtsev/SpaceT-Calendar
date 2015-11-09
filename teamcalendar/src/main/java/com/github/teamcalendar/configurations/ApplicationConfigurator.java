package com.github.teamcalendar.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Base class for Spring framework.
 * 
 * @author storgovt
 *
 */
@Configuration
@ComponentScan(scopedProxy = ScopedProxyMode.INTERFACES, basePackages = { "com.github.teamcalendar" })
public class ApplicationConfigurator
{

}
