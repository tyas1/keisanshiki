package codezine.expr;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import codezine.expr.parser.ExprParser;
import codezine.expr.parser.ParseException;

public class ExprTest {

	// ========== 足し算 ==========
	@Test
	public void keisanAdd01() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_add_01.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(2).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanAdd02() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_add_02.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(0).compareTo((BigDecimal)ret) == 0);
	}

	@Test
	public void keisanAdd03() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_add_03.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(0).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanAdd04() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_add_04.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(3).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanAdd05() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_add_05.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal("0.6999").compareTo((BigDecimal)ret) == 0);
	}
	
	// ========== 引き算 ==========
	@Test
	public void keisanSub01() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_sub_01.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(0).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanSub02() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_sub_02.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(2).compareTo((BigDecimal)ret) == 0);
	}

	@Test
	public void keisanSub03() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_sub_03.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(2).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanSub04() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_sub_04.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(-1).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanSub05() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_sub_05.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal("1.3001").compareTo((BigDecimal)ret) == 0);
	}	
	
	// ========== 足し算と引き算 ==========

	@Test
	public void keisanAddSub01() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_add_sub_01.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal("0.2999").compareTo((BigDecimal)ret) == 0);
	}
	
	// ========== リテラル ==========

	@Test
	public void keisanLiteral01() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_literal_01.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(1).compareTo((BigDecimal)ret) == 0);
	}
	
	@Test
	public void keisanLiteral02() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_literal_02.txt");
		Assert.assertTrue(ret instanceof String);
		Assert.assertEquals("AAA", ret);
	}
	
	@Test
	public void keisanLiteral03() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_literal_03.txt");
		Assert.assertTrue(ret instanceof String);
		Assert.assertEquals("あああ", ret);
	}

	// ========== 掛け算 ==========

	@Test
	public void keisanMultiple01() throws IOException, ParseException, URISyntaxException {
		Object ret = commonProc("keisan_mul_01.txt");
		Assert.assertTrue(ret instanceof BigDecimal);
		Assert.assertTrue(new BigDecimal(6).compareTo((BigDecimal)ret) == 0);
	}
	
	// =========== 共通処理  ==========
	
	/**
	 * 共通テスト処理
	 * @param fileName テストデータファイル名
	 * @throws ParseException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	Object commonProc(String fileName) throws ParseException, IOException, URISyntaxException{
		String src = new String(
				Files.readAllBytes(
						Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI())),
				StandardCharsets.UTF_8);
		
		ExprParser parser = new ExprParser(new StringReader(src));
		return parser.Start().jjtAccept(new Expr(), null);
	}
}
