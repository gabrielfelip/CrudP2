package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;



public class Aluno {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private double peso;
    private double altura;
    private List<HistoricoPeso> historicoPeso;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Aluno (String cpf, String nome, String dataNascimento, double peso, double altura) throws ParseException {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dateFormat.parse(dataNascimento);
        this.peso = peso;
        this.altura = altura;
        this.historicoPeso = new ArrayList<>();
        adicionarHistoricoPeso(new HistoricoPeso(cpf, new Date(), peso));
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDataNascimento(){
        return dateFormat.format(dataNascimento);
    }

    public void setDataNascimento(String dataNascimento) throws ParseException {
        this.dataNascimento = dateFormat.parse(dataNascimento);
    }

    public double getPeso(){
        return peso;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }

    public double getAltura(){
        return altura;
    }

    public void setAltura(double altura){
        this.altura = altura;
    }

    public List<HistoricoPeso> getHistoricoPeso() {
        return historicoPeso;
    }

    public void adicionarHistoricoPeso(HistoricoPeso historico) {
        historicoPeso.add(historico);
    }

    public void removerHistoricoPeso(int indice) {
        historicoPeso.remove(indice);
    }

    public void atualizarHistoricoPeso(int indice, HistoricoPeso historico) {
        historicoPeso.set(indice, historico);
    }

    public HistoricoPeso consultarHistoricoPeso(int indice) {
        return historicoPeso.get(indice);
    }
}




