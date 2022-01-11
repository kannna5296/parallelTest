package com.example.parallelTest;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.operation.DatabaseOperationLookup;
import com.github.springtestdbunit.operation.MicrosoftSqlDatabaseOperationLookup;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.CompositeOperation;

public class CustomDatabaseOperationLookup extends MicrosoftSqlDatabaseOperationLookup {
}
