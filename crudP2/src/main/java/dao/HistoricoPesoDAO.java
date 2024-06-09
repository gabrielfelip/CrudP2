package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.HistoricoPeso;
import util.ConnectionFactory;

public class HistoricoPesoDAO {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Método para inserir um novo registro de histórico de peso
    public void inserir(HistoricoPeso historico) throws SQLException {
        String sql = "INSERT INTO historicoPeso (cpf, data_registro, peso) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historico.getCpf());
            stmt.setString(2, dateFormat.format(historico.getDataRegistro()));
            stmt.setDouble(3, historico.getPeso());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um registro de histórico de peso
    public void excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM historicoPeso WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um registro de histórico de peso
    public void atualizar(HistoricoPeso historico) throws SQLException {
        String sql = "UPDATE historicoPeso SET data_registro = ?, peso = ? WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(historico.getDataRegistro().getTime()));
            stmt.setDouble(2, historico.getPeso());
            stmt.setString(3, historico.getCpf());
            stmt.executeUpdate();
        }
    }

    // Método para consultar todos os registros de histórico de peso de um
    // determinado aluno
    public List<HistoricoPeso> consultarPorCPF(String cpf) throws SQLException, ParseException {
        List<HistoricoPeso> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historicoPeso WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HistoricoPeso historico = new HistoricoPeso(
                            cpf,
                            rs.getDate("data_registro"),
                            rs.getDouble("peso"));
                    historicos.add(historico);
                }
            }
        }
        return historicos;
    }
}
