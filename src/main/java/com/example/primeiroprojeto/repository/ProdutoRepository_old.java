package com.example.primeiroprojeto.repository;

import com.example.primeiroprojeto.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository_old {

    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     * @param id do produto que sera localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */

    public Produto adicionar(Produto produto){
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Metodo para deletar o produto por Id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar produto na lista
     * @param produto que será atualizado
     * @return
     */
    public Produto atualizar(Produto produto){
        // Temos que primeiro remover o produto antigo da lista e substitui pelo novo
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado. isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }
        // Eu tenho que remover o produto antigo da lista
        deletar(produto.getId());
        // Depois adicionar o novo
        produtos.add(produto);
        return produto;
    }
}