# oracle-sql-query-executor

Execute a query file provide by args on a oracle database

## Instructions

### Execute a SQL file like this
```
java -jar .\oracle-sql-query-executor-1.0-SNAPSHOT.jar -ju <jdbc url such as jdbc:oracle:thin:@localhost:1521:xe> -u <databaseUsername> -p <databasePassword> -fp <absolute path sql file>
```
