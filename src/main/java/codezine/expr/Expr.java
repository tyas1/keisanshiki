package codezine.expr;

import java.math.BigDecimal;

import codezine.expr.parser.ASTAdd;
import codezine.expr.parser.ASTDivision;
import codezine.expr.parser.ASTMulti;
import codezine.expr.parser.ASTStart;
import codezine.expr.parser.ASTString;
import codezine.expr.parser.ASTSub;
import codezine.expr.parser.ExprParserVisitor;
import codezine.expr.parser.SimpleNode;

public class Expr implements ExprParserVisitor {

	// 演算結果格納オブジェクト
	private Object result;
	// 取得のみ可
	public Object getResult() {
		return result;
	}

// テスト用メソッド
//	public static void main(String[] args) throws ParseException, IOException {
//		InputStreamReader in = new InputStreamReader( System.in);
//		BufferedReader reader = new BufferedReader(in);
//		String line;
//		while ((line = reader.readLine()) != null) {
//			ExprParser parser = new ExprParser(new StringReader(line));
//			Expr visitor = new Expr();
//			ASTStart start = parser.Start();
//			System.out.println(start.jjtAccept(visitor, null));
//		}
//	}

	@Override
	public Object visit(SimpleNode node, Object data) {
		return null; // ここには来ない
	}

	/** 開始記号 */
	@Override
	public Object visit(ASTStart node, Object data) {
		return node.jjtGetChild(0).jjtAccept(this, null);
	}

	/** 足し算 */
	@Override
	public Object visit(ASTAdd node, Object data) {
		Object left = node.jjtGetChild(0).jjtAccept(this, null);
		Object right = node.jjtGetChild(1).jjtAccept(this, null);

		if (left instanceof String || right instanceof String) {
			return left.toString() + right.toString();
		}

		return ((BigDecimal) left).add((BigDecimal)right);
	}

	/** 引き算 */
	@Override
	public Object visit(ASTSub node, Object data) {
		Integer left = (Integer) node.jjtGetChild(0).jjtAccept(this, null);
		Integer right = (Integer) node.jjtGetChild(1).jjtAccept(this, null);

		return left - right;
	}

	/** 掛け算 */
	@Override
	public Object visit(ASTMulti node, Object data) {
		Integer left = (Integer) node.jjtGetChild(0).jjtAccept(this, null);
		Integer right = (Integer) node.jjtGetChild(1).jjtAccept(this, null);

		return left * right;
	}

	/** 割り算 */
	@Override
	public Object visit(ASTDivision node, Object data) {
		Integer left = (Integer) node.jjtGetChild(0).jjtAccept(this, null);
		Integer right = (Integer) node.jjtGetChild(1).jjtAccept(this, null);

		return left / right;
	}

	/** 文字列リテラル */
//	@Override
//	public Object visit(ASTString node, Object data) {
//		return node.nodeValue;
//	}

	/** 数値リテラル */
	@Override
	public Object visit(ASTString node, Object data) {
		String value = node.nodeValue;
		BigDecimal bd = new BigDecimal(value);
		return bd;
	}
}
