
package com.api.pagamentos.service.v1;
import com.api.pagamentos.dtos.ListarPagamentosResponseDto;
import com.api.pagamentos.entity.model.PagamentosModel;
import com.api.pagamentos.repository.PagamentosRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
@Service
public class ListarPagamentosService {
    private final PagamentosRepository pagamentosRepository;

    public ListarPagamentosService(PagamentosRepository pagamentosRepository) {
        this.pagamentosRepository = pagamentosRepository;
    }
    public ListarPagamentosResponseDto listarPagamentosPorId(UUID id) {
        Optional<PagamentosModel> pagamentoOptional = pagamentosRepository.findById(id);
        if (pagamentoOptional.isPresent()) {
            PagamentosModel pagamento = pagamentoOptional.get();
            ListarPagamentosResponseDto dto = new ListarPagamentosResponseDto(
                    pagamento.getIdCliente(),
                    pagamento.getIdFuncionario(),
                    pagamento.getIdFornecedor(),
               nto(),
                    pagamento.getDescrica     pagamento.getStatusPagameo(),
                    pagamento.getValor(),
                    pagamento.getData()
            );
            return dto;
        }
        return null;
    }
}
