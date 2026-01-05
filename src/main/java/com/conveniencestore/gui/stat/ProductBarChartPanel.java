package com.conveniencestore.gui.stat;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.NumberFormat;
import java.util.Map;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;

import com.conveniencestore.gui.utils.StatTheme;

public class ProductBarChartPanel extends JPanel {

    public ProductBarChartPanel(Map<String, Integer> products) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        ChartPanel chartPanel = createChart(products);
        chartPanel.setOpaque(false);
        chartPanel.setBorder(BorderFactory.createEmptyBorder());

        add(chartPanel, BorderLayout.CENTER);
    }

    private ChartPanel createChart(Map<String, Integer> products) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        products.forEach((name, qty) ->
                dataset.addValue(qty, "Số lượng", name)
        );

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                null,
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        chart.setBackgroundPaint(Color.WHITE);
        chart.setPadding(new RectangleInsets(10, 10, 10, 10));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(229, 231, 235));
        plot.setDomainGridlinesVisible(false);

        // ===== AXIS =====
        plot.getRangeAxis().setTickLabelFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );
        plot.getDomainAxis().setTickLabelFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );
        plot.getDomainAxis().setCategoryLabelPositions(
                org.jfree.chart.axis.CategoryLabelPositions.UP_45
        );

        // ===== RENDERER (ĐƠN GIẢN) =====
        BarRenderer renderer = new BarRenderer();
        renderer.setSeriesPaint(0, createGreenGradient());
        renderer.setMaximumBarWidth(0.08);
        renderer.setItemMargin(0.15);

        // Hiện số trên đầu cột
        renderer.setDefaultItemLabelGenerator(
                new StandardCategoryItemLabelGenerator(
                        "{2}", NumberFormat.getIntegerInstance()
                )
        );
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );
        renderer.setDefaultItemLabelPaint(new Color(22, 163, 74));

        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder());
        return panel;
    }


    // ===== GRADIENT XANH =====
    private Paint createGreenGradient() {
        return new GradientPaint(
                0f, 0f, StatTheme.GREEN_PRIMARY.brighter(),
                0f, 300f, StatTheme.GREEN_PRIMARY.darker()
        );
    }


}
