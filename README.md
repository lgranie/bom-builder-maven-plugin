bom-builder-maven-plugin
========================

A Maven plugin to generate a dependency management POM, sometimes called a
BOM or bill of materials POM.  The plugin reads the set of dependencies in
the current project, and writes a new POM to "target/bom-pom.xml" which
contains a dependency management section listing the dependencies of
the current project.


Usage
-----
The plugin is configured in the "plugins" section of the pom.

    <plugins>
      <plugin>
        <groupId>com.github.dperezcabrera</groupId>
        <artifactId>bom-builder-maven-plugin</artifactId>
        <version>1.2.1</version>
        <executions>
          <execution>
            <id>build-bom</id>
            <goals>
              <goal>build-bom</goal>
            </goals>
            <configuration>
              <bomGroupId>org.jboss.bom</bomGroupId>
              <bomArtifactId>my-artifacts-bom</bomArtifactId>
              <bomVersion>1.0.0</bomVersion>
              <bomName>My Artifacts BOM</bomName>
              <bomDescription>My Artifacts BOM</bomDescription>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>


With no configuration usage
---------------------------
Example without configuration:

	mvn com.github.lgranie:bom-builder-maven-plugin:1.0.0-SNAPSHOT:build-bom [-DbomGroupId=your-group] [-DbomArtifactId=your-artifact] [-DbomVersion=0.0.1]

Config Parameters
-----------------

|               Config                |                                Description                                |
|:------------------------------------|:--------------------------------------------------------------------------|
| bomGroupId                          | The groupId to set in the generated BOM                                   |
| bomArtifactId                       | The artifactId to set in the generated BOM                                |
| bomVersion                          | The version to set in the generated BOM                                   |
| bomName                             | The name to set in the generated BOM                                      |
| bomDescription                      | The description to set in the generated BOM                               |
| exclusions                          | A list of exclusions to set in the generated BOM                          |
| dependencyExclusions                | A list of dependencies which should not be included in the generated BOM  |
| useDependencies                     | Use only defined dependencies (default: true)                            |
| useDependencyManagementDependencies | Use dependency management dependencies (default: false)                   |

Each exclusion should contain four parameters:

- dependencyGroupId
- dependencyArtifactId
- exclusionGroupId
- exclusionArtifactId

Each dependency exclusion should contain two parameters:

- groupId
- artifactId

Exclusion Config Example
-------------------

    <configuration>
      <bomGroupId>org.test</bomGroupId>
      <bomArtifactId>junit-bom</bomArtifactId>
      <bomVersion>1.0</bomVersion>
      <exclusions>
        <exclusion>
          <dependencyGroupId>junit</dependencyGroupId>
          <dependencyArtifactId>junit</dependencyArtifactId>
          <exclusionGroupId>org.hamcrest</exclusionGroupId>
          <exclusionArtifactId>hamcrest</exclusionArtifactId>
        </exclusion>
      </exclusions>
    </configuration>

The above config will result in POM output that looks similar to the following:

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8</version>
      <exclusions>
        <exclusion>
          <artifactId>hamcrest</artifactId>
          <groupId>org.hamcrest</groupId>
        </exclusion>
      </exclusions>
    </dependency>

Dependency Exclusion Config Example
-------------------

    <configuration>
      <bomGroupId>org.test</bomGroupId>
      <bomArtifactId>junit-bom</bomArtifactId>
      <bomVersion>1.0</bomVersion>
      <dependencyExclusions>
        <dependencyExclusion>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
        </dependencyExclusion>
      </dependencyExclusions>
    </configuration>

The above config will result in POM which will not contain junit dependency

You can use * for value of artifactId (or groupId) to exclude all dependencies with given groupId and any artifactId
(or with given artifactId and any groupId)

Using properties for version
----------------------------

    <configuration>
      <bomGroupId>org.test</bomGroupId>
      <bomArtifactId>junit-bom</bomArtifactId>
      <bomVersion>1.0</bomVersion>
      <usePropertiesForVersion>true</usePropertiesForVersion>
    </configuration>

The above config will result in POM where version of dependencies is specified via properties.
