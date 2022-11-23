package org.mauricio;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new QueryExecutor()).execute(args);
        System.exit(exitCode);
    }
}