/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.security;

import co.edu.ufps.colossal.entities.Usuario;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Servlet Filters are an implementation of the Chain of responsibility design
 * pattern. All filters are chained (in the order of their definition in
 * web.xml). The chain.doFilter() is proceeding to the next element in the
 * chain. The last element of the chain is the target resource/servlet.
 *
 * @author Jhon Vargas
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    /**
     * Este método lo llama el contenedor una vez cuando el filtro de servlet entra en funcionamiento. 
     * El elemento FilterConfig que se pasa a este método contiene los init-parameters del filtro de
     * servlet. Los init-parameters se pueden especificar para un filtro de servlets durante la 
     * configuración mediante la herramienta de ensamblaje.
     * @param filterConfig ontiene los init-parameters del filtro de servlet.
     * @throws ServletException 
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * El contenedor llama a este método para cada solicitud de servlet correlacionada 
     * con este filtro antes de invocar el servlet. La cadena FilterChain que se pasa a este método
     * puede utilizarse para invocar el filtro siguiente en la cadena de filtros. 
     * El servlet original solicitado se ejecuta cuando el último filtro de la cadena 
     * llama al método chain.doFilter. Por lo tanto, todos los filtros deben llamar al método
     * chain.doFilter para poder ejecutar el servlet original después del filtrado.
     * Si se implementa una comprobación de autenticación adicional en el código de filtro y 
     * se produce una anomalía, no es necesario ejecutar el servlet original.
     * No se llama al método chain.doFilter y éste se puede redireccionar a 
     * alguna página de error diferente.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (!req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            res.setDateHeader("Expires", 0); // Proxies.
        }
        // obtenemos el usuario que está en la sesion
        Usuario u = (Usuario) req.getSession().getAttribute("user");

        // si es != null es porque efectivamente hay un user en sesion
        if (u != null) {
//             System.out.println(req.getRequestURL());
//                System.out.println("Context: "+req.getContextPath());
            if (req.getRequestURL().toString().contains("/login.xhtml")) {

                res.sendRedirect(req.getContextPath() + "/faces/views/componentView/componente.xhtml");
            } else if (req.getRequestURL().toString().endsWith("Colossal/")) {

                res.sendRedirect(req.getContextPath() + "/faces/views/componentView/componente.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } // si no, redireccionarlo a la vista de login
        else {
            if (req.getRequestURL().toString().contains("views")) {
                res.sendRedirect(req.getContextPath());
            } else {
                chain.doFilter(request, response);
            }
        }

    }

    /**
     * Este método lo llama el contenedor cuando el filtro de servlets deja de funcionar.
     */
    @Override
    public void destroy() {

    }

}
