package org.sonar.plugins.android.rules

import org.sonar.plugins.android.foundation.REPOSITORY_KEY

private fun defineRuleKey(rule: AndroidLintRule) =
        AndroidLintRuleKey(REPOSITORY_KEY, rule.key, rule.severity)

val ruleKeys = AndroidLintRuleLoader.loadRules().map {
    defineRuleKey(it)
}
