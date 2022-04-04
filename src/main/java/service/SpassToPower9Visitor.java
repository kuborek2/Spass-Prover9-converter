package service;

import codeGen.SpassParser;
import codeGen.SpassParserBaseListener;
import codeGen.SpassParserBaseVisitor;

public class SpassToPower9Visitor extends SpassParserBaseVisitor {

    private final StringBuilder stringBuilder;

    SpassToPower9Visitor(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public Object visitFormula_list(SpassParser.Formula_listContext ctx) {

        String formula_type = ctx.origin_type().getText();
        switch(formula_type){
            case "axioms":
                translateAxioms(ctx);
                break;

            case "conjectures":
                translateConjectures(ctx);
                break;

            default:
        }
        return null;
    }

    private void translateAxioms(SpassParser.Formula_listContext ctx){
        stringBuilder.append("formulas(assumptions).\n");
        super.visitFormula_list(ctx);
        stringBuilder.append("end_of_list.\n");
    }

    private void translateConjectures(SpassParser.Formula_listContext ctx){
        stringBuilder.append("formulas(goals).\n");
        stringBuilder.append("z(x)\n");
        super.visitFormula_list(ctx);
        stringBuilder.append("end_of_list.\n");
    }

    @Override
    public Object visitTerm(SpassParser.TermContext ctx) {

        if( ctx.quant_sym() != null ) {
            if (ctx.quant_sym().getText() == "forall")
                return super.visitTerm(ctx);
        } else if ( ctx.symbol() != null ){
            if( ctx.symbol().Implies() != null ) {
                stringBuilder.append("y(x)\n");
                stringBuilder.append(ctx.term(0).getText() + " -> " + ctx.term(1).getText() + "\n");
                return super.visitTerm(ctx);
            }
        }
        return super.visitTerm(ctx);
    }



//    @Override
//    public Object visitIdentifier(SpassParser.IdentifierContext ctx) {
//        stringBuilder.append(ctx.getText());
//        return super.visitIdentifier(ctx);
//    }


}
