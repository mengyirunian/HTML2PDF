package mavenWeb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

public class App {
	public void createPdf() throws Exception {
		// step 1
		String inputFile = "G:/test.html";
		String url = new File(inputFile).toURI().toURL().toString();
		String outputFile = "G:/test.pdf";
		// step 2
		OutputStream os = new FileOutputStream(outputFile);
		org.xhtmlrenderer.pdf.ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(url);

		// step 3 解决中文支持
		org.xhtmlrenderer.pdf.ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("G:/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		renderer.layout();
		renderer.createPDF(os);
		os.close();

		System.out.println("create pdf done!!");
	}

	public boolean convertHtmlToPdf(String inputFile, String outputFile) throws Exception {

		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		String url = new File(inputFile).toURI().toURL().toString();

		renderer.setDocument(url);

		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("G:/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		// 解决图片的相对路径问题
		renderer.getSharedContext().setBaseURL("file:/G:/");
		renderer.layout();
		renderer.createPDF(os);

		os.flush();
		os.close();
		return true;
	}

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.convertHtmlToPdf("G:/test.html", "G:/test.pdf");
	}

}