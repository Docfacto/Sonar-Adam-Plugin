package com.docfacto.sonar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.sonar.api.charts.AbstractChart;
import org.sonar.api.charts.ChartParameters;

/**
 * A bar chart to show the documentation coverage of various aspects of the
 * code.
 * <P>
 * The bar chart has each aspect of the code on the x-axis, and the percentage
 * that they are commented on y-axis. For each aspect of code, there is a bar
 * which is colored according to the percentage it is commented. 100% commented
 * bars are colored green. 25% and upwards commented bars are colored orange.
 * Less than 25% commented bars are colored red.
 * 
 * @author damonli - created May 14, 2013
 * @since 2.3.0
 */
public class BarChart extends AbstractChart {

    /**
     * The message shown when no data is available for the bar chart
     */
    private static final String NO_DATA_MESSAGE = "No data available";

    /**
     * The parameter id for the values of the data set from the given chart
     * parameters
     */
    public static final String VALUE_ID = "v";

    /**
     * The color of green bars
     */
    private static final Color GREEN_BAR_COLOR = Color.decode("0x33CC00");
    /**
     * The color of orange bars
     */
    private static final Color ORANGE_BAR_COLOR = Color.decode("0xFFC000");
    /**
     * The color of red bars
     */
    private static final Color RED_BAR_COLOR = Color.decode("0xFF0000");

    /**
     * The label for the y axis
     */
    private static final String yAxisLabel = "Percent commented (%)";

    /**
     * The minimum percentage for a bar to be colored green
     */
    private static final double MINIMUM_PERCENTAGE_COMMENTED_FOR_GREEN_COLOR = 100;
    /**
     * The minimum percentage for a bar to be colored orange
     */
    private static final double MINIMUM_PERCENTAGE_COMMENTED_FOR_ORANGE_COLOR = 25;

    /**
     * @see org.sonar.api.charts.Chart#getKey()
     */
    public String getKey() {
        return "barChart";
    }

    /**
     * @see org.sonar.api.charts.AbstractChart#getPlot(org.sonar.api.charts.ChartParameters)
     */
    @Override
    protected Plot getPlot(ChartParameters params) {
        CategoryPlot plot = generateCategoryPlot(params);
        plot.setOutlinePaint(OUTLINE_COLOR);
        plot.setDomainGridlinePaint(GRID_COLOR);
        plot.setRangeGridlinePaint(GRID_COLOR);
        return plot;
    }

    /**
     * Generates the plot for the 3D Bar Chart
     * <p>
     * This method creates and configures a Category Plot for a 3D Bar Chart
     * from a given dataset and colors for the series.
     * </p>
     * 
     * @param the chart parameters to get the data set and color series from
     * @return
     * @since 2.3.0
     */
    private CategoryPlot generateCategoryPlot(ChartParameters params) {

        DefaultCategoryDataset dataset = getDataset(params);
        Color[] colors = getSeriesColors(params);
        JFreeChart chart =
            ChartFactory.createBarChart3D("","",yAxisLabel,dataset,PlotOrientation.VERTICAL,true,true,false);

        CategoryPlot plot = (CategoryPlot)chart.getPlot();

        plot.setRenderer(new CustomRenderer(colors));
        plot.setOutlinePaint(GRID_COLOR);
        plot.setRangeGridlinePaint(GRID_COLOR);
        plot.getRangeAxis().setUpperBound(100.0);
        plot.getRangeAxis().setLowerBound(0.0);
        plot.setNoDataMessage(NO_DATA_MESSAGE);

        return plot;
    }

    private DefaultCategoryDataset getDataset(ChartParameters params) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] values = params.getValues(VALUE_ID,"|");
        addValuesToTheDataset(values,dataset,"");
        return dataset;
    }

    private Color[] getSeriesColors(ChartParameters params) {

        String[] keyAndValuePairs = params.getValues(VALUE_ID,";");

        Color[] colors = new Color[keyAndValuePairs.length];

        for (int i = 0;i<keyAndValuePairs.length;i++) {
            String[] keyAndValue = keyAndValuePairs[i].split("=");

            if (keyAndValue.length>0) {
                double value = Double.parseDouble(keyAndValue[1]);

                if (value>=MINIMUM_PERCENTAGE_COMMENTED_FOR_GREEN_COLOR)
                    colors[i] = GREEN_BAR_COLOR;
                else if (value>=MINIMUM_PERCENTAGE_COMMENTED_FOR_ORANGE_COLOR)
                    colors[i] = ORANGE_BAR_COLOR;
                else
                    colors[i] = RED_BAR_COLOR;
            }
        }

        return colors;
    }

    private void addValuesToTheDataset(String[] values,DefaultCategoryDataset dataset,String xAxisUnit) {
        for (int i = 0;i<values.length;i++) {
            String[] valuePairs = values[i].split(";");

            if (valuePairs.length==0)
                dataset.addValue((Number)0.0,i,"0");
            else {
                for (String pair:valuePairs) {
                    String[] keyValue = pair.split("=");
                    double value = Double.parseDouble(keyValue[1]);
                    dataset.addValue((Number)value,i,keyValue[0]+xAxisUnit);
                }
            }
        }
    }

    /**
     * A Custom Renderer for a 3D Bar Chart to color the bars according to a
     * given array of colors
     * <P>
     * The renderer will color each bar of a 3d Bar Chart plot in the order of
     * the given array of colors.
     * 
     * @author damonli - created May 27, 2013
     * @since 2.3.0
     */
    class CustomRenderer extends BarRenderer3D {
        private Paint[] colors;

        /**
         * A Constructor for the custom renderer
         * 
         * @param colors the array of colors to color the bars of the bar chart
         * in the order they are arranged in the array
         */
        public CustomRenderer(final Paint[] colors) {
            this.colors = colors;
        }

        /**
         * @see org.jfree.chart.renderer.AbstractRenderer#getItemPaint(int, int)
         */
        public Paint getItemPaint(final int row,final int column) {
            return this.colors[column%this.colors.length];
        }
    }
}
