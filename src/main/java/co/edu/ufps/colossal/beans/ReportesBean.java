/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.negocio.Colossal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author fakejhon666
 */
@Named(value = "reportesBean")
@Dependent
public class ReportesBean implements Serializable{

    @EJB
    private Colossal colossal;
    private HorizontalBarChartModel horizontalBarModel;
    private PieChartModel pieModel;
    private int render;
    
    @PostConstruct
    public void init() {
        this.crearGrafico(this.render);
    }
    
    /**
     * Constructor del controlador ReportesBean
     * obtiene el parametro render de la url y define el tipo de reporte a mostrar
     */
    public ReportesBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String cadenaRender = request.getParameter("render");
        try {
            int render = Integer.parseInt(cadenaRender);
            this.render = render;
        } catch (NumberFormatException e) {
            this.render = 5;
        }
        switch(this.render){
            case 4:this.render=1;break;
            case 5:this.render=4;break;
            case 6:this.render=2;break;
            case 7:this.render=3;break;
            default:this.render=1;
        }
    }

    /**
     * Metodo que devuelve una grafica de barras
     * @return 
     */
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    /**
     * Metodo que modifica una grafica de barras
     * @param horizontalBarModel 
     */
    public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
        this.horizontalBarModel = horizontalBarModel;
    }

    /**
     * Metodo que devuelve una grafica de torta
     * @return pieChartModel
     */
    public PieChartModel getPieModel() {
        return pieModel;
    }

    /**
     * Metodo que modifica una grafica de torta
     * @param pieModel 
     */
    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    /**
     * Metodo que crea un reporte dependiendo del tipo de reporte solicitado
     * @param tipoReporte 
     */
    public void crearGrafico(int tipoReporte){
        switch (tipoReporte){
            case 1: this.createHorizontalBarModel(tipoReporte);break;
            case 2: this.createHorizontalBarModel(tipoReporte);break;
            case 3: this.createHorizontalBarModel(tipoReporte);break;
            case 4: this.createPieModel();break;
            default: break;
        }
    }
    
    /**
     * Metodo que crea un grafico de barras horizontal
     * recibe un identificador tipoReporte que identifica 
     * cual es reporte es el que se va a generar
     * @param tipoReporte 
     */
    private void createHorizontalBarModel(int tipoReporte) {
        String yAxisLabel = "";
        String xAxisLabel = "";
        String reportTittle = "";
        String graphicLabel = "";
        List<Object[]> datos = new ArrayList<>();
        horizontalBarModel = new HorizontalBarChartModel();
        ChartSeries reporte = new ChartSeries();
        switch (tipoReporte) {
            case 1:
                yAxisLabel = "Categorias";
                xAxisLabel = "Cantidad de componentes";
                reportTittle = "Cantidad de componentes por categoria";
                graphicLabel = "Categorias";
                datos=this.colossal.getReporte(1);
                break;
            case 2:
                yAxisLabel = "Tecnologias";
                xAxisLabel = "Cantidad de componentes";
                reportTittle = "Cantidad de componentes por tecnologias";
                graphicLabel = "Tecnologias";
                datos=this.colossal.getReporte(2);
                break;
            case 3:
                yAxisLabel = "Componentes";
                xAxisLabel = "Cantidad de visualizaciones";
                reportTittle = "Componentes mas visualizados";
                graphicLabel = "Visualizacion de componentes";
                datos=this.colossal.getReporte(3);
                break;
            default:
                break;
        }
        reporte.setLabel(graphicLabel);
        for(Object[] result:datos){
            reporte.set(result[0].toString(), Integer.parseInt(result[1].toString()));
        }
        horizontalBarModel.addSeries(reporte);
        horizontalBarModel.setTitle(reportTittle);
        horizontalBarModel.setStacked(true);
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel(xAxisLabel);
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel(yAxisLabel);
    }
    
    /**
     * Metodo que crea un grafico de torta con su respectivo reporte.
     */
    private void createPieModel() {
        pieModel = new PieChartModel();
        List<Object[]> datos = this.colossal.getReporte(4);
        for(Object [] result:datos){
            pieModel.set(result[0].toString(), Integer.parseInt(result[1].toString()));
        }
        pieModel.setTitle("Cantidad de componentes por estado");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
//        pieModel.setDiameter(150);
    }

}


