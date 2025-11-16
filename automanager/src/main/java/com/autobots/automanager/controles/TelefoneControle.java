package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.modelo.TelefoneSelecionador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
    @Autowired
    private TelefoneRepositorio repositorio;
    @Autowired
    private TelefoneSelecionador selecionador;

    @GetMapping("/{id}")
    public Telefone obterTelefone(@PathVariable long id) {
        List<Telefone> telefones = repositorio.findAll();
        return selecionador.selecionar(telefones, id);
    }

    @GetMapping("/telefones")
    public  List<Telefone> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        return telefones;
    }

    @PostMapping("/cadastrar")
    public void cadastrarTelefone(@RequestBody Telefone telefone){
        repositorio.save(telefone);
    }

    @PutMapping("/atualizar")
    public  void atualizarTelefone(@RequestBody Telefone telefoneAtualizar) {
        Telefone telefone = repositorio.getById(telefoneAtualizar.getId());
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, telefoneAtualizar);
        repositorio.save(telefone);
    }
}
