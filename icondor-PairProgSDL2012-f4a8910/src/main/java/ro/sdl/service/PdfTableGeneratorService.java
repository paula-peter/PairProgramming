package ro.sdl.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ro.sdl.application.data.AppDataLoader;
import ro.sdl.domain.Project;
import ro.sdl.domain.User;
import ro.sdl.dto.ProjectDetailedDistributionDTO;
import ro.sdl.dto.ProjectDistributionDTO;
import ro.sdl.repository.RepositoryException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * User: Daniel Sarbe <dsarbe@sdl.com>
 * Date: Nov 7, 2011
 */
public class PdfTableGeneratorService {

    public static String GENERAL_PDF_FILE = "EngineeringGeneral.pdf";
    public static String PROJECT_COMPOSITION_PDF_FILE = "Engineering_ProjectComposition.pdf";
    public static String PROJECT_DISTRIBUTION_PDF_FILE = "Engineering_ProjectDistribution.pdf";
    public static String PROJECT_STATE_DISTRIBUTION_PDF_FILE = "Engineering_ProjectStateDistribution.pdf";
    public static String STRUCTURE_DISTRIBUTION_PDF_FILE = "Engineering_StructureDistribution.pdf";
    public static String ROLE_RATIO_PDF_FILE = "Engineering_RoleRatio.pdf";

