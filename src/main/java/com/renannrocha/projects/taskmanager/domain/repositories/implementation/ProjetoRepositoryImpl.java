package com.renannrocha.projects.taskmanager.domain.repositories.implementation;

import com.renannrocha.projects.taskmanager.domain.entities.Projeto;
import com.renannrocha.projects.taskmanager.domain.entities.Tarefa;
import com.renannrocha.projects.taskmanager.domain.entities.Usuario;
import com.renannrocha.projects.taskmanager.domain.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class ProjetoRepositoryImpl implements ProjetoRepository {

    private static String INSERT = "insert into PROJETO (id, nome, descricao, usuario_id, tarefa_id) values (?, ?, ?, ?, ?) ";
    private static String SELECT_ALL = "select * from PROJETO ";
    private static String DELETE = "delete from PROJETO where id = ? ";
    private static String UPDATE = "update PROJETO set nome = ?, descricao = ?, usuario_id = ?, tarefa_id = ? where id = ? ";
    private static String SELECT_BY_ID = "select * from PROJETO where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsuarioRepositoryImpl user;

    @Autowired
    private TarefaRepositoryImpl task;

    @Override
    public Projeto save(Projeto projeto) {
        jdbcTemplate.update(INSERT, new Object[]{
                projeto.getId(),
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getUsuario(),
                projeto.getTarefas()
        });
        return projeto;
    }

    @Override
    public Projeto update(Projeto projeto) {
        jdbcTemplate.update(UPDATE, new Object[]{
                projeto.getId(),
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getUsuario(),
                projeto.getTarefas()
        });
        return projeto;
    }

    @Override
    public void delete(Projeto projeto) {
        deleteById(projeto.getId());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    @Override
    public List<Object> list() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Object>() {

            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                Integer usuarioId = rs.getInt("usuario_id");
                Usuario usuario = user.findUsuarioById(usuarioId);
                Integer tarefaId = rs.getInt("tarefa_id");
                Tarefa tarefa = task.findTarefaById(tarefaId);
                return new Projeto(id, nome, descricao, usuario, (Set<Tarefa>) tarefa);
            }
        });
    }
}
