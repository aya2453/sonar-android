package org.sonar.plugins.android

import org.sonar.api.Plugin
import org.sonar.api.config.PropertyDefinition
import org.sonar.plugins.android.profiles.AndroidLintProfile
import org.sonar.plugins.android.rules.AndroidLintRulesDefinition
import org.sonar.plugins.android.sensor2.AndroidLintSensor


class AndroidLintPlugin : Plugin {
    override fun define(context: Plugin.Context) {
        context.let {
            it.addExtension(listOf(
                    AndroidLintSensor::class.java,
                    AndroidLintRulesDefinition::class.java,
                    AndroidLintProfile::class.java)
            )
            it.addExtension(
                    PropertyDefinition.builder("sonar.android.lint.report")
                            .name("Lint Report file")
                            .description("Path (absolute or relative) to the lint-results.xml file.")
                            .defaultValue("build/outputs/lint-results.xml")
            )
        }
    }
}