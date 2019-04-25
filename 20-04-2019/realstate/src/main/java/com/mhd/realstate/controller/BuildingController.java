package com.mhd.realstate.controller;

import com.mhd.realstate.entity.Buildings;
import com.mhd.realstate.jasper.BuildingService;
import com.mhd.realstate.jasper.MediaTypeUtils;
import com.mhd.realstate.repo.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/building/")
public class BuildingController {
    private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
    @Autowired
    private BuilldingRepo builldingRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private AreaRepo areaRepo;
    @Autowired
    private HousingRepo housingRepo;
    @Autowired
    private CatagoryRepo catagoryRepo;
    @Autowired
    private AvailRepo availRepo;
    @Autowired
    private ConstructionRepo constructionRepo;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    ServletContext context;


    @Autowired
    private ImageOptimizer imageOptimizer;

    @GetMapping(value = "building-list")
    public String list(Model model) {
        model.addAttribute("list", this.builldingRepo.findAll());
        return "buildings/building-list";
    }

    @GetMapping(value = "building-grid-list")
    public String listgrid(Model model) {
        model.addAttribute("list", this.builldingRepo.findAll());
        return "buildings/building-grid-list";
    }


    @GetMapping(value = "add-building")
    public String viewAdd(Model model) {
        model.addAttribute("building", new Buildings());
        model.addAttribute("cities", this.cityRepo.findAll());
        model.addAttribute("areas", this.areaRepo.findAll());
        model.addAttribute("housings", this.housingRepo.findAll());
        model.addAttribute("catagories", this.catagoryRepo.findAll());
        model.addAttribute("availability", this.availRepo.findAll());
        model.addAttribute("constructiontypes", this.constructionRepo.findAll());
        return "buildings/add-building";
    }

    @PostMapping(value = "add-building")
    public String add(@Valid Buildings buildings, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", this.cityRepo.findAll());
            model.addAttribute("areas", this.areaRepo.findAll());
            model.addAttribute("housings", this.housingRepo.findAll());
            model.addAttribute("catagories", this.catagoryRepo.findAll());
            model.addAttribute("availability", this.availRepo.findAll());
            model.addAttribute("constructiontypes", this.constructionRepo.findAll());
            return "buildings/add-building";
        }

        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            buildings.setFileName("new-" + file.getOriginalFilename());
            buildings.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            buildings.setFilePath("/img/" + "new-" + file.getOriginalFilename());
            buildings.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////
            buildings.setRegistrationDate(new Date());
            this.builldingRepo.save(buildings);
            model.addAttribute("building", new Buildings());
            model.addAttribute("cities", this.cityRepo.findAll());
            model.addAttribute("areas", this.areaRepo.findAll());
            model.addAttribute("housings", this.housingRepo.findAll());
            model.addAttribute("catagories", this.catagoryRepo.findAll());
            model.addAttribute("availability", this.availRepo.findAll());
            model.addAttribute("constructiontypes", this.constructionRepo.findAll());
            model.addAttribute("successMsg", "Congratulations! Successfully added data.");
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("cities", this.cityRepo.findAll());
        model.addAttribute("areas", this.areaRepo.findAll());
        model.addAttribute("housings", this.housingRepo.findAll());
        model.addAttribute("catagories", this.catagoryRepo.findAll());
        model.addAttribute("availability", this.availRepo.findAll());
        model.addAttribute("constructiontypes", this.constructionRepo.findAll());
        return "buildings/add-building";
    }


    @GetMapping(value = "edit-building/{id}")
    public String editShow(Model model, @PathVariable("id") Long id) {
        model.addAttribute("building", this.builldingRepo.getOne(id));
        model.addAttribute("cities", this.cityRepo.findAll());
        model.addAttribute("areas", this.areaRepo.findAll());
        model.addAttribute("housings", this.housingRepo.findAll());
        model.addAttribute("catagories", this.catagoryRepo.findAll());
        model.addAttribute("availability", this.availRepo.findAll());
        model.addAttribute("constructiontypes", this.constructionRepo.findAll());
        return "buildings/edit-building";
    }

    @PostMapping(value = "edit-building/{id}")
    public String userEdit(@Valid Buildings buildings
            , BindingResult bindingResult, @PathVariable("id") Long id, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", this.cityRepo.findAll());
            model.addAttribute("areas", this.areaRepo.findAll());
            model.addAttribute("housings", this.housingRepo.findAll());
            model.addAttribute("catagories", this.catagoryRepo.findAll());
            model.addAttribute("availability", this.availRepo.findAll());
            model.addAttribute("constructiontypes", this.constructionRepo.findAll());
            return "buildings/edit-building";
        }
        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            buildings.setFileName("new-" + file.getOriginalFilename());
            buildings.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            buildings.setFilePath("/img/" + "new-" + file.getOriginalFilename());
            buildings.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////
            buildings.setRegistrationDate(new Date());
            this.builldingRepo.save(buildings);
            model.addAttribute("buildings", new Buildings());
            model.addAttribute("cities", this.cityRepo.findAll());
            model.addAttribute("areas", this.areaRepo.findAll());
            model.addAttribute("housings", this.housingRepo.findAll());
            model.addAttribute("catagories", this.catagoryRepo.findAll());
            model.addAttribute("availability", this.availRepo.findAll());
            model.addAttribute("constructiontypes", this.constructionRepo.findAll());
            model.addAttribute("success", "Congratulations! Data save sucessfully");
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("cities", this.cityRepo.findAll());
        model.addAttribute("areas", this.areaRepo.findAll());
        model.addAttribute("housings", this.housingRepo.findAll());
        model.addAttribute("catagories", this.catagoryRepo.findAll());
        model.addAttribute("availability", this.availRepo.findAll());
        model.addAttribute("constructiontypes", this.constructionRepo.findAll());
        return "redirect: /building/building-list";
    }


    @GetMapping(value = "delete/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.builldingRepo.deleteById(id);
        }
        return "redirect:/building/building-list";
    }

    ////////////////////////////JASPER/////////////////////////////////


    @RequestMapping(value = "report", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(buildingService.buildingReport());
        InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }

////////////////pdf download//////////////////////

    //    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportPdf() throws Exception {
        String source = "src\\main\\resources\\report.jrxml";
        try {
            JasperCompileManager.compileReportToFile(source);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String sourceFileName = "src\\main\\resources\\report1.jasper";
        String printFileName = null;
        String destFileName = "src\\main\\resources\\report.pdf";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(buildingService.buildingReport());
        Map parameters = new HashMap();
        try {
            printFileName = JasperFillManager.fillReportToFile(sourceFileName,
                    parameters, dataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        destFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/pdf")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
        try {
            reportPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName="src\\\\main\\\\resources\\\\report.pdf";
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.context, fileName);

        File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}
