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
package org.sonar.plugins.android.sensor;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.File;
import java.util.List;

class AndroidLintProcessor {

  private static final Logger LOGGER = Loggers.get(AndroidLintProcessor.class);

  private AndroidLintProcessor() {
  }

  public static void process(final File lintXml, final SensorContext context) {
    Serializer serializer = new Persister();
    try {
      LOGGER.info("Processing android sensor report: " + lintXml.getPath());
      serializer.read(LintIssues.class, lintXml).issues
        .stream()
        .filter(lintIssue ->
          context.activeRules().find(RuleKey.of(AndroidLintRulesDefinition.REPOSITORY_KEY, lintIssue.id)) != null
        ).forEach(
        lintIssue ->
          lintIssue.locations.forEach(
            locations -> processIssueForLocation(lintIssue, locations, context)
          ));

    } catch (Exception e) {
      LOGGER.error("Exception reading " + lintXml.getPath(), e);
    }
  }

  private static void processIssueForLocation(LintIssue lintIssue, LintLocation lintLocation, SensorContext context) {

    FilePredicates predicates = context.fileSystem().predicates();
    InputFile inputFile = context.fileSystem().inputFile(predicates.and(predicates.hasRelativePath(lintLocation.file)));
    if (inputFile != null) {
      LOGGER.debug("Processing File {} for Issue {}", lintLocation.file, lintIssue.id);
      RuleKey ruleKey = RuleKey.of(AndroidLintRulesDefinition.REPOSITORY_KEY, lintIssue.id);
      NewIssue newIssue = context.newIssue()
        .forRule(ruleKey);

      NewIssueLocation primaryLocation = newIssue.newLocation()
        .on(inputFile)
        .message(lintIssue.message);
      if (lintLocation.line > 0) {
        primaryLocation.at(inputFile.selectLine(lintLocation.line));
      }
      newIssue.at(primaryLocation);

      newIssue.save();

    }
    LOGGER.warn("Unable to find file {} to report issue", lintLocation.file);
  }

  @Root(name = "location", strict = false)
  private static class LintLocation {
    @Attribute
    String file;
    @Attribute(required = false)
    Integer line;
  }

  @Root(name = "issues", strict = false)
  private static class LintIssues {
    @ElementList(required = false, inline = true, empty = false)
    List<LintIssue> issues;
  }

  @Root(name = "issue", strict = false)
  private static class LintIssue {
    @Attribute
    String id;
    @Attribute
    String message;
    @ElementList(inline = true)
    List<LintLocation> locations;
  }

}
