package com.fadesp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    @NonNull
    private Integer codigoDoDebito;

    @NotBlank(message = "Necessário possuir Cpf ou Cnpj")
    private String cpfCnpj;

    @NotBlank(message = "Necessário ter um método de pagamento")
    private String metodoDePagamento;

    private Integer numeroDoCartao;

    @NonNull
    private BigDecimal valorDoPagamento;

    @NotBlank(message = "Necessário ter um status de pagamento")
    private String statusPagamento;
}
