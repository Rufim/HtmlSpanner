package net.nightwhistler.htmlspanner;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    private static Pattern SPECIAL_CHAR_WHITESPACE = Pattern
            .compile("(&[a-z]*;|&#x?([a-f]|[A-F]|[0-9])*;|[\\s\n]+)");

    private static Pattern SPECIAL_CHAR_NO_WHITESPACE = Pattern
            .compile("(&[a-z]*;|&#x?([a-f]|[A-F]|[0-9])*;)");

    private static Map<String, String> REPLACEMENTS = new HashMap<String, String>();

    static {
        REPLACEMENTS.put("&Aacute;", "\u00C1");
        REPLACEMENTS.put("&aacute;", "\u00E1");
        REPLACEMENTS.put("&Acirc;", "\u00C2");
        REPLACEMENTS.put("&acirc;", "\u00E2");
        REPLACEMENTS.put("&acute;", "\u00B4");
        REPLACEMENTS.put("&AElig;", "\u00C6");
        REPLACEMENTS.put("&aelig;", "\u00E6");
        REPLACEMENTS.put("&Agrave;", "\u00C0");
        REPLACEMENTS.put("&agrave;", "\u00E0");
        REPLACEMENTS.put("&alefsym;", "\u2135");
        REPLACEMENTS.put("&Alpha;", "\u0391");
        REPLACEMENTS.put("&alpha;", "\u03B1");
        REPLACEMENTS.put("&amp;", "\u0026");
        REPLACEMENTS.put("&and;", "\u2227");
        REPLACEMENTS.put("&ang;", "\u2220");
        REPLACEMENTS.put("&Aring;", "\u00C5");
        REPLACEMENTS.put("&aring;", "\u00E5");
        REPLACEMENTS.put("&asymp;", "\u2248");
        REPLACEMENTS.put("&Atilde;", "\u00C3");
        REPLACEMENTS.put("&atilde;", "\u00E3");
        REPLACEMENTS.put("&Auml;", "\u00C4");
        REPLACEMENTS.put("&auml;", "\u00E4");
        REPLACEMENTS.put("&bdquo;", "\u201E");
        REPLACEMENTS.put("&Beta;", "\u0392");
        REPLACEMENTS.put("&beta;", "\u03B2");
        REPLACEMENTS.put("&brvbar;", "\u00A6");
        REPLACEMENTS.put("&bull;", "\u2022");
        REPLACEMENTS.put("&cap;", "\u2229");
        REPLACEMENTS.put("&Ccedil;", "\u00C7");
        REPLACEMENTS.put("&ccedil;", "\u00E7");
        REPLACEMENTS.put("&cedil;", "\u00B8");
        REPLACEMENTS.put("&cent;", "\u00A2");
        REPLACEMENTS.put("&Chi;", "\u03A7");
        REPLACEMENTS.put("&chi;", "\u03C7");
        REPLACEMENTS.put("&circ;", "\u02C6");
        REPLACEMENTS.put("&clubs;", "\u2663");
        REPLACEMENTS.put("&cong;", "\u2245");
        REPLACEMENTS.put("&copy;", "\u00A9");
        REPLACEMENTS.put("&crarr;", "\u21B5");
        REPLACEMENTS.put("&cup;", "\u222A");
        REPLACEMENTS.put("&curren;", "\u00A4");
        REPLACEMENTS.put("&Dagger;", "\u2021");
        REPLACEMENTS.put("&dagger;", "\u2020");
        REPLACEMENTS.put("&dArr;", "\u21D3");
        REPLACEMENTS.put("&darr;", "\u2193");
        REPLACEMENTS.put("&deg;", "\u00B0");
        REPLACEMENTS.put("&Delta;", "\u0394");
        REPLACEMENTS.put("&delta;", "\u03B4");
        REPLACEMENTS.put("&diams;", "\u2666");
        REPLACEMENTS.put("&divide;", "\u00F7");
        REPLACEMENTS.put("&Eacute;", "\u00C9");
        REPLACEMENTS.put("&eacute;", "\u00E9");
        REPLACEMENTS.put("&Ecirc;", "\u00CA");
        REPLACEMENTS.put("&ecirc;", "\u00EA");
        REPLACEMENTS.put("&Egrave;", "\u00C8");
        REPLACEMENTS.put("&egrave;", "\u00E8");
        REPLACEMENTS.put("&empty;", "\u2205");
        REPLACEMENTS.put("&emsp;", "\u2003");
        REPLACEMENTS.put("&ensp;", "\u2002");
        REPLACEMENTS.put("&Epsilon;", "\u0395");
        REPLACEMENTS.put("&epsilon;", "\u03B5");
        REPLACEMENTS.put("&equiv;", "\u2261");
        REPLACEMENTS.put("&Eta;", "\u0397");
        REPLACEMENTS.put("&eta;", "\u03B7");
        REPLACEMENTS.put("&ETH;", "\u00D0");
        REPLACEMENTS.put("&eth;", "\u00F0");
        REPLACEMENTS.put("&Euml;", "\u00CB");
        REPLACEMENTS.put("&euml;", "\u00EB");
        REPLACEMENTS.put("&euro;", "\u20AC");
        REPLACEMENTS.put("&exist;", "\u2203");
        REPLACEMENTS.put("&fnof;", "\u0192");
        REPLACEMENTS.put("&forall;", "\u2200");
        REPLACEMENTS.put("&frac12;", "\u00BD");
        REPLACEMENTS.put("&frac14;", "\u00BC");
        REPLACEMENTS.put("&frac34;", "\u00BE");
        REPLACEMENTS.put("&frasl;", "\u2044");
        REPLACEMENTS.put("&Gamma;", "\u0393");
        REPLACEMENTS.put("&gamma;", "\u03B3");
        REPLACEMENTS.put("&ge;", "\u2265");
        REPLACEMENTS.put("&gt;", "\u003E");
        REPLACEMENTS.put("&hArr;", "\u21D4");
        REPLACEMENTS.put("&harr;", "\u2194");
        REPLACEMENTS.put("&hearts;", "\u2665");
        REPLACEMENTS.put("&hellip;", "\u2026");
        REPLACEMENTS.put("&Iacute;", "\u00CD");
        REPLACEMENTS.put("&iacute;", "\u00ED");
        REPLACEMENTS.put("&Icirc;", "\u00CE");
        REPLACEMENTS.put("&icirc;", "\u00EE");
        REPLACEMENTS.put("&iexcl;", "\u00A1");
        REPLACEMENTS.put("&Igrave;", "\u00CC");
        REPLACEMENTS.put("&igrave;", "\u00EC");
        REPLACEMENTS.put("&image;", "\u2111");
        REPLACEMENTS.put("&infin;", "\u221E");
        REPLACEMENTS.put("&int;", "\u222B");
        REPLACEMENTS.put("&Iota;", "\u0399");
        REPLACEMENTS.put("&iota;", "\u03B9");
        REPLACEMENTS.put("&iquest;", "\u00BF");
        REPLACEMENTS.put("&isin;", "\u2208");
        REPLACEMENTS.put("&Iuml;", "\u00CF");
        REPLACEMENTS.put("&iuml;", "\u00EF");
        REPLACEMENTS.put("&Kappa;", "\u039A");
        REPLACEMENTS.put("&kappa;", "\u03BA");
        REPLACEMENTS.put("&Lambda;", "\u039B");
        REPLACEMENTS.put("&lambda;", "\u03BB");
        REPLACEMENTS.put("&lang;", "\u2329");
        REPLACEMENTS.put("&laquo;", "\u00AB");
        REPLACEMENTS.put("&lArr;", "\u21D0");
        REPLACEMENTS.put("&larr;", "\u2190");
        REPLACEMENTS.put("&lceil;", "\u2308");
        REPLACEMENTS.put("&ldquo;", "\u201C");
        REPLACEMENTS.put("&le;", "\u2264");
        REPLACEMENTS.put("&lfloor;", "\u230A");
        REPLACEMENTS.put("&lowast;", "\u2217");
        REPLACEMENTS.put("&loz;", "\u25CA");
        REPLACEMENTS.put("&lrm;", "\u200E");
        REPLACEMENTS.put("&lsaquo;", "\u2039");
        REPLACEMENTS.put("&lsquo;", "\u2018");
        REPLACEMENTS.put("&lt;", "\u003C");
        REPLACEMENTS.put("&macr;", "\u00AF");
        REPLACEMENTS.put("&mdash;", "\u2014");
        REPLACEMENTS.put("&micro;", "\u00B5");
        REPLACEMENTS.put("&middot;", "\u00B7");
        REPLACEMENTS.put("&minus;", "\u2212");
        REPLACEMENTS.put("&Mu;", "\u039C");
        REPLACEMENTS.put("&mu;", "\u03BC");
        REPLACEMENTS.put("&nabla;", "\u2207");
        REPLACEMENTS.put("&nbsp;", "\u00A0");
        REPLACEMENTS.put("&ndash;", "\u2013");
        REPLACEMENTS.put("&ne;", "\u2260");
        REPLACEMENTS.put("&ni;", "\u220B");
        REPLACEMENTS.put("&not;", "\u00AC");
        REPLACEMENTS.put("&notin;", "\u2209");
        REPLACEMENTS.put("&nsub;", "\u2284");
        REPLACEMENTS.put("&Ntilde;", "\u00D1");
        REPLACEMENTS.put("&ntilde;", "\u00F1");
        REPLACEMENTS.put("&Nu;", "\u039D");
        REPLACEMENTS.put("&nu;", "\u03BD");
        REPLACEMENTS.put("&Oacute;", "\u00D3");
        REPLACEMENTS.put("&oacute;", "\u00F3");
        REPLACEMENTS.put("&Ocirc;", "\u00D4");
        REPLACEMENTS.put("&ocirc;", "\u00F4");
        REPLACEMENTS.put("&OElig;", "\u0152");
        REPLACEMENTS.put("&oelig;", "\u0153");
        REPLACEMENTS.put("&Ograve;", "\u00D2");
        REPLACEMENTS.put("&ograve;", "\u00F2");
        REPLACEMENTS.put("&oline;", "\u203E");
        REPLACEMENTS.put("&Omega;", "\u03A9");
        REPLACEMENTS.put("&omega;", "\u03C9");
        REPLACEMENTS.put("&Omicron;", "\u039F");
        REPLACEMENTS.put("&omicron;", "\u03BF");
        REPLACEMENTS.put("&oplus;", "\u2295");
        REPLACEMENTS.put("&or;", "\u2228");
        REPLACEMENTS.put("&ordf;", "\u00AA");
        REPLACEMENTS.put("&ordm;", "\u00BA");
        REPLACEMENTS.put("&Oslash;", "\u00D8");
        REPLACEMENTS.put("&oslash;", "\u00F8");
        REPLACEMENTS.put("&Otilde;", "\u00D5");
        REPLACEMENTS.put("&otilde;", "\u00F5");
        REPLACEMENTS.put("&otimes;", "\u2297");
        REPLACEMENTS.put("&Ouml;", "\u00D6");
        REPLACEMENTS.put("&ouml;", "\u00F6");
        REPLACEMENTS.put("&para;", "\u00B6");
        REPLACEMENTS.put("&part;", "\u2202");
        REPLACEMENTS.put("&permil;", "\u2030");
        REPLACEMENTS.put("&perp;", "\u22A5");
        REPLACEMENTS.put("&Phi;", "\u03A6");
        REPLACEMENTS.put("&phi;", "\u03C6");
        REPLACEMENTS.put("&Pi;", "\u03A0");
        REPLACEMENTS.put("&pi;", "\u03C0");
        REPLACEMENTS.put("&piv;", "\u03D6");
        REPLACEMENTS.put("&plusmn;", "\u00B1");
        REPLACEMENTS.put("&pound;", "\u00A3");
        REPLACEMENTS.put("&Prime;", "\u2033");
        REPLACEMENTS.put("&prime;", "\u2032");
        REPLACEMENTS.put("&prod;", "\u220F");
        REPLACEMENTS.put("&prop;", "\u221D");
        REPLACEMENTS.put("&Psi;", "\u03A8");
        REPLACEMENTS.put("&psi;", "\u03C8");
        REPLACEMENTS.put("&quot;", String.valueOf('\u0022'));
        REPLACEMENTS.put("&radic;", "\u221A");
        REPLACEMENTS.put("&rang;", "\u232A");
        REPLACEMENTS.put("&raquo;", "\u00BB");
        REPLACEMENTS.put("&rArr;", "\u21D2");
        REPLACEMENTS.put("&rarr;", "\u2192");
        REPLACEMENTS.put("&rceil;", "\u2309");
        REPLACEMENTS.put("&rdquo;", "\u201D");
        REPLACEMENTS.put("&real;", "\u211C");
        REPLACEMENTS.put("&reg;", "\u00AE");
        REPLACEMENTS.put("&rfloor;", "\u230B");
        REPLACEMENTS.put("&Rho;", "\u03A1");
        REPLACEMENTS.put("&rho;", "\u03C1");
        REPLACEMENTS.put("&rlm;", "\u200F");
        REPLACEMENTS.put("&rsaquo;", "\u203A");
        REPLACEMENTS.put("&rsquo;", "\u2019");
        REPLACEMENTS.put("&sbquo;", "\u201A");
        REPLACEMENTS.put("&Scaron;", "\u0160");
        REPLACEMENTS.put("&scaron;", "\u0161");
        REPLACEMENTS.put("&sdot;", "\u22C5");
        REPLACEMENTS.put("&sect;", "\u00A7");
        REPLACEMENTS.put("&shy;", "\u00AD");
        REPLACEMENTS.put("&Sigma;", "\u03A3");
        REPLACEMENTS.put("&sigma;", "\u03C3");
        REPLACEMENTS.put("&sigmaf;", "\u03C2");
        REPLACEMENTS.put("&sim;", "\u223C");
        REPLACEMENTS.put("&spades;", "\u2660");
        REPLACEMENTS.put("&sub;", "\u2282");
        REPLACEMENTS.put("&sube;", "\u2286");
        REPLACEMENTS.put("&sum;", "\u2211");
        REPLACEMENTS.put("&sup;", "\u2283");
        REPLACEMENTS.put("&sup1;", "\u00B9");
        REPLACEMENTS.put("&sup2;", "\u00B2");
        REPLACEMENTS.put("&sup3;", "\u00B3");
        REPLACEMENTS.put("&supe;", "\u2287");
        REPLACEMENTS.put("&szlig;", "\u00DF");
        REPLACEMENTS.put("&Tau;", "\u03A4");
        REPLACEMENTS.put("&tau;", "\u03C4");
        REPLACEMENTS.put("&there4;", "\u2234");
        REPLACEMENTS.put("&Theta;", "\u0398");
        REPLACEMENTS.put("&theta;", "\u03B8");
        REPLACEMENTS.put("&thetasym;", "\u03D1");
        REPLACEMENTS.put("&thinsp;", "\u2009");
        REPLACEMENTS.put("&THORN;", "\u00DE");
        REPLACEMENTS.put("&thorn;", "\u00FE");
        REPLACEMENTS.put("&tilde;", "\u02DC");
        REPLACEMENTS.put("&times;", "\u00D7");
        REPLACEMENTS.put("&trade;", "\u2122");
        REPLACEMENTS.put("&Uacute;", "\u00DA");
        REPLACEMENTS.put("&uacute;", "\u00FA");
        REPLACEMENTS.put("&uArr;", "\u21D1");
        REPLACEMENTS.put("&uarr;", "\u2191");
        REPLACEMENTS.put("&Ucirc;", "\u00DB");
        REPLACEMENTS.put("&ucirc;", "\u00FB");
        REPLACEMENTS.put("&Ugrave;", "\u00D9");
        REPLACEMENTS.put("&ugrave;", "\u00F9");
        REPLACEMENTS.put("&uml;", "\u00A8");
        REPLACEMENTS.put("&upsih;", "\u03D2");
        REPLACEMENTS.put("&Upsilon;", "\u03A5");
        REPLACEMENTS.put("&upsilon;", "\u03C5");
        REPLACEMENTS.put("&Uuml;", "\u00DC");
        REPLACEMENTS.put("&uuml;", "\u00FC");
        REPLACEMENTS.put("&weierp;", "\u2118");
        REPLACEMENTS.put("&Xi;", "\u039E");
        REPLACEMENTS.put("&xi;", "\u03BE");
        REPLACEMENTS.put("&Yacute;", "\u00DD");
        REPLACEMENTS.put("&yacute;", "\u00FD");
        REPLACEMENTS.put("&yen;", "\u00A5");
        REPLACEMENTS.put("&Yuml;", "\u0178");
        REPLACEMENTS.put("&yuml;", "\u00FF");
        REPLACEMENTS.put("&Zeta;", "\u0396");
        REPLACEMENTS.put("&zeta;", "\u03B6");
        REPLACEMENTS.put("&zwj;", "\u200D");
        REPLACEMENTS.put("&zwnj;", "\u200C");
    }

    /**
     * Replaces all HTML entities ( &lt;, &amp; ), with their Unicode
     * characters.
     *
     * @param aText text to replace entities in
     * @return the text with entities replaced.
     */
    public static String replaceHtmlEntities(String aText,
                                             boolean preserveFormatting) {
        StringBuffer result = new StringBuffer();

        Map<String, String> replacements = new HashMap<String, String>(
                REPLACEMENTS);
        Matcher matcher;

        if (preserveFormatting) {
            matcher = SPECIAL_CHAR_NO_WHITESPACE.matcher(aText);
        } else {
            matcher = SPECIAL_CHAR_WHITESPACE.matcher(aText);
            replacements.put("", " ");
            replacements.put("\n", " ");
        }

        while (matcher.find()) {
            try {
                matcher.appendReplacement(result,
                        getReplacement(matcher, replacements));
            } catch (ArrayIndexOutOfBoundsException i) {
                //Ignore, seems to be a matcher bug
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static String getReplacement(Matcher aMatcher,
                                         Map<String, String> replacements) {

        String match = aMatcher.group(0).trim();
        String result = replacements.get(match);

        if (result != null) {
            return result;
        } else if (match.startsWith("&#")) {

            Integer code;

            // Translate to unicode character.
            try {

                //Check if it's hex or normal
                if (match.startsWith("&#x")) {
                    code = Integer.decode("0x" + match.substring(3, match.length() - 1));
                } else {
                    code = Integer.parseInt(match.substring(2,
                            match.length() - 1));
                }

                return "" + (char) code.intValue();
            } catch (NumberFormatException nfe) {
                return "";
            }
        } else {
            return "";
        }
    }

}
