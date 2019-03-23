package org.sonar.plugins.android.sensor2

import org.sonar.api.batch.sensor.Sensor
import org.sonar.api.batch.sensor.SensorContext
import org.sonar.api.batch.sensor.SensorDescriptor
import org.sonar.plugins.android.foundation.ANDROID_LINT_SENSOR
import org.sonar.plugins.android.foundation.logger
import org.sonar.plugins.android.foundation.supportLanguages


class AndroidLintSensor : Sensor {

    override fun describe(descriptor: SensorDescriptor) {
        descriptor.name(ANDROID_LINT_SENSOR).onlyOnLanguages(*supportLanguages)
    }

    override fun execute(context: SensorContext) {
        logger.info("Sensor Executed")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}