package com.cwms.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Gate_In_Out;
import com.cwms.entities.Import;
import com.cwms.repository.Gate_In_out_Repo;
import com.cwms.repository.ImportRepo;
import com.cwms.service.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@RestController
@RequestMapping("barcodeGenerater")
@CrossOrigin("*")
public class BarcodeController {

	@Autowired
	public ImportService importService;

	@Autowired
	public ExportService ExportService;

	@Autowired
	public ImportSubService ImportSubService;

	 @Autowired
		private Gate_In_out_Repo gateinoutrepo;
	 
	 @Autowired
		private ImportRepo importRepo;
	
	@Autowired
	public ExportSubService ExportSubService;

	@PostMapping(value = "/generatePDFWithMultipleBarcodes")
	public ResponseEntity<String> generatePDFWithMultipleBarcodes(@RequestBody Map<String, Object> requestData)
			throws Exception {

//		System.out.println(requestData);
		String mawbno = (String) requestData.get("mawbno");
		String sirno = (String) requestData.get("sirno");

//		System.out.println("Converting nop");
		int noOfPackages = (int) requestData.get("noOfPackages");
//		System.out.println("Converted**");
		String type = (String) requestData.get("type");
		String niptStatus = (String) requestData.get("niptStatus");

		String BENo = (String) requestData.get("requestId");
		String HAWB = (String) requestData.get("HAWB");
		String IGM = (String) requestData.get("IGM");
		String subType = (String) requestData.get("subType");

//		System.out.println("Converting sirdate to long");
		Long sirDateTimestamp = (Long) requestData.get("sirDate");
		Date sirDate = new Date(sirDateTimestamp);
//		System.out.println("Converting reqDate to long");
		Object reqDateObject = requestData.get("reqDate");
		Long reqDateTimestamp;

		if (reqDateObject instanceof Long) {
			reqDateTimestamp = (Long) reqDateObject;
		} else if (reqDateObject instanceof Integer) {
			reqDateTimestamp = ((Integer) reqDateObject).longValue();
		} else {
			reqDateTimestamp = System.currentTimeMillis();
		}

		// Now you can proceed with converting reqDateTimestamp to Date
		Date reqDate = new Date(reqDateTimestamp);

//		System.out.println("ITextRenderer Comming ");
//		// Create an ITextRenderer instance
//		ITextRenderer renderer = new ITextRenderer();

		// Create an HTML string with the content and styling

		String htmlContent = generateHTMLContent(mawbno, sirno, noOfPackages, sirDate, reqDate, type, niptStatus, BENo,HAWB,IGM,subType);

		// Set the HTML content for rendering
//		renderer.setDocumentFromString(htmlContent);
//
//		// Layout the HTML content
//		renderer.layout();
//
//		// Render the PDF to the output stream
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		renderer.createPDF(outputStream);
//
//		// Close the renderer
//		renderer.finishPDF();
//
//		// Convert the PDF content to a base64-encoded string
//		String base64PDF = Base64.getEncoder().encodeToString(outputStream.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML); // Set the content type to plain text

		// Return the base64-encoded PDF as a string
		return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
	}

