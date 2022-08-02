package Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kauebarbosa
 */
public class RegexUtils {

    private static RegexUtils instancia;

    public static Date getStrDate(String strDateText) throws java.text.ParseException {

        String strRegex = "[0123-9]{2}\\/[0123-9]{2}\\/[200-9,200-9-9]{4}";

        Pattern pattern = Pattern.compile(strRegex);
        Matcher matcher = pattern.matcher(strDateText);

        String strText = "";
        while (matcher.find()) {
            strText = matcher.group();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(strText);

    }

    

    public static String getStrDocumentoIdentidade(String strTextoPesquisa) {

        String strValorEncontrado = "";

        //CPF
        Pattern pattern = Pattern.compile("(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})");
        Matcher matcher = pattern.matcher(strTextoPesquisa);

        if (matcher.find()) {

            strValorEncontrado = matcher.group();

        } else {

            //CNPJ
            pattern = Pattern.compile("(\\d{2})\\.(\\d{3})\\.(\\d{3})\\/(\\d{4})\\-(\\d{2})");
            matcher = pattern.matcher(strTextoPesquisa);

            strValorEncontrado = matcher.group();

        }

        return strValorEncontrado;
    }

    public static double getNumeroReal(String strTextoPesquisa) {

        strTextoPesquisa = strTextoPesquisa.replace(".", "");

        Pattern pattern = Pattern.compile("(\\d*),(\\d*)");
        Matcher matcher = pattern.matcher(strTextoPesquisa);

        String strValorEncontrado = "";
        while (matcher.find()) {
            strValorEncontrado += matcher.group();
        }

        strValorEncontrado = strValorEncontrado.replace(",", ".");

        return Double.parseDouble(strValorEncontrado);
    }

    public static boolean isEmail(String strTextoAnalise) {

        Pattern pattern = Pattern.compile("(\\w*)\\@(\\w*)\\.(\\w*)");
        Matcher matcher = pattern.matcher(strTextoAnalise);

        return matcher.find();

    }

}
