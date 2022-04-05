grammar Prover9;


start_problem
	: formulas EOF
	;
formulas
	:
	asumptions
	goals
	;

clause
    :
    'positive'
    | 'negative'
    | 'mixed'
    | 'unit'
    | 'horn'
    | 'definite'
    | 'has_equality'
    | 'true'
    | 'false'
    | 'initial'
    | 'resolvent'
    | 'hyper_resolvent'
    | 'ur_resolvent'
    | 'factor'
    | 'subsumer'
    | 'back_demodulant'
    | 'paramodulant';


asumptions
	: FORMULAS LPAREN ASSUMPLTIONS RPAREN DOT
	  stat*
	  END_OF_LIST DOT
	;

goals
	: FORMULAS LPAREN GOALS RPAREN DOT
	  stat*
	  END_OF_LIST DOT
	;
stat
	: formula DOT
	;



predicate
	: TEXT LPAREN TEXT RPAREN
	| TEXT LPAREN TEXT (COMMA TEXT)* RPAREN
	;

formula
	: predicate
	| LPAREN formula RPAREN
	| NOT formula
	| formula OR formula
	| formula AND formula
	| formula IMPL formula
	| FORALL TEXT formula
	| EXISTS TEXT formula
	| clause formula
	;



FORMULAS : 			'formulas';
END_OF_LIST :		'end_of_list';
ASSUMPLTIONS :		'assumptions';
GOALS : 			'goals';
TRUE   :			'$T';
FALSE  :			'$F';
LPAREN :			'(';
RPAREN :			')';
DOT :				'.';
COMMA : 			',';
NOT :				'-';
OR : 				'|';
AND : 				'&';
IMPL : 				'->';
BIMPL : 			'<-';
BIDIR : 			'<->';
FORALL  :	 		'all';
EXISTS  :	 		'exists';


fragment LETTER : [A-Za-z];
TEXT : LETTER+;


WS : [ \t\r\n]+ -> skip;
LINE_COMMENT: '%' .*?  '\n' -> skip;