    /**
     * Generates a pdf with the Engineering structure
     */
    public void generatePdfFile() {
        try {

            Document document = new Document(PageSize.A4, 0, 0, 10, 10);

            PdfWriter.getInstance(document, new FileOutputStream(GENERAL_PDF_FILE));
            document.open();
            PdfPTable table = new PdfPTable(4);
            PdfPCell cell = new PdfPCell(new Paragraph("Engineering Dashboard"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(128, 200, 100));
            cell.setPadding(10.0f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Name"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Role"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("State"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Project"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            for (User user : AppDataLoader.users) {
                table.addCell(user.getName());
                table.addCell(user.getRole().toString());
                table.addCell(user.getState().toString());
                table.addCell(user.getProject().getDescription());
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * PDF for list project composition for a project or for all the projects
     */

    public void generatePdfFileWithProjectComposition() {
        try {

            Document document = new Document(PageSize.A4, 0, 0, 10, 10);

            PdfWriter.getInstance(document, new FileOutputStream(PROJECT_COMPOSITION_PDF_FILE));
            document.open();
            PdfPTable table = new PdfPTable(4);
            PdfPCell cell1 = new PdfPCell(new Paragraph("List project composition for all the projects"));
            cell1.setColspan(4);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(new Color(128, 200, 100));
            cell1.setPadding(10.0f);
            table.addCell(cell1);

            for (Project project : AppDataLoader.projects) {
                PdfPCell cell = new PdfPCell(new Paragraph("Project: " + project.getDescription()));
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(128, 200, 100));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Name"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Role"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("State"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Project"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                for (User user : project.getUsers()) {
                    table.addCell(user.getName());
                    table.addCell(user.getRole().toString());
                    table.addCell(user.getState().toString());
                    table.addCell(user.getProject().getDescription());
                }
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * PDF for list project distribution for a project or for all the projects
     */
    public void generatePdfFileWithProjectDistribution() {
        try {

            Document document = new Document(PageSize.A4, 0, 0, 10, 10);

            PdfWriter.getInstance(document, new FileOutputStream(PROJECT_DISTRIBUTION_PDF_FILE));
            document.open();
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell1 = new PdfPCell(new Paragraph("List project distribution for all the projects"));
            cell1.setColspan(2);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(new Color(128, 200, 100));
            cell1.setPadding(10.0f);
            table.addCell(cell1);

            for (Project project : AppDataLoader.projects) {
                ProjectDistributionDTO dto = new ProjectServiceImpl().getProjectDistribution(project);

                PdfPCell cell = new PdfPCell(new Paragraph("Project: " + project.getDescription()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(128, 200, 100));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("devPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("qaPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                table.addCell(Integer.toString(dto.getDevPercentage()));
                table.addCell(Integer.toString(dto.getQaPercentage()));

            }
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * PDF for list project state distribution for a project or for all the projects
     */
    public void generatePdfFileWithProjectStateDistribution() {
        try {

            Document document = new Document(PageSize.A4, 0, 0, 10, 10);

            PdfWriter.getInstance(document, new FileOutputStream(PROJECT_STATE_DISTRIBUTION_PDF_FILE));
            document.open();
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell1 = new PdfPCell(new Paragraph("List project state distribution for all the projects"));
            cell1.setColspan(3);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(new Color(128, 200, 100));
            cell1.setPadding(10.0f);
            table.addCell(cell1);

            for (Project project : AppDataLoader.projects) {
                ProjectDetailedDistributionDTO dto = new ProjectServiceImpl().getProjectDetailedDistribution(project);

                PdfPCell cell = new PdfPCell(new Paragraph("Project: " + project.getDescription()));
                cell.setColspan(3);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(128, 200, 100));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("DevSeniorPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);


                cell = new PdfPCell(new Paragraph("DevMidPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("DevJuniorPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                table.addCell(Integer.toString(dto.getProjectDevStateDistributionDTO().getSeniorPercentage()));
                table.addCell(Integer.toString(dto.getProjectDevStateDistributionDTO().getMidPercentage()));
                table.addCell(Integer.toString(dto.getProjectDevStateDistributionDTO().getJuniorPercentage()));

                cell = new PdfPCell(new Paragraph("QaSeniorPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("QaMidPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("QaJuniorPercentage"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);

                table.addCell(Integer.toString(dto.getProjectQaStateDistributionDTO().getSeniorPercentage()));
                table.addCell(Integer.toString(dto.getProjectQaStateDistributionDTO().getMidPercentage()));
                table.addCell(Integer.toString(dto.getProjectQaStateDistributionDTO().getJuniorPercentage()));


            }
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * PDF for list structure distribution for a project or for all the projects
     */
    public void generatePdfFileWithStructureDistribution() {
        try {

            Document document = new Document(PageSize.A4, 0, 0, 10, 10);

            PdfWriter.getInstance(document, new FileOutputStream(STRUCTURE_DISTRIBUTION_PDF_FILE));
            document.open();
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell1 = new PdfPCell(new Paragraph("List structure distribution for all the projects"));
            cell1.setColspan(3);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(new Color(128, 200, 100));
            cell1.setPadding(10.0f);
            table.addCell(cell1);


            ProjectDetailedDistributionDTO dto = new ProjectServiceImpl().getStructureDistribution();

            PdfPCell cell = new PdfPCell(new Paragraph("All"));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(128, 200, 100));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("DevSeniorPercentage"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);


            cell = new PdfPCell(new Paragraph("DevMidPercentage"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("DevJuniorPercentage"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            table.addCell(Integer.toString(dto.getProjectDevStateDistributionDTO().getSeniorPercentage()));
            table.addCell(Integer.toString(dto.getProjectDevStateDistributionDTO().getMidPercentage()));
            table.addCell(Integer.toString(dto.getProjectDevStateDistributionDTO().getJuniorPercentage()));

            cell = new PdfPCell(new Paragraph("QaSeniorPercentage"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("QaMidPercentage"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("QaJuniorPercentage"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(255, 200, 0));
            cell.setPadding(10.0f);
            table.addCell(cell);

            table.addCell(Integer.toString(dto.getProjectQaStateDistributionDTO().getSeniorPercentage()));
            table.addCell(Integer.toString(dto.getProjectQaStateDistributionDTO().getMidPercentage()));
            table.addCell(Integer.toString(dto.getProjectQaStateDistributionDTO().getJuniorPercentage()));

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * PDF for list structure distribution for a project or for all the projects
     */
    public void generatePdfFileWithRoleRatio() {
        try {

            Document document = new Document(PageSize.A4, 0, 0, 10, 10);

            PdfWriter.getInstance(document, new FileOutputStream(ROLE_RATIO_PDF_FILE));
            document.open();
            PdfPTable table = new PdfPTable(1);
            PdfPCell cell1 = new PdfPCell(new Paragraph("List structure distribution for all the projects"));
            cell1.setColspan(1);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(new Color(128, 200, 100));
            cell1.setPadding(10.0f);
            table.addCell(cell1);

            PdfPCell cell = new PdfPCell(new Paragraph("Projects"));
            cell.setColspan(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new Color(128, 200, 100));
            cell.setPadding(10.0f);
            table.addCell(cell);

            ArrayList<Project> projects = (ArrayList<Project>) new ProjectServiceImpl().listProjectWithRole(1);

            for (Project project : projects) {
                cell = new PdfPCell(new Paragraph(project.getDescription()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(255, 200, 0));
                cell.setPadding(10.0f);
                table.addCell(cell);
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
