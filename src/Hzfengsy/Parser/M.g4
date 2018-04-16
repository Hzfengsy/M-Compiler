grammar M;

prog: (define|func|clas)+;

clas: CLASS id '{' prog '}';

func: class_stat? id '(' stat_list ')' stat;

stat: IF '(' expr ')' stat                         # If_Stat
    | IF '(' expr ')' stat ELSE stat               # IfElse_Stat
    | FOR '(' expr? ';' expr? ';' expr? ')' stat   # For_Stat
    | WHILE '(' expr ')' stat                      # While_Stat
    | RETRN expr? ';'                              # Return_Stat
    | BREAK ';'                                    # Break_Stat
    | CONTI ';'                                    # Continue_Stat
    | '{' stat* '}'                                # Segment_Stat
    | (expr)? ';'                                  # Expr_Stat
    | define                                       # Define_Stat
    ;


expr: expr op=('++'|'--')                  # Postfix
    | id '(' expr_list ')'                 # Function
    | expr '[' expr ']'                    # Subscript
    | expr '.' id                          # Membervar
    | expr '.' id '(' expr_list ')'        # Memberfunc
    | op=('++'|'--') expr                  # Prefix
    | op=('+'|'-') expr                    # Unary
    | op=('!'|'~') expr                    # Not
    | 'new' class_new                      # New
    | expr op=('*'|'/'|'%') expr           # MulDivMod
    | expr op=('+'|'-') expr               # AddSub
    | expr op=('<<'|'>>') expr             # Bitwise
    | expr op=('<'|'<='|'>'|'>=') expr     # Compare
    | expr op=('=='|'!=') expr             # Equal
    | expr '&' expr                        # And
    | expr '^' expr                        # Xor
    | expr '|' expr                        # Or
    | expr '&&' expr                       # LAnd
    | expr '||' expr                       # LOr
    | NUM                                  # Number
    | TRUE                                 # True
    | FALSE                                # False
    | NULL                                 # Null
    | STR                                  # Str
    | THIS                                 # This
    | id                                   # Identity
    | '(' expr ')'                         # Parens
    | expr '=' expr                        # Assignment
    ;

expr_list: expr_list ',' expr_list         # ExprListCombine
         | expr?                           # ExprList
         ;

stat_list: stat_list ',' stat_list         # StatListCombine
         | (class_stat id)?                # StatList
         ;

define: class_stat id '=' expr ';'         # Assign_Define
      | class_stat id ';'                  # Id_Define
      ;

id: NAME                            # RAWID
//  | expr '[' expr ']'                 # Subscript
//  | id '.' id                       # Member
  ;

class_name: BOOL
          | INT
          | STRING
          | VOID
          | NAME
          ;

class_stat: class_stat '[]'         # Array
          | class_name              # SingleClass
          ;

class_new : class_name (('[' expr']')+ ('[]')*)?;

//Reserved Keywords
BOOL  : 'bool';
INT   : 'int';
STRING: 'string';
NULL  : 'null';
VOID  : 'void';

TRUE  : 'true';
FALSE : 'false';
IF    : 'if';
ELSE  : 'else';
FOR   : 'for';
WHILE : 'while';

BREAK : 'break';
CONTI : 'continue';
RETRN : 'return';

NEW   : 'new';
CLASS : 'class';
THIS  : 'this';

NUM  : [0-9]+ ;
STR  : '"' (ESC|.)*? '"' ;
ESC  : '\\"' | '\\\\' ;
//Comment
COM  : '//' .*? '\r'? '\n' -> skip;
MUL  : '*' ; // assigns token name to '*' used above in grammar
DIV  : '/' ;
ADD  : '+' ;
SUB  : '-' ;
NAME : [a-zA-Z][a-zA-Z0-9_]* ;
WS   : [ \t\n\r]+ -> skip;