package com.autobots.automanager.controles;


import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.modelo.DocumentoSelecionador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentoControle {
    @Autowired
    private DocumentoRepositorio repositorio;
    @Autowired
    private DocumentoSelecionador selecionador;

    @GetMapping("/document/{id}")
    public Documento obterCliente(@PathVariable long id) {
        List<Documento> documentos = repositorio.findAll();
        return  selecionador.selecionar(documentos, id);
    }

    @GetMapping("/documents")
    public List<Documento> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        return documentos;
    }

    @PostMapping("/cadastro")
    public void cadastroDocumento(@RequestBody Documento documento) {
        repositorio.save(documento);
    }

    @PutMapping("/atualizar")
    public void atualizarDocumento(@RequestBody Documento documentoAtualizado) {
        Documento documento = repositorio.getById(documentoAtualizado.getId());
        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, documentoAtualizado);
        repositorio.save(documento);
    }

    @DeleteMapping("/delete")
    public void deletarDocumento(@RequestBody Documento documentoExlcluir) {
        Documento documento = repositorio.getById(documentoExlcluir.getId());
        repositorio.delete(documento);
    }
}
