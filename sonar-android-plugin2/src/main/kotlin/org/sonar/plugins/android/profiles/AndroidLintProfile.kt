package org.sonar.plugins.android.profiles

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition
import org.sonar.plugins.android.foundation.REPOSITORY_NAME
import org.sonar.plugins.android.foundation.logger
import org.sonar.plugins.android.rules.ruleKeys

class AndroidLintProfile : BuiltInQualityProfilesDefinition {
    override fun define(context: BuiltInQualityProfilesDefinition.Context) {
        logger.info("Android : profile definition Loaded")
        val profile: BuiltInQualityProfilesDefinition.NewBuiltInQualityProfile =
                context.createBuiltInQualityProfile(REPOSITORY_NAME, "java")
        profile.isDefault = true
        ruleKeys.forEach {
            val rule = profile.activateRule(it.repositoryKey, it.ruleId)
            rule.overrideSeverity(it.severity)
        }
        profile.done()
    }
}