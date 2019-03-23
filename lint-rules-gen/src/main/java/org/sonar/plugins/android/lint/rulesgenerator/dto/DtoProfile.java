/*
 * SonarQube Android Lint Rules Generator
 * Copyright (C) 2015-2016 SonarSource SA and Jordan Hansen
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
package org.sonar.plugins.android.sensor.rulesgenerator.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Profile")
public class DtoProfile {
  @Element
  private String name;
  @Element
  private String language;
  @Path("rules")
  @ElementList(inline=true)
  private List<DtoProfileRule> rules;

  public DtoProfile() {
    rules = new ArrayList<DtoProfileRule>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public List<DtoProfileRule> getRules() {
    return rules;
  }

  public void setRules(List<DtoProfileRule> rules) {
    this.rules = rules;
  }

  public void addRule(String repositoryKey, String key) {
    DtoProfileRule rule = new DtoProfileRule();
    rule.setRepositoryKey(repositoryKey);
    rule.setKey(key);
    this.rules.add(rule);
  }
}
