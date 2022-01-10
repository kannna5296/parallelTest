package com.example.parallelTest;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.operation.DatabaseOperationLookup;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.CompositeOperation;

public class CustomDatabaseOperationLookup implements DatabaseOperationLookup {

    @Override
    public org.dbunit.operation.DatabaseOperation get(DatabaseOperation operation) {
        switch (operation) {
            case UPDATE:
                return org.dbunit.operation.DatabaseOperation.UPDATE;
            case INSERT:
                return org.dbunit.operation.DatabaseOperation.INSERT;
            case REFRESH:
                return org.dbunit.operation.DatabaseOperation.REFRESH;
            case DELETE:
                return org.dbunit.operation.DatabaseOperation.DELETE;
            case DELETE_ALL:
                return org.dbunit.operation.DatabaseOperation.DELETE_ALL;
            case TRUNCATE_TABLE:
                return org.dbunit.operation.DatabaseOperation.TRUNCATE_TABLE;
            case CLEAN_INSERT:
                return new CompositeOperation(
                        org.dbunit.operation.DatabaseOperation.DELETE_ALL, new InsertIdentityOperation(org.dbunit.operation.DatabaseOperation.INSERT));
            default:
                return null;
        }
    }
}
