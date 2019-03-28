/*
 * SonarQube Android Lint Plugin
 * Copyright (C) 2013-2016 SonarSource SA and Jerome Van Der Linden, Stephane Nicolas, Florian Roncari, Thomas Bores
 * sonarqube@googlegroups.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.android;

import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.plugins.android.lint.*;


public class AndroidPlugin implements Plugin {

  public static class Property {
    public static final String LINT_REPORT = "sonar.android.lint.report";
    private static final String LINT_REPORT_DEFAULT = "build/outputs/lint-results.xml";
    private static final String NAME = "Lint Report file";
    private static final String DESCRIPTION = "Path (absolute or relative) to the lint-results.xml file.";
  }

  @Override
  public void define(Context context) {
    context.addExtensions(
      AndroidLintSensor.class,
      AndroidLintRulesDefinition.class,
      AndroidLintSonarWay.class,
      AndroidLintProfileExporter.class,
      AndroidLintProfileImporter.class
    );

    context.addExtension(
      PropertyDefinition.builder(Property.LINT_REPORT)
        .name(Property.NAME)
        .description(Property.DESCRIPTION)
        .defaultValue(Property.LINT_REPORT_DEFAULT)
        .build()
    );
  }
}
