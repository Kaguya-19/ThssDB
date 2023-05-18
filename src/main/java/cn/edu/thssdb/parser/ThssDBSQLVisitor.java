/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.thssdb.parser;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.*;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.sql.SQLBaseVisitor;
import cn.edu.thssdb.sql.SQLParser;
import org.apache.commons.logging.Log;

import java.util.ArrayList;

public class ThssDBSQLVisitor extends SQLBaseVisitor<LogicalPlan> {
  private Manager manager;

  @Override
  public LogicalPlan visitQuitStmt(SQLParser.QuitStmtContext ctx) {
    return new QuitPlan();
  }

  @Override
  public LogicalPlan visitCreateDbStmt(SQLParser.CreateDbStmtContext ctx) {
    return new CreateDatabasePlan(ctx.databaseName().getText());
  }

  @Override
  public LogicalPlan visitDropDbStmt(SQLParser.DropDbStmtContext ctx) {
    return new DropDatabasePlan(ctx.databaseName().getText());
  }

  @Override
  public LogicalPlan visitUseDbStmt(SQLParser.UseDbStmtContext ctx) {
    return new UseDatabasePlan(ctx.databaseName().getText());
  }

  @Override
  public LogicalPlan visitCreateTableStmt(SQLParser.CreateTableStmtContext ctx) {
    ArrayList<Column> columns = new ArrayList<>();
    ArrayList<String> columnName = new ArrayList<>();

    for (SQLParser.ColumnDefContext context : ctx.columnDef()) {
      String name = context.columnName().getChild(0).toString();
      String type = context.typeName().getChild(0).toString();

      int maxLength = 0;
      if (type.toUpperCase().equals("STRING")) {
        maxLength = Integer.parseInt(context.typeName().getChild(2).toString());
      }
      boolean notNull = context.getChildCount() > 2 ? true : false;
      columns.add(new Column(name, Column.getType(type), 0, notNull, maxLength));
      columnName.add(name);
    }
    // only one primary key, so the efficiency is considerable
    if (ctx.tableConstraint() != null) {
      for (SQLParser.ColumnNameContext context : ctx.tableConstraint().columnName()) {
        String name = context.getChild(0).toString();
        columns.get(columnName.indexOf(name)).setPrimary(1);
      }
    }
    return new CreateTablePlan(ctx.tableName().getText(), columns);
  }

  @Override
  public LogicalPlan visitDropTableStmt(SQLParser.DropTableStmtContext ctx) {
    return new DropTablePlan(ctx.tableName().getText());
  }

  @Override
  public LogicalPlan visitShowTableStmt(SQLParser.ShowTableStmtContext ctx) {
    return new ShowTablePlan(ctx.databaseName().getText());
  }





  // TODO: parser to more logical plan
}
