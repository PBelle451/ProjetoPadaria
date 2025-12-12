package com.example.primeiroprojeto.services;

import com.example.primeiroprojeto.model.Produto;
import com.example.primeiroprojeto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    //Autowired serve para inverter o controle para o usuário
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     *
     * @return Lista de produtos
     */
    public List<Produto> obterTodos() {
        // Colocar regra de retorno de produtos
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     *
     * @param id do produto que sera localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adicionar produto na lista.
     *
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */

    public Produto adicionar(Produto produto) {
        // Poderia colocar alguma regra de negócio aqui referente ao produto.
        return produtoRepository.adicionar(produto);
    }

    /**
     * Metodo para deletar o produto por Id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        produtoRepository.deletar(id);
    }

    /**
     * Metodo para atualizar produto na lista
     * @param id que será atualizado.
     * @param produto que será atualizado
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}