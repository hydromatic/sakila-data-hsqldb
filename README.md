<!--
{% comment %}
Licensed to Julian Hyde under one or more contributor license
agreements.  See the NOTICE file distributed with this work
for additional information regarding copyright ownership.
Julian Hyde licenses this file to you under the Apache
License, Version 2.0 (the "License"); you may not use this
file except in compliance with the License.  You may obtain a
copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied.  See the License for the specific
language governing permissions and limitations under the
License.
{% endcomment %}
-->
[![Build Status](https://github.com/hydromatic/sakila-data-hsqldb/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/hydromatic/sakila-data-hsqldb/actions?query=branch%3Amain)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.hydromatic/sakila-data-hsqldb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.hydromatic/sakila-data-hsqldb)
[![javadoc](https://javadoc.io/badge2/net.hydromatic/sakila-data-hsqldb/javadoc.svg)](https://javadoc.io/doc/net.hydromatic/sakila-data-hsqldb)

# Sakila Data Set in HSQLDB Format

## Overview

The sakila-data-hsqldb project provides the Sakila dataset as an
embedded HSQLDB database.

The Sakila database is a sample database that models a DVD rental
store business. It was originally developed by Mike Hillyer of the
MySQL AB documentation team as an
[example database for MySQL](https://dev.mysql.com/doc/sakila/en/).

## Schema Structure

The database contains 16 tables:

| Table         |   Rows | Columns |
|---------------|-------:|--------:|
| ACTOR         |    200 |       4 |
| ADDRESS       |    603 |       8 |
| CATEGORY      |     16 |       3 |
| CITY          |    600 |       4 |
| COUNTRY       |    109 |       3 |
| CUSTOMER      |    599 |       9 |
| FILM          |  1,000 |      13 |
| FILM_ACTOR    |  5,462 |       3 |
| FILM_CATEGORY |  1,000 |       3 |
| FILM_TEXT     |      0 |       3 |
| INVENTORY     |  4,581 |       4 |
| LANGUAGE      |      6 |       3 |
| PAYMENT       | 16,049 |       7 |
| RENTAL        | 16,044 |       7 |
| STAFF         |      2 |      11 |
| STORE         |      2 |       4 |

The database is approximately 4.6 MB, compressing to
about 629 KB.

![Sakila schema diagram](sakila.png)

(Schema diagram courtesy of [jOOQ](https://www.jooq.org/).)

## Getting Started

### Maven Integration

Add the following dependency to your Maven `pom.xml` file:

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>sakila-data-hsqldb</artifactId>
  <version>0.1</version>
</dependency>
```

Note: `sakila-data-hsqldb` supports HSQLDB 2.0.0 and higher, and Java
8 and higher. HSQLDB 2.6.0 and higher requires
[Java 11 and higher](http://hsqldb.org/doc/2.0/changelist_2_0.txt).

### Java Connection Example

Connect using the JDBC URL `jdbc:hsqldb:res:sakila` with username
"sakila" and password "p_ssW0rd":

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.hydromatic.sakila.data.hsqldb.SakilaHsqldb;

Connection connection =
  DriverManager.getConnection(SakilaHsqldb.URI,
      SakilaHsqldb.USER, SakilaHsqldb.PASSWORD);
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(
    "SELECT \"film_id\", \"title\", \"release_year\" FROM \"FILM\" LIMIT 5");
```

### Using SQLLine

Connect from the command line using the
[SQLLine](https://github.com/julianhyde/sqlline) shell:

```
$ ./sqlline
sqlline version 1.12.0
sqlline> !connect jdbc:hsqldb:res:sakila SAKILA SAKILA
0: jdbc:hsqldb:res:sakila> SELECT "film_id", "title", "release_year"
. . . . . . . . . . . . .> FROM "FILM" LIMIT 5;
+---------+------------------+--------------+
| film_id |      title       | release_year |
+---------+------------------+--------------+
| 1       | ACADEMY DINOSAUR | 2006         |
| 2       | ACE GOLDFINGER   | 2006         |
| 3       | ADAPTATION HOLES | 2006         |
| 4       | AFFAIR PREJUDICE | 2006         |
| 5       | AFRICAN EGG      | 2006         |
+---------+------------------+--------------+
5 rows selected (0.002 seconds)
```

You may need to edit the `sqlline` or `sqlline.bat` launcher script,
adding `sakila-data-hsqldb.jar` to your class path.

### Download and build

Use Java version 11 or higher.

```bash
git clone https://github.com/hydromatic/sakila-data-hsqldb.git
cd sakila-data-hsqldb
./mvnw install
```

On Windows, the last line is

```bash
> mvnw install
```

## Related Resources

Similar data sets:
* [chinook-data-hsqldb](https://github.com/julianhyde/chinook-data-hsqldb)
* [flight-data-hsqldb](https://github.com/julianhyde/flight-data-hsqldb)
* [foodmart-data-hsqldb](https://github.com/julianhyde/foodmart-data-hsqldb)
* [foodmart-data-json](https://github.com/julianhyde/foodmart-data-json)
* [look-data-hsqldb](https://github.com/hydromatic/look-data-hsqldb)
* [scott-data-hsqldb](https://github.com/julianhyde/scott-data-hsqldb)
* [steelwheels-data-hsqldb](https://github.com/julianhyde/steelwheels-data-hsqldb)

Lukas Eder at the [jOOQ](https://www.jooq.org/) project has
[ported Sakila](https://github.com/jOOQ/sakila) to IBM DB2, Oracle,
Postgres and other databases.

## More Information

* **License:** Apache License, Version 2.0
* **Author:** [Julian Hyde](https://github.com/julianhyde)
* **Blog:** http://blog.hydromatic.net
* **Source code:** https://github.com/hydromatic/sakila-data-hsqldb
* **Distribution:**
  [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22sakila-data-hsqldb%22)
* **Developers list:**
  [dev@calcite.apache.org](mailto:dev@calcite.apache.org)
  ([archive](https://mail-archives.apache.org/mod_mbox/calcite-dev/),
  [subscribe](mailto:dev-subscribe@calcite.apache.org))
* **Issues:** https://github.com/hydromatic/sakila-data-hsqldb/issues
* **Release notes:** [HISTORY.md](HISTORY.md)
