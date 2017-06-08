package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import src.Erro;
import src.modelo.Estacao;
import src.modelo.Mares;

public class DaoMare {

    private ConnectDataBase connectDataBase = null;
    private Erro log = null;
    private Statement statement;

    public DaoMare() {
        connectDataBase = new ConnectDataBase();
    }

    public ArrayList<Mares> buscaMare(String dia, int type, int estacao) {
        ArrayList<Mares> lista = new ArrayList<>();
        String table;
        if (type == 1) {
            table = "observada";
        } else {
            table = "prevista";
        }
        String selectTableSQL = "select * from " + table + " where "
                + "data = '" + dia + "' AND id_estacao ='" + estacao + "' ORDER BY hora ASC;";
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Mares mare = new Mares();
                if (type == 1) {
                    mare.setValor(rs.getInt("valor") - 119);
                } else {
                    mare.setValor(rs.getInt("valor"));
                }
                mare.setDia(rs.getDate("data"));
                mare.setHora(rs.getInt("hora"));
                mare.setDatacad(rs.getDate("data_cad"));
                mare.setHoracad(LocalTime.parse(rs.getString("hora_cad")));
                mare.setUsercad(rs.getString("user_cad"));
                mare.setIpcad(rs.getString("ip_cad"));
                lista.add(mare);
            }
            return lista;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "buscaMare");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public Mares buscaMareObservada(String dia, int estacao, int time) {
        Mares mare = null;
        String selectTableSQL = "select * from observada where "
                + "data = '" + dia + "' AND id_estacao ='" + estacao + "'  and hora = '" + time + "';";
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                mare = new Mares();
                    mare.setValor(rs.getInt("valor") - 119);
                mare.setDia(rs.getDate("data"));
                mare.setHora(rs.getInt("hora"));
                mare.setDatacad(rs.getDate("data_cad"));
                mare.setHoracad(LocalTime.parse(rs.getString("hora_cad")));
                mare.setUsercad(rs.getString("user_cad"));
                mare.setIpcad(rs.getString("ip_cad"));
            }
            return mare;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "buscaMare");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Mares> buscaMare(int i) {
        ArrayList<Mares> lista = new ArrayList<>();
        String table;
        if (i == 1) {
            table = "chm_mare_observada";
        } else {
            table = "chm_mare_prevista";
        }
        String selectTableSQL = "select * from " + table;
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Mares mare = new Mares();
                mare.setId_estacao(1);
                mare.setValor(rs.getInt("valor"));
                mare.setDia(rs.getDate("data"));
                mare.setHora(rs.getInt("hora"));
                mare.setDatacad(rs.getDate("data_cad"));
                mare.setHoracad(LocalTime.parse(rs.getString("hora_cad")));
                mare.setUsercad(rs.getString("user_cad"));
                mare.setIpcad(rs.getString("ip_cad"));
                lista.add(mare);
            }
            return lista;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "buscaMare");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void insereMare(Mares mare, int i) {
        String table;
        if (i == 1) {
            table = "observada";
        } else {
            table = "prevista";
        }
        String insere2TableSQL = "insert into " + table + "("
                + "id_estacao, valor, data, hora, user_cad, data_cad, hora_cad, ip_cad)"
                + "  values ("
                + "'" + mare.getId_estacao() + "', "
                + "'" + mare.getValor() + "', "
                + "'" + mare.getDia() + "', "
                + "'" + mare.getHora() + "', "
                + "'" + mare.getUsercad() + "', "
                + "'" + mare.getDatacad() + "', "
                + "'" + mare.getHoracad() + "', "
                + "'" + mare.getIpcad() + "');";
        try {
            if (connectDataBase.openConection() == null) {
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insere2TableSQL);
            //statement.close();
            //connectDataBase.closeConnection();
        } catch (SQLException e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "insereMare");
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Estacao> buscaEstacao() {
        ArrayList<Estacao> lista = new ArrayList<>();
        String selectTableSQL = "select * from estacao";
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Estacao estacao = new Estacao();
                estacao.setId_estacao(rs.getInt("id_estacao"));
                estacao.setEstacao(rs.getString("nome"));
                lista.add(estacao);
            }
            return lista;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "buscaEstacao");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void insereEstacao(String estacao, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insere2TableSQL = "insert into estacao("
                + "nome, user_cad, data_cad, hora_cad, ip_cad)"
                + "  values ("
                + "'" + estacao + "', 'TESTE',  '" + date + "', '" + thisSec + "', "
                + "'" + ip + "');";
        try {
            if (connectDataBase.openConection() == null) {
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insere2TableSQL);
            //statement.close();
            //connectDataBase.closeConnection();
        } catch (SQLException e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "insereEstacao");
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean insereMareObservada(int idEstacao, String valor, String data, int time, String user, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insere2TableSQL = "INSERT INTO observada(id_estacao, valor, data, hora, "
                + "user_cad, data_cad, hora_cad, ip_cad)"
                + "  values ("
                + "'" + idEstacao + "', '" + valor + "', '" + data + "', '" + time + "', "
                + "'" + user + "',  '" + date + "', '" + thisSec + "', '" + ip + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insere2TableSQL);
            //statement.close();
            //connectDataBase.closeConnection();
            return true;
        } catch (SQLException e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "insereEstacao");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public Mares buscaMarePreterita(String data, int idEstacao, int time) {
        Mares mare = null;
        String selectTableSQL = "select * from prevista where "
                + "data = '" + data + "' AND id_estacao ='" + idEstacao + "'  and hora = '" + time + "';";
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                mare = new Mares();
                    mare.setValor(rs.getInt("valor"));
                mare.setDia(rs.getDate("data"));
                mare.setHora(rs.getInt("hora"));
                mare.setDatacad(rs.getDate("data_cad"));
                mare.setHoracad(LocalTime.parse(rs.getString("hora_cad")));
                mare.setUsercad(rs.getString("user_cad"));
                mare.setIpcad(rs.getString("ip_cad"));
            }
            return mare;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "buscaMare");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean insereMarePreterita(int idEstacao, String valor, String data, int time, String user, String ip) {
       Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insere2TableSQL = "INSERT INTO prevista(id_estacao, valor, data, hora, "
                + "user_cad, data_cad, hora_cad, ip_cad)"
                + "  values ("
                + "'" + idEstacao + "', '" + valor + "', '" + data + "', '" + time + "', "
                + "'" + user + "',  '" + date + "', '" + thisSec + "', '" + ip + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insere2TableSQL);
            //statement.close();
            //connectDataBase.closeConnection();
            return true;
        } catch (SQLException e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoMare", "insereEstacao");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }
}
