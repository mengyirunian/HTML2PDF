package mavenWeb;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

public class HTMLPDFTest2 {

	public void html2PDF() throws Exception {
		String outputFile = "G:/test1.pdf";
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("G:/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		StringBuffer html = new StringBuffer();
		// DOCTYPE 必需写否则类似于 这样的字符解析会出现错误
		html.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		html.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("<head>")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
				.append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")
				.append("</head>").append("<body><strong><span style=\"font-size: 20pt;color:red; \">欢迎使用</span></strong>");
		html.append("<div>支持中文！</div><br />");
		html.append("<img src=\"get.jpg\" width=\"500px\"></img>");
		html.append("</body></html>");
		System.out.println(html.toString());
		renderer.setDocumentFromString(html.toString());
		// 解决图片的相对路径问题
		renderer.getSharedContext().setBaseURL("file:/G:/");
		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}

	public static void main(String[] args) throws Exception {
		HTMLPDFTest2 h2 = new HTMLPDFTest2();
		h2.html2PDF();
	}

}
