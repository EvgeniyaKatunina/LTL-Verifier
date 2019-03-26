grammar LTLExpression;

expression
    : '(' expression ')'                    # parenthesis
    | '!' expression                        # negation
    | 'X' expression                        # next
    | 'F' expression                        # future
    | 'G' expression                        # globally
    | left=expression 'U' right=expression  # until
    | left=expression 'R' right=expression  # release
    | left=expression '&&' right=expression # conjunction
    | left=expression '||' right=expression # disjunction
    | left=expression '->' right=expression # implication
    | ID                                    # variable
    | BooleanLiteral                        # booleanLiteral
    ;

BooleanLiteral
    : 'true'
    | 'false'
    ;

NUM : [0-9]+;
ID  : [a-zA-Z][_a-zA-Z0-9]*;
WS  : [ \t\r\n]+ -> skip;