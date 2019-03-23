package org.sonar.plugins.android.rules

import org.sonar.api.server.rule.RulesDefinition
import org.sonar.api.server.rule.RulesDefinitionXmlLoader
import org.sonar.plugins.android.foundation.REPOSITORY_KEY
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals


object AndroidLintRulesDefinitionTest : Spek({
    describe("Android Lint Rules Definition のテスト") {
        val ruleDefinition = AndroidLintRulesDefinition(RulesDefinitionXmlLoader())
        val context = RulesDefinition.Context()
        ruleDefinition.define(context)
        val repository = context.repository(REPOSITORY_KEY)
        it("repository") {
            assertEquals(158, 158)
            (repository as RulesDefinition.ExtendedRepository).rules()
            .forEach {
                println("repoKey=${it.key()}, ruleKey=${it.name()}")
            }
        }
    }
})