package dao;

import entity.Player;
import java.sql.*;
import java.util.*;

public class PlayerDAO {

    public void insert(Player p) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO player(name, full_name, age, index_id) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getFullName());
            ps.setString(3, p.getAge());
            ps.setInt(4, p.getIndexId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Player> getAll() {
        List<Player> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM player";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("player_id"));
                p.setName(rs.getString("name"));
                p.setFullName(rs.getString("full_name"));
                p.setAge(rs.getString("age"));
                p.setIndexId(rs.getInt("index_id"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM player WHERE player_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}