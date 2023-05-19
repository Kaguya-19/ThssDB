package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.schema.Column;

public class ShowTablePlan extends LogicalPlan {
    private String databaseName;
    public ShowTablePlan(String databaseName) {
        super(LogicalPlanType.SHOW_TABLE);
        this.databaseName = databaseName;
    }
    @Override
    public String toString() {
        return "ShowTablePlan{" + databaseName + "}";
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
