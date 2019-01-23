// $ANTLR 3.4 /home/kron/Desktop/workspace/planner/src/UPDDL.g 2012-06-19 12:46:04
package upddl.antlr;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class UPDDLLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public UPDDLLexer() {} 
    public UPDDLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public UPDDLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/kron/Desktop/workspace/planner/src/UPDDL.g"; }

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:4:7: ( '(' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:4:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:5:7: ( ')' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:5:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:6:7: ( '*' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:6:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:7:7: ( '+' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:7:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:8:7: ( '-' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:8:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:9:7: ( '/' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:9:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:10:7: ( ':action' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:10:9: ':action'
            {
            match(":action"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:11:7: ( ':constants' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:11:9: ':constants'
            {
            match(":constants"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:12:7: ( ':domain' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:12:9: ':domain'
            {
            match(":domain"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:13:7: ( ':effect' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:13:9: ':effect'
            {
            match(":effect"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:14:7: ( ':functions' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:14:9: ':functions'
            {
            match(":functions"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:15:7: ( ':goal' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:15:9: ':goal'
            {
            match(":goal"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:16:7: ( ':init' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:16:9: ':init'
            {
            match(":init"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:17:7: ( ':metric' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:17:9: ':metric'
            {
            match(":metric"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:18:7: ( ':never' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:18:9: ':never'
            {
            match(":never"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:19:7: ( ':objects' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:19:9: ':objects'
            {
            match(":objects"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:20:7: ( ':parameters' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:20:9: ':parameters'
            {
            match(":parameters"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:21:7: ( ':precondition' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:21:9: ':precondition'
            {
            match(":precondition"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:22:7: ( ':predicates' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:22:9: ':predicates'
            {
            match(":predicates"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:23:7: ( ':requirements' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:23:9: ':requirements'
            {
            match(":requirements"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:24:7: ( ':types' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:24:9: ':types'
            {
            match(":types"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:25:7: ( '=' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:25:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:26:7: ( 'and' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:26:9: 'and'
            {
            match("and"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:27:7: ( 'assign' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:27:9: 'assign'
            {
            match("assign"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:28:7: ( 'decrease' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:28:9: 'decrease'
            {
            match("decrease"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:29:7: ( 'define' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:29:9: 'define'
            {
            match("define"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:30:7: ( 'domain' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:30:9: 'domain'
            {
            match("domain"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:31:7: ( 'increase' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:31:9: 'increase'
            {
            match("increase"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:32:7: ( 'is-violated' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:32:9: 'is-violated'
            {
            match("is-violated"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:33:7: ( 'maximize' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:33:9: 'maximize'
            {
            match("maximize"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:34:7: ( 'minimize' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:34:9: 'minimize'
            {
            match("minimize"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:35:7: ( 'not' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:35:9: 'not'
            {
            match("not"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:36:7: ( 'number' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:36:9: 'number'
            {
            match("number"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:37:7: ( 'oneof' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:37:9: 'oneof'
            {
            match("oneof"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:38:7: ( 'problem' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:38:9: 'problem'
            {
            match("problem"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:39:7: ( 'scale-down' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:39:9: 'scale-down'
            {
            match("scale-down"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:40:7: ( 'scale-up' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:40:9: 'scale-up'
            {
            match("scale-up"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:41:7: ( 'total-time' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:41:9: 'total-time'
            {
            match("total-time"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:42:7: ( 'unknown' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:42:9: 'unknown'
            {
            match("unknown"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:43:7: ( 'when' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:43:9: 'when'
            {
            match("when"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "REQUIRE_KEY"
    public final void mREQUIRE_KEY() throws RecognitionException {
        try {
            int _type = REQUIRE_KEY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:356:2: ( ':strips' | ':typing' | ':action-costs' | ':unknown' | ':nevers' )
            int alt1=5;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==':') ) {
                switch ( input.LA(2) ) {
                case 's':
                    {
                    alt1=1;
                    }
                    break;
                case 't':
                    {
                    alt1=2;
                    }
                    break;
                case 'a':
                    {
                    alt1=3;
                    }
                    break;
                case 'u':
                    {
                    alt1=4;
                    }
                    break;
                case 'n':
                    {
                    alt1=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:356:4: ':strips'
                    {
                    match(":strips"); 



                    }
                    break;
                case 2 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:357:4: ':typing'
                    {
                    match(":typing"); 



                    }
                    break;
                case 3 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:358:4: ':action-costs'
                    {
                    match(":action-costs"); 



                    }
                    break;
                case 4 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:359:4: ':unknown'
                    {
                    match(":unknown"); 



                    }
                    break;
                case 5 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:360:4: ':nevers'
                    {
                    match(":nevers"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REQUIRE_KEY"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:363:10: ( '?' LETTER ( ANY_CHAR )* )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:363:12: '?' LETTER ( ANY_CHAR )*
            {
            match('?'); 

            mLETTER(); 


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:363:23: ( ANY_CHAR )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='-'||(LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:365:6: ( LETTER ( ANY_CHAR )* )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:365:8: LETTER ( ANY_CHAR )*
            {
            mLETTER(); 


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:365:15: ( ANY_CHAR )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='-'||(LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:366:17: ( 'a' .. 'z' | 'A' .. 'Z' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "ANY_CHAR"
    public final void mANY_CHAR() throws RecognitionException {
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:367:19: ( LETTER | '0' .. '9' | '-' | '_' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANY_CHAR"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:369:8: ( ( DIGIT )+ ( '.' ( DIGIT )+ )? )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:369:10: ( DIGIT )+ ( '.' ( DIGIT )+ )?
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:369:10: ( DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:369:17: ( '.' ( DIGIT )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:369:18: '.' ( DIGIT )+
                    {
                    match('.'); 

                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:369:22: ( DIGIT )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:370:16: ( '0' .. '9' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:372:14: ( ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:372:16: ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match(';'); 

            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:372:20: (~ ( '\\n' | '\\r' ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\u0000' && LA7_0 <= '\t')||(LA7_0 >= '\u000B' && LA7_0 <= '\f')||(LA7_0 >= '\u000E' && LA7_0 <= '\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:372:34: ( '\\r' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\r') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:372:34: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:376:11: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:376:13: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // /home/kron/Desktop/workspace/planner/src/UPDDL.g:376:13: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= '\t' && LA9_0 <= '\n')||LA9_0=='\r'||LA9_0==' ') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/kron/Desktop/workspace/planner/src/UPDDL.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:8: ( T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | REQUIRE_KEY | VARIABLE | NAME | NUMBER | LINE_COMMENT | WHITESPACE )
        int alt10=46;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:10: T__46
                {
                mT__46(); 


                }
                break;
            case 2 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:16: T__47
                {
                mT__47(); 


                }
                break;
            case 3 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:22: T__48
                {
                mT__48(); 


                }
                break;
            case 4 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:28: T__49
                {
                mT__49(); 


                }
                break;
            case 5 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:34: T__50
                {
                mT__50(); 


                }
                break;
            case 6 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:40: T__51
                {
                mT__51(); 


                }
                break;
            case 7 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:46: T__52
                {
                mT__52(); 


                }
                break;
            case 8 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:52: T__53
                {
                mT__53(); 


                }
                break;
            case 9 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:58: T__54
                {
                mT__54(); 


                }
                break;
            case 10 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:64: T__55
                {
                mT__55(); 


                }
                break;
            case 11 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:70: T__56
                {
                mT__56(); 


                }
                break;
            case 12 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:76: T__57
                {
                mT__57(); 


                }
                break;
            case 13 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:82: T__58
                {
                mT__58(); 


                }
                break;
            case 14 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:88: T__59
                {
                mT__59(); 


                }
                break;
            case 15 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:94: T__60
                {
                mT__60(); 


                }
                break;
            case 16 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:100: T__61
                {
                mT__61(); 


                }
                break;
            case 17 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:106: T__62
                {
                mT__62(); 


                }
                break;
            case 18 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:112: T__63
                {
                mT__63(); 


                }
                break;
            case 19 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:118: T__64
                {
                mT__64(); 


                }
                break;
            case 20 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:124: T__65
                {
                mT__65(); 


                }
                break;
            case 21 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:130: T__66
                {
                mT__66(); 


                }
                break;
            case 22 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:136: T__67
                {
                mT__67(); 


                }
                break;
            case 23 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:142: T__68
                {
                mT__68(); 


                }
                break;
            case 24 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:148: T__69
                {
                mT__69(); 


                }
                break;
            case 25 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:154: T__70
                {
                mT__70(); 


                }
                break;
            case 26 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:160: T__71
                {
                mT__71(); 


                }
                break;
            case 27 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:166: T__72
                {
                mT__72(); 


                }
                break;
            case 28 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:172: T__73
                {
                mT__73(); 


                }
                break;
            case 29 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:178: T__74
                {
                mT__74(); 


                }
                break;
            case 30 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:184: T__75
                {
                mT__75(); 


                }
                break;
            case 31 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:190: T__76
                {
                mT__76(); 


                }
                break;
            case 32 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:196: T__77
                {
                mT__77(); 


                }
                break;
            case 33 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:202: T__78
                {
                mT__78(); 


                }
                break;
            case 34 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:208: T__79
                {
                mT__79(); 


                }
                break;
            case 35 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:214: T__80
                {
                mT__80(); 


                }
                break;
            case 36 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:220: T__81
                {
                mT__81(); 


                }
                break;
            case 37 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:226: T__82
                {
                mT__82(); 


                }
                break;
            case 38 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:232: T__83
                {
                mT__83(); 


                }
                break;
            case 39 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:238: T__84
                {
                mT__84(); 


                }
                break;
            case 40 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:244: T__85
                {
                mT__85(); 


                }
                break;
            case 41 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:250: REQUIRE_KEY
                {
                mREQUIRE_KEY(); 


                }
                break;
            case 42 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:262: VARIABLE
                {
                mVARIABLE(); 


                }
                break;
            case 43 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:271: NAME
                {
                mNAME(); 


                }
                break;
            case 44 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:276: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 45 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:283: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;
            case 46 :
                // /home/kron/Desktop/workspace/planner/src/UPDDL.g:1:296: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\11\uffff\13\25\23\uffff\20\25\5\uffff\1\121\10\25\1\132\7\25\5"+
        "\uffff\10\25\1\uffff\6\25\1\165\5\uffff\11\25\1\u0081\4\25\2\uffff"+
        "\1\u0087\1\u0088\1\25\1\u008a\1\u008b\4\25\1\u0090\1\uffff\4\25"+
        "\1\u0096\2\uffff\1\25\2\uffff\4\25\1\uffff\1\u009c\3\25\1\u00a0"+
        "\1\uffff\1\u00a1\1\u00a2\1\25\1\u00a4\1\u00a5\1\uffff\1\25\1\u00a7"+
        "\1\25\3\uffff\1\25\2\uffff\1\25\1\uffff\2\25\1\u00ad\1\u00ae\1\u00af"+
        "\3\uffff";
    static final String DFA10_eofS =
        "\u00b0\uffff";
    static final String DFA10_minS =
        "\1\11\6\uffff\1\141\1\uffff\1\156\1\145\1\156\1\141\1\157\1\156"+
        "\1\162\1\143\1\157\1\156\1\150\5\uffff\1\143\7\uffff\1\145\1\uffff"+
        "\1\141\1\uffff\1\171\1\uffff\1\144\1\163\1\143\1\155\1\143\1\55"+
        "\1\170\1\156\1\164\1\155\1\145\1\157\1\141\1\164\1\153\1\145\1\164"+
        "\1\166\1\uffff\1\145\1\160\1\55\1\151\1\162\1\151\1\141\1\162\1"+
        "\166\2\151\1\55\1\142\1\157\1\142\1\154\1\141\2\156\1\151\1\145"+
        "\1\143\1\145\1\uffff\1\147\1\145\1\156\1\151\1\145\1\151\2\155\1"+
        "\uffff\1\145\1\146\1\154\1\145\1\154\1\157\1\55\1\157\1\162\3\uffff"+
        "\1\156\1\141\1\145\1\156\1\141\1\157\2\151\1\162\1\55\1\145\2\55"+
        "\1\167\1\uffff\1\156\1\163\1\55\1\163\2\55\1\163\1\154\2\172\1\55"+
        "\1\uffff\1\155\1\144\1\164\1\156\1\55\2\uffff\1\145\2\uffff\1\145"+
        "\1\141\2\145\1\uffff\1\55\1\157\1\160\1\151\1\55\1\uffff\2\55\1"+
        "\164\2\55\1\uffff\1\167\1\55\1\155\3\uffff\1\145\2\uffff\1\156\1"+
        "\uffff\1\145\1\144\3\55\3\uffff";
    static final String DFA10_maxS =
        "\1\172\6\uffff\1\165\1\uffff\1\163\1\157\1\163\1\151\1\165\1\156"+
        "\1\162\1\143\1\157\1\156\1\150\5\uffff\1\143\7\uffff\1\145\1\uffff"+
        "\1\162\1\uffff\1\171\1\uffff\1\144\1\163\1\146\1\155\1\143\1\55"+
        "\1\170\1\156\1\164\1\155\1\145\1\157\1\141\1\164\1\153\1\145\1\164"+
        "\1\166\1\uffff\1\145\1\160\1\172\1\151\1\162\1\151\1\141\1\162\1"+
        "\166\2\151\1\172\1\142\1\157\1\142\1\154\1\141\2\156\1\151\1\145"+
        "\1\144\1\151\1\uffff\1\147\1\145\1\156\1\151\1\145\1\151\2\155\1"+
        "\uffff\1\145\1\146\1\154\1\145\1\154\1\157\1\172\1\157\1\162\3\uffff"+
        "\1\156\1\141\1\145\1\156\1\141\1\157\2\151\1\162\1\172\1\145\2\55"+
        "\1\167\1\uffff\1\156\1\163\1\172\1\163\2\172\1\163\1\154\3\172\1"+
        "\uffff\1\155\1\165\1\164\1\156\1\55\2\uffff\1\145\2\uffff\1\145"+
        "\1\141\2\145\1\uffff\1\172\1\157\1\160\1\151\1\172\1\uffff\2\172"+
        "\1\164\2\172\1\uffff\1\167\1\172\1\155\3\uffff\1\145\2\uffff\1\156"+
        "\1\uffff\1\145\1\144\3\172\3\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\26\13\uffff\1\52\1\53"+
        "\1\54\1\55\1\56\1\uffff\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\uffff"+
        "\1\20\1\uffff\1\24\1\uffff\1\51\22\uffff\1\21\27\uffff\1\27\10\uffff"+
        "\1\40\11\uffff\1\22\1\23\1\25\16\uffff\1\50\13\uffff\1\42\5\uffff"+
        "\1\17\1\30\1\uffff\1\32\1\33\4\uffff\1\41\5\uffff\1\7\5\uffff\1"+
        "\43\3\uffff\1\47\1\31\1\34\1\uffff\1\36\1\37\1\uffff\1\45\5\uffff"+
        "\1\44\1\46\1\35";
    static final String DFA10_specialS =
        "\u00b0\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\30\2\uffff\1\30\22\uffff\1\30\7\uffff\1\1\1\2\1\3\1\4\1\uffff"+
            "\1\5\1\uffff\1\6\12\26\1\7\1\27\1\uffff\1\10\1\uffff\1\24\1"+
            "\uffff\32\25\6\uffff\1\11\2\25\1\12\4\25\1\13\3\25\1\14\1\15"+
            "\1\16\1\17\2\25\1\20\1\21\1\22\1\25\1\23\3\25",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\31\1\uffff\1\32\1\33\1\34\1\35\1\36\1\uffff\1\37\3\uffff"+
            "\1\40\1\41\1\42\1\43\1\uffff\1\44\1\46\1\45\1\46",
            "",
            "\1\47\4\uffff\1\50",
            "\1\51\11\uffff\1\52",
            "\1\53\4\uffff\1\54",
            "\1\55\7\uffff\1\56",
            "\1\57\5\uffff\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "",
            "",
            "",
            "",
            "",
            "\1\67",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\70",
            "",
            "\1\71\20\uffff\1\72",
            "",
            "\1\73",
            "",
            "\1\74",
            "\1\75",
            "\1\76\2\uffff\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "",
            "\1\117",
            "\1\120",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144\1\145",
            "\1\146\3\uffff\1\46",
            "",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\166",
            "\1\167",
            "",
            "",
            "",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "",
            "\1\u0086",
            "\1\46",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u0089",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            "\1\u0091",
            "\1\u0092\20\uffff\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\46",
            "",
            "",
            "\1\u0097",
            "",
            "",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00a3",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            "\1\u00a6",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\u00a8",
            "",
            "",
            "",
            "\1\u00a9",
            "",
            "",
            "\1\u00aa",
            "",
            "\1\u00ab",
            "\1\u00ac",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "\1\25\2\uffff\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
            "",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | REQUIRE_KEY | VARIABLE | NAME | NUMBER | LINE_COMMENT | WHITESPACE );";
        }
    }
 

}