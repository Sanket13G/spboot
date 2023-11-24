package com.cwms.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Export;
import com.cwms.entities.ExternalParty;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExternalPartyRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RequestMapping("/export1")
@RestController
public class ExportControllerXLSPDF {

	@Autowired
	private ExportRepository exportRepository;

	@Autowired
	private ExternalPartyRepository externalPartyRepository;

	@GetMapping(value = "/getCartingAgent/{companyId}/{branchId}/{sirDate}")
	public List<ExternalParty> getCartingAgent(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);
		List<String> cIds = exportRepository.findByCompanyAndBranchAndSerDate(companyId, branchId, formattedDate);

		List<ExternalParty> externalParties = new ArrayList<>();
		for (String string : cIds) {
			externalParties.add(externalPartyRepository.findBycompbranchexternal(companyId, branchId, string));
		}

		return externalParties;
	}

	@GetMapping(value = "/getExportTpList/{companyId}/{branchId}/{sirDate}/{exId}")
	public List<Export> getExportTpList(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate,
			@PathVariable("exId") String exId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);

		List<Export> exports = exportRepository.findByCompanyAndBranchAndserDateAndexternalPId(companyId, branchId,
				formattedDate, exId);
		return exports;
	}

	public String getFlightNameByFlightNo(List<Export> dataList, String flightNoToFind) {
		for (Export export : dataList) {
			if (flightNoToFind.equals(export.getFlightNo())) {
				return export.getAirlineName(); // Assuming there is a method to get the flight name
			}
		}
		return null; // Return null if no matching flight number is found
	}

	private int countSbNoByAwbNo(List<Export> exports, String awbNo) {
		return (int) exports.stream().filter(data -> awbNo.equals(data.getAirwayBillNo())).count();
	}

	private int countNoOfPackagesByAwbNo(List<Export> exports, String awbNo) {
		return exports.stream().filter(data -> awbNo.equals(data.getAirwayBillNo())).mapToInt(Export::getNoOfPackages)
				.sum();
	}

	private int countNoOfPackagesByFlightNo(List<Export> exports, String flightNo) {
		return exports.stream().filter(data -> flightNo.equals(data.getFlightNo())).mapToInt(Export::getNoOfPackages)
				.sum();
	}

	private long countSbNoByFlightNo(List<Export> exports, String flightNo) {
		return exports.stream().filter(data -> flightNo.equals(data.getFlightNo())).map(Export::getSbNo).distinct()
				.count();
	}

	@GetMapping("/generateXlsCartingSheet/{companyId}/{branchId}/{sirDate}/{exId}")
	public ResponseEntity<byte[]> generateXLS(@PathVariable("companyId") String companyId,

			@PathVariable("branchId") String branchId,
			@PathVariable("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate,
			@PathVariable("exId") String exId) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate1 = sdf1.format(sirDate);
		List<Export> dataList = exportRepository.findByCompanyAndBranchAndserDateAndexternalPId(companyId, branchId,
				formattedDate, exId);

		// Group Export objects by flightNo
		Map<String, List<Export>> groupedData = new HashMap<>();

		for (Export data : dataList) {
			groupedData.computeIfAbsent(data.getFlightNo(), k -> new ArrayList<>()).add(data);
		}

		// Create a new workbook
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("CARTING SHEET");

		Row headerRow = sheet.createRow(0);

		// Create a cell style for the colspan
		CellStyle colspanCellStyle = workbook.createCellStyle();
		colspanCellStyle.setAlignment(HorizontalAlignment.CENTER);
		colspanCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font font = workbook.createFont();
		font.setBold(true);
		colspanCellStyle.setFont(font);
		colspanCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		colspanCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Create the first cell with colspan 4
		Cell cell1 = headerRow.createCell(0);
		cell1.setCellValue("CARTING SHEET");
		cell1.setCellStyle(colspanCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(headerRow.getRowNum(), // From row
				headerRow.getRowNum(), // To row
				0, // From column
				5 // To column (0 to 5, 6 columns in total)
		));

		// Create the second cell with colspan 3
		Cell cell2 = headerRow.createCell(6);
		cell2.setCellValue(formattedDate1);
		cell2.setCellStyle(colspanCellStyle);

		// Create a cell style for the colspan
		font.setBold(true);
		colspanCellStyle.setFont(font);

		int rowNum = 1;
		for (Map.Entry<String, List<Export>> entry : groupedData.entrySet()) {
			String previousAWBNumber = null; // Add a header row for the group
			Row groupHeaderRow = sheet.createRow(rowNum++);

			// Create a cell with colspan
			Cell groupHeaderCell = groupHeaderRow.createCell(0);
			groupHeaderCell.setCellValue(entry.getKey());
			groupHeaderCell.setCellStyle(colspanCellStyle);

			// Merge cells for the colspan
			sheet.addMergedRegion(new CellRangeAddress(groupHeaderRow.getRowNum(), // From row
					groupHeaderRow.getRowNum(), // To row
					0, // From column
					3 // To column (0 to 3, 4 columns in total)
			));

			// Set the flight name in the next cell with colspan 3 (4 to 6)
			Cell flightNameCell = groupHeaderRow.createCell(4);
			flightNameCell.setCellValue(getFlightNameByFlightNo(dataList, entry.getKey()));
			flightNameCell.setCellStyle(colspanCellStyle);
			sheet.addMergedRegion(new CellRangeAddress(groupHeaderRow.getRowNum(), // From row
					groupHeaderRow.getRowNum(), // To row
					4, // From column
					6 // To column (4 to 6, 3 columns in total)
			));

			// Create a header row for the group
			Row groupDataHeaderRow = sheet.createRow(rowNum++);
			CellStyle headerCellStyle = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerCellStyle.setFont(headerFont);

			// Set the background color to sky PINK
			headerCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			for (int i = 0; i < 7; i++) {
				Cell headerCell = groupDataHeaderRow.createCell(i);
				headerCell.setCellValue(""); // Set cell value if needed
				headerCell.setCellStyle(headerCellStyle);
			}

			// Set the cell values for the header row
			groupDataHeaderRow.getCell(0).setCellValue("FLIGHT NO");
			groupDataHeaderRow.getCell(1).setCellValue("AIRWAY BILL NO");
			groupDataHeaderRow.getCell(2).setCellValue("SER NO");
			groupDataHeaderRow.getCell(3).setCellValue("SB NO");
			groupDataHeaderRow.getCell(4).setCellValue("PORT OF DESTINATION");
			groupDataHeaderRow.getCell(5).setCellValue("NO OF SBS");
			groupDataHeaderRow.getCell(6).setCellValue("NO OF PACKAGES");

			Font boldFont = workbook.createFont();
			boldFont.setBold(true);

			// Populate the Excel sheet with data for the current group, sorted by
			// airwayBillNo
			List<Export> sortedList = entry.getValue().stream().sorted(Comparator.comparing(Export::getAirwayBillNo))
					.collect(Collectors.toList());
			for (Export data : sortedList) {

				// Check if the current AWB number is different from the previous one
				if (previousAWBNumber != null && !previousAWBNumber.equals(data.getAirwayBillNo())) {
					// Add an additional row here

					Row additionalRow = sheet.createRow(rowNum++);
					for (int i = 0; i < 7; i++) {
						Cell additionalCell = additionalRow.createCell(i);
						additionalCell.setCellValue("");
						if (i == 1) {
							additionalCell.setCellValue("SubTotal");
							CellStyle boldCellStyle = workbook.createCellStyle();
							boldCellStyle.setFont(boldFont);
							additionalCell.setCellStyle(boldCellStyle);

							// Set the colspan for the second cell (cell index 1) to cover columns 1 to 4
							sheet.addMergedRegion(new CellRangeAddress(additionalRow.getRowNum(), // From row
									additionalRow.getRowNum(), // To row
									1, // From column (index 1)
									4 // To column (index 4)
							));
						} else if (i == 5) {
							additionalCell.setCellValue(countSbNoByAwbNo(sortedList, previousAWBNumber));
							CellStyle boldCellStyle = workbook.createCellStyle();
							boldCellStyle.setFont(boldFont);
							additionalCell.setCellStyle(boldCellStyle);
						} else if (i == 6) {
							additionalCell.setCellValue(countNoOfPackagesByAwbNo(sortedList, previousAWBNumber));
							CellStyle boldCellStyle = workbook.createCellStyle();
							boldCellStyle.setFont(boldFont);
							additionalCell.setCellStyle(boldCellStyle);
						}

					}

					// You can customize the additional row data as needed
				}
				// Create a new row for each AWB number
				Row dataRow = sheet.createRow(rowNum++);
				dataRow.createCell(0).setCellValue(data.getFlightNo());
				dataRow.createCell(1).setCellValue(data.getAirwayBillNo());
				dataRow.createCell(2).setCellValue(data.getSerNo());
				dataRow.createCell(3).setCellValue(data.getSbNo());
				dataRow.createCell(4).setCellValue(data.getPortOfDestination());
				dataRow.createCell(5).setCellValue(1);
				dataRow.createCell(6).setCellValue(data.getNoOfPackages());

				previousAWBNumber = data.getAirwayBillNo();
			}

			Row additionalRow = sheet.createRow(rowNum++);
			for (int i = 0; i < 7; i++) {
				Cell additionalCell = additionalRow.createCell(i);
				additionalCell.setCellValue("");
				if (i == 1) {
					additionalCell.setCellValue("SubTotal");
					CellStyle boldCellStyle = workbook.createCellStyle();
					boldCellStyle.setFont(boldFont);
					additionalCell.setCellStyle(boldCellStyle);

					// Set the colspan for the second cell (cell index 1) to cover columns 1 to 4
					sheet.addMergedRegion(new CellRangeAddress(additionalRow.getRowNum(), // From row
							additionalRow.getRowNum(), // To row
							1, // From column (index 1)
							4 // To column (index 4)
					));
				} else if (i == 5) {
					additionalCell.setCellValue(countSbNoByAwbNo(sortedList, previousAWBNumber));
					CellStyle boldCellStyle = workbook.createCellStyle();
					boldCellStyle.setFont(boldFont);
					additionalCell.setCellStyle(boldCellStyle);
				} else if (i == 6) {
					additionalCell.setCellValue(countNoOfPackagesByAwbNo(sortedList, previousAWBNumber));
					CellStyle boldCellStyle = workbook.createCellStyle();
					boldCellStyle.setFont(boldFont);
					additionalCell.setCellStyle(boldCellStyle);
				}
			} // Add a summary row for the current group
//			

			Row summaryRow = sheet.createRow(rowNum++);
			for (int i = 0; i < 7; i++) {
				Cell additionalCell = summaryRow.createCell(i);
				additionalCell.setCellValue("");
				if (i == 1) {
					additionalCell.setCellValue("Total");
					CellStyle boldCellStyle = workbook.createCellStyle();
					boldCellStyle.setFont(boldFont);
					additionalCell.setCellStyle(boldCellStyle);

					// Set the colspan for the second cell (cell index 1) to cover columns 1 to 4
					sheet.addMergedRegion(new CellRangeAddress(summaryRow.getRowNum(), // From row
							summaryRow.getRowNum(), // To row
							1, // From column (index 1)
							4 // To column (index 4)
					));
				} else if (i == 5) {
					additionalCell.setCellValue(countSbNoByFlightNo(dataList, entry.getKey()));
					CellStyle boldCellStyle = workbook.createCellStyle();
					boldCellStyle.setFont(boldFont);
					additionalCell.setCellStyle(boldCellStyle);

				} else if (i == 6) {
					additionalCell.setCellValue(countNoOfPackagesByFlightNo(dataList, entry.getKey()));
					CellStyle boldCellStyle = workbook.createCellStyle();
					boldCellStyle.setFont(boldFont);
					additionalCell.setCellStyle(boldCellStyle);
				}
			}

		}

		Row dataRow = sheet.createRow(rowNum++);

		// Create cell styles for sky blue background and bold text
		CellStyle skyBlueStyle = workbook.createCellStyle();
		skyBlueStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		skyBlueStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		skyBlueStyle.setFont(boldFont);

		// Apply the styles to the cells
		Cell cell0 = dataRow.createCell(0);
		cell0.setCellStyle(skyBlueStyle);
		cell0.setCellValue("");

		Cell cell10= dataRow.createCell(1);
		cell10.setCellStyle(skyBlueStyle);
		cell10.setCellValue("GRAND TOTAL OF THE DAY");

		// Set colspan for cell1 to 4
		sheet.addMergedRegion(new CellRangeAddress(dataRow.getRowNum(), dataRow.getRowNum(), 1, 4));

		Cell cell5 = dataRow.createCell(5);
		cell5.setCellStyle(skyBlueStyle);
		cell5.setCellValue(countUniqueSbNos(dataList));

		Cell cell6 = dataRow.createCell(6);
		cell6.setCellStyle(skyBlueStyle);
		cell6.setCellValue(sumNoOfPackages(dataList));

		
		Row dataRow1 = sheet.createRow(rowNum++);
		dataRow1.createCell(0).setCellValue("");
		dataRow1.createCell(1).setCellValue("");
		dataRow1.createCell(2).setCellValue("");
		dataRow1.createCell(3).setCellValue("");
		dataRow1.createCell(4).setCellValue("");
		dataRow1.createCell(5).setCellValue("");
		dataRow1.createCell(6).setCellValue("");
		
		// Write the workbook to a ByteArrayOutputStream
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);

		// Set the response headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setContentDispositionFormData("attachment", "carting-sheet.xlsx");

		// Return the XLS file as a ResponseEntity
		return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
	}

