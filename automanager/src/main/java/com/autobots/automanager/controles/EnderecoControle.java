package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoSelecionador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
    @Autowired
    private EnderecoRepositorio repositorio;
    @Autowired
    private EnderecoSelecionador selecionador;

    @GetMapping("/{id}")
    public Endereco obterEndereco(@PathVariable long id) {
        List<Endereco> enderecos = repositorio.findAll();
        return  selecionador.selecionar(enderecos, id);
    }

    @GetMapping("/enderecos")
    public  List<Endereco> obterEnderecos() {
        List<Endereco> enderecos = repositorio.findAll();
        return enderecos;
    }

    @PostMapping("/cadastro")
    public void cadastrarEndereco(@RequestBody Endereco endereco) {
        repositorio.save(endereco);
    }

    @DeleteMapping("/excluir")
    public void excluirEndereco(@RequestBody Endereco enderecoExcluir) {
        Endereco endereco = repositorio.getById(enderecoExcluir.getId());
        repositorio.delete(endereco);
    }
}
