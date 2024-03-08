package com.renannrocha.projects.taskmanager.domain.repositories.implementation;

import com.renannrocha.projects.taskmanager.domain.entities.Tarefa;
import com.renannrocha.projects.taskmanager.domain.entities.Usuario;
import com.renannrocha.projects.taskmanager.domain.entities.enums.TaskStatus;
import com.renannrocha.projects.taskmanager.domain.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class TarefaRepositoryImpl implements TarefaRepository {

    private static String INSERT = "insert into TAREFA (titulo, descricao, status, data_criacao, data_conclusao, usuario_id) values (?, ?, ?, ?, ?, ?) ";
    private static String SELECT_ALL = "select * from TAREFA ";
    private static String DELETE = "delete from TAREFA where id = ? ";
    private static String UPDATE = "update TAREFA set titulo = ?, descricao = ?, status = ?, data_criacao = ?, data_conclusao = ?, usuario_id = ? where id = ? ";
    private static String GET_BY_ID = "select * from TAREFA were id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsuarioRepositoryImpl user;

    @Override
    public Tarefa save(Tarefa tarefa) {
       jdbcTemplate.update(INSERT, new Object[]{
               tarefa.getTitulo(),
               tarefa.getDescricao(),
               tarefa.getStatus(),
               tarefa.getDataCriacao(),
               tarefa.getDataConclusao(),
               tarefa.getUsuario()
       });
       return tarefa;
    }

    @Override
    public Tarefa update(Tarefa tarefa) {
        jdbcTemplate.update(UPDATE, new Object[]{
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getDataCriacao(),
                tarefa.getDataConclusao(),
                tarefa.getUsuario()
        });
        return tarefa;
    }

    @Override
    public void delete(Tarefa tarefa) {
        deleteById(tarefa.getId());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    @Override
    public List<Tarefa> list() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Tarefa>() {

            @Override
            public Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                TaskStatus status = TaskStatus.valueOf(rs.getString("status"));
                Date dateCriacao = rs.getDate("data_criacao");
                Date dataConclucao = rs.getDate("data_conclusao");
                Integer usuarioId = rs.getInt("usuario_id");
                Usuario usuario = user.findUsuarioById(usuarioId);
                return new Tarefa(id, titulo, descricao, status, dateCriacao, dataConclucao, usuario);
            }
        });
    }

    @Override
    public Tarefa findTarefaById(Integer id){
        return jdbcTemplate.queryForObject(GET_BY_ID, new Object[]{id}, new RowMapper<Tarefa>() {
            @Override
            public Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(TaskStatus.valueOf(rs.getString("status")));
                tarefa.setDataCriacao(rs.getDate("data_criacao"));
                tarefa.setDataConclusao(rs.getDate("data_conclusao"));
                Integer usuarioId = rs.getInt("usuario_id");
                Usuario usuario = user.findUsuarioById(usuarioId);
                tarefa.setUsuario(usuario);
                return tarefa;
            }
        });
    }

}
