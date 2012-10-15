package ro.sdl;

import ro.sdl.service.PdfTableGeneratorService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        PdfTableGeneratorService pdfTableGeneratorService = new PdfTableGeneratorService();
        pdfTableGeneratorService.generatePdfFile();
        pdfTableGeneratorService.generatePdfFileWithProjectComposition();
        pdfTableGeneratorService.generatePdfFileWithProjectDistribution();
        pdfTableGeneratorService.generatePdfFileWithProjectStateDistribution();
        pdfTableGeneratorService.generatePdfFileWithStructureDistribution();
        pdfTableGeneratorService.generatePdfFileWithRoleRatio();
    }
}
