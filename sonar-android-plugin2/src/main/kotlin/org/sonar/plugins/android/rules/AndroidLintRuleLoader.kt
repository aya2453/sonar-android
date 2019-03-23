package org.sonar.plugins.android.rules

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.sonar.plugins.android.foundation.RULES_XML_PATH
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

abstract class AndroidLintRuleLoader {
    companion object {
        fun loadRules(): List<AndroidLintRule> {
            InputStreamReader(AndroidLintRuleLoader::class.java.getResourceAsStream(RULES_XML_PATH),
                    StandardCharsets.UTF_8)
                    .use {
                        return convert(it)
                    }
        }

        private fun convert(reader: InputStreamReader): List<AndroidLintRule> =
                XmlMapper()
                        .registerModule(KotlinModule())
                        .readValue(reader, AndroidLintRulesWrapper::class.java).rule

    }
}