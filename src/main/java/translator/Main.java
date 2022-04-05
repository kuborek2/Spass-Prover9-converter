package translator;
import gen.Prover9.Prover9Lexer;
import gen.Prover9.Prover9Parser;
import gen.SPASS.SpassLexer;
import gen.SPASS.SpassParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


import java.io.*;
import java.util.Scanner;

public class Main {


    private static String readFromInputStream(){
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
<<<<<<< HEAD:src/main/java/service/Main.java
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

    private static String readFromInputStreamtwo(){
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
            File currentDir = new File("C:\\projects\\SpassAndProver9\\src\\example\\prover9\\AlexandraHiestermann.prover9");
=======
            File currentDir = new File("C:\\Users\\pc\\Spass-Prover9-converter\\src\\main\\java\\SPASS_problem.spass");
>>>>>>> f712f4ee2850ebbf63115ee0145f7c456947da75:src/main/java/translator/Main.java
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

    private static String readFromInputStreamtwo(){
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
            File currentDir = new File("C:\\Users\\pc\\Spass-Prover9-converter\\src\\main\\java\\Prover9_problem.prover9");
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

    static public void main(String args[]) throws FileNotFoundException {


        String prover_data = readFromInputStreamtwo();
        String spass_data = readFromInputStream();

        Prover9Lexer prover9Lexer = new Prover9Lexer(CharStreams.fromString(prover_data));
        CommonTokenStream commonTokenStream = new CommonTokenStream(prover9Lexer);
        Prover9Parser prover9Parser = new Prover9Parser(commonTokenStream);
        ParseTree parseTree = prover9Parser.start_problem();

        System.out.println("\t\t Prover9 to Spass \n");

        var resulttwo = new StringBuilder();
        var visitortwo = new Prover9ToSpassVisitor(resulttwo);
        visitortwo.visit(parseTree);
        String stringAfterVisittwo = resulttwo.toString();


        String[] splittedTreetwo = stringAfterVisittwo.split(";");

        PrintWriter spass_out = new PrintWriter("SPAS_output.txt");
        String spass_output_text = "";

        for (String x : splittedTreetwo ){
            System.out.println(x);
            spass_output_text += x;
        }

        SpassLexer lexer = new SpassLexer(CharStreams.fromString(spass_data));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SpassParser parser = new SpassParser(tokens);
        ParseTree tree = parser.problem();

        System.out.println("\n\n\n\t\t Spass to Prover9 \n");

        var result = new StringBuilder();
        var visitor = new SpassToPower9Visitor(result);
        visitor.visit(tree);
        String stringAfterVisit = result.toString();


        PrintWriter prover_out = new PrintWriter("Prover9_output.txt");
        String prover9_output_text = "";

        String[] splittedTree = stringAfterVisit.split(";");
        for (String x : splittedTree ){
            System.out.println(x);
            prover9_output_text += x;
        }

        prover_out.println(prover9_output_text);
        spass_out.println(spass_output_text);
        prover_out.close();
        spass_out.close();





    }


}
