package com.renannrocha.projects.taskmanager.domain.repositories.implementation;

import com.renannrocha.projects.taskmanager.domain.entities.Usuario;
import com.renannrocha.projects.taskmanager.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static String INSERT = "insert into USUARIO (nome, email, senha) values (?, ?, ?) ";
    private static String SELECT_ALL = "select * from USUARIO ";
    private static String DELETE = "delete from USUARIO where id = ? ";
    private static String UPDATE = "update USUARIO set nome = ? , email = ?, senha = ? where id = ? ";
    private static String SELECT_BY_ID = "select * from USUARIO where id = ? ";
    private static String EXISTS = "select count(*) from USUARIO where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Usuario save(Usuario usuario) {
        jdbcTemplate.update(INSERT, new Object[]{
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        });
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        jdbcTemplate.update(UPDATE, new Object[]{
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getId()
        });
        return usuario;
    }

    @Override
    public void delete(Usuario usuario) {
        deleteById(usuario.getId());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    @Override
    public List<Usuario> list() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                return new Usuario(id, nome, email, senha);
            }
        });
    }

    public Usuario findUsuarioById(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new RowMapper<Usuario>() {
            @Override
            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        });
    }

    // Se o número de registros for maior que zero, o método retorna true, indicando que um usuário com esse id existe. Caso contrário, retorna false.
    public boolean existsById(Integer id) {
        Integer count = jdbcTemplate.queryForObject(EXISTS, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