@GetMapping("/generatePdfCartingSheet/{companyId}/{branchId}/{sirDate}/{exId}")
	public void downloadPdf(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId,
			@PathVariable("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate,
			@PathVariable("exId") String exId, HttpServletResponse response) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate1 = sdf1.format(sirDate);

		List<Export> dataList = exportRepository.findByCompanyAndBranchAndserDateAndexternalPId(companyId, branchId,
				formattedDate, exId);
		Map<String, List<Export>> groupedData = new HashMap<>();
		for (Export export : dataList) {
			groupedData.computeIfAbsent(export.getFlightNo(), k -> new ArrayList<>()).add(export);
		}

		Document document = new Document();

		try {
			// Set the response content type
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

			// Create a PdfWriter to write the document to the response's output stream
			PdfWriter.getInstance(document, response.getOutputStream());

			// Open the document
			document.open();
//			C:\Users\HP\Pictures
			String imagePath = "C:/Users/HP/Pictures/RDGDC.png";

			File imageFile = new File(imagePath);
			if (imageFile.exists()) {
				Image image = Image.getInstance(imagePath);
				image.scaleToFit(400, 300); // Adjust the dimensions as needed
				image.setAlignment(Element.ALIGN_CENTER);
				document.add(image);
			} else {
				System.out.println("img not here");// Handle the case where the image does not exist
			}

			PdfPTable table = new PdfPTable(7);

			PdfPCell headerCell = new PdfPCell(new Paragraph("CARTING SHEET"));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setColspan(7);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			PdfPTable headerTable = new PdfPTable(1);
			headerTable.setWidthPercentage(100);
			headerTable.addCell(headerCell);
			document.add(headerTable);

			// Add the second header row with two columns
			PdfPTable secondHeaderTable = new PdfPTable(7);
			secondHeaderTable.setWidthPercentage(100);

			PdfPCell secondHeaderCell1 = new PdfPCell(new Paragraph("Column 1"));
			secondHeaderCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//			secondHeaderCell1.setBorder(PdfPCell.NO_BORDER);
			secondHeaderCell1.setColspan(6); // Set colspan to 6 for the first column
			secondHeaderCell1.setBackgroundColor(new BaseColor(135, 206, 235)); // Set background color to pink
			secondHeaderCell1.setPadding(5); // Optional: Add some padding to improve appearance
			secondHeaderCell1.setPhrase(new Phrase("AIRLINE DETAILS", new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.HELVETICA, 9, com.itextpdf.text.Font.BOLD))); // Set
			secondHeaderTable.addCell(secondHeaderCell1);

			PdfPCell secondHeaderCell2 = new PdfPCell(new Paragraph("Column 2"));
			secondHeaderCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			secondHeaderCell2.setBorder(PdfPCell.NO_BORDER);
			secondHeaderCell2.setColspan(1); // Set colspan to 1 for the second column
			secondHeaderCell2.setBackgroundColor(new BaseColor(135, 206, 235)); // Set background color to pink
			secondHeaderCell2.setPadding(5); // Optional: Add some padding to improve appearance
			secondHeaderCell2.setPhrase(new Phrase(formattedDate1, new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.HELVETICA, 9, com.itextpdf.text.Font.BOLD))); // Set
			secondHeaderTable.addCell(secondHeaderCell2);

			document.add(secondHeaderTable);
			// Add data to the PDF
			for (Map.Entry<String, List<Export>> entry : groupedData.entrySet()) {
				String flightNo = entry.getKey();
				List<Export> flightData = entry.getValue();

				// Add a header row for the flightNo
				// Add data to the table
				table.setWidthPercentage(100);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);

				// Add table headers
				PdfPCell cell = createCellWithBackgroundColorAndBold(flightNo, new BaseColor(135, 206, 235), true);
				cell.setColspan(4);
				table.addCell(cell);

				cell = createCellWithBackgroundColorAndBold(
						"AIRLINE NAME:" + getFlightNameByFlightNo(dataList, flightNo), new BaseColor(135, 206, 235),
						true);
				cell.setColspan(3);
				table.addCell(cell);

				com.itextpdf.text.Font labelFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8);
				labelFont.setColor(BaseColor.BLACK); // Set font color if needed

				
				cell = new PdfPCell(new Phrase("FLIGHT NO", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("AIRWAY BILL NO", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("SER NO", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("SB NO", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("PORT OF DESTINATION", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("NO OF SBS", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("NO OF PACKAGES", labelFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);


				// Group flightData by awbno
				Map<String, List<Export>> awbnoGroups = flightData.stream()
						.collect(Collectors.groupingBy(Export::getAirwayBillNo));

				// Iterate through awbno groups
				for (Map.Entry<String, List<Export>> awbnoEntry : awbnoGroups.entrySet()) {
					String awbno = awbnoEntry.getKey();
					List<Export> awbnoData = awbnoEntry.getValue();

					com.itextpdf.text.Font font = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 9);
					// Add data to the table for this awbno
					// Add data to the table for this awbno
					for (Export export : awbnoData) {
						PdfPCell flightNoCell = new PdfPCell(new Phrase(export.getFlightNo(), font));
						flightNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(flightNoCell);

						PdfPCell airwayBillNoCell = new PdfPCell(new Phrase(export.getAirwayBillNo(), font));
						airwayBillNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(airwayBillNoCell);

						PdfPCell serNoCell = new PdfPCell(new Phrase(export.getSerNo(), font));
						serNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(serNoCell);

						PdfPCell sbNoCell = new PdfPCell(new Phrase(export.getSbNo(), font));
					    sbNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					    table.addCell(sbNoCell);


						PdfPCell portOfDestinationCell = new PdfPCell(new Phrase(export.getPortOfDestination(), font));
						portOfDestinationCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(portOfDestinationCell);

						PdfPCell oneCell = new PdfPCell(new Phrase(("1"), font));
						oneCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(oneCell);

						PdfPCell noOfPackagesCell = new PdfPCell(new Phrase(String.valueOf(export.getNoOfPackages()), font));
						noOfPackagesCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(noOfPackagesCell);
					}

					PdfPCell emptyCell1 = createCellWithBackgroundColorAndBoldleft("", new BaseColor(135, 206, 235),
							false);
					PdfPCell emptyCell2 = createCellWithBackgroundColorAndBoldleft("SUBTOTAL",
							new BaseColor(135, 206, 235), true);
					PdfPCell emptyCell3 = createCellWithBackgroundColorAndBoldleft(
							String.valueOf(countSbNoByAwbNo(dataList, awbno)), new BaseColor(135, 206, 235), true);
					PdfPCell emptyCell4 = createCellWithBackgroundColorAndBoldleft(
							String.valueOf(countNoOfPackagesByAwbNo(dataList, awbno)), new BaseColor(135, 206, 235),
							true);

					emptyCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
					emptyCell4.setHorizontalAlignment(Element.ALIGN_CENTER);

					emptyCell2.setColspan(4); // Column 2 and 3
					emptyCell3.setColspan(1); // Column 4 and 5

					table.addCell(emptyCell1); // Column 1
					table.addCell(emptyCell2); // Column 2 and 3
					table.addCell(emptyCell3); // Column 4 and 5
					table.addCell(emptyCell4); // Column 6

				}

				// Create the total cells
				PdfPCell emptyCell1 = createCellWithBackgroundColorAndBoldleft("", new BaseColor(135, 206, 235), false);
				PdfPCell emptyCell2 = createCellWithBackgroundColorAndBoldleft("TOTAL", new BaseColor(135, 206, 235),
						true);
				PdfPCell emptyCell3 = createCellWithBackgroundColorAndBoldleft(
						String.valueOf(countSbNoByFlightNo(dataList, flightNo)), new BaseColor(135, 206, 235), true);
				PdfPCell emptyCell4 = createCellWithBackgroundColorAndBoldleft(
						String.valueOf(countNoOfPackagesByFlightNo(dataList, flightNo)), new BaseColor(135, 206, 235),
						true);

				emptyCell2.setColspan(4); // Column 2 and 3
				emptyCell3.setColspan(1); // Column 4 and 5

				emptyCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				emptyCell4.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(emptyCell1); // Column 1
				table.addCell(emptyCell2); // Column 2 and 3
				table.addCell(emptyCell3); // Column 4 and 5
				table.addCell(emptyCell4); // Column 6

			}

			// Create the table for data (replace this with your actual data table creation)

			// Add the table to the document
			document.add(table);
			PdfPTable dataTable = new PdfPTable(7);
			dataTable.setWidthPercentage(100);

			// Add data to the data table
			// ...

			document.add(dataTable);

			// Add the second header row with two columns
			PdfPTable secondHeaderTable1 = new PdfPTable(7);
			secondHeaderTable1.setWidthPercentage(100);

			PdfPCell secondHeaderCell3 = new PdfPCell(new Paragraph("Column 1"));
			secondHeaderCell3.setHorizontalAlignment(Element.ALIGN_LEFT);
//			secondHeaderCell3.setBorder(PdfPCell.NO_BORDER);
			secondHeaderCell3.setColspan(1); // Set colspan to 6 for the first column
//			secondHeaderCell3.setBackgroundColor(new BaseColor(135, 206, 235)); // Set background color to pink
			secondHeaderCell3.setPadding(5); // Optional: Add some padding to improve appearance
			secondHeaderCell3
					.setPhrase(new Phrase("", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA,
							10, com.itextpdf.text.Font.BOLD))); // Set text to bold
			secondHeaderTable1.addCell(secondHeaderCell3);

			PdfPCell secondHeaderCell5 = new PdfPCell(new Paragraph("Column 2"));
			secondHeaderCell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			secondHeaderCell5.setColspan(4); // Set colspan to 6 for the first column
			secondHeaderCell5.setPadding(5); // Optional: Add some padding to improve appearance
			secondHeaderCell5.setPhrase(new Phrase("GRAND TOTAL OF THE DAY", new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.BOLD))); // Set text to bold
			secondHeaderTable1.addCell(secondHeaderCell5);

			PdfPCell secondHeaderCell4 = new PdfPCell(new Paragraph("Column 3"));
			secondHeaderCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			secondHeaderCell4.setColspan(1); // Set colspan to 1 for the second column
			secondHeaderCell4.setPadding(1); // Optional: Add some padding to improve appearance
			secondHeaderCell4
					.setPhrase(new Phrase(String.valueOf(countUniqueSbNos(dataList)), new com.itextpdf.text.Font(
							com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.BOLD))); // Set text
																												// to
																												// bold
			secondHeaderTable1.addCell(secondHeaderCell4);

			PdfPCell secondHeaderCell6 = new PdfPCell(new Paragraph("Column 4"));
			secondHeaderCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			secondHeaderCell6.setColspan(1); // Set colspan to 1 for the second column
			secondHeaderCell6.setPadding(1); // Optional: Add some padding to improve appearance
			secondHeaderCell6
					.setPhrase(new Phrase(String.valueOf(sumNoOfPackages(dataList)), new com.itextpdf.text.Font(
							com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.BOLD))); // Set text
																												// to
																												// bold
			secondHeaderTable1.addCell(secondHeaderCell6);

			document.add(secondHeaderTable1);
			// ...
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Function to create PdfPCell with background color and bold text
	private PdfPCell createCellWithBackgroundColorAndBold(String text, BaseColor backgroundColor, boolean bold) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(backgroundColor);

		if (bold) {
			com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA,
					8, com.itextpdf.text.Font.BOLD);
			cell.setPhrase(new Phrase(text, boldFont));
		}

		return cell;
	}

	private PdfPCell createCellWithBackgroundColorAndBoldleft(String text, BaseColor backgroundColor, boolean bold) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);

		if (bold) {
			com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA,
					8, com.itextpdf.text.Font.BOLD);
			cell.setPhrase(new Phrase(text, boldFont));
		}

		return cell;
	}
	public int sumNoOfPackages(List<Export> dataList) {
		return dataList.stream().mapToInt(Export::getNoOfPackages).sum();
	}

	public int countAwbnoOccurrences(List<Export> dataList, String awbno) {
		return (int) dataList.stream().filter(export -> awbno.equals(export.getAirwayBillNo())).count();
	}

	public int countUniqueSbNos(List<Export> dataList) {
		Set<String> sbNoSet = new HashSet<>();

		for (Export export : dataList) {
			sbNoSet.add(export.getSbNo());
		}

		return sbNoSet.size();
	}
}