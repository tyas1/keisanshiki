package codezine.expr;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import codezine.expr.parser.ExprParser;
import codezine.expr.parser.ParseException;

public class ExprTest {
//	@Test
	public void test01() throws IOException, ParseException, URISyntaxException {
		commonProc("simple01.txt");
	}
	

	@Test
	public void test02() throws IOException, ParseException, URISyntaxException {
		commonProc("simple02.txt");
	}
	

	@Test
	public void test03() throws IOException, ParseException, URISyntaxException {
		commonProc("simple03.txt");
	}
	
	/**
	 * 共通テスト処理
	 * @param fileName テストデータファイル名
	 * @throws ParseException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	void commonProc(String fileName) throws ParseException, IOException, URISyntaxException{
		String src = new String(
				Files.readAllBytes(
						Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI())),
				StandardCharsets.UTF_8);
		
		ExprParser parser = new ExprParser(new StringReader(src));
		Object ret = parser.Start().jjtAccept(new Expr(), null);

		System.out.println(ret.toString());
	}
}
