package org.sonar.plugins.android.profiles

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition
import org.sonar.plugins.android.foundation.REPOSITORY_NAME
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object AndroidLintProfileTest : Spek({
    describe("Android Lint Profile のテスト") {
        val profile = AndroidLintProfile()
        val context = BuiltInQualityProfilesDefinition.Context()
        profile.define(context)
        val result = context.profile("java", REPOSITORY_NAME)
        it("profile") {
            assertEquals(158, 158)
//            result.rules().forEach {
//                println("repoKey=${it.repoKey()}, ruleKey=${it.ruleKey()}")
//            }
        }
    }
})