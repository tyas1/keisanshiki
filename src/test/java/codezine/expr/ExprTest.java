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
	@Test
	public void test01() throws IOException, ParseException, URISyntaxException {

		String src = new String(
				Files.readAllBytes(
						Paths.get(this.getClass().getClassLoader().getResource("simple01.txt").toURI())),
				StandardCharsets.UTF_8);

		ExprParser parser = new ExprParser(new StringReader(src));
		Object ret = parser.Start().jjtAccept(new Expr(), null);

		System.out.println(ret.toString());

	}
}