	private String generateHTMLContent(String mawbno, String sirno, int noOfPackages, Date sirDate, Date reqDate,
			String type, String niptStatus, String BENo, String HAWB, String IGM,String subType) throws Exception {
		StringBuilder htmlContent = new StringBuilder();
		

		htmlContent.append("<html><head><style>.imgTag img{width:100%;height:auto;}</style></head><body>");

		for (int i = 1; i <= noOfPackages; i++) {
			int uniqueNumber = 000 + i;

			String uniqueNumberString = String.format("%04d", uniqueNumber);
//			String uniqueNumberString = String.valueOf(uniqueNumber);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//	        String currentDate = dateFormat.format(new Date());

			String sirDateString = dateFormat.format(sirDate);
			String reqDateString = dateFormat.format(reqDate);

			String sir2000 = sirno + uniqueNumberString;
			String barcodeData = sirno + uniqueNumberString;

			// Create a Barcode writer for Code 128 format (you can choose a different
			// format)
			BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(barcodeData, barcodeFormat, 200, 50); // Adjust size as
																									// needed

			// Convert the BitMatrix to a BufferedImage
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

			// Create a ByteArrayOutputStream to hold the barcode image bytes
			ByteArrayOutputStream barcodeStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", barcodeStream);

			// Encode the barcode image as a base64 string
			String base64Barcode = Base64.getEncoder().encodeToString(barcodeStream.toByteArray());
//			String packageHtml = null;
			// HTML content for each package

			if ("IMPORT".equals(type) && "Y".equals(niptStatus)) {

				htmlContent.append(
						"<table border=\"1\" style=\"height:144px; width:244px; text-align:center; border-collapse: collapse;margin: auto;border-radius:5px;\">");
				htmlContent.append("<tbody>");
				htmlContent.append("<tr><td colspan=\"2\" class=\"imgTag\" style=\"width:120px;\">");
				htmlContent.append("<img class=\"barcode\" src=\"data:image/png;base64," + base64Barcode + "\" alt=\""
						+ sirno + "\" title=\"" + sirno + "\">");
				htmlContent.append("<strong style=\"font-size: 20px;\">" + sir2000 + "</strong>" + "</td>" + "</tr>");
				htmlContent.append("<tr><td style=\"font-size: 11px;\"> BE : <strong>"+ BENo +"</strong> <br> Dt : <strong>"
						+ reqDateString + "</strong> </td><td style=\"font-size: 11px;\">");
				htmlContent.append(" SIR : <strong style=\"font-size: 14px;\">"+sirno+"</strong> <br>  Dt : <strong>"
						+ sirDateString + "</strong></td></tr>");
				htmlContent.append(
						"<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>NIPT >> DGDC SEEPZ</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style=\"font-size: 14px;\">"
								+ i + "/" + noOfPackages + "</strong></td></tr>");
				htmlContent.append("</tbody></table>");

			}

			if ("IMPORT".equals(type) && "N".equals(niptStatus) || "SUBIMPORT".equals(type)) {

				htmlContent.append(
						"<table border=\"1\" style=\"height:144px; width:244px; text-align:center; border-collapse: collapse;margin: auto;border-radius:5px;\">");
				htmlContent.append("<tbody>");
				htmlContent.append("<tr><td colspan=\"2\" class=\"imgTag\" style=\"width:120px;\">");
				htmlContent.append("<img class=\"barcode\" src=\"data:image/png;base64," + base64Barcode + "\" alt=\""
						+ sirno + "\" title=\"" + sirno + "\">");
				htmlContent.append("<strong style=\"font-size: 20px;\">" + sir2000 + "</strong>" + "</td>" + "</tr>");
//								
				if("IMPORT".equals(type))
				{
				    if (HAWB.startsWith("000")) {
				        HAWB = ""; // Set HAWB to blank if it starts with "00012546"
				    }

				htmlContent.append("<tr><td style=\"font-size: 11px;\"> SIR : <strong style=\\\"font-size: 14px;\\\">"+sirno+"</strong> <br> HAWB : <strong>"
						+ HAWB + "</strong> </td><td style=\"font-size: 11px;\">");
				htmlContent.append(" Dt : <strong style=\"font-size: 14px;\">"+sirDateString+"</strong> <br>  IGM : <strong>"
						+ IGM + "</strong></td></tr>");
				}
				
				
				if("SUBIMPORT".equals(type))
				{
					
					htmlContent.append(
							"<tr><td style=\"font-size: 11px;\" colspan=\"2\"> SIR : <strong style=\"font-size: 14px;\">"
									+ sirno + "</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dt : <strong>"
									+ sirDateString + "</strong></td></tr>");
					
				}
				if(subType.equals("Zone to Zone"))
				{
					htmlContent.append("<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>" + "Z to Z >> DGDC SEEPZ"
							+ "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>" + i + "/" + noOfPackages
							+ "</strong></td></tr>");
					
				}
				else if(subType.equals("LGD"))
				{
					htmlContent.append("<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>" + "LGD >> DGDC SEEPZ"
							+ "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>" + i + "/" + noOfPackages
							+ "</strong></td></tr>");					
				}				
				
				else
				{
					
				
				if("SUBIMPORT".equals(type))	
				{
					
					
					
					
					
					htmlContent.append("<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>" + "JOB >> DGDC SEEPZ"
							+ "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>" + i + "/" + noOfPackages
							+ "</strong></td></tr>");
				}
				else
				{
					htmlContent.append("<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>" + "DGDC SEEPZ"
							+ "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>" + i + "/" + noOfPackages
							+ "</strong></td></tr>");
				}
				
				}
				htmlContent.append("</tbody></table>");

			}
			
			
//			if("SUBIMPORT".equals(type))
//			{
//				
//				
//				
//				
//				
//				
//				
//				
//			}
//			
			
			
			
			
			
			if ("EXPORT".equals(type)) {

				htmlContent.append(
						"<table border=\"1\" style=\"height:144px; width:244px; text-align:center; border-collapse: collapse;margin: auto;border-radius:5px;\">");
				htmlContent.append("<tbody>");
				htmlContent.append("<tr><td colspan=\"2\" class=\"imgTag\" style=\"width:120px;\">");
				htmlContent.append("<img class=\"barcode\" src=\"data:image/png;base64," + base64Barcode + "\" alt=\""
						+ sirno + "\" title=\"" + sirno + "\">");
				htmlContent.append("<strong style=\"font-size: 20px;\">" + sir2000 + "</strong>" + "</td>" + "</tr>");
				htmlContent.append("<tr><td style=\"font-size: 11px;\"> SB : <strong>"+mawbno+"</strong> <br> Dt : <strong>"
						+ reqDateString + "</strong> </td><td style=\"font-size: 11px;\">");
				htmlContent.append(" SER : <strong style=\"font-size: 14px;\">"+sirno+"</strong> <br>  Dt : <strong>"
						+ sirDateString + "</strong></td></tr>");
				htmlContent.append(
						"<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>DGDC SEEPZ</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style=\"font-size: 14px;\">"
								+ i + "/" + noOfPackages + "</strong></td></tr>");
				htmlContent.append("</tbody></table></body></html>");
			}
		}

		htmlContent.append("</body></html>");
		return htmlContent.toString();
	}

