grammar MiniJavaGrammar;

goal: mainClass ( classDeclaration )* end;

mainClass: 'class' Identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' Identifier ')' '{' statement '}' '}';
classDeclaration: 'class' Identifier ( 'extends' Identifier)? '{' (varDeclaration)* (methodDeclaration)* '}' end?;
varDeclaration: type Identifier ';';
methodDeclaration: 'public' type Identifier '(' (type Identifier (',' type Identifier)* )? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}';

type: 'int' '[' ']'
	| 'double' '[' ']'
	| 'boolean'
	| 'int'
    | 'double'
	| Identifier
    ;

statement
    :   '{' (statement)* '}' #StatementBlock
	| 	'if' '(' expression ')' statement 'else' statement #IfStatement
	| 	'while' '(' expression ')' statement #WhileStatement
	| 	'System.out.println' '(' expression ')' ';' #PrintLine
	| 	Identifier '=' expression ';' #Assignment
	| 	Identifier '[' expression ']' '=' expression ';' #ArrayAssignment
    ;

expression
    :   expression binary_operator=( '&&' | '<' | '+' | '-' | '*' ) expression
	| 	expression '[' expression ']'
	| 	expression '.' 'length'
	| 	expression '.' Identifier '(' ( expression ( ',' expression )* )? ')'
	| 	INTEGER_LITERAL
	|	DOUBLE_LITERAL
	| 	'true'
	| 	'false'
	| 	Identifier
	| 	'this'
	| 	'new' 'int' '[' expression ']'
	| 	'new' 'double' '[' expression ']'
	| 	'new' Identifier '(' ')'
	| 	'!' expression
	| 	'(' expression ')'
    ;

end: EOF;

Identifier: [a-zA-Z_]+[a-zA-Z_0-9]*;
INTEGER_LITERAL: [0-9]+;
DOUBLE_LITERAL: [0-9]+'.'[0-9]+;
WhiteSpace: [ \n\t\r]+ -> skip;
COMMENT : '//' .+? ('\n'|EOF) -> skip ;
