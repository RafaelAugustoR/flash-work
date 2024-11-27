package com.rafaelaugustor.flashwork.services;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.rafaelaugustor.flashwork.domain.entities.DigitalContract;
import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.ContractStatus;
import com.rafaelaugustor.flashwork.repositories.DigitalContractRepository;
import com.rafaelaugustor.flashwork.repositories.ServiceRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.DigitalContractRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.NotificationRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.SignatureRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.DigitalContractResponseDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.ServiceMinDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.UserMinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DigitalContractService {

    private final UserRepository userRepository;
    private final DigitalContractRepository digitalContractRepository;
    private final ServiceRepository serviceRepository;
    private final NotificationService notificationService;
    private final CloudinaryService cloudinary;

    public DigitalContractResponseDTO generateDocument(DigitalContractRequestDTO request, UUID serviceId) {
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
        com.rafaelaugustor.flashwork.domain.entities.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

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

            DigitalContract contract = DigitalContract.builder()
                    .service(service)
                    .client(client)
                    .freelancer(freelancer)
                    .cloudUrl(cloudinaryUrl)
                    .createdAt(LocalDateTime.now())
                    .status(ContractStatus.PENDING_SIGNATURES)
                    .signedByClient(false)
                    .signedByFreelancer(false)
                    .build();

            digitalContractRepository.save(contract);

            return toResponseDTO(contract);

        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Error to upload", e);
        }
    }

    public DigitalContractResponseDTO addSignatureToContract(UUID contractId, Principal principal, SignatureRequestDTO signatureRequest) throws FileNotFoundException {

        DigitalContract contract = digitalContractRepository.findById(contractId)
                .orElseThrow(() -> new FileNotFoundException("Contrato não encontrado para o ID fornecido."));

        User currentUser = userRepository.findByEmail(principal.getName());

        boolean isClient = contract.getClient().getId().equals(currentUser.getId());
        boolean isFreelancer = contract.getFreelancer().getId().equals(currentUser.getId());

        if (!isClient && !isFreelancer) {
            throw new SecurityException("Usuário não autorizado a assinar este contrato.");
        }

        if (isClient && contract.isSignedByClient()) {
            throw new IllegalStateException("O cliente já assinou este contrato.");
        } else if (isFreelancer && contract.isSignedByFreelancer()) {
            throw new IllegalStateException("O freelancer já assinou este contrato.");
        }

        String contractFileName = String.format("contract_%s_%s_%s.pdf",
                contract.getClient().getId(),
                contract.getFreelancer().getId(),
                LocalDate.now());
        String contractPath = "src/main/resources/contracts/" + contractFileName;
        String signedContractPath = "src/main/resources/contracts/" + contractFileName.replace("contract_", "signed_contract_");

        File contractFile = new File(contractPath);
        if (!contractFile.exists()) {
            throw new FileNotFoundException("Contrato não encontrado no sistema de arquivos.");
        }

        PdfReader reader = null;
        PdfStamper stamper = null;

        try {
            reader = new PdfReader(contractPath);
            stamper = new PdfStamper(reader, new FileOutputStream(signedContractPath));
            PdfContentByte content = stamper.getOverContent(2);

            byte[] decodedBytes = Base64.getDecoder().decode(signatureRequest.getImage().split(",")[1]);
            Image signatureImage = Image.getInstance(decodedBytes);
            signatureImage.setAbsolutePosition(isClient ? 100 : 400, 380);
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

        if (isClient) {
            contract.setSignedByClient(true);
            contract.setClientSignedAt(LocalDate.now());
        } else {
            contract.setSignedByFreelancer(true);
            contract.setFreelancerSignedAt(LocalDate.now());
        }

        if (contract.isSignedByClient() && contract.isSignedByFreelancer()) {
            contract.setStatus(ContractStatus.FULLY_SIGNED);
        }

        digitalContractRepository.save(contract);

        if (contract.getStatus() == ContractStatus.FULLY_SIGNED) {
            notificationService.sendNotification(new NotificationRequestDTO(
                    "O contrato foi assinado por ambas as partes. O serviço agora pode começar!",
                    new UserMinDTO(contract.getClient()),
                    new UserMinDTO(contract.getFreelancer())
            ));
        }
        return toResponseDTO(contract);
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

    private void addText(PdfContentByte canvas, String text, float x, float y) {
        canvas.beginText();
        canvas.setTextMatrix(x, y);
        canvas.showText(text);
        canvas.endText();
    }

    private DigitalContractResponseDTO toResponseDTO(DigitalContract digitalContract) {
        return DigitalContractResponseDTO.builder()
                .id(digitalContract.getId())
                .service(new ServiceMinDTO(digitalContract.getService()))
                .client(new UserMinDTO(digitalContract.getClient()))
                .freelancer(new UserMinDTO(digitalContract.getFreelancer()))
                .signedByClient(digitalContract.isSignedByClient())
                .signedByFreelancer(digitalContract.isSignedByFreelancer())
                .cloudUrl(digitalContract.getCloudUrl())
                .status(digitalContract.getStatus())
                .freelancerSignedAt(digitalContract.getFreelancerSignedAt())
                .clientSignedAt(digitalContract.getClientSignedAt())
                .build();
    }
}
