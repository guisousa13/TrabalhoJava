/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Jogadores;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alunocmc
 */
public class JogadoresDao {

    private Conexao conexao;
    private Connection conn;

    public JogadoresDao() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();

    }

    public void inserir(Jogadores jogador) {

        String sql = "INSERT INTO jogadores(nome, posicao) VALUES (?,?) ";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPosicao());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar jogador: " + e.getMessage());

        }
    }
    
    
    public void editar(Jogadores jogador) {

        String sql = "UPDATE jogadores SET nome=?, posicao=? WHERE id=? ";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPosicao());
            stmt.setInt(3, jogador.getId());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir jogador: " + e.getMessage());

        }
    }
    
    public void excluir(int id) {

        String sql = "DELETE FROM jogadores WHERE id=?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao excluir registro de jogador: " + e.getMessage());

        }
    }

    public List<Jogadores> getProdutos() {

        String sql = "SELECT * FROM jogadores";
        List<Jogadores> listaJogadores = new ArrayList<>();

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Jogadores jogador = new Jogadores();
                jogador.setId(rs.getInt("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setPosicao(rs.getString("posicao"));
                listaJogadores.add(jogador);

            }

            return listaJogadores;

        } catch (Exception e) {

            return listaJogadores;

        }
    }

    public Jogadores getJogador(int id) {
        String sql = "SELECT * FROM jogadores WHERE id = ?";
        Jogadores jogador = new Jogadores();

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.first();

            jogador.setId(id);
            jogador.setNome(rs.getString("nome"));
            jogador.setPosicao(rs.getString("posicao"));
            


            return jogador;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;

        }
    }
}
