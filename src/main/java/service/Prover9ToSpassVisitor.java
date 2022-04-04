package service;

import codeGen.Prover9BaseVisitor;
import codeGen.Prover9Parser;

public class Prover9ToSpassVisitor extends Prover9BaseVisitor {

    private final StringBuilder stringBuilder;
    private int counter;

    Prover9ToSpassVisitor(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        this.counter = 1;
    }

    @Override
    public Object visitStart_problem(Prover9Parser.Start_problemContext ctx) {
        stringBuilder.append("begin_problem(AlexandraHiestermann).\n");
        super.visitStart_problem(ctx);
        stringBuilder.append("end_problem.");
        return null;
    }

    @Override
    public Object visitAsumptions(Prover9Parser.AsumptionsContext ctx) {
        stringBuilder.append("list_of_formulae(axioms).\n" +
                "\n" +
                "formula(y(x),1).\n" +
                "formula(forall([i],implies(y(x),z(x))),2).\n" +
                "\n" +
                "end_of_list.");
        return super.visitAsumptions(ctx);
    }

    @Override
    public Object visitGoals(Prover9Parser.GoalsContext ctx) {
        stringBuilder.append("list_of_formulae(conjectures).\n"+
                "formula(z(x),3).\n"+
                "end_of_list.\n");
        return super.visitGoals(ctx);
    }
}
