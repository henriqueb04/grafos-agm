package ufpi.grafos.grupo9;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Map;

public class CriadorDeGraficos {
    public static void criarGraficos() {
        MedidorDeTempo medidor = new MedidorDeTempo();
        IO.println("Medindo tempos...");
        var primCompleto = medidor.medirTempoPrimCompletos();
        var kruskalCompleto = medidor.medirTempoKruskalCompletos();
        var primNaoCompleto = medidor.medirTempoPrimNaoCompletos();
        var kruskalNaoCompleto = medidor.medirTempoKruskalNaoCompletos();
        IO.println("===========================");
        IO.println("========== Prim ===========");
        IO.println("===========================");
        IO.println("-------- Completos --------");
        primCompleto.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry ->
                IO.println(String.format("%d: %.4fs", entry.getKey(), entry.getValue()))
        );
        IO.println("------ Não Completos -------");
        primNaoCompleto.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry ->
                IO.println(String.format("%d: %.4fs", entry.getKey(), entry.getValue()))
        );
        IO.println("===========================");
        IO.println("========= Kruskal =========");
        IO.println("===========================");
        IO.println("-------- Completos --------");
        kruskalCompleto.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry ->
                IO.println(String.format("%d: %.4fs", entry.getKey(), entry.getValue()))
        );
        IO.println("------ Não Completos -------");
        kruskalNaoCompleto.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry ->
                IO.println(String.format("%d: %.4fs", entry.getKey(), entry.getValue()))
        );
        mostrarGrafico("Grafos Completos", primCompleto, kruskalCompleto);
        mostrarGrafico("Grafos Não Completos (densidade = 5%)", primNaoCompleto, kruskalNaoCompleto);
    }

    static XYSeries toSeries(Map<Integer, Double> map, String nome) {
        XYSeries series = new XYSeries(nome);
        for (var entry : map.entrySet()) {
            series.add(entry.getKey(), entry.getValue());
        }
        return series;
    }

    static void mostrarGrafico(String titulo, Map<Integer, Double> prim, Map<Integer, Double> kruskal) {
        XYSeries dsPrim = toSeries(prim, "Prim");
        XYSeries dsKruskal = toSeries(kruskal, "Kruskal");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(dsPrim);
        dataset.addSeries(dsKruskal);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                titulo,
                "Quantidade de Vértices",
                "Tempo (s)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChartPanel panel = new ChartPanel(lineChart);

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
