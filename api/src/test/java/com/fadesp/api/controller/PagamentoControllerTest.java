package com.fadesp.api.controller;

import com.fadesp.api.model.Pagamento;
import com.fadesp.api.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PagamentoRepository pagamentoRepository;

    private Pagamento pagamento;

    @BeforeEach
    void init() {
        pagamento = new Pagamento();
    }

    @Test
    @DisplayName("Teste listando todos os pagamentos")
    void getListarPagamentos() throws Exception {
        pagamento.setStatusPagamento("Pendente de Processamento");
        Mockito.when(pagamentoRepository.findAll()).thenReturn(List.of(pagamento));
        this.mockMvc.perform(get("/pagamento/listar"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"codigoDoDebito\":null,\"cpfCnpj\":null,\"metodoDePagamento\":null,\"numeroDoCartao\":null,\"valorDoPagamento\":null,\"statusPagamento\":\"Pendente de Processamento\",\"id\":null}]"));
    }

    @Test
    @DisplayName("Teste efetuando um pagamento")
    void getEfetuarPagamento() throws Exception {
        Mockito.when(pagamentoRepository.save(Mockito.any())).thenReturn(pagamento);
        this.mockMvc.perform(post("/pagamento/efetuar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoDoDebito\":null,\"cpfCnpj\":null,\"metodoDePagamento\":\"dinheiro\",\"numeroDoCartao\":null,\"valorDoPagamento\":null,\"statusPagamento\":\"Pendente de Processamento\",\"id\":null}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"codigoDoDebito\":null,\"cpfCnpj\":null,\"metodoDePagamento\":dinheiro,\"numeroDoCartao\":null," +
                        "\"valorDoPagamento\":null,\"statusPagamento\":\"Pendente de Processamento\",\"id\":null}"));
    }

}