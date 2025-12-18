package com.example.primeiroprojeto.services;

import com.example.primeiroprojeto.model.Produto;
import com.example.primeiroprojeto.model.exception.ResourceNotFoundException;
import com.example.primeiroprojeto.repository.ProdutoRepository;
import com.example.primeiroprojeto.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProdutoDTO> obterTodos() {
        // Retorna uma lista de produto DTO
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ModelMapper()
                .map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id.
     *
     * @param id do produto que sera localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        // Obtendo optional de produto pelo id.
        Optional<Produto> produto = produtoRepository.findById(id);
        // Se não encontrar lança exception
        if(produto.isPresent()) {
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        // Removendo o Id para fazer o cadastro.
        produtoDto.setId(null);
        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        // Converter o nosso ProdutoDTO em um Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Salvar o Produto no Banco
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());
        // Retornar o ProdutoDTO atualizado.
        return produtoDto;
    }

    /**
     * Metodo para deletar o produto por Id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id) {
        //Verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id + " Produto não existe");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar produto na lista
     * @param id que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        // Passar o id para o produtoDto
        produtoDto.setId(id);
        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        // Converter o ProdutoDTO em um produto.
        Produto produto = mapper.map(produtoDto, Produto.class);
        //Atualizar  o produto no banco de dados.
        produto = produtoRepository.save(produto);
        // Retornar o produtoDTo atualizado.
        produtoDto.setId(produto.getId());
        return produtoDto;
    }
}