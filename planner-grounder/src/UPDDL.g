/**
 * uPDDL grammar
 * 
 * by Martin Kronegger
 * 
 * This grammar is based on Zeyn Saigol's PDDL grammar
 * from: http://www.antlr.org/grammar/1222962012944/Pddl.g
 */

grammar UPDDL;

options {
	output=AST;
	backtrack=true;
	//memoize=true;
}

tokens {
	DOMAIN;
	DOMAIN_NAME;
	REQUIREMENTS;
	TYPES;
	CONSTANTS;
	FUNCTIONS;
	PREDICATES;
	NEVERS;
	ACTION;
	ACTION_PARAMETERS;
	PROBLEM;
	PROBLEM_NAME;
	PROBLEM_DOMAIN;
	OBJECTS;
	INIT;
	ONEOF;
	FUNC_HEAD;
	PRECONDITION;
	EFFECT;
	AND_GD;
	NOT_GD;
	AND_EFFECT;
	ASSIGN_EFFECT;
	NOT_EFFECT;
	WHEN_EFFECT;
	PRED_HEAD;
	GOAL;
	BINARY_OP;
	INIT_EQ;
	NOT_PRED_INIT;
	PRED_INST;
	UNKNOWN_PRED_INST;
	PROBLEM_METRIC;
}

@parser::header {package upddl.antlr;}

@lexer::header {package upddl.antlr;}


@parser::members {
	private boolean wasError = false;
	
	public void reportError(RecognitionException e) {
		wasError = true;
		super.reportError(e);
	}
	
	public boolean invalidGrammar() {
		return wasError;
	}
}
/*
// Standard way of disabling the default error handler, and throwing Exceptions instead:
@rulecatch { }
@members {
	// raise exception, rather than recovering, on mismatched token within alt
	protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
		throw new MismatchedTokenException(ttype, input);
	}
}
*/

//************* Start of grammar *******************/

npddlDoc : domain | problem;


//************* DOMAINS ****************************/

domain
	:	'(' 'define' domainName
		requireDef?
		typesDef?
		constantsDef?
		predicatesDef?
		functionsDef?
		neversDef?
		actionDef*
		')'
	-> ^(DOMAIN domainName 
		requireDef? 
		typesDef?
		constantsDef? 
		predicatesDef? 
		functionsDef?
		neversDef?
		actionDef*)
	;

domainName
	: '(' 'domain' NAME ')'
	-> ^(DOMAIN_NAME NAME)
	;

requireDef
	: '(' ':requirements' REQUIRE_KEY+ ')'
	-> ^(REQUIREMENTS REQUIRE_KEY+)
	;

typesDef
	: '(' ':types' typedNameList ')'
	-> ^(TYPES typedNameList)
	;

// If have any typed names, they must come FIRST!
typedNameList
	: (NAME* | singleTypeNameList+ NAME*)
	;

singleTypeNameList
	: (NAME+ '-' t=type)
	-> ^(NAME $t)+
	;

type	: primType ;

primType : NAME ;

functionsDef
	: '(' ':functions' functionList ')'
	-> ^(FUNCTIONS functionList)
	;

functionList
	: (atomicFunctionSkeleton+ ('-' functionType)? )*
	;

atomicFunctionSkeleton
	: '('! functionSymbol^ typedVariableList ')'!
	;

functionSymbol : NAME ;

functionType : 'number' ; // Currently in PDDL only numeric functions are allowed

constantsDef
	: '(' ':constants' typedNameList ')'
	-> ^(CONSTANTS typedNameList)
	;

predicatesDef
	: '(' ':predicates' atomicFormulaSkeleton+ ')'
	-> ^(PREDICATES atomicFormulaSkeleton+)
	;

atomicFormulaSkeleton
	: '('! predicate^ typedVariableList ')'!
	;

predicate : NAME ;

// If have any typed variables, they must come FIRST!
typedVariableList
	: (VARIABLE* | singleTypeVarList+ VARIABLE*)
	;

singleTypeVarList
	: (VARIABLE+ '-' t=type)
	-> ^(VARIABLE $t)+
	;

neversDef:	
	'(' ':never' atomicPred+ ')'
	-> ^(NEVERS atomicPred+)
	;

atomicPred
	:'('! predicate^ constantOrTypedVariable* ')'!
	;

constantOrTypedVariable	
	:NAME
	| singleTypeVar
	;
	
singleTypeVar
	: (VARIABLE '-' t=type)
	-> ^(VARIABLE $t)
	;


//************* ACTIONS ****************************/

actionDef
	: '(' ':action' actionSymbol actionParameter actionDefBody ')'
	-> ^(ACTION actionSymbol actionParameter actionDefBody)
	;

actionSymbol : NAME ;

actionParameter
	: (':parameters' (('(' ')') | '(' typedVariableList ')') )?
	 -> ^(ACTION_PARAMETERS typedVariableList?)
	;

