import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Models.CompareOverview;
import Models.Response.Document;
import Models.Response.KeyWord;
import Models.Response.Multimedia;
import Utilities.CompareException;

class CompareTest {

	DocRepository docRepo = new DocRepository();
	
	@Test
	void documentSame() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertTrue(overview.AreEqual);
		assertEquals(overview.Details.size(), 0);
	}

	
	@Test
	void documentSingleStringDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.DocumentType = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs");
		assertEquals(overview.Details.get(0).Details, "DocumentType");
	}
	
	
	@Test
	void documentMultiStringDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.DocumentType = "Test";
		docB.Abstract = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 2);
		
		assertEquals(overview.Details.get(0).Location, "Docs");
		assertEquals(overview.Details.get(0).Details, "Abstract");
		
		assertEquals(overview.Details.get(1).Location, "Docs");
		assertEquals(overview.Details.get(1).Details, "DocumentType");
	}
	
	@Test
	void documentSingleIntegerDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.PrintPage = 2;
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs");
		assertEquals(overview.Details.get(0).Details, "PrintPage");
	}
	
	@Test
	void documentMultiIntegerDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.PrintPage = 2;
		docB.DocumentType = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 2);
		
		assertEquals(overview.Details.get(0).Location, "Docs");
		assertEquals(overview.Details.get(0).Details, "PrintPage");
		
		assertEquals(overview.Details.get(1).Location, "Docs");
		assertEquals(overview.Details.get(1).Details, "DocumentType");
	}
	
	@Test
	void documentMixedPrimitiveDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.PrintPage = 2;
		docB.WordCount = 2;
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 2);
		
		assertEquals(overview.Details.get(0).Location, "Docs");
		assertEquals(overview.Details.get(0).Details, "PrintPage");
		
		assertEquals(overview.Details.get(1).Location, "Docs");
		assertEquals(overview.Details.get(1).Details, "WordCount");
	}
	
	@Test
	void headlineDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.Headline.Main = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Headline");
		assertEquals(overview.Details.get(0).Details, "Main");
	}
	
	@Test
	void multimediaStringDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.Multimedia[0].Url = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Multimedia[0]");
		assertEquals(overview.Details.get(0).Details, "Url");
	}
	
	@Test
	void multimediaIntegerDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.Multimedia[0].Height = 1;
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Multimedia[0]");
		assertEquals(overview.Details.get(0).Details, "Height");
	}
	
	@Test
	void multimediaMixedPrimitveDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.Multimedia[0].Height = 1;		
		docB.Multimedia[0].Url = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 2);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Multimedia[0]");
		assertEquals(overview.Details.get(0).Details, "Url");
		
		assertEquals(overview.Details.get(1).Location, "Docs.Multimedia[0]");
		assertEquals(overview.Details.get(1).Details, "Height");
	}
	
	
	
	@Test
	void multimediaMultipleDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docA.Multimedia = new Multimedia[] {docRepo.getMultimedia(), docRepo.getMultimedia()};
		docB.Multimedia = new Multimedia[] {docRepo.getMultimedia(), docRepo.getMultimedia()};
		
		docB.Multimedia[0].Height = 1;		
		docB.Multimedia[0].Url = "Test";
		
		docA.Multimedia[1].Height = 1;		
		docA.Multimedia[1].Url = "Test";
		
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 4);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Multimedia[0]");
		assertEquals(overview.Details.get(0).Details, "Url");
		
		assertEquals(overview.Details.get(1).Location, "Docs.Multimedia[0]");
		assertEquals(overview.Details.get(1).Details, "Height");
		
		assertEquals(overview.Details.get(2).Location, "Docs.Multimedia[1]");
		assertEquals(overview.Details.get(2).Details, "Url");
		
		assertEquals(overview.Details.get(3).Location, "Docs.Multimedia[1]");
		assertEquals(overview.Details.get(3).Details, "Height");
	}
	
	
	@Test
	void keywordStringDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.KeyWords[0].Name = "Test";		
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Keywords[0]");
		assertEquals(overview.Details.get(0).Details, "Name");
	}
	
	@Test
	void keywordIntegerDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docB.KeyWords[0].Rank = 0;		
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 1);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Keywords[0]");
		assertEquals(overview.Details.get(0).Details, "Rank");
	}
	
	@Test
	void keywordMultipleDiff() throws CompareException {
		Document docA = docRepo.getDocument();
		Document docB = docRepo.getDocument();
		
		docA.KeyWords = new KeyWord[] {docRepo.getKeyWord(), docRepo.getKeyWord()};	
		docB.KeyWords = new KeyWord[] {docRepo.getKeyWord(), docRepo.getKeyWord()};	
		
		docB.KeyWords[0].Rank = 0;		
		docB.KeyWords[0].Value = "Test";
		
		docA.KeyWords[1].Rank = 0;		
		docA.KeyWords[1].Value = "Test";
		
		CompareOverview overview = new CompareOverview(0);
		overview = docA.compare(docB, overview);
		
		assertFalse(overview.AreEqual);
		assertEquals(overview.Details.size(), 4);
		
		assertEquals(overview.Details.get(0).Location, "Docs.Keywords[0]");
		assertEquals(overview.Details.get(0).Details, "Rank");
		
		assertEquals(overview.Details.get(1).Location, "Docs.Keywords[0]");
		assertEquals(overview.Details.get(1).Details, "Value");
		
		assertEquals(overview.Details.get(2).Location, "Docs.Keywords[1]");
		assertEquals(overview.Details.get(2).Details, "Rank");
		
		assertEquals(overview.Details.get(3).Location, "Docs.Keywords[1]");
		assertEquals(overview.Details.get(3).Details, "Value");
	}
}
