package org.sonar.plugins.android.foundation

import org.sonar.api.utils.log.Logger
import org.sonar.api.utils.log.Loggers
import org.sonar.plugins.android.AndroidLintPlugin


const val ANDROID_LINT_SENSOR = "Android Lint Sensor"
const val REPOSITORY_KEY = "android-lint"
const val REPOSITORY_NAME = "Android Lint"
const val RULES_XML_PATH = "/org/sonar/plugins/android/lint/rules.xml"
const val PROFILE_XML_PATH = "/org/sonar/plugins/android/lint/android_lint_sonar_way.xml"
val supportLanguages = arrayOf("kotlin", "java", "xml")
val logger: Logger by lazy { Loggers.get(AndroidLintPlugin::class.java) }