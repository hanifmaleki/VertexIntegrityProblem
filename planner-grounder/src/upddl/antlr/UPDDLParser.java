// $ANTLR 3.4 /home/kron/Desktop/workspace/planner/src/UPDDL.g 2012-06-19 12:46:03
package upddl.antlr;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


/**
 * uPDDL grammar
 * 
 * by Martin Kronegger
 * 
 * This grammar is based on Zeyn Saigol's PDDL grammar
 * from: http://www.antlr.org/grammar/1222962012944/Pddl.g
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class UPDDLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ACTION_PARAMETERS", "AND_EFFECT", "AND_GD", "ANY_CHAR", "ASSIGN_EFFECT", "BINARY_OP", "CONSTANTS", "DIGIT", "DOMAIN", "DOMAIN_NAME", "EFFECT", "FUNCTIONS", "FUNC_HEAD", "GOAL", "INIT", "INIT_EQ", "LETTER", "LINE_COMMENT", "NAME", "NEVERS", "NOT_EFFECT", "NOT_GD", "NOT_PRED_INIT", "NUMBER", "OBJECTS", "ONEOF", "PRECONDITION", "PREDICATES", "PRED_HEAD", "PRED_INST", "PROBLEM", "PROBLEM_DOMAIN", "PROBLEM_METRIC", "PROBLEM_NAME", "REQUIREMENTS", "REQUIRE_KEY", "TYPES", "UNKNOWN_PRED_INST", "VARIABLE", "WHEN_EFFECT", "WHITESPACE", "'('", "')'", "'*'", "'+'", "'-'", "'/'", "':action'", "':constants'", "':domain'", "':effect'", "':functions'", "':goal'", "':init'", "':metric'", "':never'", "':objects'", "':parameters'", "':precondition'", "':predicates'", "':requirements'", "':types'", "'='", "'and'", "'assign'", "'decrease'", "'define'", "'domain'", "'increase'", "'is-violated'", "'maximize'", "'minimize'", "'not'", "'number'", "'oneof'", "'problem'", "'scale-down'", "'scale-up'", "'total-time'", "'unknown'", "'when'"
    };

    public static final int EOF=-1;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int ACTION=4;
    public static final int ACTION_PARAMETERS=5;
    public static final int AND_EFFECT=6;
    public static final int AND_GD=7;
    public static final int ANY_CHAR=8;
    public static final int ASSIGN_EFFECT=9;
    public static final int BINARY_OP=10;
    public static final int CONSTANTS=11;
    public static final int DIGIT=12;
    public static final int DOMAIN=13;
    public static final int DOMAIN_NAME=14;
    public static final int EFFECT=15;
    public static final int FUNCTIONS=16;
    public static final int FUNC_HEAD=17;
    public static final int GOAL=18;
    public static final int INIT=19;
    public static final int INIT_EQ=20;
    public static final int LETTER=21;
    public static final int LINE_COMMENT=22;
    public static final int NAME=23;
    public static final int NEVERS=24;
    public static final int NOT_EFFECT=25;
    public static final int NOT_GD=26;
    public static final int NOT_PRED_INIT=27;
    public static final int NUMBER=28;
    public static final int OBJECTS=29;
    public static final int ONEOF=30;
    public static final int PRECONDITION=31;
    public static final int PREDICATES=32;
    public static final int PRED_HEAD=33;
    public static final int PRED_INST=34;
    public static final int PROBLEM=35;
    public static final int PROBLEM_DOMAIN=36;
    public static final int PROBLEM_METRIC=37;
    public static final int PROBLEM_NAME=38;
    public static final int REQUIREMENTS=39;
    public static final int REQUIRE_KEY=40;
    public static final int TYPES=41;
    public static final int UNKNOWN_PRED_INST=42;
    public static final int VARIABLE=43;
    public static final int WHEN_EFFECT=44;
    public static final int WHITESPACE=45;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public UPDDLParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public UPDDLParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return UPDDLParser.tokenNames; }
    public String getGrammarFileName() { return "/home/kron/Desktop/workspace/planner/src/UPDDL.g"; }


    	private boolean wasError = false;
    	
    	public void reportError(RecognitionException e) {
    		wasError = true;
    		super.reportError(e);
    	}
    	
    	public boolean invalidGrammar() {
    		return wasError;
    	}


    public static class npddlDoc_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "npddlDoc"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:84:1: npddlDoc : ( domain | problem );
    public final UPDDLParser.npddlDoc_return npddlDoc() throws RecognitionException {
        UPDDLParser.npddlDoc_return retval = new UPDDLParser.npddlDoc_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        UPDDLParser.domain_return domain1 =null;

        UPDDLParser.problem_return problem2 =null;



        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:84:10: ( domain | problem )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==46) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==71) ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2==46) ) {
                        int LA1_3 = input.LA(4);

                        if ( (LA1_3==72) ) {
                            alt1=1;
                        }
                        else if ( (LA1_3==80) ) {
                            alt1=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 1, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:84:12: domain
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_domain_in_npddlDoc201);
                    domain1=domain();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, domain1.getTree());

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:84:21: problem
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_problem_in_npddlDoc205);
                    problem2=problem();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, problem2.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "npddlDoc"


    public static class domain_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "domain"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:89:1: domain : '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* ) ;
    public final UPDDLParser.domain_return domain() throws RecognitionException {
        UPDDLParser.domain_return retval = new UPDDLParser.domain_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal3=null;
        Token string_literal4=null;
        Token char_literal13=null;
        UPDDLParser.domainName_return domainName5 =null;

        UPDDLParser.requireDef_return requireDef6 =null;

        UPDDLParser.typesDef_return typesDef7 =null;

        UPDDLParser.constantsDef_return constantsDef8 =null;

        UPDDLParser.predicatesDef_return predicatesDef9 =null;

        UPDDLParser.functionsDef_return functionsDef10 =null;

        UPDDLParser.neversDef_return neversDef11 =null;

        UPDDLParser.actionDef_return actionDef12 =null;


        Object char_literal3_tree=null;
        Object string_literal4_tree=null;
        Object char_literal13_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_constantsDef=new RewriteRuleSubtreeStream(adaptor,"rule constantsDef");
        RewriteRuleSubtreeStream stream_requireDef=new RewriteRuleSubtreeStream(adaptor,"rule requireDef");
        RewriteRuleSubtreeStream stream_predicatesDef=new RewriteRuleSubtreeStream(adaptor,"rule predicatesDef");
        RewriteRuleSubtreeStream stream_neversDef=new RewriteRuleSubtreeStream(adaptor,"rule neversDef");
        RewriteRuleSubtreeStream stream_domainName=new RewriteRuleSubtreeStream(adaptor,"rule domainName");
        RewriteRuleSubtreeStream stream_functionsDef=new RewriteRuleSubtreeStream(adaptor,"rule functionsDef");
        RewriteRuleSubtreeStream stream_actionDef=new RewriteRuleSubtreeStream(adaptor,"rule actionDef");
        RewriteRuleSubtreeStream stream_typesDef=new RewriteRuleSubtreeStream(adaptor,"rule typesDef");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:90:2: ( '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:90:4: '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* ')'
            {
            char_literal3=(Token)match(input,46,FOLLOW_46_in_domain217); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal3);


            string_literal4=(Token)match(input,71,FOLLOW_71_in_domain219); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(string_literal4);


            pushFollow(FOLLOW_domainName_in_domain221);
            domainName5=domainName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_domainName.add(domainName5.getTree());

            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:91:3: ( requireDef )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==46) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==65) ) {
                    alt2=1;
                }
            }
            switch (alt2) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:91:3: requireDef
                    {
                    pushFollow(FOLLOW_requireDef_in_domain225);
                    requireDef6=requireDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_requireDef.add(requireDef6.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:92:3: ( typesDef )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==46) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==66) ) {
                    alt3=1;
                }
            }
            switch (alt3) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:92:3: typesDef
                    {
                    pushFollow(FOLLOW_typesDef_in_domain230);
                    typesDef7=typesDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typesDef.add(typesDef7.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:93:3: ( constantsDef )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==46) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==53) ) {
                    alt4=1;
                }
            }
            switch (alt4) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:93:3: constantsDef
                    {
                    pushFollow(FOLLOW_constantsDef_in_domain235);
                    constantsDef8=constantsDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantsDef.add(constantsDef8.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:94:3: ( predicatesDef )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==46) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==64) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:94:3: predicatesDef
                    {
                    pushFollow(FOLLOW_predicatesDef_in_domain240);
                    predicatesDef9=predicatesDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_predicatesDef.add(predicatesDef9.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:95:3: ( functionsDef )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==46) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==56) ) {
                    alt6=1;
                }
            }
            switch (alt6) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:95:3: functionsDef
                    {
                    pushFollow(FOLLOW_functionsDef_in_domain245);
                    functionsDef10=functionsDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionsDef.add(functionsDef10.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:96:3: ( neversDef )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==46) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==60) ) {
                    alt7=1;
                }
            }
            switch (alt7) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:96:3: neversDef
                    {
                    pushFollow(FOLLOW_neversDef_in_domain250);
                    neversDef11=neversDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_neversDef.add(neversDef11.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:97:3: ( actionDef )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==46) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:97:3: actionDef
            	    {
            	    pushFollow(FOLLOW_actionDef_in_domain255);
            	    actionDef12=actionDef();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_actionDef.add(actionDef12.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            char_literal13=(Token)match(input,47,FOLLOW_47_in_domain260); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal13);


            // AST REWRITE
            // elements: actionDef, functionsDef, typesDef, predicatesDef, constantsDef, domainName, requireDef, neversDef
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 99:2: -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:99:5: ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( neversDef )? ( actionDef )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DOMAIN, "DOMAIN")
                , root_1);

                adaptor.addChild(root_1, stream_domainName.nextTree());

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:100:3: ( requireDef )?
                if ( stream_requireDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_requireDef.nextTree());

                }
                stream_requireDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:101:3: ( typesDef )?
                if ( stream_typesDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_typesDef.nextTree());

                }
                stream_typesDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:102:3: ( constantsDef )?
                if ( stream_constantsDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_constantsDef.nextTree());

                }
                stream_constantsDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:103:3: ( predicatesDef )?
                if ( stream_predicatesDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_predicatesDef.nextTree());

                }
                stream_predicatesDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:104:3: ( functionsDef )?
                if ( stream_functionsDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_functionsDef.nextTree());

                }
                stream_functionsDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:105:3: ( neversDef )?
                if ( stream_neversDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_neversDef.nextTree());

                }
                stream_neversDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:106:3: ( actionDef )*
                while ( stream_actionDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_actionDef.nextTree());

                }
                stream_actionDef.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "domain"


    public static class domainName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "domainName"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:109:1: domainName : '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) ;
    public final UPDDLParser.domainName_return domainName() throws RecognitionException {
        UPDDLParser.domainName_return retval = new UPDDLParser.domainName_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal14=null;
        Token string_literal15=null;
        Token NAME16=null;
        Token char_literal17=null;

        Object char_literal14_tree=null;
        Object string_literal15_tree=null;
        Object NAME16_tree=null;
        Object char_literal17_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:110:2: ( '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:110:4: '(' 'domain' NAME ')'
            {
            char_literal14=(Token)match(input,46,FOLLOW_46_in_domainName319); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal14);


            string_literal15=(Token)match(input,72,FOLLOW_72_in_domainName321); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_72.add(string_literal15);


            NAME16=(Token)match(input,NAME,FOLLOW_NAME_in_domainName323); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(NAME16);


            char_literal17=(Token)match(input,47,FOLLOW_47_in_domainName325); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal17);


            // AST REWRITE
            // elements: NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 111:2: -> ^( DOMAIN_NAME NAME )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:111:5: ^( DOMAIN_NAME NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DOMAIN_NAME, "DOMAIN_NAME")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NAME.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "domainName"


    public static class requireDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "requireDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:114:1: requireDef : '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) ;
    public final UPDDLParser.requireDef_return requireDef() throws RecognitionException {
        UPDDLParser.requireDef_return retval = new UPDDLParser.requireDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal18=null;
        Token string_literal19=null;
        Token REQUIRE_KEY20=null;
        Token char_literal21=null;

        Object char_literal18_tree=null;
        Object string_literal19_tree=null;
        Object REQUIRE_KEY20_tree=null;
        Object char_literal21_tree=null;
        RewriteRuleTokenStream stream_REQUIRE_KEY=new RewriteRuleTokenStream(adaptor,"token REQUIRE_KEY");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:115:2: ( '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:115:4: '(' ':requirements' ( REQUIRE_KEY )+ ')'
            {
            char_literal18=(Token)match(input,46,FOLLOW_46_in_requireDef345); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal18);


            string_literal19=(Token)match(input,65,FOLLOW_65_in_requireDef347); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_65.add(string_literal19);


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:115:24: ( REQUIRE_KEY )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==REQUIRE_KEY) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:115:24: REQUIRE_KEY
            	    {
            	    REQUIRE_KEY20=(Token)match(input,REQUIRE_KEY,FOLLOW_REQUIRE_KEY_in_requireDef349); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_REQUIRE_KEY.add(REQUIRE_KEY20);


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            char_literal21=(Token)match(input,47,FOLLOW_47_in_requireDef352); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal21);


            // AST REWRITE
            // elements: REQUIRE_KEY
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 116:2: -> ^( REQUIREMENTS ( REQUIRE_KEY )+ )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:116:5: ^( REQUIREMENTS ( REQUIRE_KEY )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(REQUIREMENTS, "REQUIREMENTS")
                , root_1);

                if ( !(stream_REQUIRE_KEY.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_REQUIRE_KEY.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_REQUIRE_KEY.nextNode()
                    );

                }
                stream_REQUIRE_KEY.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "requireDef"


    public static class typesDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typesDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:119:1: typesDef : '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) ;
    public final UPDDLParser.typesDef_return typesDef() throws RecognitionException {
        UPDDLParser.typesDef_return retval = new UPDDLParser.typesDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal22=null;
        Token string_literal23=null;
        Token char_literal25=null;
        UPDDLParser.typedNameList_return typedNameList24 =null;


        Object char_literal22_tree=null;
        Object string_literal23_tree=null;
        Object char_literal25_tree=null;
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:120:2: ( '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:120:4: '(' ':types' typedNameList ')'
            {
            char_literal22=(Token)match(input,46,FOLLOW_46_in_typesDef373); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal22);


            string_literal23=(Token)match(input,66,FOLLOW_66_in_typesDef375); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_66.add(string_literal23);


            pushFollow(FOLLOW_typedNameList_in_typesDef377);
            typedNameList24=typedNameList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList24.getTree());

            char_literal25=(Token)match(input,47,FOLLOW_47_in_typesDef379); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal25);


            // AST REWRITE
            // elements: typedNameList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 121:2: -> ^( TYPES typedNameList )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:121:5: ^( TYPES typedNameList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TYPES, "TYPES")
                , root_1);

                adaptor.addChild(root_1, stream_typedNameList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typesDef"


    public static class typedNameList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typedNameList"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:125:1: typedNameList : ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) ;
    public final UPDDLParser.typedNameList_return typedNameList() throws RecognitionException {
        UPDDLParser.typedNameList_return retval = new UPDDLParser.typedNameList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME26=null;
        Token NAME28=null;
        UPDDLParser.singleTypeNameList_return singleTypeNameList27 =null;


        Object NAME26_tree=null;
        Object NAME28_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:2: ( ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:4: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
            {
            root_0 = (Object)adaptor.nil();


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:4: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
            int alt13=2;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:5: ( NAME )*
                    {
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:5: ( NAME )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==NAME) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:5: NAME
                    	    {
                    	    NAME26=(Token)match(input,NAME,FOLLOW_NAME_in_typedNameList401); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NAME26_tree = 
                    	    (Object)adaptor.create(NAME26)
                    	    ;
                    	    adaptor.addChild(root_0, NAME26_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:13: ( singleTypeNameList )+ ( NAME )*
                    {
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:13: ( singleTypeNameList )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        alt11 = dfa11.predict(input);
                        switch (alt11) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:13: singleTypeNameList
                    	    {
                    	    pushFollow(FOLLOW_singleTypeNameList_in_typedNameList406);
                    	    singleTypeNameList27=singleTypeNameList();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeNameList27.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:33: ( NAME )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==NAME) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:126:33: NAME
                    	    {
                    	    NAME28=(Token)match(input,NAME,FOLLOW_NAME_in_typedNameList409); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NAME28_tree = 
                    	    (Object)adaptor.create(NAME28)
                    	    ;
                    	    adaptor.addChild(root_0, NAME28_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typedNameList"


    public static class singleTypeNameList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "singleTypeNameList"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:129:1: singleTypeNameList : ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ ;
    public final UPDDLParser.singleTypeNameList_return singleTypeNameList() throws RecognitionException {
        UPDDLParser.singleTypeNameList_return retval = new UPDDLParser.singleTypeNameList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME29=null;
        Token char_literal30=null;
        UPDDLParser.type_return t =null;


        Object NAME29_tree=null;
        Object char_literal30_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:130:2: ( ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:130:4: ( ( NAME )+ '-' t= type )
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:130:4: ( ( NAME )+ '-' t= type )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:130:5: ( NAME )+ '-' t= type
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:130:5: ( NAME )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==NAME) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:130:5: NAME
            	    {
            	    NAME29=(Token)match(input,NAME,FOLLOW_NAME_in_singleTypeNameList423); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NAME.add(NAME29);


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            char_literal30=(Token)match(input,50,FOLLOW_50_in_singleTypeNameList426); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_50.add(char_literal30);


            pushFollow(FOLLOW_type_in_singleTypeNameList430);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());

            }


            // AST REWRITE
            // elements: NAME, t
            // token labels: 
            // rule labels: retval, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 131:2: -> ( ^( NAME $t) )+
            {
                if ( !(stream_NAME.hasNext()||stream_t.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_NAME.hasNext()||stream_t.hasNext() ) {
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:131:5: ^( NAME $t)
                    {
                    Object root_1 = (Object)adaptor.nil();
                    root_1 = (Object)adaptor.becomeRoot(
                    stream_NAME.nextNode()
                    , root_1);

                    adaptor.addChild(root_1, stream_t.nextTree());

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_NAME.reset();
                stream_t.reset();

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "singleTypeNameList"


    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:134:1: type : primType ;
    public final UPDDLParser.type_return type() throws RecognitionException {
        UPDDLParser.type_return retval = new UPDDLParser.type_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        UPDDLParser.primType_return primType31 =null;



        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:134:6: ( primType )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:134:8: primType
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_primType_in_type452);
            primType31=primType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, primType31.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "type"


    public static class primType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primType"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:136:1: primType : NAME ;
    public final UPDDLParser.primType_return primType() throws RecognitionException {
        UPDDLParser.primType_return retval = new UPDDLParser.primType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME32=null;

        Object NAME32_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:136:10: ( NAME )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:136:12: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME32=(Token)match(input,NAME,FOLLOW_NAME_in_primType461); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME32_tree = 
            (Object)adaptor.create(NAME32)
            ;
            adaptor.addChild(root_0, NAME32_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "primType"


    public static class functionsDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionsDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:138:1: functionsDef : '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) ;
    public final UPDDLParser.functionsDef_return functionsDef() throws RecognitionException {
        UPDDLParser.functionsDef_return retval = new UPDDLParser.functionsDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal33=null;
        Token string_literal34=null;
        Token char_literal36=null;
        UPDDLParser.functionList_return functionList35 =null;


        Object char_literal33_tree=null;
        Object string_literal34_tree=null;
        Object char_literal36_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_functionList=new RewriteRuleSubtreeStream(adaptor,"rule functionList");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:139:2: ( '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:139:4: '(' ':functions' functionList ')'
            {
            char_literal33=(Token)match(input,46,FOLLOW_46_in_functionsDef471); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal33);


            string_literal34=(Token)match(input,56,FOLLOW_56_in_functionsDef473); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_56.add(string_literal34);


            pushFollow(FOLLOW_functionList_in_functionsDef475);
            functionList35=functionList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_functionList.add(functionList35.getTree());

            char_literal36=(Token)match(input,47,FOLLOW_47_in_functionsDef477); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal36);


            // AST REWRITE
            // elements: functionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 140:2: -> ^( FUNCTIONS functionList )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:140:5: ^( FUNCTIONS functionList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(FUNCTIONS, "FUNCTIONS")
                , root_1);

                adaptor.addChild(root_1, stream_functionList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionsDef"


    public static class functionList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionList"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:143:1: functionList : ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* ;
    public final UPDDLParser.functionList_return functionList() throws RecognitionException {
        UPDDLParser.functionList_return retval = new UPDDLParser.functionList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal38=null;
        UPDDLParser.atomicFunctionSkeleton_return atomicFunctionSkeleton37 =null;

        UPDDLParser.functionType_return functionType39 =null;


        Object char_literal38_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:2: ( ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
            {
            root_0 = (Object)adaptor.nil();


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==46) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:5: ( atomicFunctionSkeleton )+ ( '-' functionType )?
            	    {
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:5: ( atomicFunctionSkeleton )+
            	    int cnt15=0;
            	    loop15:
            	    do {
            	        int alt15=2;
            	        int LA15_0 = input.LA(1);

            	        if ( (LA15_0==46) ) {
            	            int LA15_2 = input.LA(2);

            	            if ( (synpred15_UPDDL()) ) {
            	                alt15=1;
            	            }


            	        }


            	        switch (alt15) {
            	    	case 1 :
            	    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:5: atomicFunctionSkeleton
            	    	    {
            	    	    pushFollow(FOLLOW_atomicFunctionSkeleton_in_functionList498);
            	    	    atomicFunctionSkeleton37=atomicFunctionSkeleton();

            	    	    state._fsp--;
            	    	    if (state.failed) return retval;
            	    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicFunctionSkeleton37.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt15 >= 1 ) break loop15;
            	    	    if (state.backtracking>0) {state.failed=true; return retval;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(15, input);
            	                throw eee;
            	        }
            	        cnt15++;
            	    } while (true);


            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:29: ( '-' functionType )?
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==50) ) {
            	        alt16=1;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:30: '-' functionType
            	            {
            	            char_literal38=(Token)match(input,50,FOLLOW_50_in_functionList502); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal38_tree = 
            	            (Object)adaptor.create(char_literal38)
            	            ;
            	            adaptor.addChild(root_0, char_literal38_tree);
            	            }

            	            pushFollow(FOLLOW_functionType_in_functionList504);
            	            functionType39=functionType();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, functionType39.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionList"


    public static class atomicFunctionSkeleton_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicFunctionSkeleton"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:147:1: atomicFunctionSkeleton : '(' ! functionSymbol ^ typedVariableList ')' !;
    public final UPDDLParser.atomicFunctionSkeleton_return atomicFunctionSkeleton() throws RecognitionException {
        UPDDLParser.atomicFunctionSkeleton_return retval = new UPDDLParser.atomicFunctionSkeleton_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal40=null;
        Token char_literal43=null;
        UPDDLParser.functionSymbol_return functionSymbol41 =null;

        UPDDLParser.typedVariableList_return typedVariableList42 =null;


        Object char_literal40_tree=null;
        Object char_literal43_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:148:2: ( '(' ! functionSymbol ^ typedVariableList ')' !)
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:148:4: '(' ! functionSymbol ^ typedVariableList ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal40=(Token)match(input,46,FOLLOW_46_in_atomicFunctionSkeleton520); if (state.failed) return retval;

            pushFollow(FOLLOW_functionSymbol_in_atomicFunctionSkeleton523);
            functionSymbol41=functionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(functionSymbol41.getTree(), root_0);

            pushFollow(FOLLOW_typedVariableList_in_atomicFunctionSkeleton526);
            typedVariableList42=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList42.getTree());

            char_literal43=(Token)match(input,47,FOLLOW_47_in_atomicFunctionSkeleton528); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicFunctionSkeleton"


    public static class functionSymbol_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionSymbol"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:151:1: functionSymbol : NAME ;
    public final UPDDLParser.functionSymbol_return functionSymbol() throws RecognitionException {
        UPDDLParser.functionSymbol_return retval = new UPDDLParser.functionSymbol_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME44=null;

        Object NAME44_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:151:16: ( NAME )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:151:18: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME44=(Token)match(input,NAME,FOLLOW_NAME_in_functionSymbol539); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME44_tree = 
            (Object)adaptor.create(NAME44)
            ;
            adaptor.addChild(root_0, NAME44_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionSymbol"


    public static class functionType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionType"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:153:1: functionType : 'number' ;
    public final UPDDLParser.functionType_return functionType() throws RecognitionException {
        UPDDLParser.functionType_return retval = new UPDDLParser.functionType_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal45=null;

        Object string_literal45_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:153:14: ( 'number' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:153:16: 'number'
            {
            root_0 = (Object)adaptor.nil();


            string_literal45=(Token)match(input,78,FOLLOW_78_in_functionType548); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal45_tree = 
            (Object)adaptor.create(string_literal45)
            ;
            adaptor.addChild(root_0, string_literal45_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionType"


    public static class constantsDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantsDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:155:1: constantsDef : '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) ;
    public final UPDDLParser.constantsDef_return constantsDef() throws RecognitionException {
        UPDDLParser.constantsDef_return retval = new UPDDLParser.constantsDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal46=null;
        Token string_literal47=null;
        Token char_literal49=null;
        UPDDLParser.typedNameList_return typedNameList48 =null;


        Object char_literal46_tree=null;
        Object string_literal47_tree=null;
        Object char_literal49_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
        RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:156:2: ( '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:156:4: '(' ':constants' typedNameList ')'
            {
            char_literal46=(Token)match(input,46,FOLLOW_46_in_constantsDef559); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal46);


            string_literal47=(Token)match(input,53,FOLLOW_53_in_constantsDef561); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_53.add(string_literal47);


            pushFollow(FOLLOW_typedNameList_in_constantsDef563);
            typedNameList48=typedNameList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList48.getTree());

            char_literal49=(Token)match(input,47,FOLLOW_47_in_constantsDef565); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal49);


            // AST REWRITE
            // elements: typedNameList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 157:2: -> ^( CONSTANTS typedNameList )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:157:5: ^( CONSTANTS typedNameList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CONSTANTS, "CONSTANTS")
                , root_1);

                adaptor.addChild(root_1, stream_typedNameList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "constantsDef"


    public static class predicatesDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "predicatesDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:160:1: predicatesDef : '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) ;
    public final UPDDLParser.predicatesDef_return predicatesDef() throws RecognitionException {
        UPDDLParser.predicatesDef_return retval = new UPDDLParser.predicatesDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal50=null;
        Token string_literal51=null;
        Token char_literal53=null;
        UPDDLParser.atomicFormulaSkeleton_return atomicFormulaSkeleton52 =null;


        Object char_literal50_tree=null;
        Object string_literal51_tree=null;
        Object char_literal53_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleSubtreeStream stream_atomicFormulaSkeleton=new RewriteRuleSubtreeStream(adaptor,"rule atomicFormulaSkeleton");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:161:2: ( '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:161:4: '(' ':predicates' ( atomicFormulaSkeleton )+ ')'
            {
            char_literal50=(Token)match(input,46,FOLLOW_46_in_predicatesDef585); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal50);


            string_literal51=(Token)match(input,64,FOLLOW_64_in_predicatesDef587); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_64.add(string_literal51);


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:161:22: ( atomicFormulaSkeleton )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==46) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:161:22: atomicFormulaSkeleton
            	    {
            	    pushFollow(FOLLOW_atomicFormulaSkeleton_in_predicatesDef589);
            	    atomicFormulaSkeleton52=atomicFormulaSkeleton();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_atomicFormulaSkeleton.add(atomicFormulaSkeleton52.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);


            char_literal53=(Token)match(input,47,FOLLOW_47_in_predicatesDef592); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal53);


            // AST REWRITE
            // elements: atomicFormulaSkeleton
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 162:2: -> ^( PREDICATES ( atomicFormulaSkeleton )+ )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:162:5: ^( PREDICATES ( atomicFormulaSkeleton )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PREDICATES, "PREDICATES")
                , root_1);

                if ( !(stream_atomicFormulaSkeleton.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_atomicFormulaSkeleton.hasNext() ) {
                    adaptor.addChild(root_1, stream_atomicFormulaSkeleton.nextTree());

                }
                stream_atomicFormulaSkeleton.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "predicatesDef"


    public static class atomicFormulaSkeleton_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicFormulaSkeleton"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:165:1: atomicFormulaSkeleton : '(' ! predicate ^ typedVariableList ')' !;
    public final UPDDLParser.atomicFormulaSkeleton_return atomicFormulaSkeleton() throws RecognitionException {
        UPDDLParser.atomicFormulaSkeleton_return retval = new UPDDLParser.atomicFormulaSkeleton_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal54=null;
        Token char_literal57=null;
        UPDDLParser.predicate_return predicate55 =null;

        UPDDLParser.typedVariableList_return typedVariableList56 =null;


        Object char_literal54_tree=null;
        Object char_literal57_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:166:2: ( '(' ! predicate ^ typedVariableList ')' !)
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:166:4: '(' ! predicate ^ typedVariableList ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal54=(Token)match(input,46,FOLLOW_46_in_atomicFormulaSkeleton613); if (state.failed) return retval;

            pushFollow(FOLLOW_predicate_in_atomicFormulaSkeleton616);
            predicate55=predicate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(predicate55.getTree(), root_0);

            pushFollow(FOLLOW_typedVariableList_in_atomicFormulaSkeleton619);
            typedVariableList56=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList56.getTree());

            char_literal57=(Token)match(input,47,FOLLOW_47_in_atomicFormulaSkeleton621); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicFormulaSkeleton"


    public static class predicate_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "predicate"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:169:1: predicate : NAME ;
    public final UPDDLParser.predicate_return predicate() throws RecognitionException {
        UPDDLParser.predicate_return retval = new UPDDLParser.predicate_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME58=null;

        Object NAME58_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:169:11: ( NAME )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:169:13: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME58=(Token)match(input,NAME,FOLLOW_NAME_in_predicate632); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME58_tree = 
            (Object)adaptor.create(NAME58)
            ;
            adaptor.addChild(root_0, NAME58_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "predicate"


    public static class typedVariableList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typedVariableList"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:172:1: typedVariableList : ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) ;
    public final UPDDLParser.typedVariableList_return typedVariableList() throws RecognitionException {
        UPDDLParser.typedVariableList_return retval = new UPDDLParser.typedVariableList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token VARIABLE59=null;
        Token VARIABLE61=null;
        UPDDLParser.singleTypeVarList_return singleTypeVarList60 =null;


        Object VARIABLE59_tree=null;
        Object VARIABLE61_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:2: ( ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:4: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
            {
            root_0 = (Object)adaptor.nil();


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:4: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
            int alt22=2;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:5: ( VARIABLE )*
                    {
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:5: ( VARIABLE )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==VARIABLE) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:5: VARIABLE
                    	    {
                    	    VARIABLE59=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_typedVariableList644); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    VARIABLE59_tree = 
                    	    (Object)adaptor.create(VARIABLE59)
                    	    ;
                    	    adaptor.addChild(root_0, VARIABLE59_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:17: ( singleTypeVarList )+ ( VARIABLE )*
                    {
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:17: ( singleTypeVarList )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        alt20 = dfa20.predict(input);
                        switch (alt20) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:17: singleTypeVarList
                    	    {
                    	    pushFollow(FOLLOW_singleTypeVarList_in_typedVariableList649);
                    	    singleTypeVarList60=singleTypeVarList();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeVarList60.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt20 >= 1 ) break loop20;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(20, input);
                                throw eee;
                        }
                        cnt20++;
                    } while (true);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:36: ( VARIABLE )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==VARIABLE) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:173:36: VARIABLE
                    	    {
                    	    VARIABLE61=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_typedVariableList652); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    VARIABLE61_tree = 
                    	    (Object)adaptor.create(VARIABLE61)
                    	    ;
                    	    adaptor.addChild(root_0, VARIABLE61_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "typedVariableList"


    public static class singleTypeVarList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "singleTypeVarList"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:176:1: singleTypeVarList : ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ ;
    public final UPDDLParser.singleTypeVarList_return singleTypeVarList() throws RecognitionException {
        UPDDLParser.singleTypeVarList_return retval = new UPDDLParser.singleTypeVarList_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token VARIABLE62=null;
        Token char_literal63=null;
        UPDDLParser.type_return t =null;


        Object VARIABLE62_tree=null;
        Object char_literal63_tree=null;
        RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
        RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:177:2: ( ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:177:4: ( ( VARIABLE )+ '-' t= type )
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:177:4: ( ( VARIABLE )+ '-' t= type )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:177:5: ( VARIABLE )+ '-' t= type
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:177:5: ( VARIABLE )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==VARIABLE) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:177:5: VARIABLE
            	    {
            	    VARIABLE62=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_singleTypeVarList666); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_VARIABLE.add(VARIABLE62);


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);


            char_literal63=(Token)match(input,50,FOLLOW_50_in_singleTypeVarList669); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_50.add(char_literal63);


            pushFollow(FOLLOW_type_in_singleTypeVarList673);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());

            }


            // AST REWRITE
            // elements: VARIABLE, t
            // token labels: 
            // rule labels: retval, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 178:2: -> ( ^( VARIABLE $t) )+
            {
                if ( !(stream_VARIABLE.hasNext()||stream_t.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_VARIABLE.hasNext()||stream_t.hasNext() ) {
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:178:5: ^( VARIABLE $t)
                    {
                    Object root_1 = (Object)adaptor.nil();
                    root_1 = (Object)adaptor.becomeRoot(
                    stream_VARIABLE.nextNode()
                    , root_1);

                    adaptor.addChild(root_1, stream_t.nextTree());

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_VARIABLE.reset();
                stream_t.reset();

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "singleTypeVarList"


    public static class neversDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "neversDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:181:1: neversDef : '(' ':never' ( atomicPred )+ ')' -> ^( NEVERS ( atomicPred )+ ) ;
    public final UPDDLParser.neversDef_return neversDef() throws RecognitionException {
        UPDDLParser.neversDef_return retval = new UPDDLParser.neversDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal64=null;
        Token string_literal65=null;
        Token char_literal67=null;
        UPDDLParser.atomicPred_return atomicPred66 =null;


        Object char_literal64_tree=null;
        Object string_literal65_tree=null;
        Object char_literal67_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleSubtreeStream stream_atomicPred=new RewriteRuleSubtreeStream(adaptor,"rule atomicPred");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:181:10: ( '(' ':never' ( atomicPred )+ ')' -> ^( NEVERS ( atomicPred )+ ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:182:2: '(' ':never' ( atomicPred )+ ')'
            {
            char_literal64=(Token)match(input,46,FOLLOW_46_in_neversDef696); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal64);


            string_literal65=(Token)match(input,60,FOLLOW_60_in_neversDef698); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_60.add(string_literal65);


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:182:15: ( atomicPred )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==46) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:182:15: atomicPred
            	    {
            	    pushFollow(FOLLOW_atomicPred_in_neversDef700);
            	    atomicPred66=atomicPred();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_atomicPred.add(atomicPred66.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);


            char_literal67=(Token)match(input,47,FOLLOW_47_in_neversDef703); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal67);


            // AST REWRITE
            // elements: atomicPred
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 183:2: -> ^( NEVERS ( atomicPred )+ )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:183:5: ^( NEVERS ( atomicPred )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(NEVERS, "NEVERS")
                , root_1);

                if ( !(stream_atomicPred.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_atomicPred.hasNext() ) {
                    adaptor.addChild(root_1, stream_atomicPred.nextTree());

                }
                stream_atomicPred.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "neversDef"


    public static class atomicPred_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicPred"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:186:1: atomicPred : '(' ! predicate ^ ( constantOrTypedVariable )* ')' !;
    public final UPDDLParser.atomicPred_return atomicPred() throws RecognitionException {
        UPDDLParser.atomicPred_return retval = new UPDDLParser.atomicPred_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal68=null;
        Token char_literal71=null;
        UPDDLParser.predicate_return predicate69 =null;

        UPDDLParser.constantOrTypedVariable_return constantOrTypedVariable70 =null;


        Object char_literal68_tree=null;
        Object char_literal71_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:187:2: ( '(' ! predicate ^ ( constantOrTypedVariable )* ')' !)
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:187:3: '(' ! predicate ^ ( constantOrTypedVariable )* ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal68=(Token)match(input,46,FOLLOW_46_in_atomicPred723); if (state.failed) return retval;

            pushFollow(FOLLOW_predicate_in_atomicPred726);
            predicate69=predicate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(predicate69.getTree(), root_0);

            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:187:19: ( constantOrTypedVariable )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==NAME||LA25_0==VARIABLE) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:187:19: constantOrTypedVariable
            	    {
            	    pushFollow(FOLLOW_constantOrTypedVariable_in_atomicPred729);
            	    constantOrTypedVariable70=constantOrTypedVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, constantOrTypedVariable70.getTree());

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            char_literal71=(Token)match(input,47,FOLLOW_47_in_atomicPred732); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicPred"


    public static class constantOrTypedVariable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantOrTypedVariable"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:190:1: constantOrTypedVariable : ( NAME | singleTypeVar );
    public final UPDDLParser.constantOrTypedVariable_return constantOrTypedVariable() throws RecognitionException {
        UPDDLParser.constantOrTypedVariable_return retval = new UPDDLParser.constantOrTypedVariable_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME72=null;
        UPDDLParser.singleTypeVar_return singleTypeVar73 =null;


        Object NAME72_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:191:2: ( NAME | singleTypeVar )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NAME) ) {
                alt26=1;
            }
            else if ( (LA26_0==VARIABLE) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:191:3: NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    NAME72=(Token)match(input,NAME,FOLLOW_NAME_in_constantOrTypedVariable744); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME72_tree = 
                    (Object)adaptor.create(NAME72)
                    ;
                    adaptor.addChild(root_0, NAME72_tree);
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:192:4: singleTypeVar
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_singleTypeVar_in_constantOrTypedVariable749);
                    singleTypeVar73=singleTypeVar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeVar73.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "constantOrTypedVariable"


    public static class singleTypeVar_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "singleTypeVar"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:195:1: singleTypeVar : ( VARIABLE '-' t= type ) -> ^( VARIABLE $t) ;
    public final UPDDLParser.singleTypeVar_return singleTypeVar() throws RecognitionException {
        UPDDLParser.singleTypeVar_return retval = new UPDDLParser.singleTypeVar_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token VARIABLE74=null;
        Token char_literal75=null;
        UPDDLParser.type_return t =null;


        Object VARIABLE74_tree=null;
        Object char_literal75_tree=null;
        RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
        RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:196:2: ( ( VARIABLE '-' t= type ) -> ^( VARIABLE $t) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:196:4: ( VARIABLE '-' t= type )
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:196:4: ( VARIABLE '-' t= type )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:196:5: VARIABLE '-' t= type
            {
            VARIABLE74=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_singleTypeVar762); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VARIABLE.add(VARIABLE74);


            char_literal75=(Token)match(input,50,FOLLOW_50_in_singleTypeVar764); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_50.add(char_literal75);


            pushFollow(FOLLOW_type_in_singleTypeVar768);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());

            }


            // AST REWRITE
            // elements: t, VARIABLE
            // token labels: 
            // rule labels: retval, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 197:2: -> ^( VARIABLE $t)
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:197:5: ^( VARIABLE $t)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                stream_VARIABLE.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_t.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "singleTypeVar"


    public static class actionDef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionDef"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:203:1: actionDef : '(' ':action' actionSymbol actionParameter actionDefBody ')' -> ^( ACTION actionSymbol actionParameter actionDefBody ) ;
    public final UPDDLParser.actionDef_return actionDef() throws RecognitionException {
        UPDDLParser.actionDef_return retval = new UPDDLParser.actionDef_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal76=null;
        Token string_literal77=null;
        Token char_literal81=null;
        UPDDLParser.actionSymbol_return actionSymbol78 =null;

        UPDDLParser.actionParameter_return actionParameter79 =null;

        UPDDLParser.actionDefBody_return actionDefBody80 =null;


        Object char_literal76_tree=null;
        Object string_literal77_tree=null;
        Object char_literal81_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
        RewriteRuleSubtreeStream stream_actionParameter=new RewriteRuleSubtreeStream(adaptor,"rule actionParameter");
        RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");
        RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:204:2: ( '(' ':action' actionSymbol actionParameter actionDefBody ')' -> ^( ACTION actionSymbol actionParameter actionDefBody ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:204:4: '(' ':action' actionSymbol actionParameter actionDefBody ')'
            {
            char_literal76=(Token)match(input,46,FOLLOW_46_in_actionDef793); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal76);


            string_literal77=(Token)match(input,52,FOLLOW_52_in_actionDef795); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_52.add(string_literal77);


            pushFollow(FOLLOW_actionSymbol_in_actionDef797);
            actionSymbol78=actionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol78.getTree());

            pushFollow(FOLLOW_actionParameter_in_actionDef799);
            actionParameter79=actionParameter();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionParameter.add(actionParameter79.getTree());

            pushFollow(FOLLOW_actionDefBody_in_actionDef801);
            actionDefBody80=actionDefBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody80.getTree());

            char_literal81=(Token)match(input,47,FOLLOW_47_in_actionDef803); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal81);


            // AST REWRITE
            // elements: actionDefBody, actionSymbol, actionParameter
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 205:2: -> ^( ACTION actionSymbol actionParameter actionDefBody )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:205:5: ^( ACTION actionSymbol actionParameter actionDefBody )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ACTION, "ACTION")
                , root_1);

                adaptor.addChild(root_1, stream_actionSymbol.nextTree());

                adaptor.addChild(root_1, stream_actionParameter.nextTree());

                adaptor.addChild(root_1, stream_actionDefBody.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "actionDef"


    public static class actionSymbol_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionSymbol"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:208:1: actionSymbol : NAME ;
    public final UPDDLParser.actionSymbol_return actionSymbol() throws RecognitionException {
        UPDDLParser.actionSymbol_return retval = new UPDDLParser.actionSymbol_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NAME82=null;

        Object NAME82_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:208:14: ( NAME )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:208:16: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME82=(Token)match(input,NAME,FOLLOW_NAME_in_actionSymbol826); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME82_tree = 
            (Object)adaptor.create(NAME82)
            ;
            adaptor.addChild(root_0, NAME82_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "actionSymbol"


    public static class actionParameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionParameter"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:210:1: actionParameter : ( ':parameters' ( ( '(' ')' ) | '(' typedVariableList ')' ) )? -> ^( ACTION_PARAMETERS ( typedVariableList )? ) ;
    public final UPDDLParser.actionParameter_return actionParameter() throws RecognitionException {
        UPDDLParser.actionParameter_return retval = new UPDDLParser.actionParameter_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal83=null;
        Token char_literal84=null;
        Token char_literal85=null;
        Token char_literal86=null;
        Token char_literal88=null;
        UPDDLParser.typedVariableList_return typedVariableList87 =null;


        Object string_literal83_tree=null;
        Object char_literal84_tree=null;
        Object char_literal85_tree=null;
        Object char_literal86_tree=null;
        Object char_literal88_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:2: ( ( ':parameters' ( ( '(' ')' ) | '(' typedVariableList ')' ) )? -> ^( ACTION_PARAMETERS ( typedVariableList )? ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:4: ( ':parameters' ( ( '(' ')' ) | '(' typedVariableList ')' ) )?
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:4: ( ':parameters' ( ( '(' ')' ) | '(' typedVariableList ')' ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==62) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:5: ':parameters' ( ( '(' ')' ) | '(' typedVariableList ')' )
                    {
                    string_literal83=(Token)match(input,62,FOLLOW_62_in_actionParameter837); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_62.add(string_literal83);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:19: ( ( '(' ')' ) | '(' typedVariableList ')' )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==46) ) {
                        int LA27_1 = input.LA(2);

                        if ( (LA27_1==47) ) {
                            int LA27_2 = input.LA(3);

                            if ( (synpred27_UPDDL()) ) {
                                alt27=1;
                            }
                            else if ( (true) ) {
                                alt27=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 27, 2, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA27_1==VARIABLE) ) {
                            alt27=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 27, 1, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;

                    }
                    switch (alt27) {
                        case 1 :
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:20: ( '(' ')' )
                            {
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:20: ( '(' ')' )
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:21: '(' ')'
                            {
                            char_literal84=(Token)match(input,46,FOLLOW_46_in_actionParameter841); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_46.add(char_literal84);


                            char_literal85=(Token)match(input,47,FOLLOW_47_in_actionParameter843); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_47.add(char_literal85);


                            }


                            }
                            break;
                        case 2 :
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:32: '(' typedVariableList ')'
                            {
                            char_literal86=(Token)match(input,46,FOLLOW_46_in_actionParameter848); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_46.add(char_literal86);


                            pushFollow(FOLLOW_typedVariableList_in_actionParameter850);
                            typedVariableList87=typedVariableList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList87.getTree());

                            char_literal88=(Token)match(input,47,FOLLOW_47_in_actionParameter852); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_47.add(char_literal88);


                            }
                            break;

                    }


                    }
                    break;

            }


            // AST REWRITE
            // elements: typedVariableList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 212:3: -> ^( ACTION_PARAMETERS ( typedVariableList )? )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:212:6: ^( ACTION_PARAMETERS ( typedVariableList )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ACTION_PARAMETERS, "ACTION_PARAMETERS")
                , root_1);

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:212:26: ( typedVariableList )?
                if ( stream_typedVariableList.hasNext() ) {
                    adaptor.addChild(root_1, stream_typedVariableList.nextTree());

                }
                stream_typedVariableList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "actionParameter"


    public static class actionDefBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionDefBody"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:218:1: actionDefBody : ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) ;
    public final UPDDLParser.actionDefBody_return actionDefBody() throws RecognitionException {
        UPDDLParser.actionDefBody_return retval = new UPDDLParser.actionDefBody_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token string_literal89=null;
        Token char_literal90=null;
        Token char_literal91=null;
        Token string_literal93=null;
        Token char_literal94=null;
        Token char_literal95=null;
        UPDDLParser.goalDesc_return goalDesc92 =null;

        UPDDLParser.effect_return effect96 =null;


        Object string_literal89_tree=null;
        Object char_literal90_tree=null;
        Object char_literal91_tree=null;
        Object string_literal93_tree=null;
        Object char_literal94_tree=null;
        Object char_literal95_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleSubtreeStream stream_effect=new RewriteRuleSubtreeStream(adaptor,"rule effect");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:2: ( ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )?
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==63) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:6: ':precondition' ( ( '(' ')' ) | goalDesc )
                    {
                    string_literal89=(Token)match(input,63,FOLLOW_63_in_actionDefBody883); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_63.add(string_literal89);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:22: ( ( '(' ')' ) | goalDesc )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==46) ) {
                        int LA29_1 = input.LA(2);

                        if ( (LA29_1==47) ) {
                            alt29=1;
                        }
                        else if ( (LA29_1==NAME||LA29_1==68||LA29_1==77) ) {
                            alt29=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 29, 1, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;

                    }
                    switch (alt29) {
                        case 1 :
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:23: ( '(' ')' )
                            {
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:23: ( '(' ')' )
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:24: '(' ')'
                            {
                            char_literal90=(Token)match(input,46,FOLLOW_46_in_actionDefBody887); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_46.add(char_literal90);


                            char_literal91=(Token)match(input,47,FOLLOW_47_in_actionDefBody889); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_47.add(char_literal91);


                            }


                            }
                            break;
                        case 2 :
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:219:35: goalDesc
                            {
                            pushFollow(FOLLOW_goalDesc_in_actionDefBody894);
                            goalDesc92=goalDesc();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc92.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:4: ( ':effect' ( ( '(' ')' ) | effect ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==55) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:6: ':effect' ( ( '(' ')' ) | effect )
                    {
                    string_literal93=(Token)match(input,55,FOLLOW_55_in_actionDefBody904); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_55.add(string_literal93);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:16: ( ( '(' ')' ) | effect )
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==46) ) {
                        int LA31_1 = input.LA(2);

                        if ( (LA31_1==47) ) {
                            alt31=1;
                        }
                        else if ( (LA31_1==NAME||(LA31_1 >= 68 && LA31_1 <= 70)||LA31_1==73||LA31_1==77||(LA31_1 >= 81 && LA31_1 <= 82)||LA31_1==85) ) {
                            alt31=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 31, 1, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 0, input);

                        throw nvae;

                    }
                    switch (alt31) {
                        case 1 :
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:17: ( '(' ')' )
                            {
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:17: ( '(' ')' )
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:18: '(' ')'
                            {
                            char_literal94=(Token)match(input,46,FOLLOW_46_in_actionDefBody908); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_46.add(char_literal94);


                            char_literal95=(Token)match(input,47,FOLLOW_47_in_actionDefBody910); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_47.add(char_literal95);


                            }


                            }
                            break;
                        case 2 :
                            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:220:29: effect
                            {
                            pushFollow(FOLLOW_effect_in_actionDefBody915);
                            effect96=effect();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_effect.add(effect96.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }


            // AST REWRITE
            // elements: goalDesc, effect
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 221:2: -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:221:6: ^( PRECONDITION ( goalDesc )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PRECONDITION, "PRECONDITION")
                , root_1);

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:221:21: ( goalDesc )?
                if ( stream_goalDesc.hasNext() ) {
                    adaptor.addChild(root_1, stream_goalDesc.nextTree());

                }
                stream_goalDesc.reset();

                adaptor.addChild(root_0, root_1);
                }

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:221:32: ^( EFFECT ( effect )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(EFFECT, "EFFECT")
                , root_1);

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:221:41: ( effect )?
                if ( stream_effect.hasNext() ) {
                    adaptor.addChild(root_1, stream_effect.nextTree());

                }
                stream_effect.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "actionDefBody"


    public static class goalDesc_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "goalDesc"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:224:1: goalDesc : ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) );
    public final UPDDLParser.goalDesc_return goalDesc() throws RecognitionException {
        UPDDLParser.goalDesc_return retval = new UPDDLParser.goalDesc_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal98=null;
        Token string_literal99=null;
        Token char_literal101=null;
        Token char_literal102=null;
        Token string_literal103=null;
        Token char_literal105=null;
        UPDDLParser.atomicTermFormula_return atomicTermFormula97 =null;

        UPDDLParser.goalDesc_return goalDesc100 =null;

        UPDDLParser.goalDesc_return goalDesc104 =null;


        Object char_literal98_tree=null;
        Object string_literal99_tree=null;
        Object char_literal101_tree=null;
        Object char_literal102_tree=null;
        Object string_literal103_tree=null;
        Object char_literal105_tree=null;
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:225:2: ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) )
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==46) ) {
                switch ( input.LA(2) ) {
                case 68:
                    {
                    alt34=2;
                    }
                    break;
                case 77:
                    {
                    alt34=3;
                    }
                    break;
                case NAME:
                    {
                    alt34=1;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }
            switch (alt34) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:225:4: atomicTermFormula
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicTermFormula_in_goalDesc947);
                    atomicTermFormula97=atomicTermFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTermFormula97.getTree());

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:226:4: '(' 'and' ( goalDesc )* ')'
                    {
                    char_literal98=(Token)match(input,46,FOLLOW_46_in_goalDesc952); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal98);


                    string_literal99=(Token)match(input,68,FOLLOW_68_in_goalDesc954); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_68.add(string_literal99);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:226:14: ( goalDesc )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==46) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:226:14: goalDesc
                    	    {
                    	    pushFollow(FOLLOW_goalDesc_in_goalDesc956);
                    	    goalDesc100=goalDesc();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc100.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);


                    char_literal101=(Token)match(input,47,FOLLOW_47_in_goalDesc959); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal101);


                    // AST REWRITE
                    // elements: goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 227:4: -> ^( AND_GD ( goalDesc )* )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:227:7: ^( AND_GD ( goalDesc )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(AND_GD, "AND_GD")
                        , root_1);

                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:227:16: ( goalDesc )*
                        while ( stream_goalDesc.hasNext() ) {
                            adaptor.addChild(root_1, stream_goalDesc.nextTree());

                        }
                        stream_goalDesc.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:228:4: '(' 'not' goalDesc ')'
                    {
                    char_literal102=(Token)match(input,46,FOLLOW_46_in_goalDesc976); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal102);


                    string_literal103=(Token)match(input,77,FOLLOW_77_in_goalDesc978); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_77.add(string_literal103);


                    pushFollow(FOLLOW_goalDesc_in_goalDesc980);
                    goalDesc104=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc104.getTree());

                    char_literal105=(Token)match(input,47,FOLLOW_47_in_goalDesc982); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal105);


                    // AST REWRITE
                    // elements: goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 229:4: -> ^( NOT_GD goalDesc )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:229:7: ^( NOT_GD goalDesc )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NOT_GD, "NOT_GD")
                        , root_1);

                        adaptor.addChild(root_1, stream_goalDesc.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "goalDesc"


    public static class atomicTermFormula_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicTermFormula"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:231:1: atomicTermFormula : '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) ;
    public final UPDDLParser.atomicTermFormula_return atomicTermFormula() throws RecognitionException {
        UPDDLParser.atomicTermFormula_return retval = new UPDDLParser.atomicTermFormula_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal106=null;
        Token char_literal109=null;
        UPDDLParser.predicate_return predicate107 =null;

        UPDDLParser.term_return term108 =null;


        Object char_literal106_tree=null;
        Object char_literal109_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        RewriteRuleSubtreeStream stream_predicate=new RewriteRuleSubtreeStream(adaptor,"rule predicate");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:232:2: ( '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:232:4: '(' predicate ( term )* ')'
            {
            char_literal106=(Token)match(input,46,FOLLOW_46_in_atomicTermFormula1002); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal106);


            pushFollow(FOLLOW_predicate_in_atomicTermFormula1004);
            predicate107=predicate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_predicate.add(predicate107.getTree());

            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:232:18: ( term )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==NAME||LA35_0==VARIABLE) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:232:18: term
            	    {
            	    pushFollow(FOLLOW_term_in_atomicTermFormula1006);
            	    term108=term();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_term.add(term108.getTree());

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            char_literal109=(Token)match(input,47,FOLLOW_47_in_atomicTermFormula1009); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal109);


            // AST REWRITE
            // elements: predicate, term
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 233:2: -> ^( PRED_HEAD predicate ( term )* )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:233:5: ^( PRED_HEAD predicate ( term )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PRED_HEAD, "PRED_HEAD")
                , root_1);

                adaptor.addChild(root_1, stream_predicate.nextTree());

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:233:27: ( term )*
                while ( stream_term.hasNext() ) {
                    adaptor.addChild(root_1, stream_term.nextTree());

                }
                stream_term.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicTermFormula"


    public static class term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "term"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:236:1: term : ( NAME | VARIABLE );
    public final UPDDLParser.term_return term() throws RecognitionException {
        UPDDLParser.term_return retval = new UPDDLParser.term_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set110=null;

        Object set110_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:236:6: ( NAME | VARIABLE )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            root_0 = (Object)adaptor.nil();


            set110=(Token)input.LT(1);

            if ( input.LA(1)==NAME||input.LA(1)==VARIABLE ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set110)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "term"


    public static class fExp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fExp"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:241:1: fExp : ( NUMBER | fHead );
    public final UPDDLParser.fExp_return fExp() throws RecognitionException {
        UPDDLParser.fExp_return retval = new UPDDLParser.fExp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token NUMBER111=null;
        UPDDLParser.fHead_return fHead112 =null;


        Object NUMBER111_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:242:2: ( NUMBER | fHead )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==NUMBER) ) {
                alt36=1;
            }
            else if ( (LA36_0==NAME||LA36_0==46) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }
            switch (alt36) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:242:4: NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER111=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_fExp1049); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER111_tree = 
                    (Object)adaptor.create(NUMBER111)
                    ;
                    adaptor.addChild(root_0, NUMBER111_tree);
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:243:4: fHead
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_fHead_in_fExp1054);
                    fHead112=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead112.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "fExp"


    public static class fHead_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fHead"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:246:1: fHead : ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) );
    public final UPDDLParser.fHead_return fHead() throws RecognitionException {
        UPDDLParser.fHead_return retval = new UPDDLParser.fHead_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal113=null;
        Token char_literal116=null;
        UPDDLParser.functionSymbol_return functionSymbol114 =null;

        UPDDLParser.term_return term115 =null;

        UPDDLParser.functionSymbol_return functionSymbol117 =null;


        Object char_literal113_tree=null;
        Object char_literal116_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        RewriteRuleSubtreeStream stream_functionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule functionSymbol");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:247:2: ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==46) ) {
                alt38=1;
            }
            else if ( (LA38_0==NAME) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:247:4: '(' functionSymbol ( term )* ')'
                    {
                    char_literal113=(Token)match(input,46,FOLLOW_46_in_fHead1065); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal113);


                    pushFollow(FOLLOW_functionSymbol_in_fHead1067);
                    functionSymbol114=functionSymbol();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionSymbol.add(functionSymbol114.getTree());

                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:247:23: ( term )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==NAME||LA37_0==VARIABLE) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:247:23: term
                    	    {
                    	    pushFollow(FOLLOW_term_in_fHead1069);
                    	    term115=term();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_term.add(term115.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);


                    char_literal116=(Token)match(input,47,FOLLOW_47_in_fHead1072); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal116);


                    // AST REWRITE
                    // elements: term, functionSymbol
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 247:33: -> ^( FUNC_HEAD functionSymbol ( term )* )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:247:36: ^( FUNC_HEAD functionSymbol ( term )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNC_HEAD, "FUNC_HEAD")
                        , root_1);

                        adaptor.addChild(root_1, stream_functionSymbol.nextTree());

                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:247:63: ( term )*
                        while ( stream_term.hasNext() ) {
                            adaptor.addChild(root_1, stream_term.nextTree());

                        }
                        stream_term.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:248:4: functionSymbol
                    {
                    pushFollow(FOLLOW_functionSymbol_in_fHead1088);
                    functionSymbol117=functionSymbol();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionSymbol.add(functionSymbol117.getTree());

                    // AST REWRITE
                    // elements: functionSymbol
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 248:19: -> ^( FUNC_HEAD functionSymbol )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:248:22: ^( FUNC_HEAD functionSymbol )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNC_HEAD, "FUNC_HEAD")
                        , root_1);

                        adaptor.addChild(root_1, stream_functionSymbol.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "fHead"


    public static class effect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "effect"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:251:1: effect : ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect );
    public final UPDDLParser.effect_return effect() throws RecognitionException {
        UPDDLParser.effect_return retval = new UPDDLParser.effect_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal118=null;
        Token string_literal119=null;
        Token char_literal121=null;
        UPDDLParser.cEffect_return cEffect120 =null;

        UPDDLParser.cEffect_return cEffect122 =null;


        Object char_literal118_tree=null;
        Object string_literal119_tree=null;
        Object char_literal121_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_cEffect=new RewriteRuleSubtreeStream(adaptor,"rule cEffect");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:252:2: ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==46) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==68) ) {
                    alt40=1;
                }
                else if ( (LA40_1==NAME||(LA40_1 >= 69 && LA40_1 <= 70)||LA40_1==73||LA40_1==77||(LA40_1 >= 81 && LA40_1 <= 82)||LA40_1==85) ) {
                    alt40=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }
            switch (alt40) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:252:4: '(' 'and' ( cEffect )* ')'
                    {
                    char_literal118=(Token)match(input,46,FOLLOW_46_in_effect1107); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal118);


                    string_literal119=(Token)match(input,68,FOLLOW_68_in_effect1109); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_68.add(string_literal119);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:252:14: ( cEffect )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==46) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:252:14: cEffect
                    	    {
                    	    pushFollow(FOLLOW_cEffect_in_effect1111);
                    	    cEffect120=cEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_cEffect.add(cEffect120.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    char_literal121=(Token)match(input,47,FOLLOW_47_in_effect1114); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal121);


                    // AST REWRITE
                    // elements: cEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 252:27: -> ^( AND_EFFECT ( cEffect )* )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:252:30: ^( AND_EFFECT ( cEffect )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(AND_EFFECT, "AND_EFFECT")
                        , root_1);

                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:252:43: ( cEffect )*
                        while ( stream_cEffect.hasNext() ) {
                            adaptor.addChild(root_1, stream_cEffect.nextTree());

                        }
                        stream_cEffect.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:253:4: cEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_cEffect_in_effect1128);
                    cEffect122=cEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cEffect122.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "effect"


    public static class cEffect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cEffect"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:256:1: cEffect : ( '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect );
    public final UPDDLParser.cEffect_return cEffect() throws RecognitionException {
        UPDDLParser.cEffect_return retval = new UPDDLParser.cEffect_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal123=null;
        Token string_literal124=null;
        Token char_literal127=null;
        UPDDLParser.goalDesc_return goalDesc125 =null;

        UPDDLParser.condEffect_return condEffect126 =null;

        UPDDLParser.pEffect_return pEffect128 =null;


        Object char_literal123_tree=null;
        Object string_literal124_tree=null;
        Object char_literal127_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
        RewriteRuleSubtreeStream stream_condEffect=new RewriteRuleSubtreeStream(adaptor,"rule condEffect");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:257:2: ( '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==46) ) {
                int LA41_1 = input.LA(2);

                if ( (LA41_1==85) ) {
                    alt41=1;
                }
                else if ( (LA41_1==NAME||(LA41_1 >= 69 && LA41_1 <= 70)||LA41_1==73||LA41_1==77||(LA41_1 >= 81 && LA41_1 <= 82)) ) {
                    alt41=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:257:4: '(' 'when' goalDesc condEffect ')'
                    {
                    char_literal123=(Token)match(input,46,FOLLOW_46_in_cEffect1139); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal123);


                    string_literal124=(Token)match(input,85,FOLLOW_85_in_cEffect1141); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_85.add(string_literal124);


                    pushFollow(FOLLOW_goalDesc_in_cEffect1143);
                    goalDesc125=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc125.getTree());

                    pushFollow(FOLLOW_condEffect_in_cEffect1145);
                    condEffect126=condEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_condEffect.add(condEffect126.getTree());

                    char_literal127=(Token)match(input,47,FOLLOW_47_in_cEffect1147); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal127);


                    // AST REWRITE
                    // elements: goalDesc, condEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 257:39: -> ^( WHEN_EFFECT goalDesc condEffect )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:257:42: ^( WHEN_EFFECT goalDesc condEffect )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(WHEN_EFFECT, "WHEN_EFFECT")
                        , root_1);

                        adaptor.addChild(root_1, stream_goalDesc.nextTree());

                        adaptor.addChild(root_1, stream_condEffect.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:258:4: pEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pEffect_in_cEffect1162);
                    pEffect128=pEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pEffect128.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "cEffect"


    public static class pEffect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pEffect"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:261:1: pEffect : ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula );
    public final UPDDLParser.pEffect_return pEffect() throws RecognitionException {
        UPDDLParser.pEffect_return retval = new UPDDLParser.pEffect_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal129=null;
        Token char_literal133=null;
        Token char_literal134=null;
        Token string_literal135=null;
        Token char_literal137=null;
        UPDDLParser.assignOp_return assignOp130 =null;

        UPDDLParser.fHead_return fHead131 =null;

        UPDDLParser.fExp_return fExp132 =null;

        UPDDLParser.atomicTermFormula_return atomicTermFormula136 =null;

        UPDDLParser.atomicTermFormula_return atomicTermFormula138 =null;


        Object char_literal129_tree=null;
        Object char_literal133_tree=null;
        Object char_literal134_tree=null;
        Object string_literal135_tree=null;
        Object char_literal137_tree=null;
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_fHead=new RewriteRuleSubtreeStream(adaptor,"rule fHead");
        RewriteRuleSubtreeStream stream_assignOp=new RewriteRuleSubtreeStream(adaptor,"rule assignOp");
        RewriteRuleSubtreeStream stream_atomicTermFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicTermFormula");
        RewriteRuleSubtreeStream stream_fExp=new RewriteRuleSubtreeStream(adaptor,"rule fExp");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:262:2: ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula )
            int alt42=3;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==46) ) {
                switch ( input.LA(2) ) {
                case 77:
                    {
                    alt42=2;
                    }
                    break;
                case 69:
                case 70:
                case 73:
                case 81:
                case 82:
                    {
                    alt42=1;
                    }
                    break;
                case NAME:
                    {
                    alt42=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:262:4: '(' assignOp fHead fExp ')'
                    {
                    char_literal129=(Token)match(input,46,FOLLOW_46_in_pEffect1173); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal129);


                    pushFollow(FOLLOW_assignOp_in_pEffect1175);
                    assignOp130=assignOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignOp.add(assignOp130.getTree());

                    pushFollow(FOLLOW_fHead_in_pEffect1177);
                    fHead131=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fHead.add(fHead131.getTree());

                    pushFollow(FOLLOW_fExp_in_pEffect1179);
                    fExp132=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp132.getTree());

                    char_literal133=(Token)match(input,47,FOLLOW_47_in_pEffect1181); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal133);


                    // AST REWRITE
                    // elements: fHead, fExp, assignOp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 262:32: -> ^( ASSIGN_EFFECT assignOp fHead fExp )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:262:35: ^( ASSIGN_EFFECT assignOp fHead fExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ASSIGN_EFFECT, "ASSIGN_EFFECT")
                        , root_1);

                        adaptor.addChild(root_1, stream_assignOp.nextTree());

                        adaptor.addChild(root_1, stream_fHead.nextTree());

                        adaptor.addChild(root_1, stream_fExp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:263:4: '(' 'not' atomicTermFormula ')'
                    {
                    char_literal134=(Token)match(input,46,FOLLOW_46_in_pEffect1198); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal134);


                    string_literal135=(Token)match(input,77,FOLLOW_77_in_pEffect1200); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_77.add(string_literal135);


                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect1202);
                    atomicTermFormula136=atomicTermFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atomicTermFormula.add(atomicTermFormula136.getTree());

                    char_literal137=(Token)match(input,47,FOLLOW_47_in_pEffect1204); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal137);


                    // AST REWRITE
                    // elements: atomicTermFormula
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 263:36: -> ^( NOT_EFFECT atomicTermFormula )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:263:39: ^( NOT_EFFECT atomicTermFormula )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NOT_EFFECT, "NOT_EFFECT")
                        , root_1);

                        adaptor.addChild(root_1, stream_atomicTermFormula.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:264:4: atomicTermFormula
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect1217);
                    atomicTermFormula138=atomicTermFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTermFormula138.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pEffect"


    public static class condEffect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "condEffect"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:267:1: condEffect : ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect );
    public final UPDDLParser.condEffect_return condEffect() throws RecognitionException {
        UPDDLParser.condEffect_return retval = new UPDDLParser.condEffect_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal139=null;
        Token string_literal140=null;
        Token char_literal142=null;
        UPDDLParser.pEffect_return pEffect141 =null;

        UPDDLParser.pEffect_return pEffect143 =null;


        Object char_literal139_tree=null;
        Object string_literal140_tree=null;
        Object char_literal142_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_pEffect=new RewriteRuleSubtreeStream(adaptor,"rule pEffect");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:268:2: ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==46) ) {
                int LA44_1 = input.LA(2);

                if ( (LA44_1==68) ) {
                    alt44=1;
                }
                else if ( (LA44_1==NAME||(LA44_1 >= 69 && LA44_1 <= 70)||LA44_1==73||LA44_1==77||(LA44_1 >= 81 && LA44_1 <= 82)) ) {
                    alt44=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }
            switch (alt44) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:268:4: '(' 'and' ( pEffect )* ')'
                    {
                    char_literal139=(Token)match(input,46,FOLLOW_46_in_condEffect1228); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal139);


                    string_literal140=(Token)match(input,68,FOLLOW_68_in_condEffect1230); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_68.add(string_literal140);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:268:14: ( pEffect )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==46) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:268:14: pEffect
                    	    {
                    	    pushFollow(FOLLOW_pEffect_in_condEffect1232);
                    	    pEffect141=pEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_pEffect.add(pEffect141.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    char_literal142=(Token)match(input,47,FOLLOW_47_in_condEffect1235); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal142);


                    // AST REWRITE
                    // elements: pEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 268:27: -> ^( AND_EFFECT ( pEffect )* )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:268:30: ^( AND_EFFECT ( pEffect )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(AND_EFFECT, "AND_EFFECT")
                        , root_1);

                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:268:43: ( pEffect )*
                        while ( stream_pEffect.hasNext() ) {
                            adaptor.addChild(root_1, stream_pEffect.nextTree());

                        }
                        stream_pEffect.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:269:4: pEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pEffect_in_condEffect1249);
                    pEffect143=pEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pEffect143.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "condEffect"


    public static class binaryOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binaryOp"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:273:1: binaryOp : ( '*' | '+' | '-' | '/' );
    public final UPDDLParser.binaryOp_return binaryOp() throws RecognitionException {
        UPDDLParser.binaryOp_return retval = new UPDDLParser.binaryOp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set144=null;

        Object set144_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:273:10: ( '*' | '+' | '-' | '/' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            root_0 = (Object)adaptor.nil();


            set144=(Token)input.LT(1);

            if ( (input.LA(1) >= 48 && input.LA(1) <= 51) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set144)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "binaryOp"


    public static class assignOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignOp"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:275:1: assignOp : ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' );
    public final UPDDLParser.assignOp_return assignOp() throws RecognitionException {
        UPDDLParser.assignOp_return retval = new UPDDLParser.assignOp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set145=null;

        Object set145_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:275:10: ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            root_0 = (Object)adaptor.nil();


            set145=(Token)input.LT(1);

            if ( (input.LA(1) >= 69 && input.LA(1) <= 70)||input.LA(1)==73||(input.LA(1) >= 81 && input.LA(1) <= 82) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set145)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignOp"


    public static class problem_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "problem"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:280:1: problem : '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? ) ;
    public final UPDDLParser.problem_return problem() throws RecognitionException {
        UPDDLParser.problem_return retval = new UPDDLParser.problem_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal146=null;
        Token string_literal147=null;
        Token char_literal155=null;
        UPDDLParser.problemDecl_return problemDecl148 =null;

        UPDDLParser.problemDomain_return problemDomain149 =null;

        UPDDLParser.requireDef_return requireDef150 =null;

        UPDDLParser.objectDecl_return objectDecl151 =null;

        UPDDLParser.init_return init152 =null;

        UPDDLParser.goal_return goal153 =null;

        UPDDLParser.metricSpec_return metricSpec154 =null;


        Object char_literal146_tree=null;
        Object string_literal147_tree=null;
        Object char_literal155_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_metricSpec=new RewriteRuleSubtreeStream(adaptor,"rule metricSpec");
        RewriteRuleSubtreeStream stream_objectDecl=new RewriteRuleSubtreeStream(adaptor,"rule objectDecl");
        RewriteRuleSubtreeStream stream_problemDecl=new RewriteRuleSubtreeStream(adaptor,"rule problemDecl");
        RewriteRuleSubtreeStream stream_requireDef=new RewriteRuleSubtreeStream(adaptor,"rule requireDef");
        RewriteRuleSubtreeStream stream_init=new RewriteRuleSubtreeStream(adaptor,"rule init");
        RewriteRuleSubtreeStream stream_problemDomain=new RewriteRuleSubtreeStream(adaptor,"rule problemDomain");
        RewriteRuleSubtreeStream stream_goal=new RewriteRuleSubtreeStream(adaptor,"rule goal");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:281:2: ( '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:281:3: '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? ')'
            {
            char_literal146=(Token)match(input,46,FOLLOW_46_in_problem1309); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal146);


            string_literal147=(Token)match(input,71,FOLLOW_71_in_problem1311); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(string_literal147);


            pushFollow(FOLLOW_problemDecl_in_problem1313);
            problemDecl148=problemDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_problemDecl.add(problemDecl148.getTree());

            pushFollow(FOLLOW_problemDomain_in_problem1316);
            problemDomain149=problemDomain();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_problemDomain.add(problemDomain149.getTree());

            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:283:2: ( requireDef )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==46) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==65) ) {
                    alt45=1;
                }
            }
            switch (alt45) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:283:2: requireDef
                    {
                    pushFollow(FOLLOW_requireDef_in_problem1319);
                    requireDef150=requireDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_requireDef.add(requireDef150.getTree());

                    }
                    break;

            }


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:284:2: ( objectDecl )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==46) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==61) ) {
                    alt46=1;
                }
            }
            switch (alt46) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:284:2: objectDecl
                    {
                    pushFollow(FOLLOW_objectDecl_in_problem1323);
                    objectDecl151=objectDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_objectDecl.add(objectDecl151.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_init_in_problem1327);
            init152=init();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_init.add(init152.getTree());

            pushFollow(FOLLOW_goal_in_problem1330);
            goal153=goal();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_goal.add(goal153.getTree());

            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:287:2: ( metricSpec )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==46) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:287:2: metricSpec
                    {
                    pushFollow(FOLLOW_metricSpec_in_problem1333);
                    metricSpec154=metricSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_metricSpec.add(metricSpec154.getTree());

                    }
                    break;

            }


            char_literal155=(Token)match(input,47,FOLLOW_47_in_problem1337); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal155);


            // AST REWRITE
            // elements: objectDecl, problemDomain, requireDef, init, metricSpec, problemDecl, goal
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 289:2: -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:289:5: ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( metricSpec )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM, "PROBLEM")
                , root_1);

                adaptor.addChild(root_1, stream_problemDecl.nextTree());

                adaptor.addChild(root_1, stream_problemDomain.nextTree());

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:289:41: ( requireDef )?
                if ( stream_requireDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_requireDef.nextTree());

                }
                stream_requireDef.reset();

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:289:53: ( objectDecl )?
                if ( stream_objectDecl.hasNext() ) {
                    adaptor.addChild(root_1, stream_objectDecl.nextTree());

                }
                stream_objectDecl.reset();

                adaptor.addChild(root_1, stream_init.nextTree());

                adaptor.addChild(root_1, stream_goal.nextTree());

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:289:75: ( metricSpec )?
                if ( stream_metricSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_metricSpec.nextTree());

                }
                stream_metricSpec.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "problem"


    public static class problemDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "problemDecl"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:292:1: problemDecl : '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) ;
    public final UPDDLParser.problemDecl_return problemDecl() throws RecognitionException {
        UPDDLParser.problemDecl_return retval = new UPDDLParser.problemDecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal156=null;
        Token string_literal157=null;
        Token NAME158=null;
        Token char_literal159=null;

        Object char_literal156_tree=null;
        Object string_literal157_tree=null;
        Object NAME158_tree=null;
        Object char_literal159_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:293:2: ( '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:293:4: '(' 'problem' NAME ')'
            {
            char_literal156=(Token)match(input,46,FOLLOW_46_in_problemDecl1372); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal156);


            string_literal157=(Token)match(input,80,FOLLOW_80_in_problemDecl1374); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_80.add(string_literal157);


            NAME158=(Token)match(input,NAME,FOLLOW_NAME_in_problemDecl1376); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(NAME158);


            char_literal159=(Token)match(input,47,FOLLOW_47_in_problemDecl1378); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal159);


            // AST REWRITE
            // elements: NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 294:2: -> ^( PROBLEM_NAME NAME )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:294:5: ^( PROBLEM_NAME NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_NAME, "PROBLEM_NAME")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NAME.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "problemDecl"


    public static class problemDomain_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "problemDomain"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:297:1: problemDomain : '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) ;
    public final UPDDLParser.problemDomain_return problemDomain() throws RecognitionException {
        UPDDLParser.problemDomain_return retval = new UPDDLParser.problemDomain_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal160=null;
        Token string_literal161=null;
        Token NAME162=null;
        Token char_literal163=null;

        Object char_literal160_tree=null;
        Object string_literal161_tree=null;
        Object NAME162_tree=null;
        Object char_literal163_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:298:2: ( '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:298:4: '(' ':domain' NAME ')'
            {
            char_literal160=(Token)match(input,46,FOLLOW_46_in_problemDomain1398); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal160);


            string_literal161=(Token)match(input,54,FOLLOW_54_in_problemDomain1400); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_54.add(string_literal161);


            NAME162=(Token)match(input,NAME,FOLLOW_NAME_in_problemDomain1402); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(NAME162);


            char_literal163=(Token)match(input,47,FOLLOW_47_in_problemDomain1404); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal163);


            // AST REWRITE
            // elements: NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 299:2: -> ^( PROBLEM_DOMAIN NAME )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:299:5: ^( PROBLEM_DOMAIN NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_DOMAIN, "PROBLEM_DOMAIN")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NAME.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "problemDomain"


    public static class objectDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objectDecl"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:302:1: objectDecl : '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) ;
    public final UPDDLParser.objectDecl_return objectDecl() throws RecognitionException {
        UPDDLParser.objectDecl_return retval = new UPDDLParser.objectDecl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal164=null;
        Token string_literal165=null;
        Token char_literal167=null;
        UPDDLParser.typedNameList_return typedNameList166 =null;


        Object char_literal164_tree=null;
        Object string_literal165_tree=null;
        Object char_literal167_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:303:2: ( '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:303:4: '(' ':objects' typedNameList ')'
            {
            char_literal164=(Token)match(input,46,FOLLOW_46_in_objectDecl1424); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal164);


            string_literal165=(Token)match(input,61,FOLLOW_61_in_objectDecl1426); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_61.add(string_literal165);


            pushFollow(FOLLOW_typedNameList_in_objectDecl1428);
            typedNameList166=typedNameList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList166.getTree());

            char_literal167=(Token)match(input,47,FOLLOW_47_in_objectDecl1430); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal167);


            // AST REWRITE
            // elements: typedNameList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 304:2: -> ^( OBJECTS typedNameList )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:304:5: ^( OBJECTS typedNameList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(OBJECTS, "OBJECTS")
                , root_1);

                adaptor.addChild(root_1, stream_typedNameList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "objectDecl"


    public static class init_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "init"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:307:1: init : '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) ;
    public final UPDDLParser.init_return init() throws RecognitionException {
        UPDDLParser.init_return retval = new UPDDLParser.init_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal168=null;
        Token string_literal169=null;
        Token char_literal171=null;
        UPDDLParser.initEl_return initEl170 =null;


        Object char_literal168_tree=null;
        Object string_literal169_tree=null;
        Object char_literal171_tree=null;
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_initEl=new RewriteRuleSubtreeStream(adaptor,"rule initEl");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:308:2: ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:308:4: '(' ':init' ( initEl )* ')'
            {
            char_literal168=(Token)match(input,46,FOLLOW_46_in_init1450); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal168);


            string_literal169=(Token)match(input,58,FOLLOW_58_in_init1452); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(string_literal169);


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:308:16: ( initEl )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==46) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:308:16: initEl
            	    {
            	    pushFollow(FOLLOW_initEl_in_init1454);
            	    initEl170=initEl();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_initEl.add(initEl170.getTree());

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            char_literal171=(Token)match(input,47,FOLLOW_47_in_init1457); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal171);


            // AST REWRITE
            // elements: initEl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 309:2: -> ^( INIT ( initEl )* )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:309:5: ^( INIT ( initEl )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(INIT, "INIT")
                , root_1);

                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:309:12: ( initEl )*
                while ( stream_initEl.hasNext() ) {
                    adaptor.addChild(root_1, stream_initEl.nextTree());

                }
                stream_initEl.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "init"


    public static class initEl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "initEl"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:312:1: initEl : ( nameLiteral | '(' 'oneof' ( nameLiteral )+ ')' -> ^( ONEOF ( nameLiteral )+ ) | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) );
    public final UPDDLParser.initEl_return initEl() throws RecognitionException {
        UPDDLParser.initEl_return retval = new UPDDLParser.initEl_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal173=null;
        Token string_literal174=null;
        Token char_literal176=null;
        Token char_literal177=null;
        Token char_literal178=null;
        Token NUMBER180=null;
        Token char_literal181=null;
        UPDDLParser.nameLiteral_return nameLiteral172 =null;

        UPDDLParser.nameLiteral_return nameLiteral175 =null;

        UPDDLParser.fHead_return fHead179 =null;


        Object char_literal173_tree=null;
        Object string_literal174_tree=null;
        Object char_literal176_tree=null;
        Object char_literal177_tree=null;
        Object char_literal178_tree=null;
        Object NUMBER180_tree=null;
        Object char_literal181_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleSubtreeStream stream_fHead=new RewriteRuleSubtreeStream(adaptor,"rule fHead");
        RewriteRuleSubtreeStream stream_nameLiteral=new RewriteRuleSubtreeStream(adaptor,"rule nameLiteral");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:313:2: ( nameLiteral | '(' 'oneof' ( nameLiteral )+ ')' -> ^( ONEOF ( nameLiteral )+ ) | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) )
            int alt50=3;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==46) ) {
                switch ( input.LA(2) ) {
                case NAME:
                case 77:
                case 84:
                    {
                    alt50=1;
                    }
                    break;
                case 79:
                    {
                    alt50=2;
                    }
                    break;
                case 67:
                    {
                    alt50=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }
            switch (alt50) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:313:4: nameLiteral
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_nameLiteral_in_initEl1478);
                    nameLiteral172=nameLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nameLiteral172.getTree());

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:314:4: '(' 'oneof' ( nameLiteral )+ ')'
                    {
                    char_literal173=(Token)match(input,46,FOLLOW_46_in_initEl1483); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal173);


                    string_literal174=(Token)match(input,79,FOLLOW_79_in_initEl1485); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_79.add(string_literal174);


                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:314:16: ( nameLiteral )+
                    int cnt49=0;
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==46) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:314:16: nameLiteral
                    	    {
                    	    pushFollow(FOLLOW_nameLiteral_in_initEl1487);
                    	    nameLiteral175=nameLiteral();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_nameLiteral.add(nameLiteral175.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt49 >= 1 ) break loop49;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(49, input);
                                throw eee;
                        }
                        cnt49++;
                    } while (true);


                    char_literal176=(Token)match(input,47,FOLLOW_47_in_initEl1490); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal176);


                    // AST REWRITE
                    // elements: nameLiteral
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 314:33: -> ^( ONEOF ( nameLiteral )+ )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:314:36: ^( ONEOF ( nameLiteral )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ONEOF, "ONEOF")
                        , root_1);

                        if ( !(stream_nameLiteral.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_nameLiteral.hasNext() ) {
                            adaptor.addChild(root_1, stream_nameLiteral.nextTree());

                        }
                        stream_nameLiteral.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:315:4: '(' '=' fHead NUMBER ')'
                    {
                    char_literal177=(Token)match(input,46,FOLLOW_46_in_initEl1504); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal177);


                    char_literal178=(Token)match(input,67,FOLLOW_67_in_initEl1506); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_67.add(char_literal178);


                    pushFollow(FOLLOW_fHead_in_initEl1508);
                    fHead179=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fHead.add(fHead179.getTree());

                    NUMBER180=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_initEl1510); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NUMBER.add(NUMBER180);


                    char_literal181=(Token)match(input,47,FOLLOW_47_in_initEl1512); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal181);


                    // AST REWRITE
                    // elements: NUMBER, fHead
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 315:29: -> ^( INIT_EQ fHead NUMBER )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:315:32: ^( INIT_EQ fHead NUMBER )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(INIT_EQ, "INIT_EQ")
                        , root_1);

                        adaptor.addChild(root_1, stream_fHead.nextTree());

                        adaptor.addChild(root_1, 
                        stream_NUMBER.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "initEl"


    public static class nameLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nameLiteral"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:318:1: nameLiteral : ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) );
    public final UPDDLParser.nameLiteral_return nameLiteral() throws RecognitionException {
        UPDDLParser.nameLiteral_return retval = new UPDDLParser.nameLiteral_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal183=null;
        Token string_literal184=null;
        Token char_literal186=null;
        UPDDLParser.atomicNameFormula_return atomicNameFormula182 =null;

        UPDDLParser.atomicNameFormula_return atomicNameFormula185 =null;


        Object char_literal183_tree=null;
        Object string_literal184_tree=null;
        Object char_literal186_tree=null;
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_atomicNameFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicNameFormula");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:319:2: ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==46) ) {
                int LA51_1 = input.LA(2);

                if ( (LA51_1==NAME||LA51_1==84) ) {
                    alt51=1;
                }
                else if ( (LA51_1==77) ) {
                    alt51=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 51, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }
            switch (alt51) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:319:4: atomicNameFormula
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral1533);
                    atomicNameFormula182=atomicNameFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicNameFormula182.getTree());

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:320:4: '(' 'not' atomicNameFormula ')'
                    {
                    char_literal183=(Token)match(input,46,FOLLOW_46_in_nameLiteral1538); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal183);


                    string_literal184=(Token)match(input,77,FOLLOW_77_in_nameLiteral1540); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_77.add(string_literal184);


                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral1542);
                    atomicNameFormula185=atomicNameFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula185.getTree());

                    char_literal186=(Token)match(input,47,FOLLOW_47_in_nameLiteral1544); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal186);


                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 320:36: -> ^( NOT_PRED_INIT atomicNameFormula )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:320:39: ^( NOT_PRED_INIT atomicNameFormula )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NOT_PRED_INIT, "NOT_PRED_INIT")
                        , root_1);

                        adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "nameLiteral"


    public static class atomicNameFormula_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicNameFormula"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:323:1: atomicNameFormula : ( '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) | '(' 'unknown' '(' predicate ( NAME )* ')' ')' -> ^( UNKNOWN_PRED_INST predicate ( NAME )* ) );
    public final UPDDLParser.atomicNameFormula_return atomicNameFormula() throws RecognitionException {
        UPDDLParser.atomicNameFormula_return retval = new UPDDLParser.atomicNameFormula_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal187=null;
        Token NAME189=null;
        Token char_literal190=null;
        Token char_literal191=null;
        Token string_literal192=null;
        Token char_literal193=null;
        Token NAME195=null;
        Token char_literal196=null;
        Token char_literal197=null;
        UPDDLParser.predicate_return predicate188 =null;

        UPDDLParser.predicate_return predicate194 =null;


        Object char_literal187_tree=null;
        Object NAME189_tree=null;
        Object char_literal190_tree=null;
        Object char_literal191_tree=null;
        Object string_literal192_tree=null;
        Object char_literal193_tree=null;
        Object NAME195_tree=null;
        Object char_literal196_tree=null;
        Object char_literal197_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_predicate=new RewriteRuleSubtreeStream(adaptor,"rule predicate");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:324:2: ( '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) | '(' 'unknown' '(' predicate ( NAME )* ')' ')' -> ^( UNKNOWN_PRED_INST predicate ( NAME )* ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==46) ) {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==84) ) {
                    alt54=2;
                }
                else if ( (LA54_1==NAME) ) {
                    alt54=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 54, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;

            }
            switch (alt54) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:324:4: '(' predicate ( NAME )* ')'
                    {
                    char_literal187=(Token)match(input,46,FOLLOW_46_in_atomicNameFormula1563); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal187);


                    pushFollow(FOLLOW_predicate_in_atomicNameFormula1565);
                    predicate188=predicate();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_predicate.add(predicate188.getTree());

                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:324:18: ( NAME )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==NAME) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:324:18: NAME
                    	    {
                    	    NAME189=(Token)match(input,NAME,FOLLOW_NAME_in_atomicNameFormula1567); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_NAME.add(NAME189);


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    char_literal190=(Token)match(input,47,FOLLOW_47_in_atomicNameFormula1570); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal190);


                    // AST REWRITE
                    // elements: predicate, NAME
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 324:28: -> ^( PRED_INST predicate ( NAME )* )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:324:31: ^( PRED_INST predicate ( NAME )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(PRED_INST, "PRED_INST")
                        , root_1);

                        adaptor.addChild(root_1, stream_predicate.nextTree());

                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:324:53: ( NAME )*
                        while ( stream_NAME.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_NAME.nextNode()
                            );

                        }
                        stream_NAME.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:325:4: '(' 'unknown' '(' predicate ( NAME )* ')' ')'
                    {
                    char_literal191=(Token)match(input,46,FOLLOW_46_in_atomicNameFormula1586); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal191);


                    string_literal192=(Token)match(input,84,FOLLOW_84_in_atomicNameFormula1588); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(string_literal192);


                    char_literal193=(Token)match(input,46,FOLLOW_46_in_atomicNameFormula1590); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal193);


                    pushFollow(FOLLOW_predicate_in_atomicNameFormula1592);
                    predicate194=predicate();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_predicate.add(predicate194.getTree());

                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:325:32: ( NAME )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==NAME) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:325:32: NAME
                    	    {
                    	    NAME195=(Token)match(input,NAME,FOLLOW_NAME_in_atomicNameFormula1594); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_NAME.add(NAME195);


                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);


                    char_literal196=(Token)match(input,47,FOLLOW_47_in_atomicNameFormula1597); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal196);


                    char_literal197=(Token)match(input,47,FOLLOW_47_in_atomicNameFormula1599); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal197);


                    // AST REWRITE
                    // elements: NAME, predicate
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 325:46: -> ^( UNKNOWN_PRED_INST predicate ( NAME )* )
                    {
                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:325:49: ^( UNKNOWN_PRED_INST predicate ( NAME )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(UNKNOWN_PRED_INST, "UNKNOWN_PRED_INST")
                        , root_1);

                        adaptor.addChild(root_1, stream_predicate.nextTree());

                        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:325:79: ( NAME )*
                        while ( stream_NAME.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_NAME.nextNode()
                            );

                        }
                        stream_NAME.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atomicNameFormula"


    public static class goal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "goal"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:332:1: goal : '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) ;
    public final UPDDLParser.goal_return goal() throws RecognitionException {
        UPDDLParser.goal_return retval = new UPDDLParser.goal_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal198=null;
        Token string_literal199=null;
        Token char_literal201=null;
        UPDDLParser.goalDesc_return goalDesc200 =null;


        Object char_literal198_tree=null;
        Object string_literal199_tree=null;
        Object char_literal201_tree=null;
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:332:6: ( '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:332:8: '(' ':goal' goalDesc ')'
            {
            char_literal198=(Token)match(input,46,FOLLOW_46_in_goal1624); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal198);


            string_literal199=(Token)match(input,57,FOLLOW_57_in_goal1626); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_57.add(string_literal199);


            pushFollow(FOLLOW_goalDesc_in_goal1628);
            goalDesc200=goalDesc();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc200.getTree());

            char_literal201=(Token)match(input,47,FOLLOW_47_in_goal1630); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal201);


            // AST REWRITE
            // elements: goalDesc
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 332:33: -> ^( GOAL goalDesc )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:332:36: ^( GOAL goalDesc )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(GOAL, "GOAL")
                , root_1);

                adaptor.addChild(root_1, stream_goalDesc.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "goal"


    public static class metricSpec_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "metricSpec"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:334:1: metricSpec : '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) ;
    public final UPDDLParser.metricSpec_return metricSpec() throws RecognitionException {
        UPDDLParser.metricSpec_return retval = new UPDDLParser.metricSpec_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal202=null;
        Token string_literal203=null;
        Token char_literal206=null;
        UPDDLParser.optimization_return optimization204 =null;

        UPDDLParser.metricFExp_return metricFExp205 =null;


        Object char_literal202_tree=null;
        Object string_literal203_tree=null;
        Object char_literal206_tree=null;
        RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_optimization=new RewriteRuleSubtreeStream(adaptor,"rule optimization");
        RewriteRuleSubtreeStream stream_metricFExp=new RewriteRuleSubtreeStream(adaptor,"rule metricFExp");
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:335:2: ( '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:335:4: '(' ':metric' optimization metricFExp ')'
            {
            char_literal202=(Token)match(input,46,FOLLOW_46_in_metricSpec1648); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_46.add(char_literal202);


            string_literal203=(Token)match(input,59,FOLLOW_59_in_metricSpec1650); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_59.add(string_literal203);


            pushFollow(FOLLOW_optimization_in_metricSpec1652);
            optimization204=optimization();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_optimization.add(optimization204.getTree());

            pushFollow(FOLLOW_metricFExp_in_metricSpec1654);
            metricFExp205=metricFExp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp205.getTree());

            char_literal206=(Token)match(input,47,FOLLOW_47_in_metricSpec1656); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_47.add(char_literal206);


            // AST REWRITE
            // elements: optimization, metricFExp
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 336:4: -> ^( PROBLEM_METRIC optimization metricFExp )
            {
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:336:7: ^( PROBLEM_METRIC optimization metricFExp )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_METRIC, "PROBLEM_METRIC")
                , root_1);

                adaptor.addChild(root_1, stream_optimization.nextTree());

                adaptor.addChild(root_1, stream_metricFExp.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "metricSpec"


    public static class optimization_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "optimization"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:339:1: optimization : ( 'minimize' | 'maximize' );
    public final UPDDLParser.optimization_return optimization() throws RecognitionException {
        UPDDLParser.optimization_return retval = new UPDDLParser.optimization_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token set207=null;

        Object set207_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:339:14: ( 'minimize' | 'maximize' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            root_0 = (Object)adaptor.nil();


            set207=(Token)input.LT(1);

            if ( (input.LA(1) >= 75 && input.LA(1) <= 76) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set207)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "optimization"


    public static class metricFExp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "metricFExp"
    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:341:1: metricFExp : ( '(' binaryOp metricFExp metricFExp ')' | '(' ( '*' | '/' ) metricFExp ( metricFExp )+ ')' | '(' '-' metricFExp ')' | NUMBER | '(' functionSymbol ( NAME )* ')' | functionSymbol | 'total-time' | '(' 'is-violated' NAME ')' );
    public final UPDDLParser.metricFExp_return metricFExp() throws RecognitionException {
        UPDDLParser.metricFExp_return retval = new UPDDLParser.metricFExp_return();
        retval.start = input.LT(1);


        Object root_0 = null;

        Token char_literal208=null;
        Token char_literal212=null;
        Token char_literal213=null;
        Token set214=null;
        Token char_literal217=null;
        Token char_literal218=null;
        Token char_literal219=null;
        Token char_literal221=null;
        Token NUMBER222=null;
        Token char_literal223=null;
        Token NAME225=null;
        Token char_literal226=null;
        Token string_literal228=null;
        Token char_literal229=null;
        Token string_literal230=null;
        Token NAME231=null;
        Token char_literal232=null;
        UPDDLParser.binaryOp_return binaryOp209 =null;

        UPDDLParser.metricFExp_return metricFExp210 =null;

        UPDDLParser.metricFExp_return metricFExp211 =null;

        UPDDLParser.metricFExp_return metricFExp215 =null;

        UPDDLParser.metricFExp_return metricFExp216 =null;

        UPDDLParser.metricFExp_return metricFExp220 =null;

        UPDDLParser.functionSymbol_return functionSymbol224 =null;

        UPDDLParser.functionSymbol_return functionSymbol227 =null;


        Object char_literal208_tree=null;
        Object char_literal212_tree=null;
        Object char_literal213_tree=null;
        Object set214_tree=null;
        Object char_literal217_tree=null;
        Object char_literal218_tree=null;
        Object char_literal219_tree=null;
        Object char_literal221_tree=null;
        Object NUMBER222_tree=null;
        Object char_literal223_tree=null;
        Object NAME225_tree=null;
        Object char_literal226_tree=null;
        Object string_literal228_tree=null;
        Object char_literal229_tree=null;
        Object string_literal230_tree=null;
        Object NAME231_tree=null;
        Object char_literal232_tree=null;

        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:342:2: ( '(' binaryOp metricFExp metricFExp ')' | '(' ( '*' | '/' ) metricFExp ( metricFExp )+ ')' | '(' '-' metricFExp ')' | NUMBER | '(' functionSymbol ( NAME )* ')' | functionSymbol | 'total-time' | '(' 'is-violated' NAME ')' )
            int alt57=8;
            switch ( input.LA(1) ) {
            case 46:
                {
                int LA57_1 = input.LA(2);

                if ( (synpred67_UPDDL()) ) {
                    alt57=1;
                }
                else if ( (synpred70_UPDDL()) ) {
                    alt57=2;
                }
                else if ( (synpred71_UPDDL()) ) {
                    alt57=3;
                }
                else if ( (synpred74_UPDDL()) ) {
                    alt57=5;
                }
                else if ( (true) ) {
                    alt57=8;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 57, 1, input);

                    throw nvae;

                }
                }
                break;
            case NUMBER:
                {
                alt57=4;
                }
                break;
            case NAME:
                {
                alt57=6;
                }
                break;
            case 83:
                {
                alt57=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;

            }

            switch (alt57) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:342:4: '(' binaryOp metricFExp metricFExp ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal208=(Token)match(input,46,FOLLOW_46_in_metricFExp1693); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal208_tree = 
                    (Object)adaptor.create(char_literal208)
                    ;
                    adaptor.addChild(root_0, char_literal208_tree);
                    }

                    pushFollow(FOLLOW_binaryOp_in_metricFExp1695);
                    binaryOp209=binaryOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, binaryOp209.getTree());

                    pushFollow(FOLLOW_metricFExp_in_metricFExp1697);
                    metricFExp210=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metricFExp210.getTree());

                    pushFollow(FOLLOW_metricFExp_in_metricFExp1699);
                    metricFExp211=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metricFExp211.getTree());

                    char_literal212=(Token)match(input,47,FOLLOW_47_in_metricFExp1701); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal212_tree = 
                    (Object)adaptor.create(char_literal212)
                    ;
                    adaptor.addChild(root_0, char_literal212_tree);
                    }

                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:4: '(' ( '*' | '/' ) metricFExp ( metricFExp )+ ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal213=(Token)match(input,46,FOLLOW_46_in_metricFExp1706); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal213_tree = 
                    (Object)adaptor.create(char_literal213)
                    ;
                    adaptor.addChild(root_0, char_literal213_tree);
                    }

                    set214=(Token)input.LT(1);

                    if ( input.LA(1)==48||input.LA(1)==51 ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (Object)adaptor.create(set214)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_metricFExp_in_metricFExp1714);
                    metricFExp215=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metricFExp215.getTree());

                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:29: ( metricFExp )+
                    int cnt55=0;
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==NAME||LA55_0==NUMBER||LA55_0==46||LA55_0==83) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:29: metricFExp
                    	    {
                    	    pushFollow(FOLLOW_metricFExp_in_metricFExp1716);
                    	    metricFExp216=metricFExp();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, metricFExp216.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt55 >= 1 ) break loop55;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(55, input);
                                throw eee;
                        }
                        cnt55++;
                    } while (true);


                    char_literal217=(Token)match(input,47,FOLLOW_47_in_metricFExp1719); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal217_tree = 
                    (Object)adaptor.create(char_literal217)
                    ;
                    adaptor.addChild(root_0, char_literal217_tree);
                    }

                    }
                    break;
                case 3 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:344:4: '(' '-' metricFExp ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal218=(Token)match(input,46,FOLLOW_46_in_metricFExp1724); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal218_tree = 
                    (Object)adaptor.create(char_literal218)
                    ;
                    adaptor.addChild(root_0, char_literal218_tree);
                    }

                    char_literal219=(Token)match(input,50,FOLLOW_50_in_metricFExp1726); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal219_tree = 
                    (Object)adaptor.create(char_literal219)
                    ;
                    adaptor.addChild(root_0, char_literal219_tree);
                    }

                    pushFollow(FOLLOW_metricFExp_in_metricFExp1728);
                    metricFExp220=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metricFExp220.getTree());

                    char_literal221=(Token)match(input,47,FOLLOW_47_in_metricFExp1730); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal221_tree = 
                    (Object)adaptor.create(char_literal221)
                    ;
                    adaptor.addChild(root_0, char_literal221_tree);
                    }

                    }
                    break;
                case 4 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:345:4: NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER222=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_metricFExp1735); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER222_tree = 
                    (Object)adaptor.create(NUMBER222)
                    ;
                    adaptor.addChild(root_0, NUMBER222_tree);
                    }

                    }
                    break;
                case 5 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:4: '(' functionSymbol ( NAME )* ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal223=(Token)match(input,46,FOLLOW_46_in_metricFExp1740); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal223_tree = 
                    (Object)adaptor.create(char_literal223)
                    ;
                    adaptor.addChild(root_0, char_literal223_tree);
                    }

                    pushFollow(FOLLOW_functionSymbol_in_metricFExp1742);
                    functionSymbol224=functionSymbol();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionSymbol224.getTree());

                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:23: ( NAME )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==NAME) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:23: NAME
                    	    {
                    	    NAME225=(Token)match(input,NAME,FOLLOW_NAME_in_metricFExp1744); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NAME225_tree = 
                    	    (Object)adaptor.create(NAME225)
                    	    ;
                    	    adaptor.addChild(root_0, NAME225_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);


                    char_literal226=(Token)match(input,47,FOLLOW_47_in_metricFExp1747); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal226_tree = 
                    (Object)adaptor.create(char_literal226)
                    ;
                    adaptor.addChild(root_0, char_literal226_tree);
                    }

                    }
                    break;
                case 6 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:347:4: functionSymbol
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_functionSymbol_in_metricFExp1752);
                    functionSymbol227=functionSymbol();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionSymbol227.getTree());

                    }
                    break;
                case 7 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:348:4: 'total-time'
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal228=(Token)match(input,83,FOLLOW_83_in_metricFExp1757); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal228_tree = 
                    (Object)adaptor.create(string_literal228)
                    ;
                    adaptor.addChild(root_0, string_literal228_tree);
                    }

                    }
                    break;
                case 8 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:349:4: '(' 'is-violated' NAME ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal229=(Token)match(input,46,FOLLOW_46_in_metricFExp1762); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal229_tree = 
                    (Object)adaptor.create(char_literal229)
                    ;
                    adaptor.addChild(root_0, char_literal229_tree);
                    }

                    string_literal230=(Token)match(input,74,FOLLOW_74_in_metricFExp1764); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal230_tree = 
                    (Object)adaptor.create(string_literal230)
                    ;
                    adaptor.addChild(root_0, string_literal230_tree);
                    }

                    NAME231=(Token)match(input,NAME,FOLLOW_NAME_in_metricFExp1766); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME231_tree = 
                    (Object)adaptor.create(NAME231)
                    ;
                    adaptor.addChild(root_0, NAME231_tree);
                    }

                    char_literal232=(Token)match(input,47,FOLLOW_47_in_metricFExp1768); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal232_tree = 
                    (Object)adaptor.create(char_literal232)
                    ;
                    adaptor.addChild(root_0, char_literal232_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "metricFExp"

    // $ANTLR start synpred15_UPDDL
    public final void synpred15_UPDDL_fragment() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:5: ( atomicFunctionSkeleton )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:144:5: atomicFunctionSkeleton
        {
        pushFollow(FOLLOW_atomicFunctionSkeleton_in_synpred15_UPDDL498);
        atomicFunctionSkeleton();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_UPDDL

    // $ANTLR start synpred27_UPDDL
    public final void synpred27_UPDDL_fragment() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:20: ( ( '(' ')' ) )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:20: ( '(' ')' )
        {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:20: ( '(' ')' )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:211:21: '(' ')'
        {
        match(input,46,FOLLOW_46_in_synpred27_UPDDL841); if (state.failed) return ;

        match(input,47,FOLLOW_47_in_synpred27_UPDDL843); if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred27_UPDDL

    // $ANTLR start synpred67_UPDDL
    public final void synpred67_UPDDL_fragment() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:342:4: ( '(' binaryOp metricFExp metricFExp ')' )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:342:4: '(' binaryOp metricFExp metricFExp ')'
        {
        match(input,46,FOLLOW_46_in_synpred67_UPDDL1693); if (state.failed) return ;

        pushFollow(FOLLOW_binaryOp_in_synpred67_UPDDL1695);
        binaryOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred67_UPDDL1697);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred67_UPDDL1699);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,47,FOLLOW_47_in_synpred67_UPDDL1701); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred67_UPDDL

    // $ANTLR start synpred70_UPDDL
    public final void synpred70_UPDDL_fragment() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:4: ( '(' ( '*' | '/' ) metricFExp ( metricFExp )+ ')' )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:4: '(' ( '*' | '/' ) metricFExp ( metricFExp )+ ')'
        {
        match(input,46,FOLLOW_46_in_synpred70_UPDDL1706); if (state.failed) return ;

        if ( input.LA(1)==48||input.LA(1)==51 ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        pushFollow(FOLLOW_metricFExp_in_synpred70_UPDDL1714);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:29: ( metricFExp )+
        int cnt71=0;
        loop71:
        do {
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==NAME||LA71_0==NUMBER||LA71_0==46||LA71_0==83) ) {
                alt71=1;
            }


            switch (alt71) {
        	case 1 :
        	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:343:29: metricFExp
        	    {
        	    pushFollow(FOLLOW_metricFExp_in_synpred70_UPDDL1716);
        	    metricFExp();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt71 >= 1 ) break loop71;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(71, input);
                    throw eee;
            }
            cnt71++;
        } while (true);


        match(input,47,FOLLOW_47_in_synpred70_UPDDL1719); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred70_UPDDL

    // $ANTLR start synpred71_UPDDL
    public final void synpred71_UPDDL_fragment() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:344:4: ( '(' '-' metricFExp ')' )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:344:4: '(' '-' metricFExp ')'
        {
        match(input,46,FOLLOW_46_in_synpred71_UPDDL1724); if (state.failed) return ;

        match(input,50,FOLLOW_50_in_synpred71_UPDDL1726); if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred71_UPDDL1728);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,47,FOLLOW_47_in_synpred71_UPDDL1730); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred71_UPDDL

    // $ANTLR start synpred74_UPDDL
    public final void synpred74_UPDDL_fragment() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:4: ( '(' functionSymbol ( NAME )* ')' )
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:4: '(' functionSymbol ( NAME )* ')'
        {
        match(input,46,FOLLOW_46_in_synpred74_UPDDL1740); if (state.failed) return ;

        pushFollow(FOLLOW_functionSymbol_in_synpred74_UPDDL1742);
        functionSymbol();

        state._fsp--;
        if (state.failed) return ;

        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:23: ( NAME )*
        loop72:
        do {
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==NAME) ) {
                alt72=1;
            }


            switch (alt72) {
        	case 1 :
        	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:346:23: NAME
        	    {
        	    match(input,NAME,FOLLOW_NAME_in_synpred74_UPDDL1744); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop72;
            }
        } while (true);


        match(input,47,FOLLOW_47_in_synpred74_UPDDL1747); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred74_UPDDL

    // Delegated rules

    public final boolean synpred67_UPDDL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred67_UPDDL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_UPDDL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_UPDDL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred70_UPDDL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_UPDDL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_UPDDL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_UPDDL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred74_UPDDL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred74_UPDDL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred71_UPDDL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred71_UPDDL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA13 dfa13 = new DFA13(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA22 dfa22 = new DFA22(this);
    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA13_eotS =
        "\4\uffff";
    static final String DFA13_eofS =
        "\4\uffff";
    static final String DFA13_minS =
        "\2\27\2\uffff";
    static final String DFA13_maxS =
        "\1\57\1\62\2\uffff";
    static final String DFA13_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA13_specialS =
        "\4\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\27\uffff\1\2",
            "\1\1\27\uffff\1\2\2\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "126:4: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )";
        }
    }
    static final String DFA11_eotS =
        "\4\uffff";
    static final String DFA11_eofS =
        "\4\uffff";
    static final String DFA11_minS =
        "\2\27\2\uffff";
    static final String DFA11_maxS =
        "\1\57\1\62\2\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA11_specialS =
        "\4\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\27\uffff\1\2",
            "\1\1\27\uffff\1\2\2\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "()+ loopback of 126:13: ( singleTypeNameList )+";
        }
    }
    static final String DFA22_eotS =
        "\4\uffff";
    static final String DFA22_eofS =
        "\4\uffff";
    static final String DFA22_minS =
        "\2\53\2\uffff";
    static final String DFA22_maxS =
        "\1\57\1\62\2\uffff";
    static final String DFA22_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA22_specialS =
        "\4\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\1\3\uffff\1\2",
            "\1\1\3\uffff\1\2\2\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "173:4: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )";
        }
    }
    static final String DFA20_eotS =
        "\4\uffff";
    static final String DFA20_eofS =
        "\4\uffff";
    static final String DFA20_minS =
        "\2\53\2\uffff";
    static final String DFA20_maxS =
        "\1\57\1\62\2\uffff";
    static final String DFA20_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA20_specialS =
        "\4\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\1\3\uffff\1\2",
            "\1\1\3\uffff\1\2\2\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "()+ loopback of 173:17: ( singleTypeVarList )+";
        }
    }
 

    public static final BitSet FOLLOW_domain_in_npddlDoc201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_problem_in_npddlDoc205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_domain217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_domain219 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_domainName_in_domain221 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_requireDef_in_domain225 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_typesDef_in_domain230 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_constantsDef_in_domain235 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_predicatesDef_in_domain240 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_functionsDef_in_domain245 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_neversDef_in_domain250 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_actionDef_in_domain255 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_domain260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_domainName319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_domainName321 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAME_in_domainName323 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_domainName325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_requireDef345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_requireDef347 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_REQUIRE_KEY_in_requireDef349 = new BitSet(new long[]{0x0000810000000000L});
    public static final BitSet FOLLOW_47_in_requireDef352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_typesDef373 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_typesDef375 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_typedNameList_in_typesDef377 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_typesDef379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_typedNameList401 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_singleTypeNameList_in_typedNameList406 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_NAME_in_typedNameList409 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_NAME_in_singleTypeNameList423 = new BitSet(new long[]{0x0004000000800000L});
    public static final BitSet FOLLOW_50_in_singleTypeNameList426 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_type_in_singleTypeNameList430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primType_in_type452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_primType461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_functionsDef471 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_functionsDef473 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_functionList_in_functionsDef475 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_functionsDef477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_functionList498 = new BitSet(new long[]{0x0004400000000002L});
    public static final BitSet FOLLOW_50_in_functionList502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_functionType_in_functionList504 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_atomicFunctionSkeleton520 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_functionSymbol_in_atomicFunctionSkeleton523 = new BitSet(new long[]{0x0000880000000000L});
    public static final BitSet FOLLOW_typedVariableList_in_atomicFunctionSkeleton526 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_atomicFunctionSkeleton528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_functionSymbol539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_functionType548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_constantsDef559 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_constantsDef561 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_typedNameList_in_constantsDef563 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_constantsDef565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_predicatesDef585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_predicatesDef587 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_atomicFormulaSkeleton_in_predicatesDef589 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_predicatesDef592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_atomicFormulaSkeleton613 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_predicate_in_atomicFormulaSkeleton616 = new BitSet(new long[]{0x0000880000000000L});
    public static final BitSet FOLLOW_typedVariableList_in_atomicFormulaSkeleton619 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_atomicFormulaSkeleton621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_predicate632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList644 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_singleTypeVarList_in_typedVariableList649 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList652 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_singleTypeVarList666 = new BitSet(new long[]{0x0004080000000000L});
    public static final BitSet FOLLOW_50_in_singleTypeVarList669 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_type_in_singleTypeVarList673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_neversDef696 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_neversDef698 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_atomicPred_in_neversDef700 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_neversDef703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_atomicPred723 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_predicate_in_atomicPred726 = new BitSet(new long[]{0x0000880000800000L});
    public static final BitSet FOLLOW_constantOrTypedVariable_in_atomicPred729 = new BitSet(new long[]{0x0000880000800000L});
    public static final BitSet FOLLOW_47_in_atomicPred732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_constantOrTypedVariable744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_singleTypeVar_in_constantOrTypedVariable749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_singleTypeVar762 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_singleTypeVar764 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_type_in_singleTypeVar768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_actionDef793 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_actionDef795 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_actionSymbol_in_actionDef797 = new BitSet(new long[]{0xC080800000000000L});
    public static final BitSet FOLLOW_actionParameter_in_actionDef799 = new BitSet(new long[]{0x8080800000000000L});
    public static final BitSet FOLLOW_actionDefBody_in_actionDef801 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_actionDef803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_actionSymbol826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_actionParameter837 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_actionParameter841 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_actionParameter843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_actionParameter848 = new BitSet(new long[]{0x0000880000000000L});
    public static final BitSet FOLLOW_typedVariableList_in_actionParameter850 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_actionParameter852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_actionDefBody883 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_actionDefBody887 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_actionDefBody889 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_goalDesc_in_actionDefBody894 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_55_in_actionDefBody904 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_actionDefBody908 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_actionDefBody910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_effect_in_actionDefBody915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicTermFormula_in_goalDesc947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_goalDesc952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_goalDesc954 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc956 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_goalDesc959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_goalDesc976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_goalDesc978 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc980 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_goalDesc982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_atomicTermFormula1002 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_predicate_in_atomicTermFormula1004 = new BitSet(new long[]{0x0000880000800000L});
    public static final BitSet FOLLOW_term_in_atomicTermFormula1006 = new BitSet(new long[]{0x0000880000800000L});
    public static final BitSet FOLLOW_47_in_atomicTermFormula1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_fExp1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_fExp1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_fHead1065 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead1067 = new BitSet(new long[]{0x0000880000800000L});
    public static final BitSet FOLLOW_term_in_fHead1069 = new BitSet(new long[]{0x0000880000800000L});
    public static final BitSet FOLLOW_47_in_fHead1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_effect1107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_effect1109 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_cEffect_in_effect1111 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_effect1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cEffect_in_effect1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_cEffect1139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_cEffect1141 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_goalDesc_in_cEffect1143 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_condEffect_in_cEffect1145 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_cEffect1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_cEffect1162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_pEffect1173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000060260L});
    public static final BitSet FOLLOW_assignOp_in_pEffect1175 = new BitSet(new long[]{0x0000400000800000L});
    public static final BitSet FOLLOW_fHead_in_pEffect1177 = new BitSet(new long[]{0x0000400010800000L});
    public static final BitSet FOLLOW_fExp_in_pEffect1179 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_pEffect1181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_pEffect1198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_pEffect1200 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect1202 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_pEffect1204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_condEffect1228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_condEffect1230 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_pEffect_in_condEffect1232 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_condEffect1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_condEffect1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_problem1309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_problem1311 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_problemDecl_in_problem1313 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_problemDomain_in_problem1316 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_requireDef_in_problem1319 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_objectDecl_in_problem1323 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_init_in_problem1327 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_goal_in_problem1330 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_metricSpec_in_problem1333 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_problem1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_problemDecl1372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_problemDecl1374 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAME_in_problemDecl1376 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_problemDecl1378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_problemDomain1398 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_problemDomain1400 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAME_in_problemDomain1402 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_problemDomain1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_objectDecl1424 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_objectDecl1426 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_typedNameList_in_objectDecl1428 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_objectDecl1430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_init1450 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_init1452 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_initEl_in_init1454 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_init1457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl1478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_initEl1483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_initEl1485 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl1487 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_47_in_initEl1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_initEl1504 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_initEl1506 = new BitSet(new long[]{0x0000400000800000L});
    public static final BitSet FOLLOW_fHead_in_initEl1508 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl1510 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_initEl1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_nameLiteral1538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_nameLiteral1540 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral1542 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_nameLiteral1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_atomicNameFormula1563 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_predicate_in_atomicNameFormula1565 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_NAME_in_atomicNameFormula1567 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_47_in_atomicNameFormula1570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_atomicNameFormula1586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_atomicNameFormula1588 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_atomicNameFormula1590 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_predicate_in_atomicNameFormula1592 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_NAME_in_atomicNameFormula1594 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_47_in_atomicNameFormula1597 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_atomicNameFormula1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_goal1624 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_goal1626 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_goalDesc_in_goal1628 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_goal1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_metricSpec1648 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_metricSpec1650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_optimization_in_metricSpec1652 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_metricSpec1654 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_metricSpec1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_metricFExp1693 = new BitSet(new long[]{0x000F000000000000L});
    public static final BitSet FOLLOW_binaryOp_in_metricFExp1695 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp1697 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp1699 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_metricFExp1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_metricFExp1706 = new BitSet(new long[]{0x0009000000000000L});
    public static final BitSet FOLLOW_set_in_metricFExp1708 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp1714 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp1716 = new BitSet(new long[]{0x0000C00010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_47_in_metricFExp1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_metricFExp1724 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_metricFExp1726 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp1728 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_metricFExp1730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_metricFExp1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_metricFExp1740 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_functionSymbol_in_metricFExp1742 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_NAME_in_metricFExp1744 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_47_in_metricFExp1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionSymbol_in_metricFExp1752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_metricFExp1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_metricFExp1762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_metricFExp1764 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_NAME_in_metricFExp1766 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_metricFExp1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_synpred15_UPDDL498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred27_UPDDL841 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_synpred27_UPDDL843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred67_UPDDL1693 = new BitSet(new long[]{0x000F000000000000L});
    public static final BitSet FOLLOW_binaryOp_in_synpred67_UPDDL1695 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_synpred67_UPDDL1697 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_synpred67_UPDDL1699 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_synpred67_UPDDL1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred70_UPDDL1706 = new BitSet(new long[]{0x0009000000000000L});
    public static final BitSet FOLLOW_set_in_synpred70_UPDDL1708 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_synpred70_UPDDL1714 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_synpred70_UPDDL1716 = new BitSet(new long[]{0x0000C00010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_47_in_synpred70_UPDDL1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred71_UPDDL1724 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_synpred71_UPDDL1726 = new BitSet(new long[]{0x0000400010800000L,0x0000000000080000L});
    public static final BitSet FOLLOW_metricFExp_in_synpred71_UPDDL1728 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_synpred71_UPDDL1730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred74_UPDDL1740 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_functionSymbol_in_synpred74_UPDDL1742 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_NAME_in_synpred74_UPDDL1744 = new BitSet(new long[]{0x0000800000800000L});
    public static final BitSet FOLLOW_47_in_synpred74_UPDDL1747 = new BitSet(new long[]{0x0000000000000002L});

}