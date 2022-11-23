package org.mauricio;

import oracle.jdbc.driver.OracleDriver;
import org.apache.ibatis.jdbc.ScriptRunner;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "queryExecutor", mixinStandardHelpOptions = true, version = "queryExecutor 1.0",
        description = "Execute a query file provide by args on a oracle database")
public class QueryExecutor implements Callable<Integer>  {

    @CommandLine.Option(names = {"-ju", "--jdbcUrl"}, description = "jdbc:oracle:thin:@localhost:1521:xe")
    private String jdbcUrl;

    @CommandLine.Option(names = {"-u", "--username"}, description = "any")
    private String username;

    @CommandLine.Option(names = {"-p", "--password"}, description = "any")
    private String password;

    @CommandLine.Option(names = {"-fp", "--filePath"}, description = "any")
    private String filePath;

    @Override
    public Integer call() throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new OracleDriver());
        //Getting the connection
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        sr.setAutoCommit(true);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader(filePath));
        //Running the script
        long start = System.currentTimeMillis();
        try {
            sr.runScript(reader);
        } finally {
            sr.closeConnection();
        }
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds");
        return 0;
    }
}