// Should allow preGD instead of goalDesc for preconditions -
// but I can't get the LL(*) parsing to work
// This means 'preference' preconditions cannot be used
actionDefBody
	: ( ':precondition' (('(' ')') | goalDesc))?
	  ( ':effect' (('(' ')') | effect))?
	->  ^(PRECONDITION goalDesc?) ^(EFFECT effect?)
	;

goalDesc
	: atomicTermFormula
	| '(' 'and' goalDesc* ')'
	  -> ^(AND_GD goalDesc*)
	| '(' 'not' goalDesc ')'
	  -> ^(NOT_GD goalDesc);

atomicTermFormula
	: '(' predicate term* ')' 
	-> ^(PRED_HEAD predicate term*)
	;

term : NAME | VARIABLE ;


//************* EXPRESSIONS ****************************/

fExp
	: NUMBER
	| fHead
	;

fHead
	: '(' functionSymbol term* ')' -> ^(FUNC_HEAD functionSymbol term*)
	| functionSymbol -> ^(FUNC_HEAD functionSymbol)
	;

effect
	: '(' 'and' cEffect* ')' -> ^(AND_EFFECT cEffect*)
	| cEffect
	;

cEffect
	: '(' 'when' goalDesc condEffect ')' -> ^(WHEN_EFFECT goalDesc condEffect)
	| pEffect
	;

pEffect
	: '(' assignOp fHead fExp ')' -> ^(ASSIGN_EFFECT assignOp fHead fExp)
	| '(' 'not' atomicTermFormula ')' -> ^(NOT_EFFECT atomicTermFormula)
	| atomicTermFormula
	;

condEffect
	: '(' 'and' pEffect* ')' -> ^(AND_EFFECT pEffect*)
	| pEffect
	;

// TODO: should these be uppercase & lexer section?
binaryOp : '*' | '+' | '-' | '/' ;

assignOp : 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' ;


//************* PROBLEMS ****************************/

problem
	:'(' 'define' problemDecl
	problemDomain
	requireDef?
	objectDecl?
	init
	goal
	metricSpec?
	')'
	-> ^(PROBLEM problemDecl problemDomain requireDef? objectDecl? init goal metricSpec?)
	;

problemDecl
	: '(' 'problem' NAME ')'
	-> ^(PROBLEM_NAME NAME)
	;

problemDomain
	: '(' ':domain' NAME ')'
	-> ^(PROBLEM_DOMAIN NAME)
	;

objectDecl
	: '(' ':objects' typedNameList ')'
	-> ^(OBJECTS typedNameList)
	;

init
	: '(' ':init' initEl* ')'
	-> ^(INIT initEl*)
	;

initEl
	: nameLiteral
	| '(' 'oneof' nameLiteral+ ')'	-> ^(ONEOF nameLiteral+)
	| '(' '=' fHead NUMBER ')'	-> ^(INIT_EQ fHead NUMBER)
	;

nameLiteral
	: atomicNameFormula
	| '(' 'not' atomicNameFormula ')'	-> ^(NOT_PRED_INIT atomicNameFormula)
	;

atomicNameFormula
	: '(' predicate NAME* ')' -> ^(PRED_INST predicate NAME*)
	| '(' 'unknown' '(' predicate NAME* ')' ')' -> ^(UNKNOWN_PRED_INST predicate NAME*)
	;

// Should allow preGD instead of goalDesc -
// but I can't get the LL(*) parsing to work
// This means 'preference' preconditions cannot be used
//goal : '(' ':goal' preGD ')'  -> ^(GOAL preGD);
goal : '(' ':goal' goalDesc ')' -> ^(GOAL goalDesc) ;

metricSpec
	: '(' ':metric' optimization metricFExp ')'
	  -> ^(PROBLEM_METRIC optimization metricFExp)
	;

optimization : 'minimize' | 'maximize' ;

metricFExp
	: '(' binaryOp metricFExp metricFExp ')'
	| '(' ('*'|'/') metricFExp metricFExp+ ')'
	| '(' '-' metricFExp ')'
	| NUMBER
	| '(' functionSymbol NAME* ')'
	| functionSymbol
	| 'total-time'
	| '(' 'is-violated' NAME ')'
	;


//************* LEXER ****************************/

REQUIRE_KEY
	: ':strips'
	| ':typing'
	| ':action-costs'
	| ':unknown'
	| ':nevers'
	;

VARIABLE : '?' LETTER ANY_CHAR*;

NAME : LETTER ANY_CHAR*;
fragment LETTER : 'a'..'z' | 'A'..'Z';
fragment ANY_CHAR : LETTER | '0'..'9' | '-' | '_';

NUMBER : DIGIT+ ('.' DIGIT+)?;
fragment DIGIT : '0'..'9';

LINE_COMMENT : ';' ~('\n'|'\r')* '\r'? '\n'
	{$channel = HIDDEN;}
	;

WHITESPACE: (' ' | '\t' | '\r' | '\n')+
	{$channel = HIDDEN;}
	;
