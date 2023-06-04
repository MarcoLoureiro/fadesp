package com.fadesp.api.controller;

import com.fadesp.api.dto.PagamentoDTO;
import com.fadesp.api.model.Pagamento;
import com.fadesp.api.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/listar")
    public ResponseEntity<List<PagamentoDTO>> getListarPagamentos() {
        List<Pagamento> pagamentoList = pagamentoService.listarPagamento();
        if (pagamentoList.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.mapPagamentoListEntityToDtoList(pagamentoList));
    }

    @PostMapping("/efetuar")
    public ResponseEntity<Pagamento> getEfetuarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoService.mapPagamentoDtoToEntity(pagamentoDTO);
        this.pagamentoService.efetuarPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> getAtualizarPagamento(@PathVariable Long id, @RequestBody String statusPagamento) {
        Pagamento pagamento = this.pagamentoService.atualizarPagamento(id, statusPagamento);
        if (Objects.nonNull(pagamento)) {
            if (pagamento.getStatusPagamento().equals("Processado com Sucesso"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pagamento com status que não permite atualização!");

            return ResponseEntity.status(HttpStatus.OK).body("Pagamento atualizado!");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pagamento com id " + id + " não encontrado!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> getDeletarPagamento(@PathVariable Long id) {
        Optional<Pagamento> pagamento = this.pagamentoService.consultarPagamentoPorId(id);
        if (pagamento.isPresent()) {
            if (pagamento.get().getStatusPagamento().equals("Pendente de Processamento")) {
                this.pagamentoService.deletarPagamento(id);
                return ResponseEntity.ok("Pagamento removido!");
            } else {
                return ResponseEntity.badRequest().body("Pagamento com status que não permite deleção!");
            }
        } else {
            return ResponseEntity.badRequest().body("Id de pagamento inválido!");
        }
    }

    @GetMapping("consultar/codigoDoDebito/{codigoDoDebito}")
    public ResponseEntity<List<Pagamento>> getConsultarPagamentoPorCodigoDoDebito(@PathVariable String codigoDoDebito) {
        List<Pagamento> pagamentoList = this.pagamentoService.consultarPagamentoPorCodigoDoDebito(codigoDoDebito);
        if (!(pagamentoList.isEmpty())) {
            return ResponseEntity.status(HttpStatus.OK).body(pagamentoList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("consultar/cpfCnpj/{cpfCnpj}")
    public ResponseEntity<List<Pagamento>> getConsultarPagamentoPorCpfCnpj(@PathVariable String cpfCnpj) {
        List<Pagamento> pagamentoList = this.pagamentoService.consultarPagamentoPorCpfCnpj(cpfCnpj);
        if (!(pagamentoList.isEmpty())) {
            return ResponseEntity.status(HttpStatus.OK).body(pagamentoList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("consultar/statusPagamento/{statusPagamento}")
    public ResponseEntity<List<Pagamento>> getConsultarPagamentoPorStatus(@PathVariable String statusPagamento) {
        List<Pagamento> pagamentoList = this.pagamentoService.consultarPagamentoPorStatus(statusPagamento);
        if (!(pagamentoList.isEmpty())) {
            return ResponseEntity.status(HttpStatus.OK).body(pagamentoList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
