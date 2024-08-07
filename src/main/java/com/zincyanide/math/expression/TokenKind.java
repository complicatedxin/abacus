package com.zincyanide.math.expression;

enum TokenKind
{
    // ordered by priority - operands first

    LITERAL_INT,

    LITERAL_LONG,

    LITERAL_HEXINT,

    LITERAL_HEXLONG,

    LITERAL_STRING,

    LITERAL_REAL,

    LITERAL_REAL_FLOAT,

    LPAREN("("),

    RPAREN(")"),

    COMMA(","),

    IDENTIFIER,

    COLON(":"),

    HASH("#"),

    RSQUARE("]"),

    LSQUARE("["),

    LCURLY("{"),

    RCURLY("}"),

    DOT("."),

    PLUS("+"),

    STAR("*"),

    MINUS("-"),

    SELECT_FIRST("^["),

    SELECT_LAST("$["),

    QMARK("?"),

    PROJECT("!["),

    DIV("/"),

    GE(">="),

    GT(">"),

    LE("<="),

    LT("<"),

    EQ("=="),

    NE("!="),

    MOD("%"),

    NOT("!"),

    ASSIGN("="),

    INSTANCEOF("instanceof"),

    MATCHES("matches"),

    BETWEEN("between"),

    SELECT("?["),

    POWER("^"),

    ELVIS("?:"),

    SAFE_NAVI("?."),

    BEAN_REF("@"),

    FACTORY_BEAN_REF("&"),

    SYMBOLIC_OR("||"),

    SYMBOLIC_AND("&&"),

    INC("++"),

    DEC("--");


    final char[] tokenChars;

    private final boolean hasPayload;  // is there more to this token than simply the kind


    private TokenKind(String tokenString)
    {
        this.tokenChars = tokenString.toCharArray();
        this.hasPayload = (this.tokenChars.length == 0);
    }

    private TokenKind()
    {
        this("");
    }


    @Override
    public String toString()
    {
        return (name() + (this.tokenChars.length != 0 ? "(" + new String(this.tokenChars) + ")" : ""));
    }

    public boolean hasPayload()
    {
        return this.hasPayload;
    }

    public int getLength()
    {
        return this.tokenChars.length;
    }

}
