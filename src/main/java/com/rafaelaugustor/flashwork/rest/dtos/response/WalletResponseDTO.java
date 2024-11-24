package com.rafaelaugustor.flashwork.rest.dtos.response;

import com.rafaelaugustor.flashwork.domain.entities.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponseDTO {

    private UUID id;

    private BigDecimal balance;

    public WalletResponseDTO(Wallet entity) {
        this.id = entity.getId();
        this.balance = entity.getBalance();
    }
}
