package com.fadesp.api.service;

import com.fadesp.api.model.Pagamento;
import com.fadesp.api.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento efetuarPagamento(Pagamento pagamento) {
        if (pagamento.getMetodoDePagamento().equals("cartao_credito") || pagamento.getMetodoDePagamento().equals("cartao_debito")) {
        } else {
            pagamento.setNumeroDoCartao(null);
        }
        return this.pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarPagamento(Long id, String novoStatusPagamento) {
        Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
        if (pagamentoOptional.isPresent()) {

            Pagamento pagamentoAtual = pagamentoOptional.get();
            Pagamento pagamentoAtualizado = new Pagamento();

            if (!(pagamentoAtual.getStatusPagamento().equals("Processado com Sucesso"))) {
                pagamentoAtualizado.setId(pagamentoAtual.getId());
                pagamentoAtualizado.setCodigoDoDebito(pagamentoAtual.getCodigoDoDebito());
                pagamentoAtualizado.setCpfCnpj(pagamentoAtual.getCpfCnpj());
                pagamentoAtualizado.setMetodoDePagamento(pagamentoAtual.getMetodoDePagamento());
                pagamentoAtualizado.setNumeroDoCartao(pagamentoAtual.getNumeroDoCartao());
                pagamentoAtualizado.setValorDoPagamento(pagamentoAtual.getValorDoPagamento());

                if (pagamentoAtual.getStatusPagamento().equals("Processado com Falha"))
                    novoStatusPagamento = "Pendente de Processamento";

                pagamentoAtualizado.setStatusPagamento(novoStatusPagamento);
                return this.pagamentoRepository.save(pagamentoAtualizado);
            }

        }
        return null;
    }

    public List<Pagamento> listarPagamento() {
        return this.pagamentoRepository.findAll();
    }

    public void deletarPagamento(Long id) {
        Optional<Pagamento> pagamentoOptional = this.pagamentoRepository.findById(id);
        if (pagamentoOptional.isPresent()) {
            this.pagamentoRepository.deleteById(id);
//            if (!(pagamentoOptional.get().getStatusPagamento().equals("Pendente de Processamento"))) {
//
//            }
        }
    }

    public List<Pagamento> consultarPagamentoPorCodigoDoDebito(String codigoDoDebito) {
        return this.pagamentoRepository.findByCodigoDoDebito(codigoDoDebito);
    }

    public List<Pagamento> consultarPagamentoPorCpfCnpj(String cpfCnpj) {
        return this.pagamentoRepository.findByCpfCnpj(cpfCnpj);
    }

    public List<Pagamento> consultarPagamentoPorStatus(String statusPagamento) {
        return this.pagamentoRepository.findByStatusPagamento(statusPagamento);
    }

    public Optional<Pagamento> consultarPagamentoPorId(Long id) {
        return this.pagamentoRepository.findById(id);
    }

}
