/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;
/**
 *
 * @author User
 */
    public class crud {
    private Connection Koneksidb;
    private String username="root";
    private String password="";
    private String dbname="db_sosial"; 
    private String urlKoneksi="jdbc:mysql://localhost/"+dbname;
    public boolean duplikasi=false;

    public String CEK_NAMA_LSM, CEK_ALAMAT_LSM, CEK_USER_IDL, CEK_PASSWORD_LSM = null;
    public String CEK_DETAIL_GD, CEK_UPDATE_INFO_GD, CEK_FUNDRAISER_GD, CEK_NIP_GD, CEK_USER_ID_GD, CEK_NO_IZIN_GD = null;
    public String CEK_DONASI_UANG, CEK_DONASI_BARANG, CEK_DONASI_SEMBAKO, CEK_NIP_DNS, CEK_USER_ID_DNS, CEK_NO_IZIN_DNS = null;
    public String CEK_FOTO_GLR, CEK_KETERANGAN_GLR, CEK_NO_IZIN_GLR = null;

    
    public crud(){
        try {
            Driver dbdriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbdriver);
            Koneksidb=DriverManager.getConnection(urlKoneksi,username,password);
            System.out.print("Database Terkoneksi");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public void simpanLSM02(String No_Izin, String Nama_LSM, String Alamat, String User_IdL, String Password){
        try {
            String sqlsimpan="INSERT INTO LSM (No_Izin, Nama_LSM, Alamat, User_IdL, Password) VALUES (?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM LSM WHERE No_Izin = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, No_Izin);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "No Izin sudah terdaftar");
                this.duplikasi = true;
                this.CEK_NAMA_LSM = data.getString("Nama_LSM");
                this.CEK_ALAMAT_LSM = data.getString("Alamat");
                this.CEK_USER_IDL = data.getString("User_IdL");
                this.CEK_PASSWORD_LSM = data.getString("Password");
            } else {
                this.duplikasi = false;
                this.CEK_NAMA_LSM = null;
                this.CEK_ALAMAT_LSM = null;
                this.CEK_USER_IDL = null;
                this.CEK_PASSWORD_LSM = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, No_Izin);
                perintah.setString(2, Nama_LSM);
                perintah.setString(3, Alamat);
                perintah.setString(4, User_IdL);
                perintah.setString(5, Password);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data LSM berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahLSM(String No_Izin, String Nama_LSM, String Alamat, String User_IdL, String Password){
        try {
            String sqlubah="UPDATE LSM SET Nama_LSM = ?, Alamat = ?, User_IdL = ?, Password = ? WHERE No_Izin = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, Nama_LSM);
            perintah.setString(2, Alamat);
            perintah.setString(3, User_IdL);
            perintah.setString(4, Password);
            perintah.setString(5, No_Izin); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data LSM berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusLSM(String No_Izin){
        try {
            String sqlhapus="DELETE FROM LSM WHERE No_Izin = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, No_Izin);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data LSM berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataLSM(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("No Izin");
            modeltabel.addColumn("Nama LSM");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("User ID");
            modeltabel.addColumn("Password");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanGalangDana02(String ID_GalangDana, String Detail, String Update_Info, String Fundraiser, String NIP, String User_id, String No_Izin){
        try {
            String sqlsimpan="INSERT INTO GalangDana (ID_GalangDana, Detail, Update_Info, Fundraiser, NIP, User_id, No_Izin) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM GalangDana WHERE ID_GalangDana = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, ID_GalangDana);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Galang Dana sudah terdaftar");
                this.duplikasi = true;
                this.CEK_DETAIL_GD = data.getString("Detail");
                this.CEK_UPDATE_INFO_GD = data.getString("Update_Info");
                this.CEK_FUNDRAISER_GD = data.getString("Fundraiser");
                this.CEK_NIP_GD = data.getString("NIP");
                this.CEK_USER_ID_GD = data.getString("User_id");
                this.CEK_NO_IZIN_GD = data.getString("No_Izin");
            } else {
                this.duplikasi = false;
                this.CEK_DETAIL_GD = null;
                this.CEK_UPDATE_INFO_GD = null;
                this.CEK_FUNDRAISER_GD = null;
                this.CEK_NIP_GD = null;
                this.CEK_USER_ID_GD = null;
                this.CEK_NO_IZIN_GD = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, ID_GalangDana);
                perintah.setString(2, Detail);
                perintah.setString(3, Update_Info);
                perintah.setString(4, Fundraiser);
                perintah.setString(5, NIP);
                perintah.setString(6, User_id);
                perintah.setString(7, No_Izin);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Galang Dana berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahGalangDana(String ID_GalangDana, String Detail, String Update_Info, String Fundraiser, String NIP, String User_id, String No_Izin){
        try {
            String sqlubah="UPDATE GalangDana SET Detail = ?, Update_Info = ?, Fundraiser = ?, NIP = ?, User_id = ?, No_Izin = ? WHERE ID_GalangDana = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, Detail);
            perintah.setString(2, Update_Info);
            perintah.setString(3, Fundraiser);
            perintah.setString(4, NIP);
            perintah.setString(5, User_id);
            perintah.setString(6, No_Izin);
            perintah.setString(7, ID_GalangDana); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Galang Dana berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusGalangDana(String ID_GalangDana){
        try {
            String sqlhapus="DELETE FROM GalangDana WHERE ID_GalangDana = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, ID_GalangDana);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Galang Dana berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataGalangDana(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID GalangDana");
            modeltabel.addColumn("Detail");
            modeltabel.addColumn("Update Info");
            modeltabel.addColumn("Fundraiser");
            modeltabel.addColumn("NIP");
            modeltabel.addColumn("User ID");
            modeltabel.addColumn("No Izin");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanDonasi02(String ID_Donasi, String Donasi_uang, String Donasi_Barang, String Donasi_Sembako, String NIP, String User_id, String No_Izin){
        try {
            String sqlsimpan="INSERT INTO Donasi (ID_Donasi, Donasi_uang, Donasi_Barang, Donasi_Sembako, NIP, User_id, No_Izin) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM Donasi WHERE ID_Donasi = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, ID_Donasi);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Donasi sudah terdaftar");
                this.duplikasi = true;
                this.CEK_DONASI_UANG = data.getString("Donasi_uang");
                this.CEK_DONASI_BARANG = data.getString("Donasi_Barang");
                this.CEK_DONASI_SEMBAKO = data.getString("Donasi_Sembako");
                this.CEK_NIP_DNS = data.getString("NIP");
                this.CEK_USER_ID_DNS = data.getString("User_id");
                this.CEK_NO_IZIN_DNS = data.getString("No_Izin");
            } else {
                this.duplikasi = false;
                this.CEK_DONASI_UANG = null;
                this.CEK_DONASI_BARANG = null;
                this.CEK_DONASI_SEMBAKO = null;
                this.CEK_NIP_DNS = null;
                this.CEK_USER_ID_DNS = null;
                this.CEK_NO_IZIN_DNS = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, ID_Donasi);
                perintah.setString(2, Donasi_uang);
                perintah.setString(3, Donasi_Barang);
                perintah.setString(4, Donasi_Sembako);
                perintah.setString(5, NIP);
                perintah.setString(6, User_id);
                perintah.setString(7, No_Izin);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Donasi berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahDonasi(String ID_Donasi, String Donasi_uang, String Donasi_Barang, String Donasi_Sembako, String NIP, String User_id, String No_Izin){
        try {
            String sqlubah="UPDATE Donasi SET Donasi_uang = ?, Donasi_Barang = ?, Donasi_Sembako = ?, NIP = ?, User_id = ?, No_Izin = ? WHERE ID_Donasi = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, Donasi_uang);
            perintah.setString(2, Donasi_Barang);
            perintah.setString(3, Donasi_Sembako);
            perintah.setString(4, NIP);
            perintah.setString(5, User_id);
            perintah.setString(6, No_Izin);
            perintah.setString(7, ID_Donasi); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Donasi berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusDonasi(String ID_Donasi){
        try {
            String sqlhapus="DELETE FROM Donasi WHERE ID_Donasi = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, ID_Donasi);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Donasi berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataDonasi(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Donasi");
            modeltabel.addColumn("Donasi Uang");
            modeltabel.addColumn("Donasi Barang");
            modeltabel.addColumn("Donasi Sembako");
            modeltabel.addColumn("NIP");
            modeltabel.addColumn("User ID");
            modeltabel.addColumn("No Izin");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanGaleri02(String Id_galeri, String Foto, String Keterangan, String No_Izin){
        try {
            String sqlsimpan="INSERT INTO Galeri (Id_galeri, Foto, Keterangan, No_Izin) VALUES (?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM Galeri WHERE Id_galeri = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, Id_galeri);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Galeri sudah terdaftar");
                this.duplikasi = true;
                this.CEK_FOTO_GLR = data.getString("Foto");
                this.CEK_KETERANGAN_GLR = data.getString("Keterangan");
                this.CEK_NO_IZIN_GLR = data.getString("No_Izin");
            } else {
                this.duplikasi = false;
                this.CEK_FOTO_GLR = null;
                this.CEK_KETERANGAN_GLR = null;
                this.CEK_NO_IZIN_GLR = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, Id_galeri);
                perintah.setString(2, Foto);
                perintah.setString(3, Keterangan);
                perintah.setString(4, No_Izin);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Galeri berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahGaleri(String Id_galeri, String Foto, String Keterangan, String No_Izin){
        try {
            String sqlubah="UPDATE Galeri SET Foto = ?, Keterangan = ?, No_Izin = ? WHERE Id_galeri = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, Foto);
            perintah.setString(2, Keterangan);
            perintah.setString(3, No_Izin);
            perintah.setString(4, Id_galeri); // ID sebagai parameter terakhir
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Galeri berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusGaleri(String Id_galeri){
        try {
            String sqlhapus="DELETE FROM Galeri WHERE Id_galeri = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, Id_galeri);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Galeri berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataGaleri(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Galeri");
            modeltabel.addColumn("Foto");
            modeltabel.addColumn("Keterangan");
            modeltabel.addColumn("No Izin");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }  
}
    
