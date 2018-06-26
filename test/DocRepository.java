import Models.Response.Byline;
import Models.Response.Document;
import Models.Response.Headline;
import Models.Response.KeyWord;
import Models.Response.LegacyMedia;
import Models.Response.Multimedia;
import Models.Response.Person;

public class DocRepository {

	public Document getDocument() {
		Document document = new Document();
		
		document.WebUrl = "https://www.nytimes.com/2017/03/02/arts/design/what-to-see-at-new-yorks-art-fairs-this-week.html";		
		document.Snippet = "Of the dozen fairs this weekend, our survival guide focuses on four....";
		document.LeadParagraph = "Of the dozen fairs this weekend, our survival guide focuses on four.";
		document.Abstract = null;
		document.PrintPage = 1;
		document.Blog = new Object[0];
		document.Source = "AP";
		
		
		document.PubDate ="2017-03-02T23:02:39+0000";
		document.DocumentType = "article";
		document.NewsDesk = "Weekend";
		document.SectionName = "Art & Design";
		document.TypeOfMaterial = "News";
		document.Id = "58b8a49995d0e024902fd3de";
		document. WordCount = 3355;
		document.SlideShowCredits = null;
		document.Score = 1;
		
		document.Multimedia = new Multimedia[] {getMultimedia()};
		document.KeyWords = new KeyWord[] {getKeyWord()};
		document.Headline = getHeadline();
		document.Byline = getByline();
		
		return document;
	}
	
	public Byline getByline() {
		Byline byline = new Byline();
		
		byline.Person = new Person[0];
		byline.Original = "By THE ASSOCIATED PRESS";
		byline.Organization = "THE ASSOCIATED PRESS";
		
		return byline;
	}
	
	public Person getPerson() {
		Person person = new Person();
		
		person.FirstName = "Jason";
		person.LastName = "FARAGO";
		person.Organization = "";
		person.Role = "reported";
		person.Rank = 1;
		
		return person;
	}
	
	public Headline getHeadline() {
		Headline headline = new Headline();
		
		headline.Main = "What to See at New York’s Art Fairs This Week";
		headline.PrintHeadline = "Dizzying Days at the Art Fairs";
		
		return headline;
	}
	
	public Multimedia getMultimedia() {
		Multimedia multimedia = new Multimedia();
		
		multimedia.Width = 75;
		multimedia.Url = "images/2017/03/03/arts/03LISTINGSTHEATER/03LISTINGSTHEATER-thumbStandard-v2.jpg";
		multimedia.Rank = 0;
		multimedia.Height = 75;
		multimedia.Subtype = "thumbnail";
		multimedia.Type = "image";
		multimedia.Legacy = new LegacyMedia();
		
		return multimedia;
	}
	
	public KeyWord getKeyWord() {
		KeyWord keyWord = new KeyWord();
		
		keyWord.IsMajor = "N";
		keyWord.Rank = 1;
		keyWord.Name = "subject";
		keyWord.Value = "Theater";
		
		return keyWord;
	}
	
	
}
