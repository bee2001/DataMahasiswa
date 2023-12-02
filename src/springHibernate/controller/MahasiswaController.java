/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springHibernate.controller;
import springHibernate.controller.MahasiswaController;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import springHibernate.configuration.MahasiswaTableModel;
import springHibernate.dao.MahasiswaDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import springHibernate.model.Mahasiswa;
import springHibernate.view.MahasiswaView;
import springHibernate.App;
/**
 *
 * @author Asus
 */
public class MahasiswaController {
    private final MahasiswaView mahasiswaView;
    private MahasiswaTableModel mahasiswaTableModel;
    private List<Mahasiswa> mahasiswas;

    public MahasiswaController(MahasiswaView mahasiswaView) {
        this.mahasiswaView = mahasiswaView;
    }

    public void tampilData() {
        mahasiswas = App.getMahasiswaService().getMahasiswas();
        mahasiswaTableModel =  new MahasiswaTableModel(mahasiswas);
        this.mahasiswaView.getTabel().setModel(mahasiswaTableModel);
    }

    public void show() {
        int index = this.mahasiswaView.getTabel().getSelectedRow();

        this.mahasiswaView.getNPM().setText(String.valueOf(
        this.mahasiswaView.getTabel().getValueAt(index, 0)));
        this.mahasiswaView.getNama().setText(String.valueOf(
        this.mahasiswaView.getTabel().getValueAt(index, 1)));
        this.mahasiswaView.getKelas().setText(String.valueOf(
        this.mahasiswaView.getTabel().getValueAt(index, 2)));
        this.mahasiswaView.getAlamat().setText(String.valueOf(
        this.mahasiswaView.getTabel().getValueAt(index, 3)));
    }

    public void clear() {
        this.mahasiswaView.getNPM().setText("");
        this.mahasiswaView.getNama().setText("");
        this.mahasiswaView.getKelas().setText("");
        this.mahasiswaView.getAlamat().setText("");
    }

    public void saveMahasiswa() {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNpm(this.mahasiswaView.getNPM().getText());
        mahasiswa.setNama(this.mahasiswaView.getNama().getText());
        mahasiswa.setKelas(this.mahasiswaView.getKelas().getText());
        mahasiswa.setAlamat(this.mahasiswaView.getAlamat().getText());
        App.getMahasiswaService().save(mahasiswa);
        JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan", "info",JOptionPane.INFORMATION_MESSAGE);
        clear();
        tampilData();
    }

    public void updateMahasiswa() {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNpm(this.mahasiswaView.getNPM().getText());
        mahasiswa.setNama(this.mahasiswaView.getNama().getText());
        mahasiswa.setKelas(this.mahasiswaView.getKelas().getText());
        mahasiswa.setAlamat(this.mahasiswaView.getAlamat().getText());
        App.getMahasiswaService().save(mahasiswa);
        JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan", "info",JOptionPane.INFORMATION_MESSAGE);
        clear();
        tampilData();
    }

    public void deleteBarang() {
        if (this.mahasiswaView.getNPM().getText() == null) {
            JOptionPane.showMessageDialog(null, "Barang belum dipilih", "error",JOptionPane.ERROR_MESSAGE);
        } else {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNpm(this.mahasiswaView.getNPM().getText());
            int pilih = JOptionPane.showConfirmDialog(null,"Apakah data ingin dihapus ?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (pilih == JOptionPane.YES_OPTION) {
                App.getMahasiswaService().delete(mahasiswa);
                JOptionPane.showMessageDialog(null,"Data Berhasil di Hapus", "info",JOptionPane.INFORMATION_MESSAGE);
                clear();
                tampilData();
            }
        }
    }

}

