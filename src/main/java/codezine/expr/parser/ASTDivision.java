/* Generated By:JJTree: Do not edit this line. ASTDivision.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=codezine.expr.BaseNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package codezine.expr.parser;

public
class ASTDivision extends SimpleNode {
  public ASTDivision(int id) {
    super(id);
  }

  public ASTDivision(ExprParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ExprParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=82837e8cb419aafa5fd13a0293f99fd0 (do not edit this line) */
