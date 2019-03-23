package org.sonar.plugins.android.rules

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

data class AndroidLintRule(val key: String,
                           val severity: String,
                           val name: String,
                           val description: String,
                           val string: String)

data class AndroidLintRulesWrapper(@JacksonXmlElementWrapper(useWrapping = false)
                                   val rule: List<AndroidLintRule>)

data class AndroidLintRuleKey(val repositoryKey: String,
                              val ruleId: String,
                              val severity: String)