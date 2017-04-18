grammar EffectSystem;


// Entry-point
effectSchema
    : EOF
    | clause (SEMI clause)*
    ;

clause
    : expression '->' effectsList
    ;


// Expressions

/**
 *   Proudly stolen from Kotlin informal grammar reference
 *   https://kotlinlang.org/docs/reference/grammar.html#expression
 */
expression
    : conjunction (disjunctionOperator conjunction)*
    ;

conjunction
    : equalityComparison (conjunctionOperator equalityComparison)*
    ;

equalityComparison
    : comparison (equalityOperator comparison)*
    ;

comparison
    : namedInfix (comparisonOperator namedInfix)*
    ;

namedInfix
    : additiveExpression (inOperation additiveExpression)*
    | additiveExpression (isOperation type)?
    ;

additiveExpression
    : multiplicativeExpression (additiveOperator multiplicativeExpression)*
    ;

multiplicativeExpression
    : prefixUnaryExpression (multiplicativeOperator prefixUnaryExpression)*
    ;

prefixUnaryExpression
    : prefixUnaryOperation* postfixUnaryExpression
    ;

postfixUnaryExpression
    : atomicExpression postfixUnaryOperation*
    ;

atomicExpression
    : '(' expression ')'
    | literalConstant
    | SimpleName
    ;

disjunctionOperator
    : '||'
    ;

conjunctionOperator
    : '&&'
    ;

equalityOperator
    : EQEQ
    | EXCLEQ
    ;

comparisonOperator
    : LT | GT | LEQ | GEQ
    ;

additiveOperator
    : PLUS | MINUS
    ;

multiplicativeOperator
    : MUL | DIV | PERC
    ;

prefixUnaryOperation
    : MINUS | PLUS
    | MINUSMINUS | PLUSPLUS
    | NOT
    ;

postfixUnaryOperation
    : PLUSPLUS | MINUSMINUS | EXCLEXCL
    ;

inOperation
    : 'in' | '!in'
    ;

isOperation
    : 'is' | '!is'
    ;




// Effects

effectsList
    : effect (',' effect)*
    ;

effect
    : throwsEffect
    | returnsEffect
    | callsEffect
    ;

throwsEffect
    : 'Throws' SimpleName
    ;

returnsEffect
    : 'Returns' (literalConstant | SimpleName)
    ;

callsEffect
    : 'Calls' '(' callsRecord (';' callsRecord)* ')'
    ;

callsRecord
    : SimpleName IntegerLiteral;

type
    : SimpleName
    ;

literalConstant
    : BooleanLiteral
    | IntegerLiteral
    | StringLiteral
    | NullLiteral
    | UnitLiteral
    ;

BooleanLiteral
    : 'true'
    | 'false'
    ;

NullLiteral : 'null';

UnitLiteral : 'unit';

/**
 *  Everything below is proudly stolen from the Java grammar:
 *  https://github.com/antlr/grammars-v4/blob/master/java/Java.g4
 */

// String literals

StringLiteral
    :   '"' StringCharacters? '"'
    ;

fragment
StringCharacters
    :   StringCharacter+
    ;

fragment
StringCharacter
    :   ~["\\]
    |   EscapeSequence
    ;

fragment
EscapeSequence
    :   '\\' [btnfr"'\\]
    |   OctalEscape
    |   UnicodeEscape
    ;

fragment
OctalEscape
    :   '\\' OctalDigit
    |   '\\' OctalDigit OctalDigit
    |   '\\' ZeroToThree OctalDigit OctalDigit
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

fragment
ZeroToThree
    :   [0-3]
    ;

// Numeric literals

//TODO add hex/binary/octal integers
IntegerLiteral
    :   DecimalIntegerLiteral
    ;

fragment
DecimalIntegerLiteral
    :   DecimalNumeral IntegerTypeSuffix?
    ;

fragment
IntegerTypeSuffix
    :   [lL]
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit (Digits? | Underscores Digits)
    ;

fragment
Digits
    :   Digit (DigitOrUnderscore* Digit)?
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
DigitOrUnderscore
    :   Digit
    |   '_'
    ;

fragment
Underscores
    :   '_'+
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
HexDigit
    :   [0-9a-fA-F]
    ;

// Identifiers

SimpleName
    :   JavaLetter JavaLetterOrDigit*
    ;

fragment
JavaLetter
    :   [a-zA-Z$_] // these are the "java letters" below 0x7F
    |   // covers all characters above 0x7F which are not a surrogate
        ~[\u0000-\u007F\uD800-\uDBFF]
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
    ;

fragment
JavaLetterOrDigit
    :   [a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
    |   // covers all characters above 0x7F which are not a surrogate
        ~[\u0000-\u007F\uD800-\uDBFF]
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
    ;


WS  :  [ \t\r\n\u000C]+ -> skip
    ;

EOL : '\r'? '\n';

SEMI : ';';

LT : '<';
GT : '>';
LEQ : '<=';
GEQ : '>=';
PLUS : '+';
MINUS : '-';
MUL : '*';
DIV : '/';
PERC : '%';
PLUSPLUS : '++';
MINUSMINUS : '--';
NOT : '!';
EXCLEXCL : '!!';
EQEQ : '==';
EXCLEQ : '!=';