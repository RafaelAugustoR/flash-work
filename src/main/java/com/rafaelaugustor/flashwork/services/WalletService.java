package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Transaction;
import com.rafaelaugustor.flashwork.domain.entities.Wallet;
import com.rafaelaugustor.flashwork.domain.enums.TransactionType;
import com.rafaelaugustor.flashwork.repositories.TransactionRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.repositories.WalletRepository;
import com.rafaelaugustor.flashwork.rest.dtos.request.DepositRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    @Transactional
    public TransactionResponseDTO deposit(Principal principal, BigDecimal amount) {
        var user = userRepository.findByEmail(principal.getName());

        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found for user: " + user.getId()));

        wallet.setBalance(wallet.getBalance().add(amount));

        Transaction transaction = Transaction.builder()
                .amount(amount)
                .transactionType(TransactionType.DEPOSIT)
                .wallet(wallet)
                .transactionDate(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
        walletRepository.save(wallet);

        return new TransactionResponseDTO(
                transaction.getAmount(),
                transaction.getTransactionType().toString(),
                transaction.getTransactionDate()
        );
    }
}
