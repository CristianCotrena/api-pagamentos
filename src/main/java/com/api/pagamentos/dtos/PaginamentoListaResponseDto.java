package com.api.pagamentos.dtos;

import java.util.List;
public class PaginamentoListaResponseDto {
    private List<ListarPagamentosResponseDto> resultados;
    private int paginaAtual;
    private int totalPaginas;

    public PaginamentoListaResponseDto(List<ListarPagamentosResponseDto> resultados, int paginaAtual, int totalPaginas) {
        this.resultados = resultados;
        this.paginaAtual = paginaAtual;
        this.totalPaginas = totalPaginas;
    }

    public List<ListarPagamentosResponseDto> getResultados() {
        return resultados;
    }

    public void setResultados(List<ListarPagamentosResponseDto> resultados) {
        this.resultados = resultados;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
}

