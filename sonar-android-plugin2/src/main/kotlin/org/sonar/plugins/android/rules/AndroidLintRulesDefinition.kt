package org.sonar.plugins.android.rules

import org.sonar.api.server.rule.RulesDefinition
import org.sonar.api.server.rule.RulesDefinitionXmlLoader
import org.sonar.plugins.android.foundation.REPOSITORY_KEY
import org.sonar.plugins.android.foundation.REPOSITORY_NAME
import org.sonar.plugins.android.foundation.RULES_XML_PATH
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class AndroidLintRulesDefinition constructor(
        private val xmlLoader: RulesDefinitionXmlLoader)
    : RulesDefinition {

    // AndoridLintRuleProcessorに変える
    override fun define(context: RulesDefinition.Context) {
        val repository: RulesDefinition.NewRepository =
                context.createRepository(REPOSITORY_KEY, "java")
                        .setName(REPOSITORY_NAME)

        InputStreamReader(javaClass.getResourceAsStream(RULES_XML_PATH), StandardCharsets.UTF_8)
                .use {
                    xmlLoader.load(repository, it)
                    repository.done()
                }
    }
}