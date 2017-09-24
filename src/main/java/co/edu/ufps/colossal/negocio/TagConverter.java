
package co.edu.ufps.colossal.negocio;

import co.edu.ufps.colossal.entities.Tag;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * Converter implementations must have a zero-arguments public constructor. In
 * addition, if the Converter class wishes to have configuration property values
 * saved and restored with the component tree, the implementation must also
 * implement StateHolder.
 * http://docs.oracle.com/javaee/5/api/javax/faces/convert/Converter.html
 *
 * @author Jhon Vargas
 */
@FacesConverter("tagConverter")
public class TagConverter implements Converter {

    /**
     * Convert the specified string value, which is associated with the
     * specified UIComponent, into a model data object that is appropriate for
     * being stored during the Apply Request Values phase of the request
     * processing lifecycle.
     *
     * @param context FacesContext for the request being processed
     * @param component UIComponent with which this model object value is
     * associated
     * @param value String value to be converted (may be null)
     * @return null if the value to convert is null, otherwise the result of the
     * conversion
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Tag t = new Tag();
        t.setNombre(value);
        return t;

    }

    /**
     * Convert the specified model object value, which is associated with the
     * specified UIComponent, into a String that is suitable for being included
     * in the response generated during the Render Response phase of the request
     * processing lifeycle.
     *
     * @param context FacesContext for the request being processed
     * @param component UIComponent with which this model object value is
     * associated
     * @param value Model object value to be converted (may be null)
     * @return a zero-length String if value is null, otherwise the result of
     * the conversion
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return (((Tag) value).getNombre());
        } else {
            return null;
        }

    }

}
