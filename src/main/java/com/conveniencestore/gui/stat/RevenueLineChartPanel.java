package com.conveniencestore.gui.stat;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.conveniencestore.gui.utils.StatTheme;

public class RevenueLineChartPanel extends JPanel {

    private ChartPanel chartPanel;

    public RevenueLineChartPanel(Map<String, Long> revenueByDay) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        chartPanel = createChart(revenueByDay);
        chartPanel.setOpaque(false);
        chartPanel.setBorder(BorderFactory.createEmptyBorder());

        add(chartPanel, BorderLayout.CENTER);

        // Responsive: resize chart theo panel
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
                revalidate();
            }
        });
    }

    private ChartPanel createChart(Map<String, Long> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((day, value) ->
                dataset.addValue(value, "Doanh thu", day)
        );

        JFreeChart chart = ChartFactory.createLineChart(
                null,
                "Ngày",
                "Doanh thu (VNĐ)",
                dataset
        );

        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(220, 220, 220));
        plot.setDomainGridlinePaint(new Color(235, 235, 235));

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        domainAxis.setLabelFont(new Font("Segoe UI", Font.BOLD, 13));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        rangeAxis.setLabelFont(new Font("Segoe UI", Font.BOLD, 13));
        rangeAxis.setNumberFormatOverride(NumberFormat.getInstance());

        LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, true);
        renderer.setSeriesPaint(0, StatTheme.GREEN_PRIMARY);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-4, -4, 8, 8));
        renderer.setSeriesShapesFilled(0, true);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator("Ngày {1}: {2} VNĐ", NumberFormat.getInstance()));

        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPopupMenu(null);
        panel.setMouseZoomable(false);
        panel.setOpaque(false);

        return panel;
    }
}