	@PostMapping(value = "/generatePDFWithMDetention")
	public ResponseEntity<?> generatePDFWithDetentionList(@RequestBody Map<String, Object> requestData)
			throws Exception {

		int siNo = (int) requestData.get("siNo");

		String noOfPackages = (String) requestData.get("noOfPackages");

		Object reqDateObject = requestData.get("depositDate");
		Long reqDateTimestamp;

		if (reqDateObject instanceof Long) {
			reqDateTimestamp = (Long) reqDateObject;
		} else if (reqDateObject instanceof Integer) {
			reqDateTimestamp = ((Integer) reqDateObject).longValue();
		} else {
			reqDateTimestamp = System.currentTimeMillis();
		}

		// Now you can proceed with converting reqDateTimestamp to Date
		Date depositDate = new Date(reqDateTimestamp);

		// Create an ITextRenderer instance
//		ITextRenderer renderer = new ITextRenderer();

		// Create an HTML string with the content and styling

		String htmlContent = generateHTMLContent2(siNo, depositDate, noOfPackages);

		// Set the HTML content for rendering
//		renderer.setDocumentFromString(htmlContent);
//
//		// Layout the HTML content
//		renderer.layout();
//
//		// Render the PDF to the output stream
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		renderer.createPDF(outputStream);
//
//		// Close the renderer
//		renderer.finishPDF();

		// Convert the PDF content to a base64-encoded string
//		String base64PDF = Base64.getEncoder().encodeToString(outputStream.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);

		return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);

	}

	private String generateHTMLContent2(int mawbno, Date sirno, String noOfPackages) throws Exception {
		StringBuilder htmlContent = new StringBuilder();
//		int recordsPerPage = 7;
//
//		int recordCounter = 0; // Counter for records added
//		System.out.println("In html method");

		htmlContent.append("<html><head><style>.imgTag img{width:100%;height:auto;}</style></head><body>");
		for (int i = 1; i <= Integer.parseInt(noOfPackages); i++) {

//			String uniqueNumberString = String.format("%04d", uniqueNumber);
//			String uniqueNumberString = String.valueOf(uniqueNumber);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String SirDate = dateFormat.format(sirno);

			// Create a Barcode writer for Code 128 format (you can choose a different
			// format)
			BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(mawbno), barcodeFormat, 200, 50); // Adjust
																											// size as
			// needed

			// Convert the BitMatrix to a BufferedImage
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

			// Create a ByteArrayOutputStream to hold the barcode image bytes
			ByteArrayOutputStream barcodeStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", barcodeStream);

			// Encode the barcode image as a base64 string
			String base64Barcode = Base64.getEncoder().encodeToString(barcodeStream.toByteArray());

			htmlContent.append(
					"<table border=\"1\" style=\"height:144px; width:244px; text-align:center; border-collapse: collapse;margin: auto;border-radius:5px;\">");
			htmlContent.append("<tbody>");
			htmlContent.append("<tr><td colspan=\"2\" class=\"imgTag\" style=\"width:120px;\">");
			htmlContent.append("<img class=\"barcode\" src=\"data:image/png;base64," + base64Barcode + "\" alt=\""
					+ mawbno + "\" title=\"" + mawbno + "\">");
			htmlContent.append("<strong style=\"font-size: 20px;\">" + mawbno + "</strong>" + "</td>" + "</tr>");
			htmlContent.append("<tr><td style=\"font-size: 12px;\"> " + SirDate + "</td></tr>");
			htmlContent.append("</tbody></table>");

		}

		htmlContent.append("</body></html>");
		return htmlContent.toString();
	}

	// Print Sir By Master Bill Number

	@GetMapping(value = "/{compid}/{branchId}/{mawb}/printByMawb")
	public ResponseEntity<?> printByMawb(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @PathVariable("mawb") String mawb) throws Exception {

		List<Import> byMAWB = importService.getByMAWB(compid, branchId, mawb);
		if (byMAWB != null && !byMAWB.isEmpty()) {

//			int recordsPerPage = 6;
//			int recordCounter = 0;

			StringBuilder htmlContent = new StringBuilder();

			htmlContent.append("<html><head><style>.imgTag img{width:100%;height:auto;}</style></head><body>");

			for (Import imp : byMAWB) {
				
				
				if(imp.getNoc() == 0) {
		        	for(int i=1;i<=imp.getNop();i++) {
		        		String srNo = String.format("%04d", i);
		        		Gate_In_Out gateinout = new Gate_In_Out();
		        		gateinout.setCompanyId(imp.getCompanyId());
		        		gateinout.setBranchId(imp.getBranchId());
		        		gateinout.setNop(imp.getNop());
		        		gateinout.setErp_doc_ref_no(imp.getMawb());
		        		gateinout.setDoc_ref_no(imp.getHawb());
		        		gateinout.setSr_No(imp.getSirNo()+srNo);
		        		gateinout.setDgdc_cargo_in_scan("N");
		        		gateinout.setDgdc_cargo_out_scan("N");
		        		gateinout.setDgdc_seepz_in_scan("N");
		        		gateinout.setDgdc_seepz_out_scan("N");
		        		
		        		gateinoutrepo.save(gateinout);
		        	}
		        	
		        	imp.setNoc(imp.getNoc()+1);
		        	importRepo.save(imp);
				}
				
				for (int i = 1; i <= imp.getNop(); i++) {
					int uniqueNumber = 000 + i;
					String uniqueNumberString = String.format("%04d", uniqueNumber);

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date utilDate = new Date(imp.getSirDate().getTime());
					String sirDateString = dateFormat.format(utilDate);

					String sir2000 = imp.getSirNo() + uniqueNumberString;
					String barcodeData = imp.getSirNo() + uniqueNumberString;

					BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
					MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
					BitMatrix bitMatrix = multiFormatWriter.encode(barcodeData, barcodeFormat, 200, 50);

					BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

					ByteArrayOutputStream barcodeStream = new ByteArrayOutputStream();
					ImageIO.write(bufferedImage, "png", barcodeStream);

					String base64Barcode = Base64.getEncoder().encodeToString(barcodeStream.toByteArray());

					htmlContent.append(
							"<table border=\"1\" style=\"height:144px; width:244px; text-align:center; border-collapse: collapse;margin: auto;border-radius:5px;\">");
					htmlContent.append("<tbody>");
					htmlContent.append("<tr><td colspan=\"2\" class=\"imgTag\" style=\"width:120px;\">");
					htmlContent.append("<img class=\"barcode\" src=\"data:image/png;base64," + base64Barcode
							+ "\" alt=\"" + imp.getSirNo() + "\" title=\"" + imp.getSirNo() + "\">");
					htmlContent
							.append("<strong style=\"font-size: 20px;\">" + sir2000 + "</strong>" + "</td>" + "</tr>");
//					htmlContent.append(
//							"<tr><td style=\"font-size: 11px;\" colspan=\"2\"> SIR : <strong style=\"font-size: 14px;\">"
//									+ imp.getSirNo()
//									+ "</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dt : <strong>"
//									+ sirDateString + "</strong></td></tr>"
//									
//								+	"<td style=\"font-size: 11px;\" colspan=\"2\"> HAWB : <strong style=\"font-size: 14px;\">"
//									+ imp.getHawb()
//									+ "</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; IGM : <strong>"
//									+ imp.getIgmNo() + "</strong></td></tr>"
//							
//							);
					
					htmlContent.append("<tr><td style=\"font-size: 11px;\"> SIR : <strong style=\\\"font-size: 14px;\\\">"+imp.getSirNo()+"</strong> <br> HAWB : <strong>"
							+ imp.getHawb() + "</strong> </td><td style=\"font-size: 11px;\">");
					htmlContent.append(" Dt : <strong style=\"font-size: 14px;\">"+sirDateString+"</strong> <br>  IGM : <strong>"
							+ imp.getHawb() + "</strong></td></tr>");
					
					
					htmlContent.append("<tr><td colspan=\"2\" style=\"font-size: 12px;\"><strong>" + "DGDC SEEPZ"
							+ "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>" + i + "/"
							+ imp.getNop() + "</strong></td></tr>");
					htmlContent.append("</tbody></table>");

				}
			}

			htmlContent.append("</body></html>");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_HTML);

			return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data Found for Entered Master Bill Number");
		}
	}

//	@GetMapping("/{compId}/{branchId}/getDataStockAtVault")
//	public List<Object[]> getStockData(@PathVariable("compId") String compId,
//			@PathVariable("branchId") String branchId) {
//		return importService.getStockData(compId, branchId);
//	}
	@GetMapping("/{compId}/{branchId}/getDataStockAtVault")
	public List<Object[]> getStockData(@PathVariable("compId") String compId,
			@PathVariable("branchId") String branchId) {
		return importService.getStockData(compId, branchId);
	}
	
	@GetMapping("/{compId}/{branchId}/getDataStockAtVaultDetention")
	public List<Object[]> getDataStockAtVaultDetention(@PathVariable("compId") String compId,
			@PathVariable("branchId") String branchId) {
		return importService.getStockDataForDetention(compId, branchId);
	}
	
//	Import Sub
	
	
	
	
	
}