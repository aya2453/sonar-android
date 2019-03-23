package org.sonar.plugins.android.rules

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals


object AndroidLintRuleLoaderTest : Spek({
    describe("Android Lint Rule Loaderのテスト") {
        val results = AndroidLintRuleLoader.loadRules()
        it("the number of rules is 122") {
            assertEquals(158, results.count())
        }
    }
})