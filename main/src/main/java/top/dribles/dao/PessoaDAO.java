/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package top.dribles.dao;

import java.util.List;
import top.dribles.model.Pessoa;

/**
 *
 * @author crist
 */
public interface PessoaDAO {
    List<Pessoa> getAll();
    int insert(Pessoa pessoa);
}
