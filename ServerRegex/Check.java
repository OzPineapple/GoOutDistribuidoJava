import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Clase para validar campos web.
 * <p>El modo de uso es simple.
 * <ol>
 *   <li>Instanciar al objeto
 *
 *      <pre>Checador validar = new Checador();</pre>
 *
 *  <li>Llamar a un metodo para determinar si la cadema es correcta o no y usa el mensaje de error
 *      <pre>if(validar.usuario(//Valiable a validar){
 *             //Codigo de validacion erronea
 *             validar.getMensaje();
 *             //Codigo de mensaja de error (opcional)
 *      }</pre>
 * </ol>
 * <p>La clase usa REGEX para validar.
 *
 * <p>Cada validacion almacena un valor dentro
 * de {@link Checador#mensaje Mensaje} que puede se consultado en
 * {@link Checador#getMensaje() getMensaje()}
 *
 * @see <a href="https://es.wikipedia.org/wiki/Expresi%C3%B3n_regular"> REGEX</a>
 * @author Zush18
 * @version 2.5
 */

public class Check {

    //<editor-fold desc="Atrubutos">
    /**
     * Variable para almacenar las mensajes de error o validacion erronea
     */
    private String mensaje;
    /**
     * Objeto para copilar REGEX
     */
    private Pattern p;
    /**
     * Obejto para comparar regexs
     */
    private Matcher m;
    //</editor-fold>

    //<editor-fold desc="REGEX">
    //Apoyos
    /**
     * Variable para vocales en regex
     * ÁÉÚÍÓáéúíóÄËÏÜÖäëüïö
     */
    private String vocalesEspañol = "ÁÉÚÍÓáéúíóÄËÏÜÖäëüïö";
    //MATCH
    /**
     * <p>Regx para determinar si es un numero entero
     *
     * [\\d]+
     */
    private String numInt = "[\\d]+";
    /**
     * <p>Regx para determinar si es un numero decimal
     *
     * [0-9]+([\\.][0-9]+)?
     */
    private String numFloat = "[0-9]+([\\.][0-9]+)?";
    /**
     * <p>Regx para determinar si es alfanumerico
     *
     * [\\w{@link Checador#vocalesEspañol Vocales latinas}\s]+
     */
    private String alfanumerico = "[\\w"+this.vocalesEspañol+"\\s]+";
    /**
     * <p>Regx para determinar si es alfabetico
     *
     * [A-Za-z{@link Checador#vocalesEspañol} Vocales latinas}\s]+
     */
    private String alfabeto = "[A-Za-z"+this.vocalesEspañol+"\\s]+";
    /**
     * <p>Regx para determinar si es un correo electronico valido
     *
     * ({@link Checador#alfanumerico Alfanumerico}\\.?)+\\@({@link Checador#alfanumerico Alfanumerico}\\.?)+\\.{@link Checador#alfanumerico Alfanumerico}
     */
    private String correo = "("+alfanumerico+"\\.?)+\\@("+alfanumerico+"\\.?)+\\."+alfanumerico+"";
    //FIND
    /**
     * <p>Regex para encontrar que la caden no este vacia
     *
     * [^\\t\\r\\n\\f\\v\\s]
     */
    private String vacio = "[^\\t\\r\\n\\f\\v\\s]";
    /**
     * <p>Regex´para encontrar que la cadena tenga comillas
     *
     * [\\\"']
     */
    private String comillas = "[\\\"']";
    //</editor-fold>

    //<editor-fold desc="Metodos publicos">
    /**
     * <p>Valida que un nombre de usuario sea correcto en todos los sentidos.
     *
     * <p>Si se quiere obtener el error usar {@link Checador#getMensaje() getMensaje()}
     *
     * Ver los metodos privados para saber como se valida
     * @param usuario campo a validar
     * @return booleano
     */
    public boolean usuario(String usuario){
        if(this.estaVacio(usuario)) return true;
        if(this.estaMalLongitud(usuario, 2, 15))return true;
        if(this.noEsAlfabetico(usuario))return true;
        this.mensaje = "valido";
        return false;
    }

    /**
     * <p>Valida que una contraseña sea correcto en todos los sentidos.
     *
     * <p>Si se quiere obtener el error usar {@link Checador#getMensaje() getMensaje()}
     *
     * Ver los metodos privados para saber como se valida
     * @param contraseña campo a validar
     * @return booleano
     */
    public boolean contraseña(String contraseña){
        if(this.estaVacio(contraseña)) return true;
        if(this.estaMalLongitud(contraseña, 8, 30)) return true;
        if(this.noEsAlfanumerico(contraseña)) return true;
        this.mensaje = "valido";
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="Metodos privados">

    /**
     * <p>Valida si no es una cadena alfanumerica
     *
     * {@link Checador#alfanumerico REGEX}
     * @param var Cadena a validar
     * @return boleano
     */
    private boolean noEsAlfanumerico(String var){
        if(!this.RegexMatch(alfanumerico, var)){
            this.mensaje = "no es alfanumerico";
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que no sea un correo electronico
     *
     * {@link Checador#correo REGEX}
     * @param var Cadena a validar
     * @return booleano
     * @see Checador#RegexMatch(String regex, String var)
     */
    private boolean noEsUnCorreo(String var){
        if(!this.RegexMatch(correo, var)){
            this.mensaje = "no es un correo";
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que la cadena tenga comillas
     *
     * {@link Checador#comillas REGEX}
     * @param var
     * @return booleano
     * @see Checador#RegexFind(java.lang.String, java.lang.String)
     */
    private boolean tieneComillas(String var){
        if(this.RegexFind(comillas, var)){
            this.mensaje = "no pude contener comillas simples o dobles";
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que la cadena este vacia
     *
     * {@link Checador#vacio REGEX}
     * @param var
     * @return booleano
     * @see Checador#RegexFind(java.lang.String, java.lang.String)
     */
    private boolean estaVacio(String var){
        if( var == null){
            this.mensaje = "esta vacio";
            return true;
        }else if(!this.RegexFind(vacio, var)){
            this.mensaje = "esta vacio";
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que la longitud de una cadena no sea erronea
     *
     * @param var
     * @param min
     * @param max
     * @return booleano
     * @see Checador#RegexMatch(String regex, String var)
     */
    private boolean estaMalLongitud(String var,int min,int max){
        if (var.length()<min) {
            this.mensaje="no puede ser de longitud menor a "+min;
            return true;
        }
        if (var.length()>max) {
            this.mensaje="no puede ser de longitud mayor a "+max;
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que no sea un numero entero
     *
     * {@link Checador#numInt REGEX}
     * @param var
     * @return booleano
     * @see Checador#RegexMatch(java.lang.String, java.lang.String)
     */
    private boolean noEsUnNumeroEntero(String var){
        if(!this.RegexMatch(this.numInt, var)){
            this.mensaje = "no es un número entero";
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que no sea un numero decimal
     *
     * {@link Checador#numFloat REGEX}
     * @param var
     * @return booleano
     * @see Checador#RegexMatch(java.lang.String, java.lang.String)
     */
    private boolean noEsUnNumeroDecimal(String var){
        if(!this.RegexMatch(this.numFloat, var)){
            this.mensaje = "no es un número decimal";
            return true;
        }
        return false;
    }

    /**
     * <p>Valida que no sea alfabetico
     *
     * {@link Checador#alfabeto REGEX}
     * @param var
     * @return booleano
     * @see Checador#RegexMatch(java.lang.String, java.lang.String)
     */
    private boolean noEsAlfabetico(String var){
        if(!this.RegexMatch(this.alfabeto, var)){
            this.mensaje = "no contiene solamente caracteres alfabeticos";
            return true;
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold desc="REGEXmethod">
    /**
     * Valida a travez de {@link java.util.regex.Pattern Pattern} y {@link java.util.regex.Matcher Matcher}
     * que la cadena que se le de sea correcta de principio a fin
     * @param regex Regex o regla de validacion
     * @param var Cadena a validar
     * @return booleano
     */
    private boolean RegexMatch(String regex, String var){
        p = Pattern.compile(regex);
        m = p.matcher(var);
        return m.matches();
    }

    /**
     * Valida a travez de {@link java.util.regex.Pattern Pattern} y {@link java.util.regex.Matcher Matcher}
     * que la cadena contenga lo que se pide de principio a fin
     * @param regex Regex o regla de validacion
     * @param var Cadena a validar
     * @return booleano
     */
    private boolean RegexFind(String regex, String var){
        p = Pattern.compile(regex);
        m = p.matcher(var);
        return m.find();
    }
    //</editor-fold>

    //<editor-fold desc="Get & Set">
    /**
     * Devualve el mensaje de error que almacena cada validacion
     * @return Mensaje de error de validacion
     */
    public String getMensaje() {
        return mensaje;
    }
    //</editor-fold>
}
