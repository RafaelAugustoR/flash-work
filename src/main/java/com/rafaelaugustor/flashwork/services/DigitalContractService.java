package com.rafaelaugustor.flashwork.services;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.DigitalContractRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.SignatureRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DigitalContractService {

    private final UserRepository userRepository;
    private final CloudinaryService cloudinary;


    public void generateDocument(DigitalContractRequestDTO request) {
        String templatePath = "src/main/resources/contracts/modelo_contrato.pdf";
        String outputDirectory = "src/main/resources/contracts/";

        String uniqueFileName = String.format("contract_%s_%s_%s.pdf",
                request.getClientId(),
                request.getFreelancerId(),
                LocalDate.now());
        String outputFilePath = outputDirectory + uniqueFileName;

        User client = userRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        User freelancer = userRepository.findById(request.getFreelancerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            PdfReader reader = new PdfReader(templatePath);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFilePath));
            PdfContentByte canvas = stamper.getOverContent(1);
            PdfContentByte canvas2 = stamper.getOverContent(2);

            BaseFont font = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
            canvas.setFontAndSize(font, 12);
            canvas2.setFontAndSize(font, 12);

            addText(canvas, client.getName(), 174, 663);
            addText(canvas, client.getAddresses().getFirst().getCity() + ", " + client.getAddresses().getFirst().getState(), 120, 650);
            addText(canvas, client.getCpf(), 380, 650);
            addText(canvas, freelancer.getName(), 170, 626);
            addText(canvas, freelancer.getCpf(), 95, 614);
            addText(canvas, freelancer.getAddresses().getFirst().getCity() + ", " + freelancer.getAddresses().getFirst().getState(), 320, 614);
            addText(canvas, request.getService().getDescription(), 90, 518);
            addText(canvas, request.getService().getBudget(), 211, 242);
            addText(canvas2, LocalDate.now().toString(), 80, 460);

            stamper.close();
            reader.close();

            MultipartFile multipartFile = getMultipartFile(outputFilePath);

            String cloudinaryUrl = cloudinary.uploadFile(multipartFile);

            System.out.println("PDF uploaded to Cloudinary: " + cloudinaryUrl);

        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Error to upload", e);
        }
    }

    private static MultipartFile getMultipartFile(String outputFilePath) {
        File fileToUpload = new File(outputFilePath);
        return new MultipartFile() {

            @Override
            public String getName() {
                return fileToUpload.getName();
            }

            @Override
            public String getOriginalFilename() {
                return fileToUpload.getName();
            }

            @Override
            public String getContentType() {
                return "application/pdf";
            }

            @Override
            public boolean isEmpty() {
                return fileToUpload.length() == 0;
            }

            @Override
            public long getSize() {
                return fileToUpload.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return StreamUtils.copyToByteArray(new FileInputStream(fileToUpload));
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(fileToUpload);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.copy(fileToUpload.toPath(), dest.toPath());
            }
        };
    }

    public void addSignatureToContract(UUID clientId, UUID freelancerId, SignatureRequestDTO signatureRequest) throws FileNotFoundException {
        byte[] decodedBytes = Base64.getDecoder().decode(signatureRequest.getImage().split(",")[1]);

        String contractFileName = String.format("contract_%s_%s_%s.pdf", clientId, freelancerId, LocalDate.now());
        String contractPath = "src/main/resources/contracts/" + contractFileName;
        String signedContractPath = "src/main/resources/contracts/" + contractFileName.replace("contract_", "signed_contract_");

        File contractFile = new File(contractPath);
        if (!contractFile.exists()) {
            throw new FileNotFoundException("Contrato não encontrado para os IDs fornecidos.");
        }

        PdfReader reader = null;
        PdfStamper stamper = null;

        try {
            reader = new PdfReader(contractPath);
            stamper = new PdfStamper(reader, new FileOutputStream(signedContractPath));
            PdfContentByte content = stamper.getOverContent(2);

            Image signatureImage = Image.getInstance(decodedBytes);
            signatureImage.setAbsolutePosition(160, 380);
            signatureImage.scaleAbsolute(100, 50);
            content.addImage(signatureImage);

        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Erro ao adicionar assinatura no contrato", e);
        } finally {
            try {
                if (stamper != null) stamper.close();
                if (reader != null) reader.close();
            } catch (IOException | DocumentException e) {
                System.err.println("Erro ao fechar recursos PDF: " + e.getMessage());
            }
        }
    }


    private void addText(PdfContentByte canvas, String text, float x, float y) {
        canvas.beginText();
        canvas.setTextMatrix(x, y);
        canvas.showText(text);
        canvas.endText();
    }
}
