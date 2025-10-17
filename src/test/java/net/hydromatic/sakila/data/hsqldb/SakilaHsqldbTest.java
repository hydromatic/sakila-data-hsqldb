/*
 * Licensed to Julian Hyde under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership. Julian Hyde
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hydromatic.sakila.data.hsqldb;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Sakila HSQLDB database
 */
public class SakilaHsqldbTest {
  @Test public void testConnection() throws SQLException {
    final Connection connection = DriverManager.getConnection(SakilaHsqldb.URI,
        SakilaHsqldb.USER, SakilaHsqldb.PASSWORD);
    final Statement statement = connection.createStatement();

    // Test main tables exist and have data
    assertRowCount(statement, "language", 6);
    assertRowCount(statement, "country", 109);
    assertRowCount(statement, "actor", 200);
    assertRowCount(statement, "film", 1000);
    assertRowCount(statement, "film_actor", 5462);
    assertRowCount(statement, "customer", 599);
    assertRowCount(statement, "rental", 16044);
    assertRowCount(statement, "payment", 16049);

    // Test a simple query to verify data exists
    final ResultSet resultSet = statement.executeQuery(
        "SELECT \"film_id\", \"title\", \"rental_rate\" " +
        "FROM \"FILM\" " +
        "LIMIT 5");

    int count = 0;
    while (resultSet.next()) {
      count++;
      int filmId = resultSet.getInt("film_id");
      String title = resultSet.getString("title");
      double rentalRate = resultSet.getDouble("rental_rate");
      assertTrue("Film ID should be positive", filmId > 0);
      assertTrue("Title should not be null", title != null && !title.isEmpty());
      assertTrue("Rental rate should be positive", rentalRate > 0);
    }
    assertTrue("Expected at least 5 films, got " + count, count >= 5);

    resultSet.close();
    statement.close();
    connection.close();
  }

  private void assertRowCount(Statement statement, String tableName, int expectedCount)
      throws SQLException {
    final ResultSet rs = statement.executeQuery(
        "SELECT COUNT(*) FROM \"" + tableName.toUpperCase() + "\"");
    assertTrue(rs.next());
    assertEquals(expectedCount, rs.getInt(1));
    rs.close();
  }
}

// End SakilaHsqldbTest.java
