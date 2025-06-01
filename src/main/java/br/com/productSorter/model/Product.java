package main.java.br.com.productSorter.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Product {
    private String nome;
    private String fabricante;
    private double preco;
    private String dataValidadeStr;
    private long dataValidadeComparable; // Formato YYYYMMDD para comparação

    private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
    private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
    private static final DecimalFormat PRICE_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        PRICE_FORMAT = new DecimalFormat("#0.00", symbols);
    }

    public Product(String nome, String fabricante, double preco, String dataValidadeStr) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.preco = preco;
        this.dataValidadeStr = dataValidadeStr;
        this.dataValidadeComparable = parseDataValidade(dataValidadeStr);
    }

    private long parseDataValidade(String dataStr) {
        try {
            Date date = INPUT_DATE_FORMAT.parse(dataStr);
            SimpleDateFormat comparableFormat = new SimpleDateFormat("yyyyMMdd");
            return Long.parseLong(comparableFormat.format(date));
        } catch (ParseException e) {
            System.err.println("Erro ao parsear data: " + dataStr + ". Usando valor padrão 0.");
            e.printStackTrace();
            return 0L;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public double getPreco() {
        return preco;
    }

    public String getDataValidadeStr() {
        return dataValidadeStr;
    }

    public long getDataValidadeComparable() {
        return dataValidadeComparable;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s",
                nome,
                fabricante,
                PRICE_FORMAT.format(preco),
                dataValidadeStr);
    }

    public static Product fromString(String linha) {
        // Formato: NOME PRODUTO # FABRICANTE # PREÇO # DATA DE VALIDADE
        String[] partes = linha.split(" # ");
        if (partes.length == 4) {
            String nome = partes[0].trim();
            String fabricante = partes[1].trim();
            double preco = 0.0;
            try {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator('.');
                String pattern = "#0.00";
                DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
                decimalFormat.setParseBigDecimal(true);
                preco = decimalFormat.parse(partes[2].trim()).doubleValue();
            } catch (ParseException e) {
                System.err.println("Erro ao parsear preço: " + partes[2].trim() + " para o produto " + nome);
                e.printStackTrace();
            }
            String dataValidade = partes[3].trim();
            return new Product(nome, fabricante, preco, dataValidade);
        }
        System.err.println("Linha mal formatada: " + linha);
        return null;
    }
}