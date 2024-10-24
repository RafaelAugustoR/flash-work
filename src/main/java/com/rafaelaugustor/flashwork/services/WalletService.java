package com.rafaelaugustor.flashwork.services;

import com.rafaelaugustor.flashwork.domain.entities.Transaction;
import com.rafaelaugustor.flashwork.domain.entities.Wallet;
import com.rafaelaugustor.flashwork.domain.enums.TransactionType;
import com.rafaelaugustor.flashwork.repositories.TransactionRepository;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.repositories.WalletRepository;
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
    public void deposit(Principal principal, BigDecimal amount) {
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
    }

    public Transaction withdraw(UUID userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found!"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setWallet(wallet);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        walletRepository.save(wallet);

        return transaction;
    }

    public TransactionResponseDTO transfer(UUID fromUserId, UUID toUserId, BigDecimal amount) {
        Wallet fromWallet = walletRepository.findByUserId(fromUserId)
                .orElseThrow(() -> new RuntimeException("Source wallet not found!"));
        Wallet toWallet = walletRepository.findByUserId(toUserId)
                .orElseThrow(() -> new RuntimeException("Destination wallet not found!"));

        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setWallet(fromWallet);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);

        return new TransactionResponseDTO(
                amount,
                TransactionType.TRANSFER.toString(),
                LocalDateTime.now()
        );
    }
}
