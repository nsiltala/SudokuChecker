import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuVerifierTest {

//implement tests to test Sudokuverifier with boundary values.  Use templates from Task 1 to derive and document test cases.
	
// A correct Sudoku string: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
// An incorrect Sudoku string: 123456789912345678891234567789123456678912345567891234456789123345678912234567891
String c = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
String i = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";

String s = "41736982563215894795872431682543716979158643234691275828964357157329168416487529"; // too short
String l = "4173698256321589479587243168254371697915864323469127582896435715732916841648752931"; // too long

String n = "41-7369825632158947958724316825437169791586432346912758289643571573291684164875293"; // contains negative num
String z = "41:36982563215894:958:2431682543:169:91586432346912:582896435:15:32916841648:5293"; // 7 replaced with :

String t = "411369825632158947958724316825437169791586432346912758289643571573291684164875293"; // 1st row 3rd num 1

String g = "417769825632158947958324316825437169791586432346912758289643571573291684164875293"; // returns -3, changed values so sub grids have 9 different nums but not rows
String o = "417369825632158947958724316852437169791586432346912758289643571573291684164875293"; // returns -4, changed values so sub grids and rows have 9 different nums but not columns

String special = "❤️ 💔 💌 💕 💞 💓 💗 💖 💘 💝 💟 💜 💛 💚 💙❤️ 💔 💌 💕 💞 💓 💗 💖 💘 💝 💟 💜💛"; // Special unicode test string (length must be 81)

/* 417369825
   632158947
   958724316
   825437169
   791586432   correct sudoku for help	
   346912758
   289643571
   573291684
   164875293 */

SudokuVerifier v = new SudokuVerifier();

	@Test // TC 1
	public void testCorrectString() {
		int a = v.verify(c);
		assertEquals("correct string", a, 0);
	}

	@Test
	public void testIncorrectString() {
		int a = v.verify(i);
		assertEquals("incorrect string", a, -2);
		
	}
	
	@Test // TC 2
	public void testStringLength() {
		int a = v.verify(s);
		int b = v.verify(l);
		assertEquals("incorrect string", a, -1);
		assertEquals("incorrect string", b, -1);
	}
	
	@Test // TC 3
	public void testDoesntAcceptSpecialChars() throws NumberFormatException {
		int a = v.verify(z);
		assertEquals("incorrect string", a, 1); // Throws exception and returns 1 as expected with special characters
	}
	
	@Test // TC 4
	public void testStringBreaksSubGridRule() {
		int a = v.verify(t);
		assertEquals("incorrect string", a, -2);	
	}
    
	@Test // TC 5
	public void testStringNumNotNegative() {
		int a = v.verify(n);
		assertEquals("incorrect string", a, -1); // Lengthcheck already checks this also but added functionality
	}
	
	@Test
	public void testStringbBreaksRowRule() {
		int a = v.verify(g);
		assertEquals("incorrect string", a, -3);
	}
	
	@Test
	public void testStringbBreaksColumnRule() {
		int a = v.verify(o);
		assertEquals("incorrect string", a, -4);
	}
	
	@Test
	public void testSpecialUnicodeChar() throws NumberFormatException {
		int a = v.verify(special);
		assertEquals("incorrect string", a, 1); // Throws exception and returns 1 as expected with special unicode characters
	}
}
