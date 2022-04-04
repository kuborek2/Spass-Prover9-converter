package service;
import codeGen.SpassLexer;
import codeGen.SpassParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static public void main(String args[]){

        String data = readFromInputStream();
//        System.out.println(data);

        SpassLexer lexer = new SpassLexer(CharStreams.fromString(data));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SpassParser parser = new SpassParser(tokens);
        ParseTree tree = parser.problem();

        System.out.println(" --------------------------------------------------------- ");

        var result = new StringBuilder();
        var visitor = new SpassToPower9Visitor(result);
        visitor.visit(tree);
        String stringAfterVisit = result.toString();


        String[] splittedTree = stringAfterVisit.split(";");
        for (String x : splittedTree ){
            System.out.println(x);
        }
        System.out.println(" --------------------------------------------------------- ");


    }

    private static String readFromInputStream(){
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
            File currentDir = new File("C:\\projects\\SpassAndProver9\\src\\example\\spass\\AlexandraHiestermann.spass");
            Scanner myReader = new Scanner(currentDir );
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                resultStringBuilder.append(data).append("\n");
            }
        myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return resultStringBuilder.toString();
    }

}
