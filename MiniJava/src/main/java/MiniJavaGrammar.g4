grammar MiniJavaGrammar;

goal: mainClass ( classDeclaration )* EOF;

mainClass: 'class' Identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' Identifier ')' '{' statement '}' '}';
classDeclaration: 'class' Identifier ( 'extends' Identifier)? '{' (varDeclaration)* (methodDeclaration)* '}' EOF?;
varDeclaration: type Identifier ';';
methodDeclaration: 'public' type Identifier '(' (type Identifier (',' type Identifier)* )? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}';

type: 'int' '[' ']'
	| 'double' '[' ']'
	| 'boolean'
	| 'int'
        | 'double'
	| Identifier
    ;

statement: '{' (statement)* '}'
	| 	'if' '(' expression ')' statement 'else' statement
	| 	'while' '(' expression ')' statement
	| 	'System.out.println' '(' expression ')' ';'
	| 	Identifier '=' expression ';'
	| 	Identifier '[' expression ']' '=' expression ';'
    ;

expression: expression ( '&&' | '<' | '+' | '-'| '*' ) expression
	| 	expression '[' expression ']'
	| 	expression '.' 'length'
	| 	expression '.' Identifier '(' ( expression ( ',' expression )* )? ')'
	| 	INTEGER_LITERAL
	|	DOUBLE_LITERAL
	| 	'true'
	| 	'false'
	| 	Identifier
	| 	'this'
	| 	'this' expression
	| 	'new' 'int' '[' expression ']'
	| 	'new' 'double' '[' expression ']'
	| 	'new' Identifier '(' ')'
	| 	'new' Identifier '(' ')' expression
	| 	'!' expression
	| 	'(' expression ')'
    ;

Identifier: [a-zA-Z_]+[a-zA-Z_0-9]*;
INTEGER_LITERAL: [0-9]+;
DOUBLE_LITERAL: [0-9]+'.'[0-9]+;
WhiteSpace: [ \n\t\r]+ -> skip;
COMMENT : '//' .+? ('\n'|EOF) -> skip ;
