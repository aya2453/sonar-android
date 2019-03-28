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
package org.sonar.plugins.android.lint;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class AndroidLintRulesDefinition implements RulesDefinition {

  public static final String REPOSITORY_KEY = "android-lint";
  public static final String REPOSITORY_NAME = "Android Lint";
  public static final String RULES_XML_PATH = "/org/sonar/plugins/android/lint/rules.xml";
  private static final Logger LOGGER = Loggers.get(AndroidLintRulesDefinition.class);

  private RulesDefinitionXmlLoader xmlLoader;

  public AndroidLintRulesDefinition(RulesDefinitionXmlLoader xmlLoader) {
    this.xmlLoader = xmlLoader;
  }

  @Override
  public void define(Context context) {
    try (Reader reader = new InputStreamReader(getClass().getResourceAsStream(RULES_XML_PATH), StandardCharsets.UTF_8)) {
      NewRepository repository = context.createRepository(REPOSITORY_KEY, "java").setName(REPOSITORY_NAME);
      xmlLoader.load(repository, reader);
      repository.done();
    } catch (IOException e) {
      LOGGER.warn(String.format("Fail to read file %s", RULES_XML_PATH), e);
    }
  }
}
