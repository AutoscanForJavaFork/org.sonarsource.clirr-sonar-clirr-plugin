package org.sonar.plugins.clirr;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;

@Properties( {
		@Property(key = "sonar.clirr.config.minSeverity", defaultValue = "warning", name = "Minimum severity to display for API breaks", description = "One of INFO, WARNING (default) or ERROR."),
		@Property(key = ClirrPlugin.CLIRR_KEY_COMPARISON_VERSION, name = "By default, the Clirr Maven Plugin compares the current code against the latest released version. Use this parameter, if you want to compare your code against a particular version", project = true, module = true, global = false),
		@Property(key = ClirrPlugin.CLIRR_KEY_EXECUTE, defaultValue = "false", name = "By default, the Clirr Maven Plugin is not activated. You need to explicitely activate it on any desired projects/modules.", project = true, module = true, global = false) })
public final class ClirrPlugin implements Plugin {

	public static final String CLIRR_PLUGIN_KEY = "clirr";
	public static final String CLIRR_PLUGIN_NAME = "CLIRR";
	public static final String CLIRR_RESULT_TXT = "target/clirr-result.txt";
	public static final String CLIRR_KEY_COMPARISON_VERSION = "sonar.clirr.config.comparisonVersion";
	public static final String CLIRR_KEY_EXECUTE = "sonar.clirr.config.execute";

	// This description will be displayed in the Configuration > Settings web
	// page
	public String getDescription() {
		return "The CLIRR plugin measures API breaks. Code breaks are categorized by severity: INFO, WARNING and ERROR.";
	}

	// This is where you're going to declare all your Sonar extensions
	public List<Class<? extends Extension>> getExtensions() {
		List<Class<? extends Extension>> list = new ArrayList<Class<? extends Extension>>();
		list.add(ClirrSensor.class);
		list.add(ClirrRulesRepository.class);
		list.add(ClirrMavenPluginHandler.class);
		return list;
	}

	// The key which uniquely identifies your plugin among all others Sonar
	// plugins
	public String getKey() {
		return CLIRR_PLUGIN_KEY;
	}

	public String getName() {
		return CLIRR_PLUGIN_NAME;
	}

	@Override
	public String toString() {
		return getKey();
	}
}