package at.tuwien.ac.graph.labeled;

import com.google.common.base.Optional;
import org.gerryai.planning.model.domain.*;
import org.gerryai.planning.model.logic.*;
import org.gerryai.planning.model.problem.Problem;
import org.gerryai.planning.parser.error.ParseException;
import org.gerryai.planning.parser.pddl.PDDLParserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Set;

/**
 * Created by root on 7/13/17.
 */
public class Parser {
    public static void main(String[] args) {
        PDDLParserService parserService = new PDDLParserService();

        try {
            String fileName = "/home/e1528895/Workspace/VertexIntegrityProblem/GraphDecomposition/samples/planning/seq-agl/Barman/domain.pddl";
            Domain domain = parserService.parseDomain(new FileInputStream(fileName));
            System.out.println("Domain Name:\t"+domain.getName());
            Actions actions = domain.getActions();
            Set<Action> actionSet = actions.asSet();
            for(Action action: actionSet) {
                System.out.println("Action:\t"+action.getName());
                Precondition precondition = action.getPrecondition();
                Optional<Formula> optional = precondition.getPrecondition();
                Set<Formula> formulas = optional.asSet();
                System.out.println("\tPreconditions:");
                for(Formula formula: formulas) {
                    if(formula instanceof And) {
                        And and = (And) formula;
                        getInfoOf(and);
                    }
                    else
                        System.out.println("\t" + formula + "\t"+formula.getClass().getName());
                }
                System.out.println();
                System.out.println("\tEffects:");
                Optional<Formula> effect = action.getEffect().getEffect();
                Set<Formula> set = effect.asSet();
                for(Formula formula : set){
                    if(formula instanceof And){
                        And and = (And) formula;
                        getInfoOf(and);
                    }else
                        System.out.println(formula.getClass().getName());
                }


                //System.out.println("\teffect:\t"+ effect);
                System.out.println();
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void getInfoOf(And and) {
        List<Formula> list = and.asList();
        for(Formula item: list ) {
            if(item instanceof Predicate) {
                Predicate predicate = (Predicate) item;
                System.out.print("\t\t\tPredicate: "+predicate.getName() +"(");
                List<Term> terms = predicate.getTerms();
                for(Term term : terms){
                    if(term instanceof Variable) {
                        Variable var = (Variable) term;
                        System.out.print(var.getName()+"\t"+var.getType()+",");
                    }
                    else
                        System.out.println("\t\t\t\tterm: "+term+"\t"+ term.getClass().getName());
                }
                System.out.println(")");
            }
            else
                System.out.println("\t\t" + item + "\t" + item.getClass().getName());
        }
    }
}
